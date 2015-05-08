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

public class NewCharacterFragment extends Fragment implements View.OnClickListener{

    ImageView imageCharacterIcon;
    EditText etCharacterName;
    EditText etGameSystem;
    EditText etCharacterClass;
    EditText etColor;

    public NewCharacterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_character, container, false);
        setHasOptionsMenu(true);

        getActivity().setTitle(getResources().getString(R.string.new_character));

        imageCharacterIcon = (ImageView) rootView.findViewById(R.id.imageCharacterIcon);
        etCharacterName = (EditText) rootView.findViewById(R.id.etCharacterName);
        etGameSystem = (EditText) rootView.findViewById(R.id.etGameSystem);
        etCharacterClass = (EditText) rootView.findViewById(R.id.etCharacterClass);
        etColor = (EditText) rootView.findViewById(R.id.etColor);

        imageCharacterIcon.setOnClickListener(this);
        etGameSystem.setFocusable(false);
        etGameSystem.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        menu.clear();
        inflater.inflate(R.menu.menu_new_character, menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageCharacterIcon:
                Log.i(null, "character icon clicked");

                break;

            case R.id.etGameSystem:
                new MaterialDialog.Builder(getActivity()).items(R.array.game_systems).itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int position, CharSequence text) {
                                etGameSystem.setFocusable(true);
                                //etGameSystem.setOnClickListener(null);
                                if(position == 0){
                                    etGameSystem.requestFocus();
                                }else{
                                    etGameSystem.setText(getResources().getStringArray(R.array.game_systems)[position]);
                                }
                            }
                        }).show();

                break;

            default:
                break;
        }
    }
}