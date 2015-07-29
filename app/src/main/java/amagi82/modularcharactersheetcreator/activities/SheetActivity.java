package amagi82.modularcharactersheetcreator.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.IOException;

import amagi82.modularcharactersheetcreator.App;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.ViewPagerAdapter;
import amagi82.modularcharactersheetcreator.events.ModuleAddedEvent;
import amagi82.modularcharactersheetcreator.models.Choice;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.modules.HeaderModule;
import amagi82.modularcharactersheetcreator.utils.Otto;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static amagi82.modularcharactersheetcreator.App.CHARACTER;

public class SheetActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.viewpager) ViewPager viewPager;
    @Bind(R.id.fab) FloatingActionButton fab;
    private GameCharacter character;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sheet);
        ButterKnife.bind(this);

        try {
            character = LoganSquare.parse(getIntent().getStringExtra(CHARACTER), GameCharacter.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        toolbar.setTitle(character.getName());
        Log.i(null, character.getName() + " loaded in SheetActivity");

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), character.getSheets());
        viewPager.setAdapter(adapter);
        tabLayout.setTabsFromPagerAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.fab)
    public void onClickFab(){
        Log.i(null, "adding new module");
        HeaderModule module = new HeaderModule();
        module.setTitle("New Module");
        Otto.BUS.getBus().post(new ModuleAddedEvent(module));
    }

    private String getUrl(Choice choice){
        return choice.getUrl() == App.NONE? getString(R.string.url_default) : getString(choice.getBaseUrl()) + getString(choice.getUrl());
    }

    public GameCharacter getCharacter(){
        return character;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sheet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_edit:
                //Update any character info before editing.
                //TODO: open CharacterActivity and pass in character
                return true;
            case R.id.action_tab_add:
                Sheet sheet = new Sheet();
                sheet.setTitle("New Sheet");
                adapter.addFragment(sheet);
                adapter.notifyDataSetChanged();
                tabLayout.setTabsFromPagerAdapter(adapter);
                return true;
            case R.id.action_tab_delete:
                adapter.deleteFragment(viewPager.getCurrentItem());
                adapter.notifyDataSetChanged();
                tabLayout.setTabsFromPagerAdapter(adapter);
                toolbar.getMenu().findItem(R.id.action_tab_delete).setVisible(tabLayout.getTabCount() != 0);
                return true;
            case R.id.action_tab_move_left:
                return true;
            case R.id.action_tab_move_right:
                return true;
            case R.id.action_tab_rename:
                return true;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}