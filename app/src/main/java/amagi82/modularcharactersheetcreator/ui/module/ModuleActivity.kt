package amagi82.modularcharactersheetcreator.ui.module

import android.databinding.DataBindingUtil
import android.os.Bundle

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.databinding.ModuleActivityBinding
import amagi82.modularcharactersheetcreator.databinding.ModuleActivityNewBinding
import amagi82.modularcharactersheetcreator.models.modules.Module
import amagi82.modularcharactersheetcreator.ui._base.BaseActivity

class ModuleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val module = intent.getParcelableExtra<Module>(BaseActivity.MODULE)

        if (module == null) {
            val binding = DataBindingUtil.setContentView<ModuleActivityNewBinding>(this, R.layout.module_activity_new)
            binding.newModuleViewModel = NewModuleViewModel()
        } else {
            val binding = DataBindingUtil.setContentView<ModuleActivityBinding>(this, R.layout.module_activity)
            binding.moduleViewModel = ModuleViewModel()
        }


    }
}
