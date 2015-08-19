package amagi82.modularcharactersheetcreator.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.graphics.Palette;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.edmodo.cropper.CropImageView;

import java.io.IOException;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.activities.EditCharacterActivity;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.utils.ScreenSize;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static amagi82.modularcharactersheetcreator.activities.MainActivity.NONE;
import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;
import static android.app.Activity.RESULT_OK;

public class CharacterNameFragment extends Fragment {

    private static final int PICK_FROM_FILE = 99;
    @Bind(R.id.etName) EditText etName;
    @Bind(R.id.imagePortrait) ImageView imagePortrait;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.bUpdateCharacter) Button bUpdateCharacter;
    private GameCharacter character;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_character_name, container, false);
        ButterKnife.bind(this, rootView);

        character = ((EditCharacterActivity) getActivity()).getGameCharacter();
        etName.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override public void afterTextChanged(Editable s) {
                if(s.length() >= 1){
                    bUpdateCharacter.setVisibility(View.VISIBLE);
                    fab.show();
                }else{
                    bUpdateCharacter.setVisibility(View.INVISIBLE);
                    fab.hide();
                }
            }
        });


        return rootView;
    }

    @OnClick(R.id.fab) void getPhoto() {
        Uri image = getUri();
        if (image == null) getImageFromGallery();
        else {
            new AlertDialog.Builder(getActivity()).setItems(R.array.portrait_choices, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        //Remove the image and use the default icon
                        character.setImageUriPort(null);
                        character.setImageUriLand(null);
                        imagePortrait.setImageResource(0);
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
                        Glide.with(CharacterNameFragment.this).load(uri).into(imagePortrait);
                        //setTextScrims();
                    }
                }).show();
    }

    private Uri getUri() {
        return isOrientationPort()? character.getImageUriPort() : character.getImageUriLand();
    }

    private boolean isOrientationPort(){
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    @Override
    public void onStart() {
        super.onStart();
        BUS.getBus().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        BUS.getBus().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}