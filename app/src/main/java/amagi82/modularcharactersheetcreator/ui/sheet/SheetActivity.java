package amagi82.modularcharactersheetcreator.ui.sheet;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.ActivitySheetBinding;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.ui._base.BaseActivity;
import amagi82.modularcharactersheetcreator.ui.main.MainActivity;

public class SheetActivity extends BaseActivity {
    private ActivitySheetBinding binding;
    private SheetViewModel sheetViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sheet);

        GameCharacter character = getIntent().getParcelableExtra(MainActivity.CHARACTER);

        sheetViewModel = new SheetViewModel(character);
        binding.setSheetViewModel(sheetViewModel);
        binding.tabLayout.setTabsFromPagerAdapter(binding.viewPager.getAdapter());
        binding.tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(binding.viewPager));
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
    }

    public void onFabClicked(View view) {
        //Open the bottom sheet
    }
}
