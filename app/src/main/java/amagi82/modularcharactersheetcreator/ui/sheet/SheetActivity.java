package amagi82.modularcharactersheetcreator.ui.sheet;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
        setToolbar(binding.toolbar);

        GameCharacter character = getIntent().getParcelableExtra(MainActivity.CHARACTER);
        Log.i("SheetActivity", "Character ==" + character);
        if (character == null) finish();

        sheetViewModel = new SheetViewModel(character);
        binding.setSheetViewModel(sheetViewModel);
        binding.executePendingBindings();
        binding.tabLayout.setTabsFromPagerAdapter(binding.viewPager.getAdapter());
        binding.tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(binding.viewPager));
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_sheet, menu);
        setMenuTint(menu, Color.WHITE);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_theme:
                return true;
            case R.id.action_edit:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onFabClicked(View view) {
        //Open the bottom sheet
    }
}
