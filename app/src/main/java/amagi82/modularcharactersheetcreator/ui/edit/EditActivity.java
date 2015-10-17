package amagi82.modularcharactersheetcreator.ui.edit;

import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Subscribe;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.ActivityEditBinding;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.ui.base.BaseActivity;
import amagi82.modularcharactersheetcreator.ui.edit.axis.AxisSelectedEvent;
import amagi82.modularcharactersheetcreator.ui.edit.game.GameSelectedEvent;
import amagi82.modularcharactersheetcreator.ui.edit.name.KeyboardVisibleEvent;
import amagi82.modularcharactersheetcreator.ui.edit.name.NameChangedEvent;
import amagi82.modularcharactersheetcreator.ui.edit.name.ResetItemEvent;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.Otto;
import amagi82.modularcharactersheetcreator.ui.xtras.widgets.NoSwipeViewPager;
import icepick.State;

import static amagi82.modularcharactersheetcreator.ui.main.MainActivity.CHARACTER;

public class EditActivity extends BaseActivity {
    public static final String LEFT = "Left";
    private ActivityEditBinding binding;
    private NoSwipeViewPager viewPager;
    private boolean keyboardVisible = false;
    @State GameCharacter character;
    @State @GameCharacter.Progress int currentPage;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit);
        binding.setEditViewModel(new EditViewModel());

        setSupportActionBar(binding.toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable icon = ContextCompat.getDrawable(this, R.drawable.ic_close_24dp);
        icon.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(icon);

        if (character == null) {
            if (getIntent().getParcelableExtra(CHARACTER) != null) character = getIntent().getParcelableExtra(CHARACTER);
            else character = GameCharacter.builder().build();
        }
        if (character.getGameSystem() != null) binding.getEditViewModel().splashUrl.set(character.getGameSystem().getSplashUrl());

        viewPager = binding.viewpager;
        viewPager.setAdapter(new EditViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(character.getProgress());

    }

    public GameCharacter getGameCharacter() {
        return character;
    }

    @Subscribe public void resetItem(ResetItemEvent event){
        goBack(event.toPage);
    }

    @Subscribe public void gameSelected(GameSelectedEvent event) {
        character = character.toBuilder().gameTitle(event.gameTitle).build();
        binding.getEditViewModel().splashUrl.set(character.getGameSystem().getSplashUrl());
        nextPage();
    }

    @Subscribe public void axisSelected(AxisSelectedEvent event) {
        if (viewPager.getCurrentItem() == 1) character = character.toBuilder().left(event.splat).build();
        else character = character.toBuilder().right(event.splat).build();
        nextPage();
    }

    @Subscribe public void nameChanged(NameChangedEvent event){
        Log.i("EditActivity", "nameChanged to "+event.name);

        if(event.name.length() > 0) binding.fab.show();
        else binding.fab.hide();

        character = character.toBuilder().name(event.name).build();
    }

    @Subscribe public void keyboardVisible(KeyboardVisibleEvent event){
        binding.fab.hide();
    }

    private void nextPage() {
        Otto.BUS.getBus().post(new CharacterUpdatedEvent());
        binding.appbar.setExpanded(true);
        viewPager.nextPage();
        currentPage++;
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_discard:
                finish();
                return true;
        }
        return false;
    }

    @Override public void onBackPressed() {
        if (currentPage > 0) goBack(currentPage - 1);
        else super.onBackPressed();
    }

    private void goBack(int toPage){
        currentPage = toPage;
        character = character.removeProgress(currentPage);
        Otto.BUS.getBus().post(new CharacterUpdatedEvent());
        binding.appbar.setExpanded(true);
        viewPager.setCurrentItem(currentPage);

        if (currentPage == GameCharacter.START) binding.getEditViewModel().splashUrl.set(0);
    }
}
