package amagi82.modularcharactersheetcreator.ui.edit;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.ActivityEditBinding;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.characters.Sheet;
import amagi82.modularcharactersheetcreator.models.templates.Template;
import amagi82.modularcharactersheetcreator.ui._base.BaseActivity;
import amagi82.modularcharactersheetcreator.ui.crop.CropActivity;
import amagi82.modularcharactersheetcreator.ui.edit._events.AxisSelectedEvent;
import amagi82.modularcharactersheetcreator.ui.edit._events.AxisUpdateEvent;
import amagi82.modularcharactersheetcreator.ui.edit._events.GameSelectedEvent;
import amagi82.modularcharactersheetcreator.ui.edit._events.KeyboardVisibleEvent;
import amagi82.modularcharactersheetcreator.ui.edit._events.NameChangedEvent;
import amagi82.modularcharactersheetcreator.ui.edit._events.ResetSelectionEvent;
import icepick.State;

/*
    Screen used to create or modify the core of a GameCharacter. Choose game system, character axis(commonly referred to as "splats", name, and photo.
    Character sheet settings are not handled here (see Sheet).

    Navigation:
    This screen can be reached from either MainActivity (for new characters) or from SheetActivity (for modifying existing characters).
    Exiting this screen takes you back to the MainActivity.
 */
public class EditActivity extends BaseActivity {
    private ActivityEditBinding binding;
    private EditViewModel editViewModel;
    @State GameCharacter character;
    @State int backstack;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit);

        binding.toolbar.setNavigationIcon(getTintedIcon(R.drawable.ic_close_24dp, Color.WHITE));
        binding.toolbar.inflateMenu(R.menu.menu_edit);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        if (character == null) {
            if (getIntent().getParcelableExtra(CHARACTER) != null) character = getIntent().getParcelableExtra(CHARACTER);
            else character = GameCharacter.create();
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

    @Subscribe public void gameSelected(GameSelectedEvent event) {
        character = character.withGame(event.gameTitle);
        update();
    }

    @Subscribe public void axisUpdated(AxisUpdateEvent event) {
        editViewModel.update(character, event.splat);
        binding.appbar.setExpanded(true);
    }

    @Subscribe public void axisSelected(AxisSelectedEvent event) {
        if (editViewModel.page.get() == GameCharacter.LEFT) character = character.withLeft(event.splat);
        else character = character.withRight(event.splat);
        update();
    }

    @Subscribe public void nameChanged(NameChangedEvent event) {
        character = character.withName(event.name);
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
        editViewModel.update(character);
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
                        editViewModel.update(character);
                    } else getCroppedImage();
                }
            }).show();
        }
    }

    private void getCroppedImage() {
        startActivityForResult(new Intent(EditActivity.this, CropActivity.class).putExtra(CHARACTER, character), DEFAULT);
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK || data == null) {
            Log.i("EditActivity", "onActivityResult: failure");
            return;
        }
        character = data.getParcelableExtra(CHARACTER);
        editViewModel.update(character);
    }

    public void onFabClicked(View view) {
        if (character.sheets().size() == 0) {
            Sheet defaultSheet = Template.create(getResources(), character);
            List<Sheet> sheets = new ArrayList<>(1);
            sheets.add(defaultSheet);
            character = character.withSheets(sheets);
        }
        setResult(RESULT_OK, new Intent().putExtra(CHARACTER, character).putExtra(POSITION, getIntent().getIntExtra(POSITION, -1)));
        finish();
    }

    public void onActionDelete(MenuItem item) {
        setResult(RESULT_DELETED, new Intent().putExtra(POSITION, getIntent().getIntExtra(POSITION, -1)));
        finish();
    }
}
