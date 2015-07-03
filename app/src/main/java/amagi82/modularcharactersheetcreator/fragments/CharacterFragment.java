package amagi82.modularcharactersheetcreator.fragments;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.colintmiller.simplenosql.NoSQL;
import com.colintmiller.simplenosql.NoSQLEntity;
import com.colintmiller.simplenosql.RetrievalCallback;
import com.edmodo.cropper.CropImageView;
import com.squareup.otto.Subscribe;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.CharacterAdapter;
import amagi82.modularcharactersheetcreator.events.TileGridItemClickedEvent;
import amagi82.modularcharactersheetcreator.events.TileItemClickedEvent;
import amagi82.modularcharactersheetcreator.models.Choice;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.game_systems.Game;
import amagi82.modularcharactersheetcreator.models.game_systems.Onyx;
import amagi82.modularcharactersheetcreator.network.VolleySingleton;
import amagi82.modularcharactersheetcreator.utils.Logan;
import amagi82.modularcharactersheetcreator.utils.Otto;
import amagi82.modularcharactersheetcreator.widgets.AnimatedNetworkImageView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class CharacterFragment extends Fragment implements Toolbar.OnMenuItemClickListener, View.OnClickListener {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PICK_FROM_FILE = 2;
    private GameCharacter gameCharacter;
    private Game game = new Game();
    private Onyx onyx;
    private CharacterAdapter characterAdapter;
    private boolean isEditMode = false;
    private Uri photoUri;
    private CropImageView cropper;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.appbar) RelativeLayout appbar;
    @Bind(R.id.imagePortrait) AnimatedNetworkImageView imagePortrait;
    @Bind(R.id.textInputLayout) TextInputLayout textInputLayout;
    @Bind(R.id.iconLeft) AnimatedNetworkImageView iconLeft;
    @Bind(R.id.tvIconLeft) TextView tvIconLeft;
    @Bind(R.id.iconRight) AnimatedNetworkImageView iconRight;
    @Bind(R.id.tvIconRight) TextView tvIconRight;
    @Bind(R.id.tvGameSystem) TextView tvGameSystem;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_character, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);

        if (gameCharacter == null) gameCharacter = new GameCharacter();

        if (getArguments() != null && getArguments().getString("entityId") != null) {
            Log.i(null, "found character");
            isEditMode = true;

            NoSQL.with(getActivity()).withDeserializer(new Logan()).using(GameCharacter.class).bucketId("bucket").entityId(getArguments().getString("entityId")).
                    retrieve(new RetrievalCallback<GameCharacter>() {
                        @Override public void retrievedResults(List<NoSQLEntity<GameCharacter>> entities) {
                            if (entities.size() > 0) gameCharacter = entities.get(0).getData();
                            if (gameCharacter.getPortraitUri() != null) imagePortrait.setImageURI(gameCharacter.getPortraitUri());
                            //recyclerView.setAdapter(new CharacterAdapter(getActivity(), );
                        }
                    });
        } else {
            initiateGameSystemChoices();
        }

        //colorMask.animate().alpha(0).setDuration(300);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(this);
        toolbar.inflateMenu(isEditMode ? R.menu.menu_edit_character : R.menu.menu_new_character);
        toolbar.setOnMenuItemClickListener(this);

        return rootView;
    }

    private void initiateGameSystemChoices() {
        if (gameCharacter.getGameEName() == null) {
            //If we have no game system set, get the list of game systems
            chooseNewGameSystem();
        } else {
            //We have a game system, use a grid layout for the sub-lists. Display the game system.
            displayGameSystem();
            onyx = gameCharacter.getGameSystem().getOnyx();
            setLeftResources();
            //See if we need the right choice
            if (onyx.hasRight()) setRightResources();
        }
    }

    private void chooseNewGameSystem() {
        tvGameSystem.setVisibility(View.INVISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        characterAdapter = new CharacterAdapter(getResources(), game.getList(Game.Category.DEFAULT), false);
        recyclerView.setAdapter(characterAdapter);
    }

    private void displayGameSystem() {
        tvGameSystem.setText(Game.System.valueOf(onyx.getSystemName()).getName());
        tvGameSystem.setVisibility(View.VISIBLE);
    }

    private void clearIcons() {
        tvIconLeft.setVisibility(View.INVISIBLE);
        tvIconRight.setVisibility(View.INVISIBLE);
        iconLeft.setVisibility(View.INVISIBLE);
        iconRight.setVisibility(View.INVISIBLE);
    }

    private void setLeftResources() {
        tvIconLeft.setText(onyx.getLeft().getTitle());
        if(onyx.getLeft().getUrl() != -1) iconLeft.setImageUrl(getUrl(onyx.getLeft()), VolleySingleton.INSTANCE.getImageLoader());
        tvIconLeft.setVisibility(View.VISIBLE);
        iconLeft.setVisibility(View.VISIBLE);
    }

    private void setRightResources() {
        tvIconRight.setText(onyx.getRight().getTitle());
        if(onyx.getRight().getUrl() != -1) iconRight.setImageUrl(getUrl(onyx.getRight()), VolleySingleton.INSTANCE.getImageLoader());
        tvIconRight.setVisibility(View.VISIBLE);
        iconRight.setVisibility(View.VISIBLE);
    }

    private String getUrl(Choice choice) {
        return getString(choice.getBaseUrl()) + getString(choice.getUrl());
    }

    //Game System selected
    @Subscribe public void onTileClicked(TileItemClickedEvent event) {
        if (event.system == Game.System.CWOD) characterAdapter.setList(game.getList(Game.Category.CWOD));
        else if (event.system == Game.System.NWOD) characterAdapter.setList(game.getList(Game.Category.NWOD));
        else {
            onyx = event.system.getOnyx();
            clearIcons();
            displayGameSystem();

            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.character_grid_span_count)));
            characterAdapter = new CharacterAdapter(getResources(), onyx.getListLeft(null), true);
            recyclerView.setAdapter(characterAdapter);
        }
    }

    //Game-specific subcategory selected
    @Subscribe
    public void onGridTileClicked(TileGridItemClickedEvent event) {
        String eName = event.eName;
        Log.i(null, "eName == " + eName);
        if (onyx.getLeft() == null && onyx.getListLeft(eName).size() > 0) {
            Log.i(null, "left is null, loading left list");
            characterAdapter.setList(onyx.getListLeft(eName));
        } else if (iconLeft.getVisibility() == View.INVISIBLE) {
            Log.i(null, "iconLeft invisible");
            setLeftResources();
            if (onyx.hasRight()) {
                Log.i(null, "left resources set, setting list right");
                characterAdapter.setList(onyx.getListRight(null));
            } else {
                Log.i(null, "setting onyx, no right");
                gameCharacter.setOnyx(onyx);
            }
        } else if (onyx.hasRight()) {
            Log.i(null, "has right");
            if (onyx.getListRight(eName).size() > 0) {
                Log.i(null, "right is null, setting list right");
                characterAdapter.setList(onyx.getListRight(eName));
            } else {
                Log.i(null, "setting right resources and onyx");
                setRightResources();
                gameCharacter.setOnyx(onyx);
            }
        }
    }

    @Override public void onStart() {
        super.onStart();
        Otto.BUS.getBus().register(this);
    }

    @Override public void onStop() {
        super.onStop();
        Otto.BUS.getBus().unregister(this);
    }

