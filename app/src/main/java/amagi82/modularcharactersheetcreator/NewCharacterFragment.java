package amagi82.modularcharactersheetcreator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;

import amagi82.modularcharactersheetcreator.models.GameCharacter;

public class NewCharacterFragment extends Fragment implements View.OnClickListener {

    private ImageView iconRace;
    private ImageView iconClass;
    private EditText etName;
    private EditText etGameSystem;
    private EditText etRace;
    private EditText etClass;
    private int idRace;
    private int idClass;
    private int characterPosition;
    private boolean isEditMode = false;

    public NewCharacterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_character, container, false);
        setHasOptionsMenu(true);

        //If this is a character being edited, not a new character, change title and load character data
        Bundle arguments = getArguments();
        if(arguments != null) {
            isEditMode = getArguments().getBoolean("edit mode");
            characterPosition = getArguments().getInt("character");
        }
        getActivity().setTitle(getResources().getString(isEditMode? R.string.edit_character : R.string.new_character));

        ImageView iconCharacter = (ImageView) rootView.findViewById(R.id.iconCharacter);
        ImageView iconGameSystem = (ImageView) rootView.findViewById(R.id.iconGameSystem);
        iconRace = (ImageView) rootView.findViewById(R.id.iconRace);
        iconClass = (ImageView) rootView.findViewById(R.id.iconClass);
        ImageView iconTemplate = (ImageView) rootView.findViewById(R.id.iconTemplate);
        ImageView iconColor = (ImageView) rootView.findViewById(R.id.iconColor);
        etName = (EditText) rootView.findViewById(R.id.etName);
        etGameSystem = (EditText) rootView.findViewById(R.id.etGameSystem);
        etRace = (EditText) rootView.findViewById(R.id.etRace);
        etClass = (EditText) rootView.findViewById(R.id.etClass);
        EditText etTemplate = (EditText) rootView.findViewById(R.id.etTemplate);
        EditText etColor = (EditText) rootView.findViewById(R.id.etColor);

        if(isEditMode){
            GameCharacter character = MainActivity.gameCharacterList.get(characterPosition);
            iconCharacter.setImageBitmap(character.getImageCharacterIcon());
            etName.setText(character.getCharacterName());
            etGameSystem.setText(character.getGameSystem());
            etRace.setText(character.getCharacterRace());
            etClass.setText(character.getCharacterClass());
        }

        iconCharacter.setOnClickListener(this);
        iconGameSystem.setOnClickListener(this);
        iconRace.setOnClickListener(this);
        iconClass.setOnClickListener(this);
        iconTemplate.setOnClickListener(this);
        iconColor.setOnClickListener(this);
        etTemplate.setOnClickListener(this);
        etColor.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_new_character, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_add){
            GameCharacter character = new GameCharacter(etName.getText().toString(), etGameSystem.getText().toString(), etClass.getText().toString());
            character.setCharacterRace(etRace.getText().toString());
            //TODO- set up the rest of the character data once implemented
            if(isEditMode){
                MainActivity.gameCharacterList.set(characterPosition, character);
            }else{
                MainActivity.gameCharacterList.add(0, character);
            }
            getFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iconCharacter:
                Log.i(null, "character icon clicked");
                break;
            case R.id.iconGameSystem:
                chooseGameSystem(R.array.game_systems, etGameSystem);
                break;
            case R.id.iconRace:
                chooseArchetype(idRace, etRace);
                break;
            case R.id.iconClass:
                chooseArchetype(idClass, etClass);
                break;
            case R.id.iconTemplate | R.id.etTemplate:
                Log.i(null, "Offer template if available");
                break;
            case R.id.iconColor | R.id.etColor:
                Log.i(null, "Offer color choices");
                break;
            default:
                break;
        }
    }

    private void chooseGameSystem(final int arrayId, final EditText editText) {
        new MaterialDialog.Builder(getActivity()).items(arrayId).itemsCallback(new MaterialDialog.ListCallback() {
            @Override
            public void onSelection(MaterialDialog dialog, View view, int position, CharSequence text) {
                if (!text.equals(getString(R.string.other))) {
                    if (editText.getText().toString().equals(getString(R.string.dnd))) {
                        editText.setText(editText.getText() + " " + text + " " + getString(R.string.edition));
                    } else {
                        editText.setText(text);
                        if (text.equals(getString(R.string.dnd))) chooseGameSystem(R.array.game_systems_dnd, editText);
                        if (text.equals(getString(R.string.world_of_darkness))) chooseGameSystem(R.array.game_systems_wod, editText);
                    }
                }
                gameSystemChanged(text.toString().contains(":") ? -position : position);
            }
        }).show();
    }

    private void chooseArchetype(final int arrayId, final EditText editText) {
        new MaterialDialog.Builder(getActivity()).items(arrayId).itemsCallback(new MaterialDialog.ListCallback() {
            @Override
            public void onSelection(MaterialDialog dialog, View view, int position, CharSequence text) {
                if (!text.equals(getString(R.string.other))) editText.setText(text);
            }
        }).show();
    }

    private void gameSystemChanged(int position) {
        etRace.setHint(getString(R.string.character_race));
        etClass.setHint(getString(R.string.character_class));
        switch (position) {
            case 1:
                iconRace.setVisibility(View.GONE);
                etRace.setVisibility(View.GONE);
                iconClass.setVisibility(View.INVISIBLE);
                etClass.setHint("Profession");
                break;
            case 2:
                idRace = R.array.races_dnd;
                idClass = R.array.classes_dnd;
                iconRace.setVisibility(View.VISIBLE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                break;
            case 3:
                idRace = R.array.races_dungeon_world;
                idClass = R.array.classes_dungeon_world;
                iconRace.setVisibility(View.VISIBLE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                break;
            case 4:
                idRace = R.array.races_earthdawn;
                idClass = R.array.classes_earthdawn;
                iconRace.setVisibility(View.VISIBLE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                etClass.setHint("Discipline");
                break;
            case 5:
                idClass = R.array.classes_feng_shui;
                iconRace.setVisibility(View.GONE);
                etRace.setVisibility(View.VISIBLE);
                etRace.setHint("Character species, if not human");
                iconClass.setVisibility(View.VISIBLE);
                etClass.setHint("Archetype");
                break;
            case 6:
                iconClass.setVisibility(View.INVISIBLE);
                etRace.setVisibility(View.VISIBLE);
                etClass.setHint("Character concept");
                break;
            case 7:
                idRace = R.array.races_pathfinder;
                idClass = R.array.classes_pathfinder;
                iconRace.setVisibility(View.VISIBLE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                break;
            case 8:
                idRace = R.array.races_shadowrun;
                idClass = R.array.classes_shadowrun;
                iconRace.setVisibility(View.VISIBLE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                etClass.setHint("Archetype");
                break;
            case -1:
                idClass = R.array.classes_cwod_vampire;
                iconRace.setVisibility(View.GONE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.GONE);
                etClass.setHint("Clan");
                break;
            case -2:
                idRace = R.array.races_cwod_werewolf;
                idClass = R.array.classes_cwod_werewolf;
                iconRace.setVisibility(View.VISIBLE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                etRace.setHint("Breed");
                etClass.setHint("Tribe");
                break;
            case -3:
                idClass = R.array.classes_cwod_mage;
                iconRace.setVisibility(View.GONE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.GONE);
                etClass.setHint("Tradition");
                break;
            case -4:
                idClass = R.array.classes_cwod_wraith;
                iconRace.setVisibility(View.GONE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.GONE);
                etClass.setHint("Faction");
                break;
            case -5:
                idRace = R.array.classes_cwod_changeling;
                idClass = R.array.classes_cwod_changeling;
                iconRace.setVisibility(View.VISIBLE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                etRace.setHint("Court");
                etClass.setHint("Kith");
                break;
            case -6:
                idClass = R.array.classes_nwod_vampire;
                iconRace.setVisibility(View.GONE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.GONE);
                etClass.setHint("Clan");
                break;
            case -7:
                idClass = R.array.classes_nwod_werewolf;
                iconRace.setVisibility(View.GONE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.GONE);
                etClass.setHint("Tribe");
                break;
            case -8:
                idClass = R.array.classes_nwod_mage;
                iconRace.setVisibility(View.GONE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.GONE);
                etClass.setHint("Order");
                break;
            default:
                iconClass.setVisibility(View.INVISIBLE);
                etRace.setVisibility(View.VISIBLE);
        }
    }
}