package amagi82.modularcharactersheetcreator.widgets;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.listeners.OnGameCharacterChangedListener;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import de.hdodenhof.circleimageview.CircleImageView;

public class CreateCharacterFragment extends Fragment implements View.OnClickListener {

    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_FILE = 2;
    private static Uri imageCaptureUri;
    private static Bitmap croppedBitmap;
    private GameCharacter gameCharacter;
    private OnGameCharacterChangedListener listener;
    private MaterialDialog dialog;
    private CircleImageView iconCharacter;
    private ImageView iconRace;
    private ImageView iconClass;
    private EditText etName;
    private EditText etGameSystem;
    private EditText etRace;
    private EditText etClass;
    private CropImageView cropper;
    private int idRace;
    private int idClass;
    private int characterPosition;
    private int circleImageSize;
    private boolean isEditMode = false;
    private boolean hasCustomCharacterIcon = false;
    private boolean deletingCharacter = false;

    public CreateCharacterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Check if we're editing a character or creating a new one
        Bundle arguments = getArguments();
        if(arguments != null) {
            isEditMode = getArguments().getBoolean("edit mode");
            characterPosition = getArguments().getInt("character");
        }
        getActivity().setTitle(getResources().getString(isEditMode? R.string.edit_character : R.string.new_character));

        View rootView = inflater.inflate(isEditMode? R.layout.fragment_edit_character : R.layout.fragment_add_character, container, false);
        setHasOptionsMenu(true);

        listener = (OnGameCharacterChangedListener) getActivity();

        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(isEditMode ? R.menu.menu_edit_character : R.menu.menu_new_character, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iconCharacter: //Select an image for a custom icon
                dialog = new MaterialDialog.Builder(getActivity()).items(R.array.icon_choices)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int position, CharSequence text) {
                                switch (position){
                                    case 0:
                                        //Get image from camera. Check to make sure device is equipped with a camera
                                        if(getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
                                            Intent intentTakePhoto 	 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                            imageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                                    "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));
                                            intentTakePhoto.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageCaptureUri);
                                            try {
                                                intentTakePhoto.putExtra("return-data", true);
                                                startActivityForResult(intentTakePhoto, PICK_FROM_CAMERA);
                                            } catch (ActivityNotFoundException e) {
                                                e.printStackTrace();
                                            }
                                        }else{
                                            Toast.makeText(getActivity(), "No camera detected on your device", Toast.LENGTH_SHORT).show();
                                        }
                                        break;

                                    case 1:
                                        //Get image from gallery
                                        Intent intentFromGallery = new Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
                                        startActivityForResult(Intent.createChooser(intentFromGallery, "Complete action using"), PICK_FROM_FILE);
                                        break;
                                    case 2:
                                        //Just use the default icon
                                        //iconCharacter.setImageBitmap(createDefaultIcon());
                                        hasCustomCharacterIcon = false;
                                        break;
                                }
                            }
                        }).show();
                break;
            case R.id.iconGameSystem:
                //chooseGameSystem(R.array.game_systems, etGameSystem);
                break;
            case R.id.iconRace:
                //chooseArchetype(idRace, etRace);
                break;
            case R.id.iconClass:
                //chooseArchetype(idClass, etClass);
                break;
            case R.id.iconTemplate:
                Log.i(null, "Offer template if available");
                break;
            case R.id.etTemplate:
                Log.i(null, "Offer template if available");
                break;
            case R.id.bDelete:
                listener.OnGameCharacterDeleted(characterPosition);
                deletingCharacter = true;
                getFragmentManager().popBackStack();
                break;
            default:
                break;
        }
    }

    //Called when an image is selected from the camera or the gallery, and lets you crop it into an icon
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (data != null) imageCaptureUri = data.getData();

        try {
            //Measure the size of the image without loading it into memory
            InputStream input = getActivity().getContentResolver().openInputStream(imageCaptureUri);
            BitmapFactory.Options bitmapInfo = new BitmapFactory.Options();
            bitmapInfo.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(input, null, bitmapInfo);
            input.close();
            if ((bitmapInfo.outWidth == -1) || (bitmapInfo.outHeight == -1)) return;

            //Get screen size, and find ratio of image size to screen size
            WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            wm = null;
            Point size = new Point();
            display.getSize(size);
            double ratio = Math.max(bitmapInfo.outHeight / (size.y*.8), bitmapInfo.outWidth / (size.x*.8));

            //Scale image size down to match screen size
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inSampleSize = (int) ratio;
            bitmapOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
            input = getActivity().getContentResolver().openInputStream(imageCaptureUri);
            Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
            input.close();

            dialog = new MaterialDialog.Builder(getActivity())
                    .title(getResources().getString(R.string.crop_image)).customView(R.layout.dialog_crop_image, false)
                    .positiveText(getResources().getString(R.string.ok))
                    .negativeText(getResources().getString(R.string.cancel))
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            croppedBitmap = cropper.getCroppedImage();
                            croppedBitmap = Bitmap.createScaledBitmap(croppedBitmap, circleImageSize, circleImageSize, true);
                            iconCharacter.setImageBitmap(croppedBitmap);
                            hasCustomCharacterIcon = true;
                        }
                    })
                    .build();
            cropper = (CropImageView) dialog.findViewById(R.id.cropImageView);
            cropper.setImageBitmap(bitmap);
            dialog.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}