//    @OnClick(R.id.fab)
//    void getPhoto(){
//        new AlertDialog.Builder(getActivity()).setItems(gameCharacter.getPortraitUri() == null ?
//                R.array.portrait_choices_initial : R.array.portrait_choices, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                switch (which) {
//                    case 0:
//                        //Get image from camera. Check to make sure device is equipped with a camera
//                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        // Ensure that there's a camera activity to handle the intent
//                        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//                            // Create the File where the photo should go
//                            File photoFile = null;
//                            try {
//                                photoFile = createImageFile("IMG_");
//                            } catch (IOException e) {
//                                // Error occurred while creating the File
//                                e.printStackTrace();
//                            }
//                            // Continue only if the File was successfully created
//                            if (photoFile != null) {
//                                photoUri = Uri.fromFile(photoFile);
//                                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//                                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//                            }
//                        } else {
//                            Toast.makeText(getActivity(), "No camera app detected on your device", Toast.LENGTH_SHORT).show();
//                        }
//                        break;
//                    case 1:
//                        //Get image from gallery
//                        Intent intentFromGallery = new Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
//                        startActivityForResult(Intent.createChooser(intentFromGallery, "Complete action using"), PICK_FROM_FILE);
//                        break;
//                    case 2:
//                        //Just use the default icon
//                        gameCharacter.setPortraitUri(null);
//                        gameCharacter.setIcon(null);
//                        imagePortrait.setImageResource(0);
//                        break;
//                }
//            }
//        }).show();
//    }

