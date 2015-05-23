package amagi82.modularcharactersheetcreator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import amagi82.modularcharactersheetcreator.listeners.OnGameCharacterChangedListener;

public class CreateCharacterFragment extends Fragment {

    private OnGameCharacterChangedListener listener;
    private boolean isEditMode = false;

    public CreateCharacterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_character, container, false);
        setHasOptionsMenu(true);

        listener = (OnGameCharacterChangedListener) getActivity();

        //Check if we're editing a character or creating a new one
        Bundle arguments = getArguments();
        if(arguments != null) {
            isEditMode = getArguments().getBoolean("edit mode");
            //characterPosition = getArguments().getInt("character");
        }
        getActivity().setTitle(getResources().getString(isEditMode ? R.string.edit_character : R.string.new_character));

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

        etName.requestFocus();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.game_systems, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinGameSystem.setAdapter(adapter);
        spinGameSystem.setPrompt("Game system");

        return rootView;
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
                break;
            case R.id.action_save_template:
                break;
            case R.id.action_discard:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}