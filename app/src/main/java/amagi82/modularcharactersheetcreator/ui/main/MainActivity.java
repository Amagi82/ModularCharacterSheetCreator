package amagi82.modularcharactersheetcreator.ui.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.otto.Subscribe;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.MainActivityBinding;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.games.Game;
import amagi82.modularcharactersheetcreator.models.games.templates.Template;
import amagi82.modularcharactersheetcreator.ui._base.BaseActivity;
import amagi82.modularcharactersheetcreator.ui.create.CreateActivity;
import amagi82.modularcharactersheetcreator.ui.sheet.SheetActivity;
import auto.parcelgson.gson.AutoParcelGsonTypeAdapterFactory;

/*
    Main screen the user launches into. Contains a list of characters the user has created.

    Navigation:
    Floating Action Button navigates to Create screen, where you can create a new character.
    Clicking on a character takes you to the Sheet screen.
 */
public class MainActivity extends BaseActivity {
    private MainViewModel viewModel;
    private MainActivityBinding binding;
    private Gson gson;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        gson = new GsonBuilder().registerTypeAdapterFactory(new AutoParcelGsonTypeAdapterFactory()).create();
        viewModel = new MainViewModel();
        loadSavedCharacters();

        binding.setMainViewModel(viewModel);
        //binding.toolbar.setLogo(R.drawable.title_onyx);
    }

    @Override protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                binding.fab.show();
            }
        }, 400);
    }

    @Override protected void onPause() {
        super.onPause();
        binding.fab.hide();
    }

    private void loadSavedCharacters() {
        Log.i("MainActivity", "Shared Preferences contains: " + getSaved().getAll().toString());
        List<GameCharacter> characters = new ArrayList<>();
        if (getSaved().contains(LIST)) {
            Type listType = new TypeToken<List<GameCharacter>>() {
            }.getType();
            characters = gson.fromJson(getSaved().getString(LIST, null), listType);
        }
        if(characters == null || characters.size() == 0) characters = generateSampleCharacters();
        viewModel.addAll(characters);
    }

    //Temporary sample data for testing purposes. Eventually add a splash screen to direct the user to create a new character if the list is empty
    private List<GameCharacter> generateSampleCharacters() {
        Log.i("MainActivity", "Creating data");
        List<GameCharacter> characters = new ArrayList<>();
        characters.add(GameCharacter.Companion.create("Thomas Anstis", Game.CVAMPIRE, 104, 1001));
        characters.add(GameCharacter.Companion.create("Tom Lytton", Game.CVAMPIRE, 102, 1003));
        characters.add(GameCharacter.Companion.create("Georgia Johnson", Game.CVAMPIRE, 121, 1001));
        characters.add(GameCharacter.Companion.create("Augustus von Rabenholtz", Game.CVAMPIRE, 123, 1001));
        characters.add(GameCharacter.Companion.create("Dr. Von Natsi", Game.CMAGE, 1, 107));
        characters.add(GameCharacter.Companion.create("Stormwalker", Game.CWEREWOLF, 8, 101));

        for (int i = 0; i < characters.size(); i++) {
            Sheet defaultSheet = Template.create(getResources(), characters.get(i));
            List<Sheet> sheets = new ArrayList<>(1);
            sheets.add(defaultSheet);
            characters.set(i, characters.get(i).withSheets(sheets));
        }
        putSaved(LIST, gson.toJson(characters));

        return characters;
    }

    private void saveCharacters() {
        putSaved(LIST, gson.toJson(viewModel.getCharacters()));
    }

    @Override protected void onActivityResult(@ReqCode int requestCode, @ResultCode int resultCode, Intent data) {
        Log.i("MainActivity", "onActivityResult resultCode = " + resultCode);
        if (resultCode == RESULT_CANCELED) return;

        GameCharacter character = data.getParcelableExtra(CHARACTER);
        Log.i("MainActivity", "onActivityResult: character returned: " + character);

        if (resultCode == RESULT_EDIT) {
            startActivityForResult(new Intent(MainActivity.this, CreateActivity.class).putExtra(CHARACTER, character), MODIFY);
        } else if (resultCode == RESULT_OK && requestCode == ADD) {
            viewModel.add(character);
            saveCharacters();
        } else if (resultCode == RESULT_OK && requestCode == MODIFY) {
            viewModel.update(character);
            saveCharacters();
        } else if (resultCode == RESULT_DELETED) {
            viewModel.remove(character);
            saveCharacters();
        }
    }

    @Subscribe public void onItemClicked(CharacterClickedEvent event) {
        startActivityForResult(new Intent(MainActivity.this, SheetActivity.class).putExtra(CHARACTER, event.character), DEFAULT);
    }

    public void onClickAddCharacter(View view) {
        startActivityForResult(new Intent(MainActivity.this, CreateActivity.class), ADD);
    }
}