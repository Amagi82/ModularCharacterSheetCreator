package amagi82.modularcharactersheetcreator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.squareup.otto.Subscribe;

import amagi82.modularcharactersheetcreator.events.CreateCharacterEvent;
import amagi82.modularcharactersheetcreator.fragments.CreateCharacterFragment;
import amagi82.modularcharactersheetcreator.fragments.MainFragment;
import amagi82.modularcharactersheetcreator.utils.Otto;


public class MainActivity extends AppCompatActivity {

    private FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NoSQL.with(this).using(GameCharacter.class).bucketId("bucket").delete();

//        List<GameCharacter> characters = new ArrayList<>();
//        if (characters.size() == 0) {
//            Log.i(null, "Creating data");
//            List<GameCharacter> sampleData = new ArrayList<>();
//            sampleData.add(new GameCharacter("Thomas Anstis", "Vampire", "", "Gangrel"));
//            sampleData.add(new GameCharacter("Tom Lytton", "Vampire", "", "Brujah"));
//            sampleData.add(new GameCharacter("Georgia Johnson", "Vampire", "", "Tremere"));
//            sampleData.add(new GameCharacter("Augustus von Rabenholtz", "Vampire", "", "Ventrue"));
//            sampleData.add(new GameCharacter("Dr. Von Natsi", "Mage", "", "Etherite"));
//            sampleData.add(new GameCharacter("Revin", "Pathfinder", "Tiefling", "Paladin of Glasya"));
//            sampleData.add(new GameCharacter("Sven", "Shadowrun", "Human", "Shaman of Loki"));
//            sampleData.add(new GameCharacter("Fir'keelie Selenya'Tala", "Earthdawn", "Windling", "Windmaster"));
//            sampleData.add(new GameCharacter("Scarlett Lee", "Dungeon World", "Human", "Fighter"));
//            sampleData.add(new GameCharacter("Raven", "Shadowrun", "Human", "Shaman"));
//            characters = sampleData;
//
//            TextModule module1 = new TextModule();
//            module1.setText("Test text 1");
//            TextModule module2 = new TextModule();
//            module2.setText("This is another module");
//
//            for (GameCharacter character : characters) {
//                character.getModuleList().add(module1);
//                character.getModuleList().add(module2);
//            }
//        }
//
//        for (GameCharacter character : characters) {
//            NoSQLEntity<GameCharacter> entity = new NoSQLEntity<>("bucket", character.getEntityId());
//            entity.setData(character);
//            NoSQL.with(this).using(GameCharacter.class).save(entity);
//        }

        if (fm.getBackStackEntryCount() == 0) {
            Fragment fragment = new MainFragment();
            fm.beginTransaction().add(R.id.container, fragment).commit();
        }
    }

    @Override protected void onStart() {
        super.onStart();
        Otto.BUS.getBus().register(this);
    }

    @Override protected void onStop() {
        super.onStop();
        Otto.BUS.getBus().unregister(this);
        //SavedData.CHARACTERS.saveGameCharacters("Characters");
    }

    @Subscribe
    public void onCreateCharacter(CreateCharacterEvent event) {
        final Fragment fragment = new CreateCharacterFragment();
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
//            CreateCharacterFragment fragment = new CreateCharacterFragment();
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
