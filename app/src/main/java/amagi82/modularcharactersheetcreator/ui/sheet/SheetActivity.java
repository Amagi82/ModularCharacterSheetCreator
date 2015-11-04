package amagi82.modularcharactersheetcreator.ui.sheet;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cocosw.bottomsheet.BottomSheet;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.ActivitySheetBinding;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.ui._base.BaseActivity;
import amagi82.modularcharactersheetcreator.ui.main.MainActivity;

public class SheetActivity extends BaseActivity {
    private SheetViewModel sheetViewModel;
    private ActivitySheetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sheet);
        setToolbar(binding.toolbar);

        final GameCharacter character = getIntent().getParcelableExtra(MainActivity.CHARACTER);
        Log.i("SheetActivity", "Character ==" + character);
        if (character == null) {
            finish();
            return;
        }
        sheetViewModel = new SheetViewModel(character);
        binding.setSheetViewModel(sheetViewModel);
        binding.executePendingBindings();
        binding.tabLayout.setTabsFromPagerAdapter(binding.viewPager.getAdapter());
        binding.tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(binding.viewPager));
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));

        if(character.colorScheme() != null) {
            //noinspection ConstantConditions
            int color = character.colorScheme().colorBackground();
            binding.tabLayout.setBackgroundColor(color);
            binding.collapsingToolbar.setContentScrimColor(color);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) binding.collapsingToolbar.setStatusBarScrimColor(color);
        }
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
                finish(RESULT_EDIT, new Intent().putExtra(CHARACTER, sheetViewModel.character.get()));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onFabClicked(View view) {
        final FloatingActionButton fab = (FloatingActionButton) view;
        fab.hide();
        new BottomSheet.Builder(this).sheet(binding.viewPager.getCurrentItem() == 0 ? R.menu.bottom_sheet_start : R.menu.bottom_sheet).grid()
                .listener(new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case R.id.action_new_module:
                                break;
                            case R.id.action_new_tab:
                                break;
                            case R.id.action_rename_tab:
                                break;
                            case R.id.action_delete_tab:
                                break;
                        }
                    }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override public void onDismiss(DialogInterface dialog) {
                fab.show();
            }
        }).show();
    }
}
