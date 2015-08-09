package amagi82.modularcharactersheetcreator.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.graphics.Palette;
import android.support.v7.util.SortedList;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edmodo.cropper.CropImageView;
import com.squareup.otto.Subscribe;

import java.io.IOException;
import java.util.List;

import amagi82.modularcharactersheetcreator.MainActivity;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.CharacterAdapter;
import amagi82.modularcharactersheetcreator.events.CharacterAddedEvent;
import amagi82.modularcharactersheetcreator.events.CharacterChangedEvent;
import amagi82.modularcharactersheetcreator.events.CharacterDeletedEvent;
import amagi82.modularcharactersheetcreator.events.TileGridItemClickedEvent;
import amagi82.modularcharactersheetcreator.events.TileItemClickedEvent;
import amagi82.modularcharactersheetcreator.events.UpNavigationEvent;
import amagi82.modularcharactersheetcreator.models.Choice;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.game_systems.Game;
import amagi82.modularcharactersheetcreator.models.game_systems.Onyx;
import amagi82.modularcharactersheetcreator.network.SizedImage;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static amagi82.modularcharactersheetcreator.App.NONE;
import static amagi82.modularcharactersheetcreator.MainActivity.EDIT_MODE;
import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;

public class CharacterFragment extends Fragment implements Toolbar.OnMenuItemClickListener, View.OnClickListener {

