package amagi82.modularcharactersheetcreator.ui.edit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.ui.edit.axis.AxisFragment;
import amagi82.modularcharactersheetcreator.ui.edit.game.GameFragment;
import amagi82.modularcharactersheetcreator.ui.edit.name.NameFragment;

public class EditPagerAdapter extends FragmentPagerAdapter {

    /*
        Should never contain more than 4 fragments. These include, in order:
        GameFragment
        AxisFragment (x2)
        NameFragment
     */
    private List<Fragment> fragments = new ArrayList<>(4);

    public EditPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new GameFragment());
        fragments.add(getAxisFragment(true));
        fragments.add(getAxisFragment(false));
        fragments.add(new NameFragment());
    }

    private Fragment getAxisFragment(boolean left) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(EditActivity.LEFT, left);
        AxisFragment fragment = new AxisFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override public int getCount() {
        return fragments.size();
    }
}
