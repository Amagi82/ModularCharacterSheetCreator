package amagi82.modularcharactersheetcreator.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.activities.EditCharacterActivity;
import amagi82.modularcharactersheetcreator.fragments.CharacterAxisFragment;
import amagi82.modularcharactersheetcreator.fragments.CharacterGameFragment;
import amagi82.modularcharactersheetcreator.fragments.CharacterNameFragment;

public class CharacterPagerAdapter extends FragmentPagerAdapter {

    /*
        Should never contain more than 4 fragments. These include, in order:
        CharacterGameFragment
        CharacterAxisFragment (x2)
        CharacterNameFragment
     */
    private List<Fragment> fragments = new ArrayList<>();

    public CharacterPagerAdapter(FragmentManager fm) {
        super(fm);
        Log.i(null, "creating new pager adapter");
        fragments.add(new CharacterGameFragment());
        fragments.add(getAxisFragment(true));
        fragments.add(getAxisFragment(false));
        fragments.add(new CharacterNameFragment());
    }

    private Fragment getAxisFragment(boolean left) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(EditCharacterActivity.LEFT, left);
        CharacterAxisFragment fragment = new CharacterAxisFragment();
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
