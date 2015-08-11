package amagi82.modularcharactersheetcreator.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.edmodo.cropper.CropImageView;
import com.squareup.otto.Subscribe;

import java.io.IOException;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.events.GameSystemEvent;
import amagi82.modularcharactersheetcreator.events.LeftAxisEvent;
import amagi82.modularcharactersheetcreator.events.RightAxisEvent;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.game_systems.Onyx;
import amagi82.modularcharactersheetcreator.utils.Logan;
import amagi82.modularcharactersheetcreator.utils.ScreenSize;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static amagi82.modularcharactersheetcreator.App.NONE;
import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class EditCharacterActivity extends AppCompatActivity {

    private static final int PICK_FROM_FILE = 99;
    @Bind(R.id.coordinatorLayout) CoordinatorLayout coordinatorLayout;
    @Bind(R.id.appbar) AppBarLayout appbar;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.imagePortrait) ImageView imagePortrait;
    @Bind(R.id.textInputLayout) TextInputLayout textInputLayout;
    @Bind(R.id.bGameSystem) Button bGameSystem;
    @Bind(R.id.bLeft) Button bLeft;
    @Bind(R.id.bRight) Button bRight;
    @Bind(R.id.bAddCharacter) Button bAddCharacter;
    @Bind(R.id.imageOnyxLogo) ImageView imageOnyxLogo;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.container) FrameLayout container;
    private FragmentManager fm = getSupportFragmentManager();
    private GameCharacter character;
    private Onyx onyx;
    private Logan logan = new Logan();
    private enum Clear {ALL, LEFTRIGHT, RIGHT}
    public enum Step {GAMESYSTEM, LEFTAXIS, RIGHTAXIS}
    private Step step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_character);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        if(savedInstanceState == null){
//            fm.beginTransaction().replace(R.id.container, new MainFragment()).commit();
//        }


//        //Check if we're editing a character or creating a new one
//        isEditMode = getArguments() != null && getArguments().getBoolean(EDIT_MODE, false);
//        if (isEditMode) {
//            Log.i(null, "edit mode");
//            character = ((MainActivity) getActivity()).getCurrentCharacter();
//            setTextScrims();
//            textInputLayout.getEditText().setText(character.getName());
//            if (character.getPortraitUri() != null) Glide.with(this).load(character.getPortraitUri()).centerCrop().into(imagePortrait);
//            onyx = character.getGameSystem().getOnyx();
//            onyx.setLeft(character.getLeft().geteName());
//            if (onyx.hasRight()) onyx.setRight(character.getRight().geteName());
//            displayGameSystem();
//            setLeftResources();
//            if(onyx.hasRight()) setRightResources();
//            displayOnyxLogo();
//        } else {
//            if (character == null) character = new GameCharacter();
//            if (onyx == null) chooseNewGameSystem();
//            else {
//                displayGameSystem();
//                if (onyx.getLeft() == null) chooseLeftCategory();
//                else {
//                    setLeftResources();
//                    if (onyx.hasRight() && onyx.getRight() == null) chooseRightCategory();
//                    else {
//                        setRightResources();
//                        displayOnyxLogo();
//                    }
//                }
//            }
//        }
    }

    @OnClick(R.id.bGameSystem)
    public void chooseNewGameSystem() {
        clearSelections(Clear.ALL);
    }

    @OnClick(R.id.bLeft)
    public void chooseLeftCategory() {
        clearSelections(Clear.LEFTRIGHT);
    }

    @OnClick(R.id.bRight)
    public void chooseRightCategory() {
        clearSelections(Clear.RIGHT);
    }

    private void displayOnyxLogo(){
        imageOnyxLogo.setAlpha(0f);
        imageOnyxLogo.setVisibility(VISIBLE);
        imageOnyxLogo.animate().alpha(1).setDuration(800);
    }

    private void hideOnyxLogo(){
        imageOnyxLogo.animate().alpha(0).setDuration(240);
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                imageOnyxLogo.setVisibility(GONE);
            }
        }, 250);
    }

    private void clearSelections(Clear which){
        hideOnyxLogo();
        switch(which){
            case ALL:
                bGameSystem.setVisibility(GONE);
                bLeft.setVisibility(GONE);
                bRight.setVisibility(GONE);
                break;
            case LEFTRIGHT:
                bLeft.setVisibility(GONE);
                bRight.setVisibility(GONE);
                break;
            case RIGHT:
                bRight.setVisibility(GONE);
                break;
        }
    }

    @Subscribe
    public void onGameSystemChosen(GameSystemEvent event){
        onyx = event.onyx;
    }

    @Subscribe
    public void onLeftAxisChosen(LeftAxisEvent event){
        bLeft.setVisibility(VISIBLE);
        bLeft.setText(event.choice.getTitle());
        if (onyx.hasRight()) chooseRightCategory();
        else finishCharacter();
    }

    @Subscribe
    public void onRightAxisChosen(RightAxisEvent event){
        if(onyx.getListRight(null).size() > 0) chooseRightCategory();
        else {
            bRight.setVisibility(VISIBLE);
            bRight.setText(event.choice.getTitle());
            finishCharacter();
        }
    }

    private void finishCharacter(){
        character.setOnyx(onyx);
        displayOnyxLogo();
        if(textInputLayout.getEditText().getText().length() > 1) bAddCharacter.setVisibility(VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_character, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                new AlertDialog.Builder(this).setMessage(R.string.delete_character_query).setNegativeButton(R.string.cancel, null)
                        .setPositiveButton(R.string.action_delete, new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialog, int which) {
                                finish();  //Test??
                            }
                        }).show();
                return true;
            case R.id.action_save_template:
                return true;
            case R.id.action_discard:
                finish();
                return true;
        }
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        try {
//            if (currentCharacter != null) outState.putString(CURRENT_CHARACTER, LoganSquare.serialize(currentCharacter));
//            outState.putString(CHARACTERS, LoganSquare.serialize(characters, GameCharacter.class));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        BUS.getBus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        BUS.getBus().unregister(this);
    }

    @OnClick(R.id.fab)
    void getPhoto(){
        if(character.getPortraitUri() == null) getImageFromGallery();
        else{
            new AlertDialog.Builder(this).setItems(R.array.portrait_choices, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which == 0){
                        //Remove the portrait and use the default icon
                        character.setPortraitUri(null);
                        imagePortrait.setImageResource(0);
                        character.setColorBackground(NONE);
                        character.setColorText(NONE);
                        //setTextScrims();
                    }else getImageFromGallery();
                }
            }).show();
        }
    }

    private void getImageFromGallery() {
        Intent intentFromGallery = new Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intentFromGallery, getString(R.string.complete_action_using)), PICK_FROM_FILE);
    }

    //Called when an image is selected from the camera or the gallery, and lets you crop it into an icon
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK || data == null) return;

        final CropImageView cropper = new CropImageView(this);
        cropper.setAspectRatio(new ScreenSize(this).getWidth(), getResources().getDimensionPixelSize(R.dimen.sheet_collapsing_toolbar_height));
        cropper.setFixedAspectRatio(true);
        try {
            cropper.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        new AlertDialog.Builder(this).setTitle(R.string.crop_image).setView(cropper).setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.crop, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        Bitmap croppedImage = cropper.getCroppedImage();
                        String url = MediaStore.Images.Media.insertImage(getContentResolver(), croppedImage, "OnyxPathCharacterPortrait", "Portrait used by your character");
                        character.setPortraitUri(Uri.parse(url));

                        Palette palette = Palette.from(croppedImage).generate();
                        Palette.Swatch swatch = null;
                        if (palette.getDarkVibrantSwatch() != null) swatch = palette.getDarkVibrantSwatch();
                        else if (palette.getVibrantSwatch() != null) swatch = palette.getVibrantSwatch();
                        else {
                            for (Palette.Swatch s : palette.getSwatches()) {
                                if (swatch == null || swatch.getPopulation() < s.getPopulation()) swatch = s;
                            }
                        }
                        if (swatch != null) {
                            character.setColorBackground(swatch.getRgb());
                            character.setColorText(swatch.getBodyTextColor());
                            character.setColorTextDim(swatch.getTitleTextColor());
                        }
                        Glide.with(EditCharacterActivity.this).load(character.getPortraitUri()).centerCrop().into(imagePortrait);
                        //setTextScrims();
                    }
                }).show();
    }
}
