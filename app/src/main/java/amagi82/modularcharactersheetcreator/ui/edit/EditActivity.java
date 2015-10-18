package amagi82.modularcharactersheetcreator.ui.edit;

import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.squareup.otto.Subscribe;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.ActivityEditBinding;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.ui._base.BaseActivity;
import amagi82.modularcharactersheetcreator.ui.edit._events.AxisSelectedEvent;
import amagi82.modularcharactersheetcreator.ui.edit._events.AxisUpdateEvent;
import amagi82.modularcharactersheetcreator.ui.edit._events.GameSelectedEvent;
import amagi82.modularcharactersheetcreator.ui.edit._events.KeyboardVisibleEvent;
import amagi82.modularcharactersheetcreator.ui.edit._events.NameChangedEvent;
import amagi82.modularcharactersheetcreator.ui.edit._events.ResetSelectionEvent;
import icepick.State;

import static amagi82.modularcharactersheetcreator.ui.main.MainActivity.CHARACTER;

public class EditActivity extends BaseActivity {
    private ActivityEditBinding binding;
    private EditViewModel editViewModel;
    @State GameCharacter character;
    @State int backstack;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit);

        setSupportActionBar(binding.toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable icon = ContextCompat.getDrawable(this, R.drawable.ic_close_24dp);
        icon.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(icon);

        if (character == null) {
            if (getIntent().getParcelableExtra(CHARACTER) != null) character = getIntent().getParcelableExtra(CHARACTER);
            else character = GameCharacter.builder().build();
        }

        editViewModel = new EditViewModel(character);
        binding.setEditViewModel(editViewModel);
        //If an update to a page > 0 is handled immediately, the adapter doesn't get set up.
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                editViewModel.update(character);
            }
        }, 10);
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

    @Subscribe public void gameSelected(GameSelectedEvent event) {
        character = character.toBuilder().gameTitle(event.gameTitle).build();
        update();
    }

    @Subscribe public void axisUpdated(AxisUpdateEvent event) {
        editViewModel.update(character, event.splat);
        binding.appbar.setExpanded(true);
    }

    @Subscribe public void axisSelected(AxisSelectedEvent event) {
        if (editViewModel.page.get() == GameCharacter.LEFT) character = character.toBuilder().left(event.splat).build();
        else character = character.toBuilder().right(event.splat).build();
        update();
    }

    @Subscribe public void nameChanged(NameChangedEvent event) {
        character = character.toBuilder().name(event.name).build();
        editViewModel.update(character);
    }

    @Subscribe public void keyboardVisible(KeyboardVisibleEvent event) {
        editViewModel.softKeyboardVisible();
    }

    @Subscribe public void resetItem(ResetSelectionEvent event) {
        goBack(event.toPage);
    }

    @Override public void onBackPressed() {
        if (backstack > 0) goBack(backstack - 1);
        else super.onBackPressed();
    }

    private void goBack(int toPage) {
        character = character.removeProgress(toPage);
        update();
    }

    private void update(){
        editViewModel.update(character);
        binding.appbar.setExpanded(true);
        backstack = character.getProgress();
    }

    public void onActionPhoto(View view) {

    }

    public void onFabClicked(View view) {

    }
}
