package amagi82.modularcharactersheetcreator.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import amagi82.modularcharactersheetcreator.MainApplication;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.MainRecyclerViewAdapter;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CreateCharacterFragment extends Fragment implements Toolbar.OnMenuItemClickListener, View.OnClickListener {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PICK_FROM_FILE = 2;
    private GameCharacter gameCharacter;
    private int characterPosition;
    private boolean isEditMode = false;
    private EditText editText;
    private Uri photoUri;
    private CropImageView cropper;
//    @InjectView(R.id.color_mask) View colorMask;
    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.appbar) AppBarLayout appbar;
    //@InjectView(R.id.etName) EditText etName;
    @InjectView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @InjectView(R.id.imagePortrait) ImageView imagePortrait;
    @InjectView(R.id.fab) FloatingActionButton fab;
    @InjectView(R.id.recycler_view) RecyclerView recyclerView;
//    @InjectView(R.id.spinGameSystem) Spinner spinGameSystem;
//    @InjectView(R.id.iconRace) ImageView iconRace;
//    @InjectView(R.id.spinRace) Spinner spinRace;
//    @InjectView(R.id.iconClass) ImageView iconClass;
//    @InjectView(R.id.spinClass) Spinner spinClass;
//    @InjectView(R.id.imagePortrait) ImageButton imagePortrait;
//    @InjectView(R.id.spinTheme) Spinner spinTheme;
//    @InjectView(R.id.iconTemplate) ImageView iconTemplate;
//    @InjectView(R.id.spinTemplate) Spinner spinTemplate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_character, container, false);
        ButterKnife.inject(this, rootView);
        setHasOptionsMenu(true);

        if(gameCharacter == null) gameCharacter = new GameCharacter();

        //colorMask.animate().alpha(0).setDuration(300);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(this);
        collapsingToolbar.setTitle(gameCharacter.getCharacterName().length() < 1? getString(R.string.new_character) : gameCharacter.getCharacterName());
        toolbar.inflateMenu(isEditMode ? R.menu.menu_edit_character : R.menu.menu_new_character);
        toolbar.setOnMenuItemClickListener(this);

        if(gameCharacter.getPortraitUri() != null) imagePortrait.setImageURI(gameCharacter.getPortraitUri());

//        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
//                Log.i(null, "offset == " + i);
//                if(i > -5){
//                    etName.setVisibility(View.VISIBLE);
//                    if(!gameCharacter.getCharacterName().equals(etName.getText().toString())) etName.setText(gameCharacter.getCharacterName());
//                    collapsingToolbar.setTitle("");
//
//                }else {
//                    collapsingToolbar.setTitle(etName.getText());
//                    etName.setVisibility(View.GONE);
//
//                }
//            }
//        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MainRecyclerViewAdapter recyclerViewAdapter = new MainRecyclerViewAdapter(getActivity(), MainApplication.getGameCharacters());
        recyclerView.setAdapter(recyclerViewAdapter);

        //Check if we're editing a character or creating a new one
