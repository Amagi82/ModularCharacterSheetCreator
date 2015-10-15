package amagi82.modularcharactersheetcreator.ui.edit.name;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.FragmentEditNameBinding;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.ui.base.BaseFragment;
import amagi82.modularcharactersheetcreator.ui.edit.CharacterUpdatedEvent;
import amagi82.modularcharactersheetcreator.ui.edit.EditActivity;

public class NameFragment extends BaseFragment {
    private NameViewModel nameViewModel;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentEditNameBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_name, container, false);

        nameViewModel = new NameViewModel(getCurrentCharacter());
        binding.setNameViewModel(nameViewModel);

        return binding.getRoot();
    }

    private GameCharacter getCurrentCharacter(){
        return ((EditActivity) getActivity()).getGameCharacter();
    }

    @Subscribe public void characterUpdated(CharacterUpdatedEvent event){
        nameViewModel.update(getCurrentCharacter());
    }

//    @OnClick(R.id.fab) void getPhoto() {
//        character = getCharacter();
//        if (character.image() == null) getImageFromGallery();
//        else {
//            new AlertDialog.Builder(getActivity()).setItems(R.array.portrait_choices, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    if (which == 0) {
//                        //Remove the image and use the default icon
//                        character = character.toBuilder().image(null).colorScheme(null).build();
//                        BUS.getBus().post(new CharacterUpdatedEvent(character));
//                        imagePortrait.setImageResource(0);
//                        //setTextScrims();
//                    } else getImageFromGallery();
//                }
//            }).show();
//        }
//    }
//
//    private void getImageFromGallery() {
//        Intent intentFromGallery = new Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intentFromGallery, getString(R.string.complete_action_using)), PICK_FROM_FILE);
//    }
//
//    //Called when an image is selected from the camera or the gallery, and lets you crop it into an icon
//    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode != RESULT_OK || data == null) {
//            imagePortrait.setImageResource(0);
//            return;
//        }
//
//        final CropImageView cropper = new CropImageView(getActivity());
//        cropper.setAspectRatio(new ScreenSize(getActivity()).getWidth(), getResources().getDimensionPixelSize(R.dimen.portrait_height));
//        cropper.setFixedAspectRatio(true);
//        try {
//            cropper.setImageBitmap(MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        new AlertDialog.Builder(getActivity()).setTitle(R.string.crop_image).setView(cropper).setNegativeButton(R.string.cancel, null)
//                .setPositiveButton(R.string.crop, new DialogInterface.OnClickListener() {
//                    @Override public void onClick(DialogInterface dialog, int which) {
//                        Bitmap croppedImage = cropper.getCroppedImage();
//                        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), croppedImage, "OnyxPathCharacterPortrait", "Portrait used by your character"));
//
//                        Palette palette = Palette.from(croppedImage).generate();
//                        Palette.Swatch swatch = null;
//                        if (palette.getDarkVibrantSwatch() != null) swatch = palette.getDarkVibrantSwatch();
//                        else if (palette.getVibrantSwatch() != null) swatch = palette.getVibrantSwatch();
//                        else {
//                            for (Palette.Swatch s : palette.getSwatches()) {
//                                if (swatch == null || swatch.getPopulation() < s.getPopulation()) swatch = s;
//                            }
//                        }
//                        boolean noSwatch = swatch == null;
//                        character = getCharacter();
//                        character = character.toBuilder()
//                                .image(GameCharacter.CharacterImage.create(uri, croppedImage.getHeight(), croppedImage.getWidth()))
//                                .colorScheme(noSwatch ? null : ColorScheme.create(swatch.getRgb(), swatch.getBodyTextColor(), swatch.getTitleTextColor()))
//                                .build();
//                        Glide.with(NameFragment.this).load(uri).into(imagePortrait);
//                        BUS.getBus().post(new CharacterUpdatedEvent(character));
//                        //setTextScrims();
//                    }
//                }).show();
//    }
}