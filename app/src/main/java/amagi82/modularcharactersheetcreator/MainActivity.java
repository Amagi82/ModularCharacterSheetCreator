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

        SavedData.CHARACTERS.getCharacters(this);

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
        SavedData.CHARACTERS.saveGameCharacters("Characters");
    }

    @Subscribe
    public void onCreateCharacter(CreateCharacterEvent event) {
        final Fragment fragment = new CreateCharacterFragment();
        fm.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack("transaction")
                .commit();
    }

//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        //Save characters to default file
//        saveGameCharacters("Characters");
//    }
//
//

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
