package amagi82.modularcharactersheetcreator;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Display;
import android.view.animation.PathInterpolator;

import com.squareup.otto.Subscribe;

import amagi82.modularcharactersheetcreator.events.CreateCharacterEvent;
import amagi82.modularcharactersheetcreator.fragments.CreateCharacterFragment;
import amagi82.modularcharactersheetcreator.fragments.MainFragment;
import amagi82.modularcharactersheetcreator.utils.BusProvider;


public class MainActivity extends AppCompatActivity {

    private FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(fm.getBackStackEntryCount() == 0){
            Fragment fragment = new MainFragment();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Add transition info to new fragment
            //fragment.setSharedElementReturnTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.fade));
            fragment.setExitTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.fade));
        }
            fm.beginTransaction().add(R.id.container, fragment).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register ourselves so that we can provide the initial value.
        BusProvider.getBus().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Always unregister when an object no longer should be on the bus.
        BusProvider.getBus().unregister(this);
    }

    @Subscribe
    public void onCreateCharacter(CreateCharacterEvent event) {
        final Fragment fragment = new CreateCharacterFragment();
        final FloatingActionButton fab = event.fab;

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = 2*size.x / fab.getWidth();

        Log.i(null, "width == " + width);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            fragment.setEnterTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.fade));
//        }
        int moveX =fab.getWidth()*2;
        int moveY =fab.getHeight()*2;

        fab.animate()
                .translationX(moveX)
                .translationY(moveY)
                .setInterpolator(new PathInterpolator(1, 0))
                .setDuration(800)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        fab.setImageResource(0);
                        fab.animate().scaleX(width).scaleY(width).setDuration(2000).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                Log.i(null, "width after == "+fab.getWidth());
                                fm.beginTransaction()
                                        .replace(R.id.container, fragment)
                                        .addToBackStack("transaction")
                                        .commit();
                            }
                        });
                    }
                });
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
