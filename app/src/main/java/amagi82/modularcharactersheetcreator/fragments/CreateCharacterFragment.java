package amagi82.modularcharactersheetcreator.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import amagi82.modularcharactersheetcreator.MainApplication;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.SpinnerArrayAdapter;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class CreateCharacterFragment extends Fragment implements Toolbar.OnMenuItemClickListener, View.OnClickListener, AdapterView.OnItemSelectedListener {

    private GameCharacter gameCharacter;
    private int characterPosition;
    private boolean isEditMode = false;
    @InjectView(R.id.color_mask) View colorMask;
    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.etName) EditText etName;
    @InjectView(R.id.spinGameSystem) Spinner spinGameSystem;
    @InjectView(R.id.iconRace) ImageView iconRace;
    @InjectView(R.id.spinRace) Spinner spinRace;
    @InjectView(R.id.iconClass) ImageView iconClass;
    @InjectView(R.id.spinClass) Spinner spinClass;
    @InjectView(R.id.imagePortrait) ImageButton imagePortrait;
    @InjectView(R.id.spinTheme) Spinner spinTheme;
    @InjectView(R.id.iconTemplate) ImageView iconTemplate;
    @InjectView(R.id.spinTemplate) Spinner spinTemplate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_character, container, false);
        ButterKnife.inject(this, rootView);
        setHasOptionsMenu(true);

        colorMask.animate().alpha(0).setDuration(300);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(this);
        toolbar.setTitle(getString(R.string.new_character));
        toolbar.inflateMenu(isEditMode ? R.menu.menu_edit_character : R.menu.menu_new_character);
        toolbar.setOnMenuItemClickListener(this);

        setSpinnerAdapter(spinGameSystem, R.array.game_systems);

        spinGameSystem.setOnItemSelectedListener(this);
        spinRace.setOnItemSelectedListener(this);
        spinClass.setOnItemSelectedListener(this);
        spinTheme.setOnItemSelectedListener(this);
        spinTemplate.setOnItemSelectedListener(this);

        //Check if we're editing a character or creating a new one
        Bundle args = getArguments();
        if(args != null) {
            isEditMode = args.getBoolean("edit mode");
            characterPosition = args.getInt("character");

            if(isEditMode && gameCharacter == null){
                gameCharacter = MainApplication.getGameCharacters().get(characterPosition);
                etName.setText(gameCharacter.getCharacterName());
                spinGameSystem.setSelection(getSpinnerIndex(spinGameSystem, gameCharacter.getGameSystem()));
                spinRace.setSelection(getSpinnerIndex(spinRace, gameCharacter.getCharacterRace()));
                spinClass.setSelection(getSpinnerIndex(spinClass, gameCharacter.getCharacterClass()));
                if(gameCharacter.getPortraitUri() != null){
                    int thumbSize = (int) getResources().getDimension(R.dimen.portrait_thumbnail_size);
                    Bitmap thumbImage = ThumbnailUtils.extractThumbnail(
                            BitmapFactory.decodeFile(gameCharacter.getPortraitUri().getPath()), thumbSize, thumbSize);
                    imagePortrait.setImageBitmap(thumbImage);
                }

                //spinTheme.setSelection(getSpinnerIndex(spinTheme, gameCharacter.getTheme()));
                //spinTemplate.setSelection(getSpinnerIndex(spinTemplate, gameCharacter.getTemplate()));
            }
        }
        if(gameCharacter == null) gameCharacter = new GameCharacter();
        getActivity().setTitle(getResources().getString(isEditMode ? R.string.edit_character : R.string.new_character));

        return rootView;
    }

    private void setSpinnerAdapter(Spinner spinner, int arrayId){
        spinner.setAdapter(SpinnerArrayAdapter.createFromResource(getActivity(), arrayId));
    }

    //Get the index of the user's selection
    private int getSpinnerIndex(Spinner spinner, String string) {
        int index = 0;
        for (int i = 0; i < spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equals(string)){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = parent.getSelectedItem().toString();
        switch (parent.getId()){
            case R.id.spinGameSystem:
                gameCharacter.setGameSystem(selected);
                break;
            case R.id.spinRace:
                gameCharacter.setCharacterRace(selected);
                break;
            case R.id.spinClass:
                gameCharacter.setCharacterClass(selected);
                break;
            case R.id.spinTheme:

                break;
            case R.id.spinTemplate:

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
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
                //TODO: After templates created, save to custom list. Use dialog to name and confirm.
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