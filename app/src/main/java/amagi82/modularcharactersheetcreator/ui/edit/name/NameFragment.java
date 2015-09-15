package amagi82.modularcharactersheetcreator.ui.edit.name;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.graphics.Palette;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edmodo.cropper.CropImageView;

import java.io.IOException;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.ui.base.BaseFragment;
import amagi82.modularcharactersheetcreator.ui.edit.EditActivity;
import amagi82.modularcharactersheetcreator.ui.xtras.events.CharacterUpdatedEvent;
import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter.ColorScheme;
import amagi82.modularcharactersheetcreator.entities.games.GameSystem;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.ScreenSize;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.SplatIcon;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static amagi82.modularcharactersheetcreator.ui.xtras.utils.Otto.BUS;
import static android.app.Activity.RESULT_OK;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class NameFragment extends BaseFragment {

    private static final int PICK_FROM_FILE = 99;
    @Bind(R.id.tvPrompt) TextView tvPrompt;
    @Bind(R.id.etName) EditText etName;
    @Bind(R.id.imagePortrait) ImageView imagePortrait;
    @Bind(R.id.tvLeftSplatTitle) TextView tvLeftSplatTitle;
    @Bind(R.id.tvRightSplatTitle) TextView tvRightSplatTitle;
    @Bind(R.id.leftSplat) ImageView leftSplat;
    @Bind(R.id.rightSplat) ImageView rightSplat;
    @Bind(R.id.tvLeftSplat) TextView tvLeftSplat;
    @Bind(R.id.tvRightSplat) TextView tvRightSplat;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.bUpdateCharacter) Button bUpdateCharacter;
    private TextEntryListener textWatcher;
    private GameCharacter character;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_name, container, false);
        ButterKnife.bind(this, rootView);

        textWatcher = new TextEntryListener() {
            @Override public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() >= 1) {
                    bUpdateCharacter.setVisibility(VISIBLE);
                    tvPrompt.setText(getString(R.string.confirm));
                    fab.show();
                } else {
                    bUpdateCharacter.setVisibility(INVISIBLE);
                    tvPrompt.setText(getString(R.string.choose_name));
                    fab.hide();
                }
            }
        };
        etName.addTextChangedListener(textWatcher);

        return rootView;
    }

    @Override public void onDestroyView() {
        etName.removeTextChangedListener(textWatcher);
        super.onDestroyView();
    }

    @SuppressWarnings("ConstantConditions") @Override public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            character = getCharacter();
            GameSystem system = character.getGameSystem();
            if(system != null && character.left() != null && character.right() != null){
                tvLeftSplatTitle.setText(getString(system.getLeftTitle())+":");
                tvRightSplatTitle.setText(getString(system.getRightTitle(character.left())) + ":");
                int size = getResources().getDimensionPixelSize(R.dimen.character_circle_icon_size);
                Glide.with(this).load(new SplatIcon(getResources(), character.left(), size).getUrl()).into(leftSplat);
                Glide.with(this).load(new SplatIcon(getResources(), character.right(), size).getUrl()).into(rightSplat);
                tvLeftSplat.setText(getString(character.left().title()));
                tvRightSplat.setText(getString(character.right().title()));
            }
        }
    }

    @OnClick(R.id.bUpdateCharacter) void updateCharacter(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("Character", getCharacter());
        getActivity().setResult(RESULT_OK, returnIntent);
        getActivity().finish();
    }

    @OnClick(R.id.fab) void getPhoto() {
        character = getCharacter();
        if (character.image() == null) getImageFromGallery();
        else {
            new AlertDialog.Builder(getActivity()).setItems(R.array.portrait_choices, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        //Remove the image and use the default icon
                        character = character.toBuilder().image(null).colorScheme(null).build();
                        BUS.getBus().post(new CharacterUpdatedEvent(character));
                        imagePortrait.setImageResource(0);
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
    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK || data == null) {
            imagePortrait.setImageResource(0);
            return;
        }

        final CropImageView cropper = new CropImageView(getActivity());
        cropper.setAspectRatio(new ScreenSize(getActivity()).getWidth(), getResources().getDimensionPixelSize(R.dimen.portrait_height));
        cropper.setFixedAspectRatio(true);
        try {
            cropper.setImageBitmap(MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        new AlertDialog.Builder(getActivity()).setTitle(R.string.crop_image).setView(cropper).setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.crop, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        Bitmap croppedImage = cropper.getCroppedImage();
                        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), croppedImage, "OnyxPathCharacterPortrait", "Portrait used by your character"));

                        Palette palette = Palette.from(croppedImage).generate();
                        Palette.Swatch swatch = null;
                        if (palette.getDarkVibrantSwatch() != null) swatch = palette.getDarkVibrantSwatch();
                        else if (palette.getVibrantSwatch() != null) swatch = palette.getVibrantSwatch();
                        else {
                            for (Palette.Swatch s : palette.getSwatches()) {
                                if (swatch == null || swatch.getPopulation() < s.getPopulation()) swatch = s;
                            }
                        }
                        boolean noSwatch = swatch == null;
                        character = getCharacter();
                        character = character.toBuilder()
                                .image(GameCharacter.CharacterImage.create(uri, croppedImage.getHeight(), croppedImage.getWidth()))
                                .colorScheme(noSwatch ? null : ColorScheme.create(swatch.getRgb(), swatch.getBodyTextColor(), swatch.getTitleTextColor()))
                                .build();
                        Glide.with(NameFragment.this).load(uri).into(imagePortrait);
                        BUS.getBus().post(new CharacterUpdatedEvent(character));
                        //setTextScrims();
                    }
                }).show();
    }

    private boolean isOrientationPort(){
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    private GameCharacter getCharacter(){
        return ((EditActivity) getActivity()).getGameCharacter();
    }
}