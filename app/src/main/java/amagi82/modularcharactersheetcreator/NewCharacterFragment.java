package amagi82.modularcharactersheetcreator;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;

import amagi82.modularcharactersheetcreator.listeners.OnGameCharacterAddedListener;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import de.hdodenhof.circleimageview.CircleImageView;

public class NewCharacterFragment extends Fragment implements View.OnClickListener {

    private OnGameCharacterAddedListener listener;
    private CircleImageView iconCharacter;
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
    private boolean isIconCharacterPresent = false;

    public NewCharacterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_character, container, false);
        setHasOptionsMenu(true);

        listener = (OnGameCharacterAddedListener) getActivity();

        //If this is a character being edited, not a new character, change title and load character data
        Bundle arguments = getArguments();
        if(arguments != null) {
            isEditMode = getArguments().getBoolean("edit mode");
            characterPosition = getArguments().getInt("character");
        }
        getActivity().setTitle(getResources().getString(isEditMode? R.string.edit_character : R.string.new_character));

        iconCharacter = (CircleImageView) rootView.findViewById(R.id.iconCharacter);
        ImageView iconGameSystem = (ImageView) rootView.findViewById(R.id.iconGameSystem);
        iconRace = (ImageView) rootView.findViewById(R.id.iconRace);
        iconClass = (ImageView) rootView.findViewById(R.id.iconClass);
        ImageView iconTemplate = (ImageView) rootView.findViewById(R.id.iconTemplate);
        etName = (EditText) rootView.findViewById(R.id.etName);
        etGameSystem = (EditText) rootView.findViewById(R.id.etGameSystem);
        etRace = (EditText) rootView.findViewById(R.id.etRace);
        etClass = (EditText) rootView.findViewById(R.id.etClass);
        EditText etTemplate = (EditText) rootView.findViewById(R.id.etTemplate);

        if(isEditMode){
            GameCharacter character = MainActivity.gameCharacterList.get(characterPosition);
            iconCharacter.setImageBitmap(character.getCharacterIcon());
            etName.setText(character.getCharacterName());
            etGameSystem.setText(character.getGameSystem());
            etRace.setText(character.getCharacterRace());
            etClass.setText(character.getCharacterClass());
            isIconCharacterPresent = true;
        }

        iconCharacter.setOnClickListener(this);
        iconGameSystem.setOnClickListener(this);
        iconRace.setOnClickListener(this);
        iconClass.setOnClickListener(this);
        iconTemplate.setOnClickListener(this);
        etTemplate.setOnClickListener(this);

        etName.addTextChangedListener(new TextWatcher() {
            char firstLetter;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                firstLetter = s.charAt(0);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (firstLetter != s.charAt(0)) {
                    iconCharacter.setImageBitmap(createDefaultIcon());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                isIconCharacterPresent = true;
            }
        });


        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_new_character, menu);
        menu.findItem(R.id.action_add).getActionView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameCharacter character = new GameCharacter(etName.getText().toString(), etGameSystem.getText().toString(),
                        etRace.getText().toString(), etClass.getText().toString());
                character.setCharacterIcon(isIconCharacterPresent ? ((BitmapDrawable) iconCharacter.getDrawable()).getBitmap() : createDefaultIcon());
                //TODO- set up the rest of the character data once implemented
                if (isEditMode) {
                    listener.OnGameCharacterUpdated(characterPosition, character);
                } else {
                    listener.OnGameCharacterAdded(character);
                }
                getFragmentManager().popBackStack();

                //Dismiss keyboard
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etName.getWindowToken(), 0);
            }
        });

    }

    private Bitmap createDefaultIcon(){
        int iconSize = getResources().getDimensionPixelSize(R.dimen.circle_icon_size);
        int textColor = getResources().getColor(R.color.white);
        int backgroundColor = getResources().getColor(R.color.primary);

        TextPaint textPaint = new TextPaint();
        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setLinearText(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(getResources().getDimension(R.dimen.text_size_circle_icon));

        Rect rect = new Rect();
        rect.set(0, 0, iconSize, iconSize);

        int xPos = rect.centerX();
        int yPos = (int) (rect.centerY() - (textPaint.descent() + textPaint.ascent()) * .5);

        Bitmap bitmap = Bitmap.createBitmap(iconSize, iconSize, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(backgroundColor);
        canvas.drawText(etName.getText().toString().substring(0, 1), xPos, yPos, textPaint);

        return bitmap;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
            case R.id.iconTemplate:
                Log.i(null, "Offer template if available");
                break;
            case R.id.etTemplate:
                Log.i(null, "Offer template if available");
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
                        return;
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