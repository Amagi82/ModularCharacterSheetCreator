package amagi82.modularcharactersheetcreator.ui.edit;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Subscribe;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.ActivityEditBinding;
import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.entities.characters.Splat;
import amagi82.modularcharactersheetcreator.entities.games.Game;
import amagi82.modularcharactersheetcreator.ui.base.BaseActivity;
import amagi82.modularcharactersheetcreator.ui.xtras.widgets.NoSwipeViewPager;
import icepick.State;

import static amagi82.modularcharactersheetcreator.ui.main.MainActivity.CHARACTER;
import static amagi82.modularcharactersheetcreator.ui.xtras.utils.Otto.BUS;

public class EditActivity extends BaseActivity {
    public static final String LEFT = "Left";
    private ActivityEditBinding binding;
    private NoSwipeViewPager viewPager;
    @State GameCharacter character;
    @State int backstack;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit);
        binding.setEditViewModel(new EditViewModel());

        setSupportActionBar(binding.toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    @Subscribe public void itemClicked(ItemClickedEvent event) {
        int pos = event.position;
        Splat splat;
        switch (viewPager.getCurrentItem()) {
            case 0:
                character = character.toBuilder().gameTitle(new Game().get(pos).getGameTitle()).build();
                binding.getEditViewModel().splashUrl.set(character.getGameSystem().getSplashUrl());
                break;
            case 1:
                splat = getSplat(true, pos);
                if (!splat.isEndPoint()) return;
                character = character.toBuilder().left(splat).build();
                break;
            case 2:
                splat = getSplat(false, pos);
                if (!splat.isEndPoint()) return;
                character = character.toBuilder().right(splat).build();
                binding.appbar.setExpanded(true);
                break;
        }
        BUS.getBus().post(new CharacterUpdatedEvent());
        next();
    }

    private Splat getSplat(boolean left, int position){
        return ((EditViewPagerAdapter) viewPager.getAdapter()).getFragment(left).getSplat(position);
    }

    private void next() {
        viewPager.nextPage();
        backstack++;
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
        if (backstack > 0) {
            character = character.removeProgress(viewPager.getCurrentItem());
            viewPager.previousPage();
            backstack--;
            if(viewPager.getCurrentItem() == 0) binding.getEditViewModel().splashUrl.set(0);
        } else super.onBackPressed();
    }
}
