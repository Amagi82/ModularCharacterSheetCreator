package amagi82.modularcharactersheetcreator.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.otto.Subscribe;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.events.LeftAxisEvent;
import amagi82.modularcharactersheetcreator.events.RightAxisEvent;
import amagi82.modularcharactersheetcreator.events.TileGameClickedEvent;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import butterknife.Bind;
import butterknife.ButterKnife;

import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;

public class EditCharacterActivity extends AppCompatActivity {

    public static final String LEFT = "Left";
    private static final String FRAG_LEFT = "frag_left";
    private static final String FRAG_RIGHT = "frag_right";

    @Bind(R.id.coordinatorLayout) CoordinatorLayout coordinatorLayout;
    @Bind(R.id.appbar) AppBarLayout appbar;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.imageBackdrop) ImageView imageBackdrop;
    @Bind(R.id.viewpager) ViewPager viewPager;
    private FragmentManager fm = getSupportFragmentManager();
    private GameCharacter character;

    private enum Clear {ALL, LEFTRIGHT, RIGHT}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_character);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //if (savedInstanceState == null) fm.beginTransaction().replace(R.id.container, new CharacterGameFragment()).commit();

        //Check if we're editing a character or creating a new one
//        boolean isEditMode = getIntent().getStringExtra(CHARACTER) != null;
//        if (isEditMode) {
//            Log.i(null, "edit mode");
//            try {
//                character = LoganSquare.parse(getIntent().getStringExtra(CHARACTER), GameCharacter.class);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            character = new GameCharacter();
//        }
    }

    public GameCharacter getGameCharacter(){
        return character;
    }

    private void clearSelections(Clear which) {
        //appbar.setMinimumHeight(0);
        switch (which) {
            case ALL:
                //fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;
            case LEFTRIGHT:
                //fm.popBackStack(FRAG_LEFT, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;
            case RIGHT:
                //fm.popBackStack(FRAG_RIGHT, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;
        }
    }

    @Subscribe
    public void onGameSystemSelected(TileGameClickedEvent event) {
        character.setGameTitle(event.system.getGameTitle());
        Glide.with(this).load(event.system.getSplashUrl()).into(imageBackdrop);
        //chooseLeftCategory();
    }

    @Subscribe
    public void onLeftAxisChosen(LeftAxisEvent event) {
        character.setLeft(event.splat);
        //chooseRightCategory();
    }

    @Subscribe
    public void onRightAxisChosen(RightAxisEvent event) {
        character.setRight(event.splat);
        //finishCharacter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_character, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_delete:
//                new AlertDialog.Builder(this).setMessage(R.string.delete_character_query).setNegativeButton(R.string.cancel, null)
//                        .setPositiveButton(R.string.action_delete, new DialogInterface.OnClickListener() {
//                            @Override public void onClick(DialogInterface dialog, int which) {
//                                finish();  //Test??
//                            }
//                        }).show();
//                return true;
//            case R.id.action_save_template:
//                return true;
            case R.id.action_discard:
                finish();
                return true;
        }
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        try {
//            if (currentCharacter != null) outState.putString(CURRENT_CHARACTER, LoganSquare.serialize(currentCharacter));
//            outState.putString(CHARACTERS, LoganSquare.serialize(characters, GameCharacter.class));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        BUS.getBus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        BUS.getBus().unregister(this);
    }


}
