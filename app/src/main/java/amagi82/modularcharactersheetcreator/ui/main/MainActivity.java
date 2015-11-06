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
import amagi82.modularcharactersheetcreator.databinding.ActivityMainBinding;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.characters.Sheet;
import amagi82.modularcharactersheetcreator.models.characters.Splat;
import amagi82.modularcharactersheetcreator.models.games.CMage;
import amagi82.modularcharactersheetcreator.models.games.CVampire;
import amagi82.modularcharactersheetcreator.models.games.CWerewolf;
import amagi82.modularcharactersheetcreator.models.templates.Template;
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
    ActivityMainBinding binding;
    private Gson gson;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        gson = new GsonBuilder().registerTypeAdapterFactory(new AutoParcelGsonTypeAdapterFactory()).create();
        viewModel = new MainViewModel();
        loadSavedCharacters();

        binding.setMainViewModel(viewModel);
        binding.toolbar.setLogo(R.drawable.title_onyx);
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