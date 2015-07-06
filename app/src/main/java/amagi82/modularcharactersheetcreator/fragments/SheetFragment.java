package amagi82.modularcharactersheetcreator.fragments;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.IOException;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.ViewPagerAdapter;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.network.VolleySingleton;
import amagi82.modularcharactersheetcreator.widgets.AnimatedNetworkImageView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SheetFragment extends Fragment implements Toolbar.OnMenuItemClickListener, View.OnClickListener{

    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.imagePortrait) AnimatedNetworkImageView imagePortrait;
    @Bind(R.id.iconLeft) AnimatedNetworkImageView iconLeft;
    @Bind(R.id.tvIconLeft) TextView tvIconLeft;
    @Bind(R.id.iconRight) AnimatedNetworkImageView iconRight;
    @Bind(R.id.tvIconRight) TextView tvIconRight;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.viewpager) ViewPager viewPager;
    @Bind(R.id.fab) FloatingActionButton fab;
    private GameCharacter gameCharacter;
    private ViewPagerAdapter adapter;
    private FragmentManager fm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sheet, container, false);
        ButterKnife.bind(this, rootView);

        fm = getFragmentManager();

        try {
            gameCharacter = LoganSquare.parse(getArguments().getString("Character"), GameCharacter.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (gameCharacter == null) fm.popBackStack();

        collapsingToolbar.setTitle(gameCharacter.getName());
        iconLeft.setImageUrl(getString(gameCharacter.getLeft().getBaseUrl())+getString(gameCharacter.getLeft().getUrl()), VolleySingleton.INSTANCE.getImageLoader());
        tvIconLeft.setText(gameCharacter.getLeft().getTitle());

        if(gameCharacter.getGameSystem().getOnyx().hasRight()) {
            tvIconRight.setText(gameCharacter.getRight().getTitle());
            iconRight.setImageUrl(getString(gameCharacter.getRight().getBaseUrl()) + getString(gameCharacter.getRight().getUrl()), VolleySingleton.INSTANCE.getImageLoader());
        }

        adapter = new ViewPagerAdapter(fm, gameCharacter.getSheets());
        viewPager.setAdapter(adapter);
        tabLayout.setTabsFromPagerAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override public void onTabSelected(TabLayout.Tab tab) {
                toolbar.getMenu().findItem(R.id.action_tab_move_left).setVisible(tab.getPosition() != 0);
                toolbar.getMenu().findItem(R.id.action_tab_move_right).setVisible(tab.getPosition() != tabLayout.getTabCount()-1);
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

    //Up navigation
    @Override public void onClick(View v) {
        fm.popBackStack();
    }

    @Override public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
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