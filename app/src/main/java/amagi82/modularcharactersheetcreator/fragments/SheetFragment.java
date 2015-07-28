package amagi82.modularcharactersheetcreator.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import amagi82.modularcharactersheetcreator.App;
import amagi82.modularcharactersheetcreator.MainActivity;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.ViewPagerAdapter;
import amagi82.modularcharactersheetcreator.events.EditCharacterEvent;
import amagi82.modularcharactersheetcreator.events.ModuleAddedEvent;
import amagi82.modularcharactersheetcreator.models.Choice;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.modules.HeaderModule;
import amagi82.modularcharactersheetcreator.utils.Otto;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SheetFragment extends Fragment implements Toolbar.OnMenuItemClickListener, View.OnClickListener{

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.viewpager) ViewPager viewPager;
    @Bind(R.id.fab) FloatingActionButton fab;
    private GameCharacter character;
    private ViewPagerAdapter adapter;
    private FragmentManager fm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sheet, container, false);
        ButterKnife.bind(this, rootView);
        fm = getFragmentManager();
        character = ((MainActivity) getActivity()).getCurrentCharacter();
        toolbar.setTitle(character.getName());

        adapter = new ViewPagerAdapter(fm, character.getSheets());
        viewPager.setAdapter(adapter);
        tabLayout.setTabsFromPagerAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(this);
        toolbar.inflateMenu(R.menu.menu_sheet);
        toolbar.setOnMenuItemClickListener(this);

        return rootView;
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

    //Up navigation
    @Override public void onClick(View v) {
        fm.popBackStack();
    }

    @Override public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_edit:
                //Update any character info before editing.
                Otto.BUS.getBus().post(new EditCharacterEvent(character));
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
}