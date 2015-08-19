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
        fragments.add(new CharacterGameFragment());
    }

    public void next() {
        switch (fragments.size()){
            case 1:
                Bundle bundle1 = new Bundle();
                bundle1.putBoolean(EditCharacterActivity.LEFT, true);
                CharacterAxisFragment fragment1 = new CharacterAxisFragment();
                fragment1.setArguments(bundle1);
                fragments.add(fragment1);
                break;
            case 2:
                Bundle bundle2 = new Bundle();
                bundle2.putBoolean(EditCharacterActivity.LEFT, false);
                CharacterAxisFragment fragment2 = new CharacterAxisFragment();
                fragment2.setArguments(bundle2);
                fragments.add(fragment2);
                break;
            case 3:
                fragments.add(new CharacterNameFragment());
                break;
            default:
                Log.e(null, "Trying to add new fragment when size == "+fragments.size());
                break;
        }
        notifyDataSetChanged();
    }

    //TODO: keep data from CharacterNameFragment
    public void previous() {
        fragments.remove(fragments.size()-1);
    }

    @Override public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override public int getCount() {
        return fragments.size();
    }
}
