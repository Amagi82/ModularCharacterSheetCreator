package amagi82.modularcharactersheetcreator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.colintmiller.simplenosql.NoSQL;
import com.colintmiller.simplenosql.NoSQLEntity;
import com.colintmiller.simplenosql.RetrievalCallback;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.events.CreateCharacterEvent;
import amagi82.modularcharactersheetcreator.fragments.CharacterFragment;
import amagi82.modularcharactersheetcreator.fragments.MainFragment;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.game_systems.CMage;
import amagi82.modularcharactersheetcreator.models.game_systems.CVampire;
import amagi82.modularcharactersheetcreator.models.game_systems.CWerewolf;
import amagi82.modularcharactersheetcreator.models.modules.TextModule;
import amagi82.modularcharactersheetcreator.utils.Logan;
import amagi82.modularcharactersheetcreator.utils.Otto;


public class MainActivity extends AppCompatActivity {

    private FragmentManager fm = getSupportFragmentManager();
    private Logan logan = new Logan();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NoSQL.with(this).using(GameCharacter.class).bucketId("bucket").delete();

        //generateSampleCharacters();

        NoSQL.with(this).withSerializer(logan).using(GameCharacter.class).bucketId("bucket").retrieve(new RetrievalCallback<GameCharacter>() {
            @Override public void retrievedResults(List<NoSQLEntity<GameCharacter>> entities) {
                Log.i(null, "Found "+entities.size()+" sample characters");
                if (entities.size() == 0) {
                    Log.i(null, "generating sample characters");
                    generateSampleCharacters();
                }
            }
        });

        if (fm.getBackStackEntryCount() == 0) {
            fm.beginTransaction().add(R.id.container, new MainFragment()).commit();
        }
    }

    private void generateSampleCharacters() {
        List<GameCharacter> characters = new ArrayList<>();
        if (characters.size() == 0) {
            Log.i(null, "Creating data");

            CVampire vampire1 = new CVampire();
            vampire1.setSect(CVampire.Sect.CAMARILLA);
            vampire1.setClan(CVampire.Clan.GANGREL);
            characters.add(new GameCharacter("Thomas Anstis", vampire1));

            CVampire vampire2 = new CVampire();
            vampire2.setSect(CVampire.Sect.ANARCH);
            vampire2.setClan(CVampire.Clan.BRUJAH);
            characters.add(new GameCharacter("Tom Lytton", vampire2));

            CVampire vampire3 = new CVampire();
            vampire3.setSect(CVampire.Sect.CAMARILLA);
            vampire3.setClan(CVampire.Clan.TREMERE);
            characters.add(new GameCharacter("Georgia Johnson", vampire3));

            CVampire vampire4 = new CVampire();
            vampire4.setSect(CVampire.Sect.CAMARILLA);
            vampire4.setClan(CVampire.Clan.VENTRUE);
            characters.add(new GameCharacter("Augustus von Rabenholtz", vampire4));

            CMage mage1 = new CMage();
            mage1.setFaction(CMage.Faction.SCIONSOFETHER);
            characters.add(new GameCharacter("Dr. Von Natsi", mage1));

            CWerewolf werewolf1 = new CWerewolf();
            werewolf1.setTribe(CWerewolf.Tribe.GLASSWALKERS);
            werewolf1.setAuspice(CWerewolf.Auspice.AHROUN);
            characters.add(new GameCharacter("Stormwalker", werewolf1));

            TextModule module1 = new TextModule();
            module1.setText("Test text 1");
            TextModule module2 = new TextModule();
            module2.setText("This is another module");

            for (GameCharacter character : characters) {
                character.getModuleList().add(module1);
                character.getModuleList().add(module2);
                Log.i(null, character.getName() + " contains "+character.getGameSystem().toString());
            }
        }

        for (GameCharacter character : characters) {
            NoSQLEntity<GameCharacter> entity = new NoSQLEntity<>("bucket", character.getEntityId());
            entity.setData(character);
            NoSQL.with(this).withSerializer(logan).using(GameCharacter.class).save(entity);
        }
    }

    @Override protected void onStart() {
        super.onStart();
        Otto.BUS.getBus().register(this);
    }

    @Override protected void onStop() {
        super.onStop();
        Otto.BUS.getBus().unregister(this);
    }

    @Subscribe
    public void onCreateCharacter(CreateCharacterEvent event) {
        final Fragment fragment = new CharacterFragment();
        fm.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack("transaction")
                .commit();
    }

//    /*
//        Handle user clicks and interface methods
//     */
//    @Override
//    public void OnGameCharacterAdded(GameCharacter character) {
//        addCharacter(0, character);
//    }
//
//    @Override
//    public void OnGameCharacterUpdated(int position, GameCharacter character) {
//        updateCharacter(position, character);
//    }
//
//    @Override
//    public void OnGameCharacterDeleted(final int position) {
//        final GameCharacter gameCharacter = gameCharacterList.get(position);
//        removeCharacter(position);
//    }
//
//    @Override
//    public void onAddModule() {
//    }
//
//    @Override
//    public void onModuleClicked(ArrayList<Module> module, int position) {
//    }
//
//    @Override
//    public void onCharacterClicked(int position) {
//        CharacterSheetFragment fragment = new CharacterSheetFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("character", position);
//        fragment.setArguments(bundle);
//        attachFragment(fragment, MaterialMenuDrawable.IconState.ARROW);
//    }
//
//    @Override
//    public void onCharacterLongClicked(int position) {
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.fab) {
//            //Floating action button clicked - add new character
//            CharacterFragment fragment = new CharacterFragment();
//            backListener = fragment;
//            attachFragment(fragment, MaterialMenuDrawable.IconState.ARROW);
//        }else {
//            //Up navigation clicked
//            backListener.onBackPressed();
//            if (fm.getBackStackEntryCount() > 0) fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        backListener.onBackPressed();
//        super.onBackPressed();
//
//    }
//
//    @Override
//    public void onBackStackChanged() {
//        if (fm.getBackStackEntryCount() == 0) {
//            isHomeScreen = true;
//        }
//    }
}
