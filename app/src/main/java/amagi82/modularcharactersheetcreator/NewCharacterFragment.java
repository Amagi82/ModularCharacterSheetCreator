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

import java.util.HashMap;

public class NewCharacterFragment extends Fragment implements View.OnClickListener {

    private ImageView iconCharacter;
    private ImageView iconGameSystem;
    private ImageView iconClass;
    private ImageView iconTemplate;
    private ImageView iconColor;
    private EditText etCharacterName;
    private EditText etGameSystem;
    private EditText etCharacterClass;
    private EditText etTemplate;
    private EditText etColor;
    private HashMap<String, Integer> mapArrayIds;

    public NewCharacterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_character, container, false);
        setHasOptionsMenu(true);

        //Set up hashmap Ids
        if(mapArrayIds == null) {
            mapArrayIds = new HashMap<>();
            int[] classIds = {R.array.classes_fantasy, R.array.classes_cthulhu, R.array.classes_fantasy, R.array.classes_dungeon_world,
                    R.array.classes_earthdawn, R.array.classes_feng_shui, R.array.classes_gurps, R.array.classes_fantasy, R.array.classes_shadowrun};
            int[] classIdsWod = {R.array.classes_cwod_vampire, R.array.classes_cwod_werewolf, R.array.classes_cwod_mage, R.array.classes_cwod_wraith,
                    R.array.classes_cwod_changeling, R.array.classes_nwod_vampire, R.array.classes_nwod_werewolf, R.array.classes_nwod_mage};
            String[] stringArray = getResources().getStringArray(R.array.game_systems);
            for (int i = 0; i < stringArray.length - 1; i++) {
                mapArrayIds.put(stringArray[i], classIds[i]);
            }
            stringArray = getResources().getStringArray(R.array.game_systems_wod);
            for (int i = 0; i < stringArray.length - 1; i++) {
                mapArrayIds.put(stringArray[i], classIdsWod[i]);
            }
        }

        getActivity().setTitle(getResources().getString(R.string.new_character));

        iconCharacter = (ImageView) rootView.findViewById(R.id.iconCharacter);
        iconGameSystem = (ImageView) rootView.findViewById(R.id.iconGameSystem);
        iconClass = (ImageView) rootView.findViewById(R.id.iconClass);
        iconTemplate = (ImageView) rootView.findViewById(R.id.iconTemplate);
        iconColor = (ImageView) rootView.findViewById(R.id.iconColor);
        etCharacterName = (EditText) rootView.findViewById(R.id.etCharacterName);
        etGameSystem = (EditText) rootView.findViewById(R.id.etGameSystem);
        etCharacterClass = (EditText) rootView.findViewById(R.id.etCharacterClass);
        etTemplate = (EditText) rootView.findViewById(R.id.etTemplate);
        etColor = (EditText) rootView.findViewById(R.id.etColor);

        iconCharacter.setOnClickListener(this);
        iconGameSystem.setOnClickListener(this);
        iconClass.setOnClickListener(this);
        iconTemplate.setOnClickListener(this);
        iconColor.setOnClickListener(this);
        etTemplate.setOnClickListener(this);
        etColor.setOnClickListener(this);

        return rootView;
    }

    void openMenu(final int arrayId, final EditText editText, final ImageView icon){
        new MaterialDialog.Builder(getActivity()).items(arrayId).itemsCallback(new MaterialDialog.ListCallback() {
            @Override
            public void onSelection(MaterialDialog dialog, View view, int position, CharSequence text) {
                if(!text.equals(getString(R.string.other))){
                    if(editText.getText().toString().equals(getString(R.string.dnd))){
                        editText.setText(editText.getText() + " " + text + " " + getString(R.string.edition));
                    }else{
                        editText.setText(text);
                        icon.setVisibility(View.VISIBLE);
                        if(text.equals(getString(R.string.dnd))) openMenu(R.array.game_systems_dnd, editText, icon);
                        if(text.equals(getString(R.string.world_of_darkness))) openMenu(R.array.game_systems_wod, editText, icon);
                    }
                }
            }
        }).show();
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
                openMenu(R.array.game_systems, etGameSystem, iconClass);
                break;
            case R.id.iconClass:
                int id = mapArrayIds.get(getString(R.string.other));
                openMenu(id, etCharacterClass, null);
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
}