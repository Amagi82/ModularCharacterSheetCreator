package amagi82.modularcharactersheetcreator.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bluelinelabs.logansquare.LoganSquare;
import com.bumptech.glide.Glide;
import com.squareup.otto.Subscribe;

import java.io.IOException;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.CharacterPagerAdapter;
import amagi82.modularcharactersheetcreator.events.LeftAxisEvent;
import amagi82.modularcharactersheetcreator.events.PageChangedEvent;
import amagi82.modularcharactersheetcreator.events.RightAxisEvent;
import amagi82.modularcharactersheetcreator.events.TileGameClickedEvent;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.utils.ScreenSize;
import amagi82.modularcharactersheetcreator.widgets.NoSwipeViewPager;
import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.State;

import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;

public class EditCharacterActivity extends BaseActivity {

    public static final String LEFT = "Left";
    private static final String CHARACTER = "Character";
    private static final String BACKSTACK = "Backstack";
    @Bind(R.id.coordinatorLayout) CoordinatorLayout coordinatorLayout;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.appbar) AppBarLayout appbar;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.imageBackdrop) ImageView imageBackdrop;
    @Bind(R.id.viewpager) NoSwipeViewPager viewPager;
    private FragmentManager fm = getSupportFragmentManager();
    @State private GameCharacter character;
    @State private int backstack;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_character);
        ButterKnife.bind(this);

        imageBackdrop.getLayoutParams().height = new ScreenSize(this).getWidth() * 308 / 610;
        imageBackdrop.requestLayout();

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CharacterPagerAdapter adapter;
        if (savedInstanceState == null) {
            Log.i(null, "savedInstanceState is null");
            if(getIntent().getStringExtra(CHARACTER) != null) try {
                Log.i(null, "Character from intent found");
                character = LoganSquare.parse(getIntent().getStringExtra(CHARACTER), GameCharacter.class);
            } catch (IOException e) {
                e.printStackTrace();
                Log.i(null, "failed to parse character from intent");
            }
            if(character == null) character = new GameCharacter();
        } else{
            Log.i(null, "savedInstanceState is not null");
            backstack = savedInstanceState.getInt(BACKSTACK);
            try {
                character = LoganSquare.parse(savedInstanceState.getString(CHARACTER), GameCharacter.class);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(null, "Failed to recover character. Creating new one");
                character = new GameCharacter();
            }
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override public void onPageSelected(int position) {
                BUS.getBus().post(new PageChangedEvent(position));
            }

            @Override public void onPageScrollStateChanged(int state) {
            }
        });
        viewPager.setAdapter(new CharacterPagerAdapter(fm));
        viewPager.setCurrentItem(character.getProgress());
    }

    public GameCharacter getGameCharacter(){
        return character;
    }

    @Subscribe public void onGameSystemSelected(TileGameClickedEvent event) {
        character.setGameTitle(event.system.getGameTitle());
        Glide.with(this).load(getString(event.system.getSplashUrl())).into(imageBackdrop);
        Log.i(null, "imageBackdrop is " + imageBackdrop.getMeasuredWidth() + " wide by " + imageBackdrop.getMeasuredHeight() + " tall");
        next();
    }

    @Subscribe public void onLeftAxisChosen(LeftAxisEvent event) {
        character.setLeft(event.splat);
        next();
    }

    @Subscribe public void onRightAxisChosen(RightAxisEvent event) {
        character.setRight(event.splat);
        if(character.getGameSystem().checkLeft()) character.setLeft(character.getGameSystem().updateLeft(character.getLeft(), character.getRight()));
        appbar.setExpanded(true);
        next();
    }

    private void next(){
        viewPager.nextPage();
        backstack++;
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_character, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
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

    @Override public void onBackPressed() {
        if(backstack > 0){
            character.removeProgress(viewPager.getCurrentItem());
            viewPager.previousPage();
            backstack--;
            if(viewPager.getCurrentItem() == 0) imageBackdrop.setImageResource(0);
        }else super.onBackPressed();
    }
}
