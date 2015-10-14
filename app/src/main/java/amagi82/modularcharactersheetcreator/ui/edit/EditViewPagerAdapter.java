package amagi82.modularcharactersheetcreator.ui.edit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import amagi82.modularcharactersheetcreator.ui.edit.axis.AxisFragment;
import amagi82.modularcharactersheetcreator.ui.edit.game.GameFragment;
import amagi82.modularcharactersheetcreator.ui.edit.name.NameFragment;

public class EditViewPagerAdapter extends FragmentPagerAdapter {
    private AxisFragment leftAxisFragment;
    private AxisFragment rightAxisFragment;

    public EditViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public AxisFragment getFragment(boolean left){
        return left? leftAxisFragment : rightAxisFragment;
    }

    @Override public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        // save the appropriate reference depending on url
        if(position == 1) leftAxisFragment = (AxisFragment) createdFragment;
        if(position == 2) rightAxisFragment = (AxisFragment) createdFragment;
        return createdFragment;
    }

    @Override public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new GameFragment();
            case 1:
                return getAxisFragment(true);
            case 2:
                return getAxisFragment(false);
            case 3:
                return new NameFragment();
            default:
                return null;
        }
    }

    private Fragment getAxisFragment(boolean left) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(EditActivity.LEFT, left);
        AxisFragment fragment = new AxisFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override public int getCount() {
        return 4;
    }
}
