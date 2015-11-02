package amagi82.modularcharactersheetcreator.ui.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
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
import amagi82.modularcharactersheetcreator.databinding.ActivityMainBinding;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.characters.Sheet;
import amagi82.modularcharactersheetcreator.models.characters.Splat;
import amagi82.modularcharactersheetcreator.models.games.CMage;
import amagi82.modularcharactersheetcreator.models.games.CVampire;
import amagi82.modularcharactersheetcreator.models.games.CWerewolf;
import amagi82.modularcharactersheetcreator.models.templates.Template;
import amagi82.modularcharactersheetcreator.ui._base.BaseActivity;
import amagi82.modularcharactersheetcreator.ui.edit.EditActivity;
import amagi82.modularcharactersheetcreator.ui.sheet.SheetActivity;
import auto.parcelgson.gson.AutoParcelGsonTypeAdapterFactory;

/*
    Main screen the user launches into. Contains a list of characters the user has created.

    Navigation:
    Floating Action Button navigates to Edit screen, where you can create a new character.
    Clicking on a character takes you to the Sheet screen.
 */
public class MainActivity extends BaseActivity {
    private MainViewModel viewModel;
    private Gson gson;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        gson = new GsonBuilder().registerTypeAdapterFactory(new AutoParcelGsonTypeAdapterFactory()).create();
        viewModel = new MainViewModel();
        getSharedPreferences(CHARACTERS, MODE_PRIVATE).edit().clear();
        loadSavedCharacters();

        binding.setMainViewModel(viewModel);
        binding.toolbar.setLogo(R.drawable.title_onyx);
    }

    private void loadSavedCharacters() {
        Log.i("MainActivity", "Shared Preferences contains: "+getSharedPreferences(CHARACTERS, MODE_PRIVATE).getAll().toString());
        List<GameCharacter> characters;
        if(getSharedPreferences(CHARACTERS, MODE_PRIVATE).contains(CHARACTERS)){
            Type listType = new TypeToken<List<GameCharacter>>(){}.getType();
            characters = gson.fromJson(getSharedPreferences(CHARACTERS, MODE_PRIVATE).getString(CHARACTERS, null), listType);
        } else characters = generateSampleCharacters();
        viewModel.addAll(characters);
    }

    //Temporary sample data for testing purposes. Eventually add a splash screen to direct the user to create a new character if the list is empty
    private List<GameCharacter> generateSampleCharacters() {
        Log.i("MainActivity", "Creating data");
        List<GameCharacter> characters = new ArrayList<>();
        characters.add(GameCharacter.create("Thomas Anstis", new CVampire(), Splat.create(R.string.gangrel, R.string.url_cwod_vampire_clan_gangrel), Splat.create(R.string.camarilla, R.string.url_cwod_vampire_sect_camarilla)));
        characters.add(GameCharacter.create("Tom Lytton", new CVampire(), Splat.create(R.string.brujah_antitribu, R.string.url_cwod_vampire_antitribu_brujah), Splat.create(R.string.anarchs, R.string.url_cwod_vampire_sect_anarchs)));
        characters.add(GameCharacter.create("Georgia Johnson", new CVampire(), Splat.create(R.string.tremere, R.string.url_cwod_vampire_clan_tremere), Splat.create(R.string.camarilla, R.string.url_cwod_vampire_sect_camarilla)));
        characters.add(GameCharacter.create("Augustus von Rabenholtz", new CVampire(), Splat.create(R.string.ventrue, R.string.url_cwod_vampire_clan_ventrue), Splat.create(R.string.camarilla, R.string.url_cwod_vampire_sect_camarilla)));
        characters.add(GameCharacter.create("Dr. Von Natsi", new CMage(), Splat.create(R.string.traditions, R.string.url_cwod_mage_faction_traditions), Splat.create(R.string.scions_of_ether, R.string.url_cwod_mage_tradition_scions_of_ether)));
        characters.add(GameCharacter.create("Stormwalker", new CWerewolf(), Splat.create(R.string.glass_walkers, R.string.url_cwod_werewolf_tribe_glass_walkers), Splat.create(R.string.ahroun, R.string.url_cwod_werewolf_auspice_ahroun)));

        for (int i = 0; i < characters.size(); i++) {
            Sheet defaultSheet = Template.create(getResources(), characters.get(i));
            List<Sheet> sheets = new ArrayList<>(1);
            sheets.add(defaultSheet);
            characters.set(i, characters.get(i).withSheets(sheets));
        }
        getSharedPreferences(CHARACTERS, MODE_PRIVATE).edit().putString(CHARACTERS, gson.toJson(characters)).commit();

        return characters;
    }

    private void saveCharacters() {
        getSharedPreferences(CHARACTERS, MODE_PRIVATE).edit().putString(CHARACTERS, gson.toJson(viewModel.getCharacters())).commit();
    }

    @Override protected void onActivityResult(@ReqCode int requestCode, @ResultCode int resultCode, Intent data) {
        Log.i("MainActivity", "onActivityResult resultCode = " + resultCode);
        if (resultCode == RESULT_CANCELED) return;

        GameCharacter character = data.getParcelableExtra(CHARACTER);
        int position = data.getIntExtra(POSITION, -1);
        Log.i("MainActivity", "onActivityResult: position: " + position);
        Log.i("MainActivity", "onActivityResult: character returned: " + character);

        if (resultCode == RESULT_OK && requestCode == ADD) {
            viewModel.add(character);
            saveCharacters();
        }
        else if (resultCode == RESULT_OK && requestCode == MODIFY && position >= 0) {
            viewModel.update(character, position);
            saveCharacters();
        }
        else if (resultCode == RESULT_DELETED && position >= 0) {
            viewModel.remove(position);
            saveCharacters();
        }
    }

    @Subscribe public void onItemClicked(CharacterClickedEvent event) {
        startActivity(new Intent(MainActivity.this, SheetActivity.class).putExtra(CHARACTER, event.character));
    }

    public void onClickAddCharacter(View view) {
        startActivityForResult(new Intent(MainActivity.this, EditActivity.class), ADD);
    }
}