//        Bundle args = getArguments();
//        if(args != null) {
//            isEditMode = args.getBoolean("edit mode");
//            characterPosition = args.getInt("character");
//
//            if(isEditMode && gameCharacter == null){
//                gameCharacter = MainApplication.getGameCharacters().get(characterPosition);
//                etName.setText(gameCharacter.getCharacterName());
//                spinGameSystem.setSelection(getSpinnerIndex(spinGameSystem, gameCharacter.getGameSystem()));
//                spinRace.setSelection(getSpinnerIndex(spinRace, gameCharacter.getCharacterRace()));
//                spinClass.setSelection(getSpinnerIndex(spinClass, gameCharacter.getCharacterClass()));
//                if(gameCharacter.getPortraitUri() != null){
//                    int thumbSize = (int) getResources().getDimension(R.dimen.portrait_thumbnail_size);
//                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
//                            BitmapFactory.decodeFile(gameCharacter.getPortraitUri().getPath()), thumbSize, thumbSize);
//                    imagePortrait.setImageBitmap(thumbImage);
//                }
//
//                //spinTheme.setSelection(getSpinnerIndex(spinTheme, gameCharacter.getTheme()));
//                //spinTemplate.setSelection(getSpinnerIndex(spinTemplate, gameCharacter.getTemplate()));
//            }
//        }

        return rootView;
    }

    @OnClick(R.id.fab)
    void getPhoto(){
        new AlertDialog.Builder(getActivity()).setItems(gameCharacter.getPortraitUri() == null ?
                R.array.portrait_choices_initial : R.array.portrait_choices, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        //Get image from camera. Check to make sure device is equipped with a camera
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // Ensure that there's a camera activity to handle the intent
                        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                            // Create the File where the photo should go
                            File photoFile = null;
                            try {
                                photoFile = createImageFile("IMG_");
                            } catch (IOException e) {
                                // Error occurred while creating the File
                                e.printStackTrace();
                            }
                            // Continue only if the File was successfully created
                            if (photoFile != null) {
                                photoUri = Uri.fromFile(photoFile);
                                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "No camera app detected on your device", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 1:
                        //Get image from gallery
                        Intent intentFromGallery = new Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intentFromGallery, "Complete action using"), PICK_FROM_FILE);
                        break;
                    case 2:
                        //Just use the default icon
                        gameCharacter.setPortraitUri(null);
                        gameCharacter.setCharacterIcon(null);
                        imagePortrait.setImageResource(0);
                        break;
                }
            }
        }).show();
    }

    String mCurrentPhotoPath;

    private File createImageFile(String prefix) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = prefix + timeStamp;
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }


    //Called when an image is selected from the camera or the gallery, and lets you crop it into an icon
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (data != null) photoUri = data.getData();

        try {
            //Measure the size of the image without loading it into memory
            InputStream input = getActivity().getContentResolver().openInputStream(photoUri);
            BitmapFactory.Options bitmapInfo = new BitmapFactory.Options();
            bitmapInfo.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(input, null, bitmapInfo);
            input.close();
            if ((bitmapInfo.outWidth == -1) || (bitmapInfo.outHeight == -1)) return;

            //Get screen size, and find ratio of image size to screen size
            WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
            Point size = new Point();
            wm.getDefaultDisplay().getSize(size);
            wm = null;
            double ratio = Math.max(bitmapInfo.outHeight / (size.y), bitmapInfo.outWidth / (size.x));

            //Scale image size down to match screen size
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inSampleSize = (int) ratio;
            bitmapOptions.inPreferredConfig= Bitmap.Config.ARGB_8888;//optional
            input = getActivity().getContentResolver().openInputStream(photoUri);
            final Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
            input.close();

            LayoutInflater inflater = getActivity().getLayoutInflater();
            final View cropImageView = inflater.inflate(R.layout.dialog_crop_image, null);
            cropper = (CropImageView) cropImageView.findViewById(R.id.cropImageView);
            cropper.setImageBitmap(bitmap);

            new AlertDialog.Builder(getActivity())
                    .setTitle(getResources().getString(R.string.crop_image_portrait))
                    .setView(cropImageView)
                    .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            imagePortrait.setImageBitmap(cropper.getCroppedImage());
                            gameCharacter.setPortraitUri(Uri.parse(MediaStore.Images.Media.insertImage(
                                    getActivity().getContentResolver(), cropper.getCroppedImage(), gameCharacter.getCharacterName(), gameCharacter.getGameSystem())));
                            Log.i(null, "portrait uri == "+gameCharacter.getPortraitUri()+" and as a string: "+gameCharacter.getPortraitUri().toString());
                            setIcon(bitmap);
                        }
                    })
                    .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setIcon(bitmap);
                        }
                    }).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setIcon(Bitmap bitmap){
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View cropImageView = inflater.inflate(R.layout.dialog_crop_image, null);
        cropper = (CropImageView) cropImageView.findViewById(R.id.cropImageView);
        cropper.setImageBitmap(bitmap);

        new AlertDialog.Builder(getActivity())
                .setTitle(getResources().getString(R.string.crop_image_icon))
                .setView(cropImageView)
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bitmap croppedBitmap = cropper.getCroppedImage();
                        int circleImageSize = (int) getResources().getDimension(R.dimen.circle_icon_size);
                        croppedBitmap = Bitmap.createScaledBitmap(croppedBitmap, circleImageSize, circleImageSize, true);
                        gameCharacter.setCharacterIcon(croppedBitmap);
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }

    //Up navigation
    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                //listener.OnGameCharacterDeleted(characterPosition);
                getFragmentManager().popBackStack();
                return true;
            case R.id.action_save_template:
                return true;
            case R.id.action_discard:
                gameCharacter = null;
                getFragmentManager().popBackStack();
                return true;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}