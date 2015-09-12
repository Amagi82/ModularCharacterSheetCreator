package amagi82.modularcharactersheetcreator.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.colintmiller.simplenosql.NoSQL;
import com.colintmiller.simplenosql.NoSQLEntity;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.events.CharacterAddedEvent;
import amagi82.modularcharactersheetcreator.events.CharacterUpdatedEvent;
import amagi82.modularcharactersheetcreator.events.CharacterClickedEvent;
import amagi82.modularcharactersheetcreator.events.CharacterDeletedEvent;
import amagi82.modularcharactersheetcreator.events.CreateNewCharacterEvent;
import amagi82.modularcharactersheetcreator.events.EditCharacterEvent;
import amagi82.modularcharactersheetcreator.events.UpNavigationEvent;
import amagi82.modularcharactersheetcreator.fragments.CharacterGameFragment;
import amagi82.modularcharactersheetcreator.fragments.MainFragment;
import amagi82.modularcharactersheetcreator.fragments.SheetFragment;
import amagi82.modularcharactersheetcreator.core_models.GameCharacter;
import amagi82.modularcharactersheetcreator.core_models.Sheet;
import amagi82.modularcharactersheetcreator.core_models.Splat;
import amagi82.modularcharactersheetcreator.game_models.CMage;
import amagi82.modularcharactersheetcreator.game_models.CVampire;
import amagi82.modularcharactersheetcreator.game_models.CWerewolf;
import amagi82.modularcharactersheetcreator.utils.Template;

public class MainActivity extends BaseActivity {