    private static final int PICK_FROM_FILE = 90;
    private static final String TV_GAME_SYSTEM = "tvGameSystem";
    private static final String TV_ICON = "tvIcon";
    private static final String EDIT_TEXT = "editText";
    private static final String EDIT_TEXT_HINT = "editTextHint";
    @Bind(R.id.coordinatorLayout) CoordinatorLayout coordinatorLayout;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.imagePortrait) ImageView imagePortrait;
    @Bind(R.id.textInputLayout) TextInputLayout textInputLayout;
    @Bind(R.id.iconLeft) ImageView iconLeft;
    @Bind(R.id.tvIconLeft) TextView tvIconLeft;
    @Bind(R.id.iconRight) ImageView iconRight;
    @Bind(R.id.tvIconRight) TextView tvIconRight;
    @Bind(R.id.tvGameSystem) TextView tvGameSystem;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    private Resources res;
    private GameCharacter character;
    private Game game = new Game();
    private Onyx onyx;
    private CharacterAdapter characterAdapter;
    private boolean isEditMode = false;
    private SortedList<Choice> sortedList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_character, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);

        res = getResources();

        if (sortedList == null) sortedList = new SortedList<>(Choice.class, new SortedList.Callback<Choice>() {
            @Override public int compare(Choice o1, Choice o2) {
                if (o1.getPosition() > o2.getPosition()) return 1;
                if (o1.getPosition() < o2.getPosition()) return -1;
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
        characterAdapter = new CharacterAdapter(this, sortedList);
        recyclerView.setAdapter(characterAdapter);

        //Check if we're editing a character or creating a new one
        isEditMode = getArguments() != null && getArguments().getBoolean(EDIT_MODE, false);
        if (isEditMode) {
            Log.i(null, "edit mode");
            character = ((MainActivity) getActivity()).getCurrentCharacter();
            setTextColors();
            textInputLayout.getEditText().setText(character.getName());
            if (character.getPortraitUri() != null) Glide.with(this).load(character.getPortraitUri()).centerCrop().into(imagePortrait);
            onyx = character.getGameSystem().getOnyx();
            onyx.setLeft(character.getLeft().geteName());
            if (onyx.hasRight()) onyx.setRight(character.getRight().geteName());
            displayGameSystem();
            setLeftResources();
            if(onyx.hasRight()) setRightResources();
            displayOnyxPathLogo();
        } else {
            if (character == null) character = new GameCharacter();
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewCompat.requestApplyInsets(coordinatorLayout);
    }

    private void displayOnyxPathLogo() {
//        if (imageOnyxLogo.getVisibility() != View.VISIBLE || imageOnyxLogo.getAlpha() < 1f) {
//            recyclerView.animate().alpha(0f).setDuration(200).start();
//            imageOnyxLogo.setVisibility(View.VISIBLE);
//            imageOnyxLogo.setAlpha(0f);
//            imageOnyxLogo.animate().alpha(1f).setDuration(800).setStartDelay(100).start();
//        }
    }

    private void removeOnyxPathLogo() {
        if (recyclerView.getVisibility() != View.VISIBLE || recyclerView.getAlpha() < 1f) {
            //imageOnyxLogo.animate().alpha(0f).setDuration(300).start();
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAlpha(0f);
            recyclerView.animate().alpha(1f).setDuration(200).setStartDelay(200).start();
        }
    }

    @OnClick(R.id.tvGameSystem)
    public void chooseNewGameSystem() {
        removeOnyxPathLogo();
        clearIcons();
        removeGameSystem();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateSortedList(game.getList(Game.Category.DEFAULT), true);
    }

    @OnClick({R.id.iconLeft, R.id.tvIconLeft})
    public void chooseLeftCategory() {
        removeOnyxPathLogo();
        clearIcons();
        if (tvGameSystem.getVisibility() != View.VISIBLE) displayGameSystem();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), res.getInteger(R.integer.character_grid_span_count)));
        characterAdapter.setLeft(true);
        updateSortedList(onyx.getListLeft(null), true);
    }

    @OnClick({R.id.iconRight, R.id.tvIconRight})
    public void chooseRightCategory() {
        removeOnyxPathLogo();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), res.getInteger(R.integer.character_grid_span_count)));
        characterAdapter.setLeft(false);
        updateSortedList(onyx.getListRight(null), true);
    }

    private void updateSortedList(final List<Choice> list, boolean clearList) {
        if (clearList) {
            characterAdapter.notifyItemRangeRemoved(0, sortedList.size());
            sortedList.clear();
        } else {
            for (int i = sortedList.size() - 1; i >= 0; i--) {
                if (sortedList.get(i).geteName().equals("BLOODLINES") || sortedList.get(i).geteName().equals("OTHERS")) sortedList.removeItemAt(i);
            }
        }
        sortedList.beginBatchedUpdates();
        //SortedList screws up the order of the ArrayList, so save the position in each Choice to keep everything in check
        int size = sortedList.size();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setPosition(i + size);
            sortedList.add(list.get(i));
        }
        sortedList.endBatchedUpdates();
    }

    private void displayGameSystem() {
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
        Glide.with(this).load(new SizedImage(res, onyx.getLeft(), R.dimen.circle_icon_size).getUrl()).centerCrop().into(iconLeft);
    }

    private void setRightResources() {
        tvIconRight.setVisibility(View.VISIBLE);
        iconRight.setVisibility(View.VISIBLE);
        tvIconRight.setText(onyx.getRight().getTitle());
        Glide.with(this).load(new SizedImage(res, onyx.getLeft(), R.dimen.circle_icon_size).getUrl()).centerCrop().into(iconRight);
    }

    private String getUrl(Choice choice) {
        return getString(choice.getBaseUrl()) + getString(choice.getUrl());
    }

    //Game System selected
    @Subscribe public void onTileClicked(TileItemClickedEvent event) {
        //If a system with subcategories has been selected, show them
        if (event.system == Game.System.CWOD) updateSortedList(game.getList(Game.Category.CWOD), true);
        else if (event.system == Game.System.NWOD) updateSortedList(game.getList(Game.Category.NWOD), true);
        else {
            //System selected. Update onyx and choose archetype.
            onyx = event.system.getOnyx();
            chooseLeftCategory();
        }
    }

    //Game-specific subcategory selected
    @Subscribe
    public void onGridTileClicked(TileGridItemClickedEvent event) {
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
                    character.setOnyx(onyx);
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
                character.setOnyx(onyx);
                displayOnyxPathLogo();
            }
        }
    }

    @Override public void onPause() {
        tvGameSystem.animate().cancel();
        //imageOnyxLogo.animate().cancel();
        super.onPause();
    }

    @Override public void onStart() {
        super.onStart();
        BUS.getBus().register(this);
    }

    @Override public void onStop() {
        super.onStop();
        BUS.getBus().unregister(this);
    }

    @OnClick(R.id.fab)
    void getPhoto(){
        if(character.getPortraitUri() == null) getImageFromGallery();
        else{
            new AlertDialog.Builder(getActivity()).setItems(R.array.portrait_choices, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which == 0){
                        //Remove the portrait and use the default icon
                        character.setPortraitUri(null);
                        imagePortrait.setImageResource(0);
                        character.setColorBackground(NONE);
                        character.setColorText(NONE);
                        setTextColors();
                    }else getImageFromGallery();
                }
            }).show();
        }
    }

    private void getImageFromGallery() {
        Intent intentFromGallery = new Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intentFromGallery, "Complete action using"), PICK_FROM_FILE);
    }

    //Called when an image is selected from the camera or the gallery, and lets you crop it into an icon
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK || data == null) return;
        character.setPortraitUri(data.getData());

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = res.getDimensionPixelSize(R.dimen.sheet_collapsingToolbar_height);

        final CropImageView cropper = new CropImageView(getActivity());
        cropper.setAspectRatio(width, height);
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
                        String url = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), croppedImage, "OnyxPathCharacterPortrait", "Portrait used by your character");
                        character.setPortraitUri(Uri.parse(url));

                        Palette palette = Palette.from(croppedImage).generate();
                        Palette.Swatch swatch = null;
                        if(palette.getDarkVibrantSwatch() != null) swatch = palette.getDarkVibrantSwatch();
                        else if(palette.getVibrantSwatch() != null) swatch = palette.getVibrantSwatch();
                        else{
                            for (Palette.Swatch s : palette.getSwatches()) {
                                if (swatch == null || swatch.getPopulation() < s.getPopulation()) swatch = s;
                            }
                        }
                        if (swatch != null) {
                            character.setColorBackground(swatch.getRgb());
                            character.setColorText(swatch.getBodyTextColor());
                            character.setColorTextDim(swatch.getTitleTextColor());
                        }
                        Glide.with(CharacterFragment.this).load(character.getPortraitUri()).centerCrop().into(imagePortrait);
                        setTextColors();

                    }
                }).show();
    }

    private void setTextColors() {
        int shadow = res.getColor(R.color.black);
        if(character.getPortraitUri() != null){
            tvIconLeft.setShadowLayer(3, 0, 0, shadow);
            tvIconRight.setShadowLayer(3, 0, 0, shadow);
            textInputLayout.getEditText().setShadowLayer(6, 0, 0, shadow);
        }else{
            int colorPrimary = res.getColor(R.color.primary);
            tvIconLeft.setShadowLayer(0, 0, 0, colorPrimary);
            tvIconRight.setShadowLayer(0, 0, 0, colorPrimary);
            textInputLayout.getEditText().setShadowLayer(0,0,0, colorPrimary);
        }
    }

    //Up navigation
    @Override
    public void onClick(View v) {
        BUS.getBus().post(new UpNavigationEvent());
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                new AlertDialog.Builder(getActivity()).setMessage("Delete this character?").setNegativeButton(R.string.cancel, null)
                        .setPositiveButton(R.string.action_delete, new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialog, int which) {
                                BUS.getBus().post(new CharacterDeletedEvent(character));
                                character = null;
                            }
                        }).show();
                return true;
            case R.id.action_save_template:
                return true;
            case R.id.action_discard:
                character = null;
                getFragmentManager().popBackStack();
                return true;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        Log.i(null, "destroyingView");
        if (character != null) {
            character.setName(textInputLayout.getEditText().getText().toString());
            if (character.isComplete()) {
                Log.i(null, "saving " + character.getName());
                BUS.getBus().post(isEditMode? new CharacterChangedEvent(character) : new CharacterAddedEvent(character));
            }
        }
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}