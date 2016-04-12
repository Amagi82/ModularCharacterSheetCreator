package amagi82.modularcharactersheetcreator.ui.sheet

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.databinding.SheetActivityBinding
import amagi82.modularcharactersheetcreator.models.GameCharacter
import amagi82.modularcharactersheetcreator.ui._base.BaseActivity
import amagi82.modularcharactersheetcreator.ui._extras.utils.ScreenSize
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

class SheetActivity : BaseActivity() {
    private var sheetViewModel: SheetViewModel? = null
    private var binding: SheetActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<SheetActivityBinding>(this, R.layout.sheet_activity)
        setToolbar(binding!!.toolbar)

        val character = intent.getParcelableExtra<GameCharacter>(BaseActivity.CHARACTER)
        Log.i("SheetActivity", "Character ==" + character!!)
        if (character == null) {
            finish()
            return
        }
        if (character.image != null) {
            //noinspection ConstantConditions
            binding?.imageBackdrop?.minimumHeight = (ScreenSize(this).width / (1.0 * (character.image?.width ?: 1)) * (1.0 * (character.image?.height ?: 1))).toInt()
            binding?.imageBackdrop?.maxHeight = (ScreenSize(this).width / (1.0 * (character.image?.width ?: 1)) * (1.0 * (character.image?.height ?: 1))).toInt()
        }


        sheetViewModel = SheetViewModel(character)
        binding?.setSheetViewModel(sheetViewModel)
        binding?.executePendingBindings()
        binding?.tabLayout?.setTabsFromPagerAdapter(binding?.viewPager?.adapter)
        binding?.tabLayout?.setOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(binding?.viewPager))
        binding?.viewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding?.tabLayout))
        binding?.viewPager?.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if (state != ViewPager.SCROLL_STATE_IDLE && binding?.viewPager?.adapter?.count ?: 0 > 1)
                    binding?.fab?.hide()
                else
                    Handler().postDelayed({ binding!!.fab.show() }, 300)
            }
        })

        if (character.colorScheme != null) {
            //noinspection ConstantConditions
            val color = character.colorScheme?.colorBackground ?: return
            binding!!.tabLayout.setBackgroundColor(color)
            binding!!.collapsingToolbar.setContentScrimColor(color)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) binding?.collapsingToolbar?.setStatusBarScrimColor(color)
        }
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({ binding?.fab?.show() }, 400)
    }

    override fun onPause() {
        super.onPause()
        binding?.fab?.hide()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_sheet, menu)
        setMenuTint(menu, Color.WHITE)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_theme -> return true
            R.id.action_edit -> {
                finish(BaseActivity.RESULT_EDIT, Intent().putExtra(BaseActivity.CHARACTER, sheetViewModel?.character?.get()))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun onFabClicked(view: View) {
        val fab = view as FloatingActionButton
        fab.hide()
        //        new BottomSheet.Builder(this).sheet(binding.viewPager.getCurrentItem() == 0 ? R.menu.bottom_sheet_start : R.menu.bottom_sheet).grid()
        //                .listener(new DialogInterface.OnClickListener() {
        //                    @Override public void onClick(DialogInterface dialog, int which) {
        //                        switch (which) {
        //                            case R.id.action_new_module:
        //                                break;
        //                            case R.id.action_new_tab:
        //                                break;
        //                            case R.id.action_rename_tab:
        //                                break;
        //                            case R.id.action_delete_tab:
        //                                break;
        //                        }
        //                    }
        //        }).setOnDismissListener(new DialogInterface.OnDismissListener() {
        //            @Override public void onDismiss(DialogInterface dialog) {
        //                fab.show();
        //            }
        //        }).show();
    }
}