//    String mCurrentPhotoPath;
//
//    private File createImageFile(String prefix) throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = prefix + timeStamp;
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)      /* directory */
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
//        return image;
//    }
//
//    private void galleryAddPic() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(mCurrentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        getActivity().sendBroadcast(mediaScanIntent);
//    }

    //Called when an image is selected from the camera or the gallery, and lets you crop it into an icon
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode != Activity.RESULT_OK) return;
//        if (data != null) photoUri = data.getData();
//
//        try {
//            //Measure the size of the image without loading it into memory
//            InputStream input = getActivity().getContentResolver().openInputStream(photoUri);
//            BitmapFactory.Options bitmapInfo = new BitmapFactory.Options();
//            bitmapInfo.inJustDecodeBounds = true;
//            BitmapFactory.decodeStream(input, null, bitmapInfo);
//            input.close();
//            if ((bitmapInfo.outWidth == -1) || (bitmapInfo.outHeight == -1)) return;
//
//            //Get screen size, and find ratio of image size to screen size
//            WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
//            Point size = new Point();
//            wm.getDefaultDisplay().getSize(size);
//            wm = null;
//            double ratio = Math.max(bitmapInfo.outHeight / (size.y), bitmapInfo.outWidth / (size.x));
//
//            //Scale image size down to match screen size
//            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//            bitmapOptions.inSampleSize = (int) ratio;
//            bitmapOptions.inPreferredConfig= Bitmap.Config.ARGB_8888;//optional
//            input = getActivity().getContentResolver().openInputStream(photoUri);
//            final Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
//            input.close();
//
//            LayoutInflater inflater = getActivity().getLayoutInflater();
//            final View cropImageView = inflater.inflate(R.layout.dialog_crop_image, null);
//            cropper = (CropImageView) cropImageView.findViewById(R.id.cropImageView);
//            cropper.setImageBitmap(bitmap);
//
//            new AlertDialog.Builder(getActivity())
//                    .setTitle(getResources().getString(R.string.crop_image_portrait))
//                    .setView(cropImageView)
//                    .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            imagePortrait.setImageBitmap(cropper.getCroppedImage());
//                            gameCharacter.setPortraitUri(Uri.parse(MediaStore.Images.Media.insertImage(
//                                    getActivity().getContentResolver(), cropper.getCroppedImage(), gameCharacter.getName(), gameCharacter.getGameSystem())));
//                            Log.i(null, "portrait uri == "+gameCharacter.getPortraitUri()+" and as a string: "+gameCharacter.getPortraitUri().toString());
//                            setIcon(bitmap);
//                        }
//                    })
//                    .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            setIcon(bitmap);
//                        }
//                    }).show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void setIcon(Bitmap bitmap) {
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

    //Up navigation
    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
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
        ButterKnife.unbind(this);
    }
}