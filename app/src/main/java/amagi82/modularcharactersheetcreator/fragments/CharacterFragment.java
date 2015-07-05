package amagi82.modularcharactersheetcreator.fragments;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.util.SortedList;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
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
import butterknife.OnClick;

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
    private SortedList<Choice> sortedList;
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
    @Bind(R.id.imageOnyxLogo) ImageView imageOnyxLogo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_character, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);

        if (gameCharacter == null) gameCharacter = new GameCharacter();

        if (sortedList == null) sortedList = new SortedList<>(Choice.class, new SortedList.Callback<Choice>() {
            @Override public int compare(Choice o1, Choice o2) {
                return 0;
            }

            @Override public void onInserted(int position, int count) {
                characterAdapter.notifyItemRangeInserted(position, count);
            }

            @Override public void onRemoved(int position, int count) {
                characterAdapter.notifyItemRangeRemoved(position, count);
            }

            @Override public void onMoved(int fromPosition, int toPosition) {
                characterAdapter.notifyItemMoved(fromPosition, toPosition);
            }

            @Override public void onChanged(int position, int count) {
                characterAdapter.notifyItemRangeChanged(position, count);
            }

            @Override public boolean areContentsTheSame(Choice oldItem, Choice newItem) {
                return false;
            }

            @Override public boolean areItemsTheSame(Choice item1, Choice item2) {
                return item1.geteName().equals(item2.geteName());
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        characterAdapter = new CharacterAdapter(getResources(), sortedList);
        recyclerView.setAdapter(characterAdapter);

        if (getArguments() != null && getArguments().getString("entityId") != null) {
            Log.i(null, "found character");
            isEditMode = true;

            NoSQL.with(getActivity()).withDeserializer(new Logan()).using(GameCharacter.class).bucketId("bucket").entityId(getArguments().getString("entityId")).
                    retrieve(new RetrievalCallback<GameCharacter>() {
                        @Override public void retrievedResults(List<NoSQLEntity<GameCharacter>> entities) {
                            if (entities.size() > 0) gameCharacter = entities.get(0).getData();
                            if (gameCharacter.getPortraitUri() != null) imagePortrait.setImageURI(gameCharacter.getPortraitUri());
                            onyx = gameCharacter.getGameSystem().getOnyx();
                            onyx.setLeft(gameCharacter.getLeft().geteName());
                            onyx.setRight(gameCharacter.getRight().geteName());
                            displayGameSystem();
                            setLeftResources();
                            setRightResources();
                        }
                    });
        } else {
            if (onyx == null) chooseNewGameSystem();
            else {
                displayGameSystem();
                if (onyx.getLeft() == null) chooseLeftCategory();
                else {
                    setLeftResources();
                    if (onyx.hasRight() && onyx.getRight() == null) chooseRightCategory();
                    else {
                        setRightResources();
                        displayOnyxPathLogo();
                    }
                }
            }
        }
        //colorMask.animate().alpha(0).setDuration(300);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(this);
        toolbar.inflateMenu(isEditMode ? R.menu.menu_edit_character : R.menu.menu_new_character);
        toolbar.setOnMenuItemClickListener(this);

        return rootView;
    }

    private void displayOnyxPathLogo() {
        Log.i(null, "displayOnyxPathLogo");
        if (imageOnyxLogo.getVisibility() != View.VISIBLE || imageOnyxLogo.getAlpha() < 1f) {
            recyclerView.animate().alpha(0f).setDuration(200).start();
            //recyclerView.setVisibility(View.INVISIBLE);

            imageOnyxLogo.setVisibility(View.VISIBLE);
            imageOnyxLogo.setAlpha(0f);
            imageOnyxLogo.animate().alpha(1f).setDuration(800).setStartDelay(100).start();
        }
    }

    private void removeOnyxPathLogo() {
        Log.i(null, "removeOnyxPathLogo");
        if (recyclerView.getVisibility() != View.VISIBLE || recyclerView.getAlpha() < 1f) {
            imageOnyxLogo.animate().alpha(0f).setDuration(300).start();
            //imageOnyxLogo.setVisibility(View.GONE);

            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAlpha(0f);
            recyclerView.animate().alpha(1f).setDuration(1200).setStartDelay(200).start();
        }
    }

    @OnClick(R.id.tvGameSystem)
    public void chooseNewGameSystem() {
        Log.i(null, "chooseNewGameSystem");
        removeOnyxPathLogo();
        clearIcons();
        removeGameSystem();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateSortedList(game.getList(Game.Category.DEFAULT), true);
    }

    @OnClick({R.id.iconLeft, R.id.tvIconLeft})
    public void chooseLeftCategory() {
        Log.i(null, "chooseLeftCategory");
        removeOnyxPathLogo();
        clearIcons();
        if (tvGameSystem.getVisibility() != View.VISIBLE) displayGameSystem();

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.character_grid_span_count)));
        characterAdapter.setLeft(true);
        updateSortedList(onyx.getListLeft(null), true);
    }

    @OnClick({R.id.iconRight, R.id.tvIconRight})
    public void chooseRightCategory() {
        Log.i(null, "chooseRightCategory");
        removeOnyxPathLogo();

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.character_grid_span_count)));
        characterAdapter.setLeft(false);
        updateSortedList(onyx.getListRight(null), true);
    }

    private void updateSortedList(final List<Choice> list, boolean clearList) {
        if (clearList) {
            Log.i(null, "clearing sortedlist");
            characterAdapter.notifyItemRangeRemoved(0, sortedList.size());
            sortedList.clear();
            Log.i(null, "sortedlist should be clear");
        }
        sortedList.beginBatchedUpdates();
        for (Choice choice : list) sortedList.add(choice);
        sortedList.endBatchedUpdates();
    }

    private void displayGameSystem() {
        Log.i(null, "displayGameSystem");
        tvGameSystem.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= 21) {
            tvGameSystem.setTranslationZ(-4);
            tvGameSystem.animate().translationZ(8).setInterpolator(new DecelerateInterpolator()).setDuration(250).setStartDelay(400).start();
        }
        tvGameSystem.setTranslationY(-250);
        tvGameSystem.setText(Game.System.valueOf(onyx.getSystemName()).getName());
        tvGameSystem.animate().setInterpolator(new DecelerateInterpolator()).translationY(0).setDuration(400).start();
    }

    private void removeGameSystem() {
        Log.i(null, "removeGameSystem");
        if (Build.VERSION.SDK_INT >= 21) {
            tvGameSystem.setTranslationZ(8);
            tvGameSystem.animate().translationZ(-4).setInterpolator(new DecelerateInterpolator()).setDuration(250).start();
        }
        tvGameSystem.animate().setInterpolator(new DecelerateInterpolator()).translationY(-250).setDuration(400).setStartDelay(250).start();
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                tvGameSystem.setVisibility(View.GONE);
            }
        }, 650);
    }

    private void clearIcons() {
        tvIconLeft.setVisibility(View.GONE);
        tvIconRight.setVisibility(View.GONE);
        iconLeft.setVisibility(View.GONE);
        iconRight.setVisibility(View.GONE);
    }

    private void setLeftResources() {
        tvIconLeft.setVisibility(View.VISIBLE);
        iconLeft.setVisibility(View.VISIBLE);
        tvIconLeft.setText(onyx.getLeft().getTitle());
        iconLeft.setImageUrl((onyx.getLeft().getUrl() != -1) ? getUrl(onyx.getLeft()) : getString(R.string.url_default), VolleySingleton.INSTANCE.getImageLoader());
    }

    private void setRightResources() {
        tvIconRight.setVisibility(View.VISIBLE);
        iconRight.setVisibility(View.VISIBLE);
        tvIconRight.setText(onyx.getRight().getTitle());
        iconRight.setImageUrl((onyx.getRight().getUrl() != -1) ? getUrl(onyx.getRight()) : getString(R.string.url_default), VolleySingleton.INSTANCE.getImageLoader());
    }

    private String getUrl(Choice choice) {
        return getString(choice.getBaseUrl()) + getString(choice.getUrl());
    }

    //Game System selected
    @Subscribe public void onTileClicked(TileItemClickedEvent event) {
        //If a system with subcategories has been selected, show them
        Log.i(null, "onTileClicked");
        if (event.system == Game.System.CWOD) updateSortedList(game.getList(Game.Category.CWOD), true);
        else if (event.system == Game.System.NWOD) updateSortedList(game.getList(Game.Category.NWOD), true);
        else {
            Log.i(null, "onTileClicked else- event == " + event.system.name());
            //System selected. Choose categories.
            onyx = event.system.getOnyx();
            chooseLeftCategory();
        }
    }

    //Game-specific subcategory selected
    @Subscribe
    public void onGridTileClicked(TileGridItemClickedEvent event) {
        Log.i(null, "onGridTileClicked");
        String eName = event.eName;
        if (event.left) {
            //Choose the left category
            if (onyx.getListLeft(eName).size() > 0) {
                //If there are additional choices available, present them
                updateSortedList(onyx.getListLeft(eName), false);
            } else {
                //An option has been selected. Set the image and load the right side if needed
                setLeftResources();
                if (onyx.hasRight() && onyx.getListRight(null).size() > 0) {
                    chooseRightCategory();
                } else {
                    //There is no right side, so finalize the game character and show the Onyx Path logo
                    gameCharacter.setOnyx(onyx);
                    displayOnyxPathLogo();
                }
            }
        } else {
            //Choose the right category
            if (onyx.getListRight(eName).size() > 0) {
                //If there are additional options available, present them
                updateSortedList(onyx.getListRight(eName), false);
            } else {
                //Finished. Set our images and finalize the game character
                setRightResources();
                gameCharacter.setOnyx(onyx);
                displayOnyxPathLogo();
            }
        }
    }


    @Override public void onPause() {
        tvGameSystem.animate().cancel();
        imageOnyxLogo.animate().cancel();
        super.onPause();
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