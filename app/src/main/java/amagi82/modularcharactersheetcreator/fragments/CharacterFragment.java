package amagi82.modularcharactersheetcreator.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edmodo.cropper.CropImageView;
import com.squareup.otto.Subscribe;

import java.io.IOException;

import amagi82.modularcharactersheetcreator.MainActivity;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.CharacterAdapter;
import amagi82.modularcharactersheetcreator.events.CharacterAddedEvent;
import amagi82.modularcharactersheetcreator.events.CharacterChangedEvent;
import amagi82.modularcharactersheetcreator.events.TileGridItemClickedEvent;
import amagi82.modularcharactersheetcreator.events.TileItemClickedEvent;
import amagi82.modularcharactersheetcreator.events.UpNavigationEvent;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.game_systems.Game;
import amagi82.modularcharactersheetcreator.models.game_systems.Onyx;
import amagi82.modularcharactersheetcreator.utils.Icon;
import amagi82.modularcharactersheetcreator.utils.ScreenSize;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static amagi82.modularcharactersheetcreator.App.NONE;
import static amagi82.modularcharactersheetcreator.MainActivity.EDIT_MODE;
import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;
import static android.app.Activity.RESULT_OK;
import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class CharacterFragment extends Fragment implements Toolbar.OnMenuItemClickListener, View.OnClickListener {

    private static final int PICK_FROM_FILE = 99;
    @Bind(R.id.coordinatorLayout) CoordinatorLayout coordinatorLayout;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.imagePortrait) ImageView imagePortrait;
    @Bind(R.id.textInputLayout) TextInputLayout textInputLayout;
    @Bind(R.id.iconLeft) ImageView iconLeft;
    @Bind(R.id.tvIconLeft) TextView tvIconLeft;
    @Bind(R.id.iconRight) ImageView iconRight;
    @Bind(R.id.tvIconRight) TextView tvIconRight;
    @Bind(R.id.scrim_top) View scrimTop;
    @Bind(R.id.scrim_bottom) View scrimBottom;
    @Bind(R.id.tvGameSystem) TextView tvGameSystem;
    @Bind(R.id.imageOnyxLogo) ImageView imageOnyxLogo;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    private Resources res;
    private GameCharacter character;
    private Game game = new Game();
    private Onyx onyx;
    private CharacterAdapter adapter;
    private boolean isEditMode = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_character, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);

        res = getResources();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CharacterAdapter(this);
        recyclerView.setAdapter(adapter);

        //Check if we're editing a character or creating a new one
        isEditMode = getArguments() != null && getArguments().getBoolean(EDIT_MODE, false);
        if (isEditMode) {
            Log.i(null, "edit mode");
            character = ((MainActivity) getActivity()).getCurrentCharacter();
            setTextScrims();
            textInputLayout.getEditText().setText(character.getName());
            if (character.getPortraitUri() != null) Glide.with(this).load(character.getPortraitUri()).centerCrop().into(imagePortrait);
            onyx = character.getGameSystem().getOnyx();
            onyx.setLeft(character.getLeft().geteName());
            if (onyx.hasRight()) onyx.setRight(character.getRight().geteName());
            displayGameSystem();
            setLeftResources();
            if(onyx.hasRight()) setRightResources();
            displayOnyxLogo();
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
                        displayOnyxLogo();
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

    @OnClick(R.id.tvGameSystem)
    public void chooseNewGameSystem() {
        clearIcons();
        hideOnyxLogo();
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                tvGameSystem.setText(null);
                tvGameSystem.setVisibility(GONE);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                adapter.replaceAll(game.getList(Game.Category.DEFAULT));
            }
        }, 260);
    }

    @OnClick({R.id.iconLeft, R.id.tvIconLeft})
    public void chooseLeftCategory() {
        clearIcons();
        hideOnyxLogo();
        if (tvGameSystem.getVisibility() != VISIBLE) displayGameSystem();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), res.getInteger(R.integer.character_grid_span_count)));
        adapter.setLeft(true);
        adapter.replaceAll(onyx.getListLeft(null));
    }

    @OnClick({R.id.iconRight, R.id.tvIconRight})
    public void chooseRightCategory() {
        hideOnyxLogo();
        tvIconRight.setVisibility(GONE);
        iconRight.setVisibility(GONE);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), res.getInteger(R.integer.character_grid_span_count)));
        adapter.setLeft(false);
        adapter.replaceAll(onyx.getListRight(null));
    }

    private void displayGameSystem() {
        tvGameSystem.setVisibility(VISIBLE);
        tvGameSystem.setText(Game.System.valueOf(onyx.getSystemName()).getName());
    }

    private void clearIcons() {
        tvIconLeft.setVisibility(GONE);
        tvIconRight.setVisibility(GONE);
        iconLeft.setVisibility(GONE);
        iconRight.setVisibility(GONE);
    }

    private void setLeftResources() {
        tvIconLeft.setVisibility(VISIBLE);
        iconLeft.setVisibility(VISIBLE);
        tvIconLeft.setText(onyx.getLeft().getTitle());
        Glide.with(this).load(new Icon(res, onyx.getLeft()).getUrl()).centerCrop().into(iconLeft);
    }

    private void setRightResources() {
        tvIconRight.setVisibility(VISIBLE);
        iconRight.setVisibility(VISIBLE);
        tvIconRight.setText(onyx.getRight().getTitle());
        Glide.with(this).load(new Icon(res, onyx.getRight()).getUrl()).centerCrop().into(iconRight);
    }

    private void displayOnyxLogo(){
        imageOnyxLogo.setAlpha(0f);
        imageOnyxLogo.setVisibility(VISIBLE);
        imageOnyxLogo.animate().alpha(1).setDuration(800);
    }

    private void hideOnyxLogo(){
        imageOnyxLogo.animate().alpha(0).setDuration(240);
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                imageOnyxLogo.setVisibility(GONE);
            }
        }, 250);
    }

    //Game System selected
    @Subscribe public void onTileClicked(TileItemClickedEvent event) {
        //If a system with subcategories has been selected, show them
        if (event.system == Game.System.CWOD) adapter.replaceAll(game.getList(Game.Category.CWOD));
        else if (event.system == Game.System.NWOD) adapter.replaceAll(game.getList(Game.Category.NWOD));
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
                adapter.addAll(onyx.getListLeft(eName));
            } else {
                //An option has been selected. Set the image and load the right side if needed
                setLeftResources();
                if (onyx.hasRight() && onyx.getListRight(null).size() > 0) {
                    chooseRightCategory();
                } else {
                    //There is no right side, so finalize the game character and show the Onyx Path logo
                    character.setOnyx(onyx);
                    displayOnyxLogo();
                }
            }
        } else {
            //Choose the right category
            if (onyx.getListRight(eName).size() > 0) {
                //If there are additional options available, present them
                adapter.remove("BLOODLINES");
                adapter.remove("OTHERS");
                adapter.addAll(onyx.getListRight(eName));
            } else {
                //Finished. Set our images and finalize the game character
                setRightResources();
                character.setOnyx(onyx);
                displayOnyxLogo();
            }
        }
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
                        setTextScrims();
                    }else getImageFromGallery();
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
        if (resultCode != RESULT_OK || data == null) return;

        final CropImageView cropper = new CropImageView(getActivity());
        cropper.setAspectRatio(new ScreenSize(getActivity()).getWidth(), res.getDimensionPixelSize(R.dimen.sheet_collapsing_toolbar_height));
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
                        setTextScrims();
                    }
                }).show();
    }

    private void setTextScrims() {
        int shadow = res.getColor(R.color.black);
        if(character.getPortraitUri() != null){
            tvIconLeft.setShadowLayer(3, 0, 0, shadow);
            tvIconRight.setShadowLayer(3, 0, 0, shadow);
            scrimTop.setVisibility(VISIBLE);
            scrimBottom.setVisibility(VISIBLE);
        }else{
            int colorPrimary = res.getColor(R.color.primary);
            tvIconLeft.setShadowLayer(0, 0, 0, colorPrimary);
            tvIconRight.setShadowLayer(0, 0, 0, colorPrimary);
            scrimTop.setVisibility(INVISIBLE);
            scrimBottom.setVisibility(INVISIBLE);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_delete:
//                new AlertDialog.Builder(getActivity()).setMessage(R.string.delete_character_query).setNegativeButton(R.string.cancel, null)
//                        .setPositiveButton(R.string.action_delete, new DialogInterface.OnClickListener() {
//                            @Override public void onClick(DialogInterface dialog, int which) {
//                                BUS.getBus().post(new CharacterDeletedEvent(character));
//                                character = null;
//                            }
//                        }).show();
//                return true;
//            case R.id.action_save_template:
//                return true;
            case R.id.action_discard:
                character = null;
                getFragmentManager().popBackStack();
                return true;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
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

    //Up navigation
    @Override
    public void onClick(View v) {
        BUS.getBus().post(new UpNavigationEvent());
    }
}