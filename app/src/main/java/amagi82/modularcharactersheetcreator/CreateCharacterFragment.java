package amagi82.modularcharactersheetcreator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import amagi82.modularcharactersheetcreator.listeners.OnBackPressedListener;
import amagi82.modularcharactersheetcreator.listeners.OnGameCharacterChangedListener;
import amagi82.modularcharactersheetcreator.models.GameCharacter;

public class CreateCharacterFragment extends Fragment implements OnBackPressedListener, AdapterView.OnItemSelectedListener {

    private OnGameCharacterChangedListener listener;
    private GameCharacter gameCharacter;
    private int characterPosition;
    private boolean isEditMode = false;

    public CreateCharacterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_character, container, false);
        setHasOptionsMenu(true);

        listener = (OnGameCharacterChangedListener) getActivity();

        EditText etName = (EditText) rootView.findViewById(R.id.etName);
        Spinner spinGameSystem = (Spinner) rootView.findViewById(R.id.spinGameSystem);
        ImageView iconRace = (ImageView) rootView.findViewById(R.id.iconRace);
        Spinner spinRace = (Spinner) rootView.findViewById(R.id.spinRace);
        ImageView iconClass = (ImageView) rootView.findViewById(R.id.iconClass);
        Spinner spinClass = (Spinner) rootView.findViewById(R.id.spinClass);
        ImageButton imagePortrait = (ImageButton) rootView.findViewById(R.id.imagePortrait);
        Spinner spinTheme = (Spinner) rootView.findViewById(R.id.spinTheme);
        ImageView iconTemplate = (ImageView) rootView.findViewById(R.id.iconTemplate);
        Spinner spinTemplate = (Spinner) rootView.findViewById(R.id.spinTemplate);

        //TODO: make custom ArrayAdapter for spinners
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.game_systems, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinGameSystem.setAdapter(adapter);

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
                gameCharacter = MainActivity.gameCharacterList.get(characterPosition);
                etName.setText(gameCharacter.getCharacterName());
                spinGameSystem.setSelection(getSpinnerIndex(spinGameSystem, gameCharacter.getGameSystem()));
                spinRace.setSelection(getSpinnerIndex(spinRace, gameCharacter.getCharacterRace()));
                spinClass.setSelection(getSpinnerIndex(spinClass, gameCharacter.getCharacterClass()));
                int thumbSize = (int) getResources().getDimension(R.dimen.portrait_thumbnail_size);
                Bitmap thumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(gameCharacter.getPortraitUri().getPath()), thumbSize, thumbSize);
                imagePortrait.setImageBitmap(thumbImage);
                //spinTheme.setSelection(getSpinnerIndex(spinTheme, gameCharacter.getTheme()));
                //spinTemplate.setSelection(getSpinnerIndex(spinTemplate, gameCharacter.getTemplate()));
            }
        }
        getActivity().setTitle(getResources().getString(isEditMode ? R.string.edit_character : R.string.new_character));

        return rootView;
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(isEditMode ? R.menu.menu_edit_character : R.menu.menu_new_character, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                listener.OnGameCharacterDeleted(characterPosition);
                getFragmentManager().popBackStack();
                break;
            case R.id.action_save_template:
                //TODO: After templates created, save to custom list. Use dialog to name and confirm.
                break;
            case R.id.action_discard:
                gameCharacter = null;
                getFragmentManager().popBackStack();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spinGameSystem:
                break;
            case R.id.spinRace:
                break;
            case R.id.spinClass:
                break;
            case R.id.spinTheme:
                break;
            case R.id.spinTemplate:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        switch (parent.getId()){
            case R.id.spinGameSystem:
                break;
            case R.id.spinRace:
                break;
            case R.id.spinClass:
                break;
            case R.id.spinTheme:
                break;
            case R.id.spinTemplate:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(gameCharacter != null){
            if(isEditMode) listener.OnGameCharacterUpdated(characterPosition, gameCharacter);
            else listener.OnGameCharacterAdded(gameCharacter);
        }
    }
}