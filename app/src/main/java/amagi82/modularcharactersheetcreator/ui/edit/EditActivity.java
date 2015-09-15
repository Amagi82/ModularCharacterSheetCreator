package amagi82.modularcharactersheetcreator.ui.edit;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.otto.Subscribe;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ActivityEditCharacterBinding;
import amagi82.modularcharactersheetcreator.ui.xtras.events.CharacterUpdatedEvent;
import amagi82.modularcharactersheetcreator.ui.xtras.events.LeftAxisEvent;
import amagi82.modularcharactersheetcreator.ui.xtras.events.PageChangedEvent;
import amagi82.modularcharactersheetcreator.ui.xtras.events.RightAxisEvent;
import amagi82.modularcharactersheetcreator.ui.xtras.events.TileGameClickedEvent;
import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.entities.characters.Splat;
import amagi82.modularcharactersheetcreator.ui.base.BaseActivity;
import amagi82.modularcharactersheetcreator.ui.xtras.widgets.NoSwipeViewPager;
import icepick.State;

import static amagi82.modularcharactersheetcreator.ui.xtras.utils.Otto.BUS;

public class EditActivity extends BaseActivity {

    public static final String LEFT = "Left";
    private ActivityEditCharacterBinding binding;
    private NoSwipeViewPager viewPager;
    private FragmentManager fm = getSupportFragmentManager();
    private EditViewModel observer;
    @State GameCharacter character;
    @State int backstack;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit);
        viewPager = binding.viewpager;

        setSupportActionBar(binding.toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (character == null) character = GameCharacter.builder().build();
        if (observer == null) observer = new EditViewModel(character);
        binding.setObserver(observer);

        viewPager.addOnPageChangeListener(new PageListener() {
            @Override public void onPageSelected(int position) {
                BUS.getBus().post(new PageChangedEvent(position));
            }
        });
        viewPager.setAdapter(new EditPagerAdapter(fm));
        viewPager.setCurrentItem(character.getProgress());
    }

    public GameCharacter getGameCharacter() {
        return character;
    }

    @Subscribe public void gameSelected(TileGameClickedEvent event) {
        character = character.toBuilder().gameTitle(event.system.getGameTitle()).build();
        observer.setCharacter(character);
        next();
    }

    @Subscribe public void leftAxisSelected(LeftAxisEvent event) {
        character = character.toBuilder().left(event.splat).build();
        observer.setCharacter(character);
        next();
    }

    @Subscribe public void rightAxisSelected(RightAxisEvent event) {
        Splat updatedLeft = character.getGameSystem().checkLeft() ? character.getGameSystem().updateLeft(character.left(), event.splat) : character.left();
        character = character.toBuilder().left(updatedLeft).right(event.splat).build();
        observer.setCharacter(character);
        binding.appbar.setExpanded(true);
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
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_discard:
                finish();
                return true;
        }
        return false;
    }

    @Override public void onBackPressed() {
        if (backstack > 0) {
            character = character.removeProgress(viewPager.getCurrentItem());
            observer.setCharacter(character);
            viewPager.previousPage();
            backstack--;
        } else super.onBackPressed();
    }
}
