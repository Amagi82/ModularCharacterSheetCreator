package amagi82.modularcharactersheetcreator.fragments;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.activities.MainActivity;
import amagi82.modularcharactersheetcreator.adapters.SheetPagerAdapter;
import amagi82.modularcharactersheetcreator.events.EditCharacterEvent;
import amagi82.modularcharactersheetcreator.events.ModuleAddedEvent;
import amagi82.modularcharactersheetcreator.events.UpNavigationEvent;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.modules.HeaderModule;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static amagi82.modularcharactersheetcreator.activities.MainActivity.NONE;
import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;

public class SheetFragment extends Fragment implements Toolbar.OnMenuItemClickListener, View.OnClickListener{

    @Bind(R.id.coordinatorLayout) CoordinatorLayout coordinatorLayout;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.imagePortrait) ImageView imagePortrait;
    @Bind(R.id.iconLeft) ImageView iconLeft;
    @Bind(R.id.tvIconLeft) TextView tvIconLeft;
    @Bind(R.id.iconRight) ImageView iconRight;
    @Bind(R.id.tvIconRight) TextView tvIconRight;
    @Bind(R.id.scrim_top) View scrimTop;
    @Bind(R.id.scrim_bottom) View scrimBottom;
    @Bind(R.id.tabLayout) TabLayout tabLayout;
    @Bind(R.id.viewpager) ViewPager viewPager;
    @Bind(R.id.fab) FloatingActionButton fab;
    private GameCharacter character;
    private SheetPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sheet, container, false);
        ButterKnife.bind(this, rootView);
        character = ((MainActivity) getActivity()).getCurrentCharacter();
        setColors();
        collapsingToolbar.setTitle(character.name());

//        if(character.getPortraitUri() != null) {
//            Glide.with(this).load(character.getPortraitUri()).centerCrop().into(imagePortrait);
//            scrimTop.setVisibility(View.VISIBLE);
//            scrimBottom.setVisibility(View.VISIBLE);
//        }
//        Glide.with(this).load(url(character.getLeft())).centerCrop().into(iconLeft);
//        tvIconLeft.setText(character.getLeft().title());
//        if(character.getGameSystem().getOnyx().hasRight()) {
//            tvIconRight.setText(character.getRight().title());
//            Glide.with(this).load(url(character.getRight())).centerCrop().into(iconRight);
//        }

        adapter = new SheetPagerAdapter(getFragmentManager(), character.sheets());
        viewPager.setAdapter(adapter);
        tabLayout.setTabsFromPagerAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                toolbar.getMenu().findItem(R.id.action_tab_move_left).setVisible(tab.getPosition() != 0);
                toolbar.getMenu().findItem(R.id.action_tab_move_right).setVisible(tab.getPosition() != tabLayout.getTabCount() - 1);
            }

            @Override public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(this);
        toolbar.inflateMenu(R.menu.menu_sheet);
        toolbar.setOnMenuItemClickListener(this);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewCompat.requestApplyInsets(coordinatorLayout);
    }

    private void setColors() {
        int shadow = getResources().getColor(R.color.black);
        tvIconLeft.setShadowLayer(3, 0, 0, shadow);
        tvIconRight.setShadowLayer(3, 0, 0, shadow);
        int colorText = character.colorText();
        int colorTextDim = character.colorTextDim();
        int colorBackground = character.colorBackground();
        if(colorBackground != NONE) tabLayout.setBackgroundColor(colorBackground);
        if(colorText != NONE && colorTextDim != NONE) tabLayout.setTabTextColors(colorTextDim, colorText);
    }

    @OnClick(R.id.fab)
    public void onClickFab(){
        Log.i(null, "adding new module");
        HeaderModule module = new HeaderModule();
        module.setTitle("New Module");
        BUS.getBus().post(new ModuleAddedEvent(module));
    }

//    private String url(Choice choice){
//        return new Icon(getResources(), choice).url();
//    }

    public GameCharacter getCharacter(){
        return character;
    }

    //Up navigation
    @Override public void onClick(View v) {
        BUS.getBus().post(new UpNavigationEvent());
    }

    @Override public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_edit:
                //Update any character info before editing.
                BUS.getBus().post(new EditCharacterEvent(character));
                return true;
            case R.id.action_tab_add:
                adapter.addFragment(Sheet.create("New Sheet", new ArrayList<Module>()));
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