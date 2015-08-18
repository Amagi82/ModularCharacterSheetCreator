package amagi82.modularcharactersheetcreator.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.bluelinelabs.logansquare.LoganSquare;
import com.edmodo.cropper.CropImageView;
import com.squareup.otto.Subscribe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.events.LeftAxisEvent;
import amagi82.modularcharactersheetcreator.events.RightAxisEvent;
import amagi82.modularcharactersheetcreator.events.TileGameClickedEvent;
import amagi82.modularcharactersheetcreator.fragments.CharacterAxisFragment;
import amagi82.modularcharactersheetcreator.fragments.CharacterGameFragment;
import amagi82.modularcharactersheetcreator.fragments.ImageFragment;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.utils.ScreenSize;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static amagi82.modularcharactersheetcreator.activities.MainActivity.NONE;
import static amagi82.modularcharactersheetcreator.activities.MainActivity.CHARACTER;
import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class EditCharacterActivity extends AppCompatActivity {

    public static final String LEFT = "Left";
    private static final String FRAG_LEFT = "frag_left";
    private static final String FRAG_RIGHT = "frag_right";
    private static final int PICK_FROM_FILE = 99;
    @Bind(R.id.coordinatorLayout) CoordinatorLayout coordinatorLayout;
    @Bind(R.id.appbar) AppBarLayout appbar;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.textInputLayout) TextInputLayout textInputLayout;
    @Bind(R.id.bGameSystem) Button bGameSystem;
    @Bind(R.id.bLeft) Button bLeft;
    @Bind(R.id.bRight) Button bRight;
    @Bind(R.id.bAddCharacter) Button bAddCharacter;
    @Bind(R.id.fab) FloatingActionButton fab;
    private FragmentManager fm = getSupportFragmentManager();
    private GameCharacter character;

    private enum Clear {ALL, LEFTRIGHT, RIGHT}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_character);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) fm.beginTransaction().replace(R.id.container, new CharacterGameFragment()).commit();

        //Check if we're editing a character or creating a new one
        boolean isEditMode = getIntent().getStringExtra(CHARACTER) != null;
        if (isEditMode) {
            Log.i(null, "edit mode");
            try {
                character = LoganSquare.parse(getIntent().getStringExtra(CHARACTER), GameCharacter.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //setTextScrims();
            textInputLayout.getEditText().setText(character.getName());
            setBackground();
            bLeft.setVisibility(VISIBLE);
            bLeft.setText(character.getLeft().getTitle());
            bRight.setVisibility(VISIBLE);
            bRight.setText(character.getRight().getTitle());
            bAddCharacter.setText(R.string.update_character);
            finishCharacter();
        } else {
            character = new GameCharacter();
        }
    }

    public GameCharacter getGameCharacter(){
        return character;
    }

    @OnClick(R.id.bGameSystem)
    public void chooseNewGameSystem() {
        clearSelections(Clear.ALL);
    }

    @OnClick(R.id.bLeft)
    public void chooseLeftCategory() {
        clearSelections(Clear.LEFTRIGHT);
        Fragment fragment = new CharacterAxisFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(LEFT, true);
        fragment.setArguments(bundle);
        fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(FRAG_LEFT).commit();
    }

    @OnClick(R.id.bRight)
    public void chooseRightCategory() {
        clearSelections(Clear.RIGHT);
        Fragment fragment = new CharacterAxisFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(LEFT, false);
        fragment.setArguments(bundle);
        fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(FRAG_RIGHT).commit();
    }

    private void clearSelections(Clear which) {
        appbar.setMinimumHeight(0);
        switch (which) {
            case ALL:
                bGameSystem.setVisibility(GONE);
                bLeft.setVisibility(GONE);
                bRight.setVisibility(GONE);
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;
            case LEFTRIGHT:
                bLeft.setVisibility(GONE);
                bRight.setVisibility(GONE);
                fm.popBackStack(FRAG_LEFT, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;
            case RIGHT:
                bRight.setVisibility(GONE);
                fm.popBackStack(FRAG_RIGHT, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;
        }
    }

    @Subscribe
    public void onGameSystemSelected(TileGameClickedEvent event) {
        character.setGameTitle(event.system.getGameTitle());
        bGameSystem.setVisibility(VISIBLE);
        bGameSystem.setText(event.system.getGameTitle());
        chooseLeftCategory();
    }

    @Subscribe
    public void onLeftAxisChosen(LeftAxisEvent event) {
        character.setLeft(event.splat);
        bLeft.setVisibility(VISIBLE);
        bLeft.setText(event.splat.getTitle());
        chooseRightCategory();
    }

    @Subscribe
    public void onRightAxisChosen(RightAxisEvent event) {
        character.setRight(event.splat);
        bRight.setVisibility(VISIBLE);
        bRight.setText(event.splat.getTitle());
        finishCharacter();
    }

    private void finishCharacter() {
        appbar.setMinimumHeight(getResources().getDimensionPixelSize(R.dimen.portrait_height));
        fab.show();
        displayOnyxLogo();
        if (textInputLayout.getEditText().getText().length() > 1) bAddCharacter.setVisibility(VISIBLE);
    }

    private void displayOnyxLogo() {
        fm.beginTransaction().replace(R.id.container, new ImageFragment()).addToBackStack(null).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_character, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_delete:
//                new AlertDialog.Builder(this).setMessage(R.string.delete_character_query).setNegativeButton(R.string.cancel, null)
//                        .setPositiveButton(R.string.action_delete, new DialogInterface.OnClickListener() {
//                            @Override public void onClick(DialogInterface dialog, int which) {
//                                finish();  //Test??
//                            }
//                        }).show();
//                return true;
//            case R.id.action_save_template:
//                return true;
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

    @OnClick(R.id.fab) void getPhoto() {
        Uri image = getUri();
        if (image == null) getImageFromGallery();
        else {
            new AlertDialog.Builder(this).setItems(R.array.portrait_choices, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        //Remove the image and use the default icon
                        character.setImageUriPort(null);
                        character.setImageUriLand(null);
                        setBackground();
                        character.setColorBackground(NONE);
                        character.setColorText(NONE);
                        //setTextScrims();
                    } else getImageFromGallery();
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
        if (resultCode != RESULT_OK || data == null) {
            setBackground();
            return;
        }

        final CropImageView cropper = new CropImageView(this);
        cropper.setAspectRatio(new ScreenSize(this).getWidth(), getResources().getDimensionPixelSize(R.dimen.portrait_height));
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
                        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), croppedImage, "OnyxPathCharacterPortrait", "Portrait used by your character"));
                        if(isOrientationPort()) character.setImageUriPort(uri);
                        else character.setImageUriLand(uri);

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
                        setBackground();
                        //setTextScrims();
                    }
                }).show();
    }

    private void setBackground() {
        Drawable drawable = null;
        InputStream is = null;
        Uri image = getUri();
        try {
            if (image != null) {
                is = getContentResolver().openInputStream(image);
                drawable = new BitmapDrawable(getResources(), is);
            } else appbar.setBackgroundColor(getResources().getColor(R.color.primary));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (is != null) try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Build.VERSION.SDK_INT >= 16) appbar.setBackground(drawable);
        else //noinspection deprecation
            appbar.setBackgroundDrawable(drawable);
    }

    private Uri getUri() {
        return isOrientationPort()? character.getImageUriPort() : character.getImageUriLand();
    }

    private boolean isOrientationPort(){
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }
}
