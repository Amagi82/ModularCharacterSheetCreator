package amagi82.modularcharactersheetcreator.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import amagi82.modularcharactersheetcreator.fragments.TabFragment;
import amagi82.modularcharactersheetcreator.models.Sheet;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Sheet> sheets = new ArrayList<>();
    private Map<Sheet,Fragment> map = new HashMap<>();

    public ViewPagerAdapter(FragmentManager fm, List<Sheet> sheets) {
        super(fm);
        this.sheets = sheets;
    }

    public void addFragment(Sheet sheet){
        sheets.add(sheet);
    }

    public void deleteFragment(int position){
        map.remove(sheets.get(position));
        sheets.remove(position);
    }

    @Override public Fragment getItem(int position) {
        Sheet sheet = sheets.get(position);
        if(map.get(sheet)==null) {
            TabFragment tabFragment = new TabFragment();
            Bundle bundle = new Bundle();
            try {
                bundle.putString("Sheet", LoganSquare.serialize(sheet));
                tabFragment.setArguments(bundle);
                map.put(sheet, tabFragment);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map.get(sheet);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return sheets.get(position).getTitle();
    }

    @Override public int getCount() {
        return sheets.size();
    }
}