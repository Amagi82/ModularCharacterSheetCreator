package amagi82.modularcharactersheetcreator.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.ArrayMap;

import java.util.List;
import java.util.Map;

import amagi82.modularcharactersheetcreator.fragments.TabFragment;
import amagi82.modularcharactersheetcreator.core_models.Sheet;

public class SheetPagerAdapter extends FragmentStatePagerAdapter {

    private List<Sheet> sheets;
    private Map<Sheet, Fragment> map = new ArrayMap<>();

    public SheetPagerAdapter(FragmentManager fm, List<Sheet> sheets) {
        super(fm);
        this.sheets = sheets;
    }

    public void addFragment(Sheet sheet) {
        sheets.add(sheet);
    }

    public void deleteFragment(int position) {
        map.remove(sheets.get(position));
        sheets.remove(position);
    }

    @Override public Fragment getItem(int position) {
        Sheet sheet = sheets.get(position);
        if (map.get(sheet) == null) {
            TabFragment tabFragment = new TabFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            tabFragment.setArguments(bundle);
            map.put(sheet, tabFragment);
        }
        return map.get(sheet);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return sheets.get(position).title();
    }

    @Override public int getCount() {
        return sheets.size();
    }
}
