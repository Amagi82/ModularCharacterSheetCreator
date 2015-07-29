package amagi82.modularcharactersheetcreator.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.bluelinelabs.logansquare.LoganSquare;
import com.colintmiller.simplenosql.NoSQL;
import com.colintmiller.simplenosql.NoSQLEntity;
import com.squareup.otto.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.MainAdapter;
import amagi82.modularcharactersheetcreator.events.CharacterAddedEvent;
import amagi82.modularcharactersheetcreator.events.CharacterChangedEvent;
import amagi82.modularcharactersheetcreator.events.CharacterClickedEvent;
import amagi82.modularcharactersheetcreator.events.CharacterDeletedEvent;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.game_systems.CMage;
import amagi82.modularcharactersheetcreator.models.game_systems.CVampire;
import amagi82.modularcharactersheetcreator.models.game_systems.CWerewolf;
import amagi82.modularcharactersheetcreator.templates.Template;
import amagi82.modularcharactersheetcreator.utils.Logan;
import amagi82.modularcharactersheetcreator.utils.Otto;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static amagi82.modularcharactersheetcreator.App.BUCKET;
import static amagi82.modularcharactersheetcreator.App.CHARACTER;
import static amagi82.modularcharactersheetcreator.App.CHARACTERS;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.fab) FloatingActionButton fab;
    private FragmentManager fm = getSupportFragmentManager();
    private List<GameCharacter> characters;
    private GameCharacter currentCharacter;
    private Logan logan = new Logan();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        ButterKnife.bind(this);

        toolbar.setLogo(R.drawable.title_onyx);

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

        recyclerView.setHasFixedSize(true); //Improves performance if changes in content never change layout size
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainAdapter adapter = new MainAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.addAll(characters);
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        try {
            outState.putString(CHARACTERS, LoganSquare.serialize(characters, GameCharacter.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onSaveInstanceState(outState);
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
        startActivity(new Intent(this, CharacterActivity.class));
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
        characters.add(new GameCharacter("Thomas Anstis", new CVampire(CVampire.Sect.CAMARILLA, CVampire.Clan.GANGREL)));
        characters.add(new GameCharacter("Tom Lytton", new CVampire(CVampire.Sect.ANARCH, CVampire.Clan.BRUJAH)));
        characters.add(new GameCharacter("Georgia Johnson", new CVampire(CVampire.Sect.CAMARILLA, CVampire.Clan.TREMERE)));
        characters.add(new GameCharacter("Augustus von Rabenholtz", new CVampire(CVampire.Sect.CAMARILLA, CVampire.Clan.VENTRUE)));
        characters.add(new GameCharacter("Dr. Von Natsi", new CMage(CMage.Faction.SCIONSOFETHER)));
        characters.add(new GameCharacter("Stormwalker", new CWerewolf(CWerewolf.Tribe.GLASSWALKERS, CWerewolf.Auspice.AHROUN)));

        for (GameCharacter character : characters) {
            Log.i(null, "Creating template for "+character.getName());
            Template template = new Template(this, character);
            Sheet sheet = template.createDefaultSheet();
            character.getSheets().add(new Sheet(getString(R.string.profile)));
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
        Otto.BUS.getBus().register(this);
    }

    @Override protected void onStop() {
        super.onStop();
        Otto.BUS.getBus().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
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
    public void onCharacterClicked(CharacterClickedEvent event) {
        Intent intent = new Intent(this, SheetActivity.class);
        try {
            intent.putExtra(CHARACTER, LoganSquare.serialize(event.character));
            startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
