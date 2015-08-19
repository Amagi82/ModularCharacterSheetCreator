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
import amagi82.modularcharactersheetcreator.adapters.CharacterPagerAdapter;
import amagi82.modularcharactersheetcreator.events.LeftAxisEvent;
import amagi82.modularcharactersheetcreator.events.RightAxisEvent;
import amagi82.modularcharactersheetcreator.events.TileGameClickedEvent;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import butterknife.Bind;
import butterknife.ButterKnife;

import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;

public class EditCharacterActivity extends AppCompatActivity {

    public static final String LEFT = "Left";
    @Bind(R.id.coordinatorLayout) CoordinatorLayout coordinatorLayout;
    @Bind(R.id.appbar) AppBarLayout appbar;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.imageBackdrop) ImageView imageBackdrop;
    @Bind(R.id.viewpager) ViewPager viewPager;
    private CharacterPagerAdapter pagerAdapter;
    private FragmentManager fm = getSupportFragmentManager();
    private GameCharacter character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_character);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Test for savedInstanceState and current progress
        character = new GameCharacter();
        pagerAdapter = new CharacterPagerAdapter(fm);
        viewPager.setAdapter(pagerAdapter);

        //if (savedInstanceState == null) fm.beginTransaction().replace(R.id.container, new CharacterGameFragment()).commit();
    }

    public GameCharacter getGameCharacter(){
        return character;
    }

    @Subscribe
    public void onGameSystemSelected(TileGameClickedEvent event) {
        character.setGameTitle(event.system.getGameTitle());
        Glide.with(this).load(getString(event.system.getSplashUrl())).into(imageBackdrop);
        pagerAdapter.next();
        viewPager.setCurrentItem(1);
    }

    @Subscribe
    public void onLeftAxisChosen(LeftAxisEvent event) {
        character.setLeft(event.splat);
        pagerAdapter.next();
        viewPager.setCurrentItem(2);
    }

    @Subscribe
    public void onRightAxisChosen(RightAxisEvent event) {
        character.setRight(event.splat);
        pagerAdapter.next();
        viewPager.setCurrentItem(3);
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
