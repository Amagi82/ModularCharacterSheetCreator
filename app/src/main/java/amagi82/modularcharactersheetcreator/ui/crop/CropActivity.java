package amagi82.modularcharactersheetcreator.ui.crop;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.ActivityCropBinding;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.ui.main.MainActivity;

/*
    This screen takes an image from the gallery and allows the user to crop it.
 */
public class CropActivity extends AppCompatActivity {
    private static final int REQ_CODE = 1;
    private GameCharacter character;
    private ActivityCropBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_crop);

        character = getIntent().getParcelableExtra(MainActivity.CHARACTER);
        if(character == null) {
            Log.i("CropActivity", "ERROR: character is null");
            finish();
        }

        Intent intentFromGallery = new Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intentFromGallery, getString(R.string.complete_action_using)), REQ_CODE);
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK || data == null) {
            finish();
            return;
        }
        try {
            binding.cropImageView.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onFabClicked(View view) {
        Bitmap croppedImage = binding.cropImageView.getCroppedBitmap();
        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), croppedImage, "OnyxPathCharacterPortrait", "Portrait used by your character"));

        Palette palette = Palette.from(croppedImage).generate();
        Palette.Swatch swatch = null;
        if (palette.getDarkVibrantSwatch() != null) swatch = palette.getDarkVibrantSwatch();
        else if (palette.getVibrantSwatch() != null) swatch = palette.getVibrantSwatch();
        else {
            for (Palette.Swatch s : palette.getSwatches()) {
                if (swatch == null || swatch.getPopulation() < s.getPopulation()) swatch = s;
            }
        }

        character = character.toBuilder()
                .image(GameCharacter.CharacterImage.create(uri, croppedImage.getHeight(), croppedImage.getWidth()))
                .colorScheme(swatch == null ? null : GameCharacter.ColorScheme.create(swatch.getRgb(), swatch.getBodyTextColor(), swatch.getTitleTextColor()))
                .build();

        setResult(RESULT_OK, new Intent().putExtra(MainActivity.CHARACTER, character));
        finish();
    }
}