    public static final String BUCKET = "bucket";
    public static final String EDIT_MODE = "EditMode";
    public static final int REQUEST_CODE = 50;
    public static final int NONE = -1;
    private FragmentManager fm = getSupportFragmentManager();
    private List<GameCharacter> characters;
    private GameCharacter currentCharacter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NoSQL.with(this).using(GameCharacter.class).bucketId("bucket").delete();
//        if (savedInstanceState != null) {
//            try {
//                if(savedInstanceState.getString(CURRENT_CHARACTER) != null) {
//                    currentCharacter = LoganSquare.parse(savedInstanceState.getString(CURRENT_CHARACTER), GameCharacter.class);
//                }
//                characters = LoganSquare.parseList(savedInstanceState.getString(CHARACTERS), GameCharacter.class);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else
        loadSavedCharacters();
        generateSampleCharacters();
        currentCharacter = characters.get(0);
        if(savedInstanceState == null){
            fm.beginTransaction().replace(R.id.container, new MainFragment()).commit();
        }
    }

    public List<GameCharacter> getCharacters() {
        return characters;
    }

    public GameCharacter getCurrentCharacter() {
        return currentCharacter;
    }

    private void loadSavedCharacters() {
        characters = new ArrayList<>();
//        NoSQL.with(this).withDeserializer(logan).using(GameCharacter.class).bucketId(BUCKET).orderBy(new DataComparator<GameCharacter>() {
//            @Override public int compare(NoSQLEntity<GameCharacter> o1, NoSQLEntity<GameCharacter> o2) {
//                return o1.getData().getTimeStamp() > o2.getData().getTimeStamp() ? -1 : o1.getData().getTimeStamp() < o2.getData().getTimeStamp() ? 1 : 0;
//            }
//        }).retrieve(new RetrievalCallback<GameCharacter>() {
//            @Override public void retrievedResults(List<NoSQLEntity<GameCharacter>> entities) {
//                Log.i(null, "Found " + entities.size() + " sample characters");
//                if (entities.size() == 0) {
//                    Log.i(null, "generating sample characters");
//                    generateSampleCharacters();
//                } else for (NoSQLEntity<GameCharacter> entity : entities) characters.add(entity.getData());
//
//                fm.beginTransaction().add(R.id.container, new MainFragment()).commit();
//            }
//        });
    }

    private void generateSampleCharacters() {
        Log.i(null, "Creating data");
        characters.add(GameCharacter.create("Thomas Anstis", new CVampire(), Splat.create(R.string.gangrel, R.string.url_cwod_vampire_clan_gangrel), Splat.create(R.string.camarilla, R.string.url_cwod_vampire_sect_camarilla)));
        characters.add(GameCharacter.create("Tom Lytton", new CVampire(), Splat.create(R.string.brujah_antitribu, R.string.url_cwod_vampire_antitribu_brujah), Splat.create(R.string.anarchs, R.string.url_cwod_vampire_sect_anarchs)));
        characters.add(GameCharacter.create("Georgia Johnson", new CVampire(), Splat.create(R.string.tremere, R.string.url_cwod_vampire_clan_tremere), Splat.create(R.string.camarilla, R.string.url_cwod_vampire_sect_camarilla)));
        characters.add(GameCharacter.create("Augustus von Rabenholtz", new CVampire(), Splat.create(R.string.ventrue, R.string.url_cwod_vampire_clan_ventrue), Splat.create(R.string.camarilla, R.string.url_cwod_vampire_sect_camarilla)));
        characters.add(GameCharacter.create("Dr. Von Natsi", new CMage(), Splat.create(R.string.traditions, R.string.url_cwod_mage_faction_traditions), Splat.create(R.string.scions_of_ether, R.string.url_cwod_mage_tradition_scions_of_ether)));
        characters.add(GameCharacter.create("Stormwalker", new CWerewolf(), Splat.create(R.string.glass_walkers, R.string.url_cwod_werewolf_tribe_glass_walkers), Splat.create(R.string.ahroun, R.string.url_cwod_werewolf_auspice_ahroun)));

        for (GameCharacter character : characters) {
            Log.i(null, "Creating template for "+character.name());
            Template template = new Template(this, character);
            Sheet sheet = template.createDefaultSheet();
            List<Sheet> sheets = new ArrayList<>();
            sheets.add(sheet);
            character = character.toBuilder().sheets(sheets).build();
            Log.i(null, character.name() + " contains " + character.getGameSystem().toString());
        }
        for (GameCharacter character : characters) saveCharacter(character);
    }

    private void saveCharacter(GameCharacter character) {
        NoSQLEntity<GameCharacter> entity = new NoSQLEntity<>(BUCKET, character.entityId());
        entity.setData(character);
        NoSQL.with(this).using(GameCharacter.class).save(entity);
    }

    @Subscribe public void onCharacterAdded(CharacterAddedEvent event) {
        characters.add(event.character);
        saveCharacter(event.character);
    }

    @Subscribe public void onCharacterChanged(CharacterUpdatedEvent event) {
        for (GameCharacter character : characters) {
            if (character.entityId().equals(event.character.entityId())) {
                character = event.character;
                saveCharacter(character);
                Log.i(null, character.name() + " has been updated");
            } else Log.i(null, "Character failed to update");
        }
    }

    @Subscribe public void onCharacterDeleted(CharacterDeletedEvent event) {
        for (int i = characters.size() - 1; i>= 0; i--) {
            if (characters.get(i).entityId().equals(event.character.entityId())) {
                NoSQL.with(this).using(GameCharacter.class).bucketId(BUCKET).entityId(event.character.entityId()).delete();
                characters.remove(characters.get(i));
                break;
            }
        }
    }

    @Subscribe public void onCreateNewCharacter(CreateNewCharacterEvent event) {
        startActivityForResult(new Intent(this, EditCharacterActivity.class), REQUEST_CODE);
        //fm.beginTransaction().replace(R.id.container, new CharacterFragment()).addToBackStack(null).commit();
    }

    @Subscribe public void onEditCharacter(EditCharacterEvent event) {
        currentCharacter = event.character;
        CharacterGameFragment fragment = new CharacterGameFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(EDIT_MODE, true);
        fragment.setArguments(bundle);
        fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }

    @Subscribe public void onCharacterClicked(CharacterClickedEvent event) {
        currentCharacter = event.character;
        fm.beginTransaction().replace(R.id.container, new SheetFragment()).addToBackStack(null).commit();
    }

    @Subscribe public void upNavigation(UpNavigationEvent event){
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
