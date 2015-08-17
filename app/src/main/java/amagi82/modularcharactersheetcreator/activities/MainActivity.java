package amagi82.modularcharactersheetcreator.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bluelinelabs.logansquare.LoganSquare;
import com.colintmiller.simplenosql.NoSQL;
import com.colintmiller.simplenosql.NoSQLEntity;
import com.squareup.otto.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.events.CharacterAddedEvent;
import amagi82.modularcharactersheetcreator.events.CharacterChangedEvent;
import amagi82.modularcharactersheetcreator.events.CharacterClickedEvent;
import amagi82.modularcharactersheetcreator.events.CharacterDeletedEvent;
import amagi82.modularcharactersheetcreator.events.CreateNewCharacterEvent;
import amagi82.modularcharactersheetcreator.events.EditCharacterEvent;
import amagi82.modularcharactersheetcreator.events.UpNavigationEvent;
import amagi82.modularcharactersheetcreator.fragments.CharacterFragment;
import amagi82.modularcharactersheetcreator.fragments.MainFragment;
import amagi82.modularcharactersheetcreator.fragments.SheetFragment;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.games.systems.CMage;
import amagi82.modularcharactersheetcreator.models.games.systems.CMage.Faction;
import amagi82.modularcharactersheetcreator.models.games.systems.CVampire;
import amagi82.modularcharactersheetcreator.models.games.systems.CVampire.Clan;
import amagi82.modularcharactersheetcreator.models.games.systems.CVampire.Sect;
import amagi82.modularcharactersheetcreator.models.games.systems.CWerewolf;
import amagi82.modularcharactersheetcreator.models.games.systems.CWerewolf.Auspice;
import amagi82.modularcharactersheetcreator.models.games.systems.CWerewolf.Tribe;
import amagi82.modularcharactersheetcreator.templates.Template;
import amagi82.modularcharactersheetcreator.utils.Logan;

import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;


public class MainActivity extends AppCompatActivity {

    public static final String CURRENT_CHARACTER = "currentCharacter";
    public static final String CHARACTERS = "characters";
    public static final String BUCKET = "bucket";
    public static final String EDIT_MODE = "EditMode";
    public static final String CHARACTER = "Character";
    public static final int REQUEST_CODE = 50;
    private FragmentManager fm = getSupportFragmentManager();
    private List<GameCharacter> characters;
    private GameCharacter currentCharacter;
    private Logan logan = new Logan();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    @Override protected void onSaveInstanceState(Bundle outState) {
        try {
            if (currentCharacter != null) outState.putString(CURRENT_CHARACTER, LoganSquare.serialize(currentCharacter));
            outState.putString(CHARACTERS, LoganSquare.serialize(characters, GameCharacter.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onSaveInstanceState(outState);
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
        characters.add(new GameCharacter("Thomas Anstis", new CVampire(Sect.CAMARILLA, Clan.GANGREL)));
        characters.add(new GameCharacter("Tom Lytton", new CVampire(Sect.ANARCH, Clan.BRUJAH)));
        characters.add(new GameCharacter("Georgia Johnson", new CVampire(Sect.CAMARILLA, Clan.TREMERE)));
        characters.add(new GameCharacter("Augustus von Rabenholtz", new CVampire(Sect.CAMARILLA, Clan.VENTRUE)));
        characters.add(new GameCharacter("Dr. Von Natsi", new CMage(Faction.SCIONSOFETHER)));
        characters.add(new GameCharacter("Stormwalker", new CWerewolf(Tribe.GLASSWALKERS, Auspice.AHROUN)));

        for (GameCharacter character : characters) {
            Log.i(null, "Creating template for "+character.getName());
            Template template = new Template(this, character);
            Sheet sheet = template.createDefaultSheet();
            character.getSheets().add(sheet);
            Log.i(null, character.getName() + " contains " + character.getGameSystem().toString());
        }
        for (GameCharacter character : characters) saveCharacter(character);
    }

    private void saveCharacter(GameCharacter character) {
        NoSQLEntity<GameCharacter> entity = new NoSQLEntity<>(BUCKET, character.getEntityId());
        entity.setData(character);
        NoSQL.with(this).withSerializer(logan).using(GameCharacter.class).save(entity);
    }

    @Override protected void onStart() {
        super.onStart();
        BUS.getBus().register(this);
    }

    @Override protected void onStop() {
        super.onStop();
        BUS.getBus().unregister(this);
    }

    @Subscribe
    public void onCharacterAdded(CharacterAddedEvent event) {
        characters.add(event.character);
        saveCharacter(event.character);
    }

    @Subscribe
    public void onCharacterChanged(CharacterChangedEvent event) {
        for (GameCharacter character : characters) {
            if (character.getEntityId().equals(event.character.getEntityId())) {
                character = event.character;
                saveCharacter(character);
                Log.i(null, character.getName() + " has been updated");
            } else Log.i(null, "Character failed to update");
        }
    }

    @Subscribe
    public void onCharacterDeleted(CharacterDeletedEvent event) {
        for (int i = characters.size() - 1; i>= 0; i--) {
            if (characters.get(i).getEntityId().equals(event.character.getEntityId())) {
                NoSQL.with(this).using(GameCharacter.class).bucketId(BUCKET).entityId(event.character.getEntityId()).delete();
                characters.remove(characters.get(i));
                break;
            }
        }
    }

    @Subscribe
    public void onCreateNewCharacter(CreateNewCharacterEvent event) {
        startActivityForResult(new Intent(this, EditCharacterActivity.class), REQUEST_CODE);
        //fm.beginTransaction().replace(R.id.container, new CharacterFragment()).addToBackStack(null).commit();
    }

    @Subscribe
    public void onEditCharacter(EditCharacterEvent event) {
        currentCharacter = event.character;
        CharacterFragment fragment = new CharacterFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(EDIT_MODE, true);
        fragment.setArguments(bundle);
        fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }

    @Subscribe
    public void onCharacterClicked(CharacterClickedEvent event) {
        currentCharacter = event.character;
        fm.beginTransaction().replace(R.id.container, new SheetFragment()).addToBackStack(null).commit();
    }

    @Subscribe
    public void upNavigation(UpNavigationEvent event){
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}