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
import android.os.Handler;
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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.colintmiller.simplenosql.NoSQL;
import com.colintmiller.simplenosql.NoSQLEntity;
import com.colintmiller.simplenosql.RetrievalCallback;
import com.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.CharacterRVAdapter;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.widgets.ControllableAppBarLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class CharacterFragment extends Fragment implements Toolbar.OnMenuItemClickListener, View.OnClickListener {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PICK_FROM_FILE = 2;
    private GameCharacter gameCharacter;
    private boolean isEditMode = false;
    private Uri photoUri;
    private CropImageView cropper;
//    @InjectView(R.id.color_mask) View colorMask;
    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.appbar) ControllableAppBarLayout appbar;
    @InjectView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @InjectView(R.id.imagePortrait) ImageView imagePortrait;
    @InjectView(R.id.etName) EditText etName;
    @InjectView(R.id.fab) FloatingActionButton fab;
    @InjectView(R.id.recycler_view) RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_character, container, false);
        ButterKnife.inject(this, rootView);
        setHasOptionsMenu(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(gameCharacter == null) gameCharacter = new GameCharacter();

        if(getArguments() != null && getArguments().getString("entityId") != null){
            isEditMode = true;
            etName.setVisibility(View.INVISIBLE);

            NoSQL.with(getActivity()).using(GameCharacter.class).bucketId("bucket").entityId(getArguments().getString("entityId")).
            retrieve(new RetrievalCallback<GameCharacter>() {
                @Override public void retrievedResults(List<NoSQLEntity<GameCharacter>> entities) {
                    if (entities.size() > 0) gameCharacter = entities.get(0).getData();
                    if (gameCharacter.getPortraitUri() != null) imagePortrait.setImageURI(gameCharacter.getPortraitUri());
                    recyclerView.setAdapter(new CharacterRVAdapter(getActivity(), gameCharacter));
                }
            });
        }else{
            recyclerView.setAdapter(new CharacterRVAdapter(getActivity(), gameCharacter));
        }
        appbar.setOnClickListener(CharacterFragment.this);

        etName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == 6){
                    gameCharacter.setName(etName.getText().toString());
                    new Handler().postDelayed(new Runnable() {
                        @Override public void run() {
                            refreshAppBar(false);
                        }
                    }, 50);

                }
                return false;
            }
        });

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if(i!=0 && etName.getVisibility() == View.VISIBLE){
                    gameCharacter.setName(etName.getText().toString());
                    refreshAppBar(false);
                }
            }
        });

        //colorMask.animate().alpha(0).setDuration(300);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(this);
        toolbar.inflateMenu(isEditMode ? R.menu.menu_edit_character : R.menu.menu_new_character);
        toolbar.setOnMenuItemClickListener(this);

        return rootView;
    }

    private void refreshAppBar(final boolean isEditable) {
        Log.i(null, "refreshing app bar with isEditable "+isEditable);

        if(gameCharacter.getName().length() > 1) etName.setText(gameCharacter.getName());
        else etName.setHint(getString(R.string.name));

        collapsingToolbar.setTitle(isEditable? "" : gameCharacter.getName());

        appbar.collapseToolbar(false);
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                appbar.expandToolbar(false);
                etName.setVisibility(isEditable? View.VISIBLE : View.INVISIBLE);
            }
        }, 1);
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
                        gameCharacter.setIcon(null);
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
                                    getActivity().getContentResolver(), cropper.getCroppedImage(), gameCharacter.getName(), gameCharacter.getGameSystem())));
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
                        gameCharacter.setIcon(croppedBitmap);
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }

    //Up navigation and appbar refresh
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.appbar){
            Log.i(null, "appbar clicked");
            refreshAppBar(true);
        }
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