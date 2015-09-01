package amagi82.modularcharactersheetcreator.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.squareup.otto.Subscribe;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.CharacterPagerAdapter;
import amagi82.modularcharactersheetcreator.callbacks.PageListener;
import amagi82.modularcharactersheetcreator.events.CharacterUpdatedEvent;
import amagi82.modularcharactersheetcreator.events.LeftAxisEvent;
import amagi82.modularcharactersheetcreator.events.PageChangedEvent;
import amagi82.modularcharactersheetcreator.events.RightAxisEvent;
import amagi82.modularcharactersheetcreator.events.TileGameClickedEvent;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.games.Splat;
import amagi82.modularcharactersheetcreator.widgets.ImageBackdrop;
import amagi82.modularcharactersheetcreator.widgets.NoSwipeViewPager;
import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.State;

import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;

public class EditCharacterActivity extends BaseActivity {

    public static final String LEFT = "Left";
    @Bind(R.id.coordinatorLayout) CoordinatorLayout coordinatorLayout;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.appbar) AppBarLayout appbar;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.imageBackdrop) ImageBackdrop imageBackdrop;
    @Bind(R.id.viewpager) NoSwipeViewPager viewPager;
    private FragmentManager fm = getSupportFragmentManager();
    @State GameCharacter character;
    @State int backstack;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_character);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (character == null) character = GameCharacter.builder().build();
        else if (character.getGameSystem() != null) Glide.with(this).load(getString(character.getGameSystem().getSplashUrl())).into(imageBackdrop);

        viewPager.addOnPageChangeListener(new PageListener() {
            @Override public void onPageSelected(int position) {
                BUS.getBus().post(new PageChangedEvent(position));
            }
        });
        viewPager.setAdapter(new CharacterPagerAdapter(fm));
        viewPager.setCurrentItem(character.getProgress());
    }

    public GameCharacter getGameCharacter() {
        return character;
    }

    @Subscribe public void gameSelected(TileGameClickedEvent event) {
        character = character.toBuilder().gameTitle(event.system.getGameTitle()).build();
        Glide.with(this).load(getString(event.system.getSplashUrl())).into(imageBackdrop);
        next();
    }

    @Subscribe public void leftAxisSelected(LeftAxisEvent event) {
        character = character.toBuilder().left(event.splat).build();
        next();
    }

    @Subscribe public void rightAxisSelected(RightAxisEvent event) {
        Splat updatedLeft = character.getGameSystem().checkLeft() ? character.getGameSystem().updateLeft(character.left(), event.splat) : character.left();
        character = character.toBuilder().left(updatedLeft).right(event.splat).build();
        appbar.setExpanded(true);
        next();
    }

    @Subscribe public void characterUpdated(CharacterUpdatedEvent event) {
        character = event.character;
    }

    private void next() {
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
        if (backstack > 0) {
            character.removeProgress(viewPager.getCurrentItem());
            viewPager.previousPage();
            backstack--;
            if (viewPager.getCurrentItem() == 0) imageBackdrop.setImageResource(0);
        } else super.onBackPressed();
    }
}
