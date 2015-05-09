package amagi82.modularcharactersheetcreator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;

public class NewCharacterFragment extends Fragment implements View.OnClickListener {

    enum GameSystem {
        CTHULHU, EARTHDAWN, FENGSHUI, GURPS, SHADOWRUN, CWODVAMPIRE, CWODWEREWOLF, CWODMAGE, CWODWRAITH,
        CWODCHANGELING, NWODVAMPIRE, NWODWEREWOLF, NWODMAGE
    }
    private ImageView iconCharacter;
    private ImageView iconGameSystem;
    private ImageView iconRace;
    private ImageView iconClass;
    private ImageView iconTemplate;
    private ImageView iconColor;
    private EditText etName;
    private EditText etGameSystem;
    private EditText etRace;
    private EditText etClass;
    private EditText etTemplate;
    private EditText etColor;
    private GameSystem gameSystem;
    private int idRace;
    private int idClass;

    public NewCharacterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_character, container, false);
        setHasOptionsMenu(true);

        getActivity().setTitle(getResources().getString(R.string.new_character));

        iconCharacter = (ImageView) rootView.findViewById(R.id.iconCharacter);
        iconGameSystem = (ImageView) rootView.findViewById(R.id.iconGameSystem);
        iconRace = (ImageView) rootView.findViewById(R.id.iconRace);
        iconClass = (ImageView) rootView.findViewById(R.id.iconClass);
        iconTemplate = (ImageView) rootView.findViewById(R.id.iconTemplate);
        iconColor = (ImageView) rootView.findViewById(R.id.iconColor);
        etName = (EditText) rootView.findViewById(R.id.etName);
        etGameSystem = (EditText) rootView.findViewById(R.id.etGameSystem);
        etRace = (EditText) rootView.findViewById(R.id.etRace);
        etClass = (EditText) rootView.findViewById(R.id.etClass);
        etTemplate = (EditText) rootView.findViewById(R.id.etTemplate);
        etColor = (EditText) rootView.findViewById(R.id.etColor);

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iconCharacter:
                Log.i(null, "character icon clicked");
                break;
            case R.id.iconGameSystem:
                openMenu(R.array.game_systems, etGameSystem);
                break;
            case R.id.iconRace:
                openMenu(idRace, etClass);
                break;
            case R.id.iconClass:
                openMenu(idClass, etClass);
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

    private void openMenu(final int arrayId, final EditText editText) {
        new MaterialDialog.Builder(getActivity()).items(arrayId).itemsCallback(new MaterialDialog.ListCallback() {
            @Override
            public void onSelection(MaterialDialog dialog, View view, int position, CharSequence text) {
                if (!text.equals(getString(R.string.other))) {
                    if (editText.getText().toString().equals(getString(R.string.dnd))) {
                        editText.setText(editText.getText() + " " + text + " " + getString(R.string.edition));
                    } else {
                        editText.setText(text);
                        if (text.equals(getString(R.string.dnd))) openMenu(R.array.game_systems_dnd, editText);
                        if (text.equals(getString(R.string.world_of_darkness))) openMenu(R.array.game_systems_wod, editText);
                    }
                }
            }
        }).show();
    }

    private void gameSystemChanged() {
        etRace.setHint(getString(R.string.character_race));
        etRace.setVisibility(View.GONE);
        iconRace.setVisibility(View.GONE);
        iconClass.setVisibility(View.VISIBLE);
        etClass.setHint(getString(R.string.character_class));
        switch (gameSystem) {
            case CTHULHU:
                iconClass.setVisibility(View.INVISIBLE);
                etClass.setHint("Profession");
                break;
            case EARTHDAWN:
                idRace = R.array.races_earthdawn;
                idClass = R.array.classes_earthdawn;
                iconRace.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                etClass.setHint("Discipline");
                break;
            case FENGSHUI:
                idClass = R.array.classes_feng_shui;
                etClass.setHint("Archetype");
                break;
            case GURPS:
                iconClass.setVisibility(View.INVISIBLE);
                etRace.setVisibility(View.VISIBLE);
                etClass.setHint("Character concept");
                break;
            case SHADOWRUN:
                idRace = R.array.races_shadowrun;
                idClass = R.array.classes_shadowrun;
                iconRace.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                etClass.setHint("Archetype");
                break;
            case CWODVAMPIRE:
                idClass = R.array.classes_cwod_vampire;
                etClass.setHint("Clan");
                break;
            case CWODWEREWOLF:
                idRace = R.array.races_cwod_werewolf;
                idClass = R.array.classes_cwod_werewolf;
                iconRace.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                etRace.setHint("Breed");
                etClass.setHint("Tribe");
                break;
            case CWODMAGE:
                idClass = R.array.classes_cwod_mage;
                etClass.setHint("Tradition");
                break;
            case CWODWRAITH:
                idClass = R.array.classes_cwod_wraith;
                etClass.setHint("Faction");
                break;
            case CWODCHANGELING:
                idRace = R.array.classes_cwod_changeling;
                idClass = R.array.classes_cwod_changeling;
                iconRace.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                etRace.setHint("Court");
                etClass.setHint("Kith");
                break;
            case NWODVAMPIRE:
                idClass = R.array.classes_nwod_vampire;
                etClass.setHint("Clan");
                break;
            case NWODWEREWOLF:
                idClass = R.array.classes_nwod_werewolf;
                etClass.setHint("Tribe");
                break;
            case NWODMAGE:
                idClass = R.array.classes_nwod_mage;
                etClass.setHint("Order");
                break;
        }
    }
}