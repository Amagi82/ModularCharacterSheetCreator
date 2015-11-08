package amagi82.modularcharactersheetcreator.ui.create;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.CreateActivityBinding;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.games.templates.Template;
import amagi82.modularcharactersheetcreator.ui._base.BaseActivity;
import amagi82.modularcharactersheetcreator.ui.create._events.AxisSelectedEvent;
import amagi82.modularcharactersheetcreator.ui.create._events.AxisUpdateEvent;
import amagi82.modularcharactersheetcreator.ui.create._events.GameSelectedEvent;
import amagi82.modularcharactersheetcreator.ui.create._events.KeyboardVisibleEvent;
import amagi82.modularcharactersheetcreator.ui.create._events.NameChangedEvent;
import amagi82.modularcharactersheetcreator.ui.create._events.ResetSelectionEvent;
import amagi82.modularcharactersheetcreator.ui.crop.CropActivity;
import icepick.State;

/*
    Screen used to create or modify the core of a GameCharacter. Choose game gameId, character axis(commonly referred to as "splats", name, and photo.
    Character sheet settings are not handled here (see Sheet).

    Navigation:
    This screen can be reached from either MainActivity (for new characters) or from SheetActivity (for modifying existing characters).
    Exiting this screen takes you back to the MainActivity.
 */
public class CreateActivity extends BaseActivity {
    private CreateActivityBinding binding;
    private CreateViewModel createViewModel;
    @State GameCharacter character;
    @State int backstack;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.create_activity);

        binding.toolbar.setNavigationIcon(getTintedIcon(R.drawable.ic_close_24dp, Color.WHITE));
        binding.toolbar.inflateMenu(R.menu.menu_create);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                finish(RESULT_CANCELED);
            }
        });

        if (character == null) {
            if (getIntent().getParcelableExtra(CHARACTER) != null) character = getIntent().getParcelableExtra(CHARACTER);
            else character = GameCharacter.create();
        }

        createViewModel = new CreateViewModel(character);
        binding.setCreateViewModel(createViewModel);
        //If an update to a page > 0 is handled immediately, the adapter doesn't get set up.
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                createViewModel.update(character);
            }
        }, 10);
    }

    @Subscribe public void gameSelected(GameSelectedEvent event) {
        character = character.withGame(event.gameId);
        update();
    }

    @Subscribe public void axisUpdated(AxisUpdateEvent event) {
        createViewModel.update(character, event.splatId);
        binding.appbar.setExpanded(true);
    }

    @Subscribe public void axisSelected(AxisSelectedEvent event) {
        if (createViewModel.page.get() == GameCharacter.LEFT) character = character.withLeft(event.splat);
        else character = character.withRight(event.splat);
        update();
    }

    @Subscribe public void nameChanged(NameChangedEvent event) {
        character = character.withName(event.name);
        createViewModel.update(character);
    }

    @Subscribe public void keyboardVisible(KeyboardVisibleEvent event) {
        createViewModel.softKeyboardVisible();
    }

    @Subscribe public void resetItem(ResetSelectionEvent event) {
        goBack(event.toPage);
    }

    @Override public void onBackPressed() {
        if (backstack > 0) goBack(backstack - 1);
        else {
            setResult(RESULT_CANCELED);
            super.onBackPressed();
        }
    }

    private void goBack(int toPage) {
        character = character.removeProgress(toPage);
        update();
    }

    private void update() {
        createViewModel.update(character);
        binding.appbar.setExpanded(true);
        backstack = character.getProgress();
    }

    public void onActionPhoto(View view) {
        if (character.image() == null) getCroppedImage();
        else {
            new AlertDialog.Builder(this).setItems(R.array.portrait_choices, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        //Remove the image and use the default icon
                        character = character.withImage(null, null);
                        createViewModel.update(character);
                    } else getCroppedImage();
                }
            }).show();
        }
    }

    private void getCroppedImage() {
        startActivityForResult(new Intent(CreateActivity.this, CropActivity.class).putExtra(CHARACTER, character), DEFAULT);
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK || data == null) return;
        character = data.getParcelableExtra(CHARACTER);
        createViewModel.update(character);
    }

    public void onFabClicked(View view) {
        if (character.sheets().size() == 0) {
            Sheet defaultSheet = Template.create(getResources(), character);
            List<Sheet> sheets = new ArrayList<>(1);
            sheets.add(defaultSheet);
            character = character.withSheets(sheets);
        }
        finish(RESULT_OK, new Intent().putExtra(CHARACTER, character));
    }

    public void onActionDelete(MenuItem item) {
        finish(RESULT_DELETED, new Intent().putExtra(CHARACTER, character));
    }
}
