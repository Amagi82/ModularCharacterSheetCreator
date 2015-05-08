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
    private int[] stringArrayIds = {}; //TODO: add list of string arrays
    private int stringArrayId;

    public NewCharacterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_character, container, false);
        setHasOptionsMenu(true);

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
                new MaterialDialog.Builder(getActivity()).items(R.array.game_systems).itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int position, CharSequence text) {
                        etGameSystem.setText(getResources().getStringArray(R.array.game_systems)[position]);
                        //stringArrayId = stringArrayIds[position];
                    }
                }).show();
                break;
            case R.id.iconClass:
                new MaterialDialog.Builder(getActivity()).items(stringArrayId).itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int position, CharSequence text) {
                        etCharacterClass.setText(getResources().getStringArray(stringArrayId)[position]);
                    }
                }).show();
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