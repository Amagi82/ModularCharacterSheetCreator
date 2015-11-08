package amagi82.modularcharactersheetcreator.ui.module;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.ModuleActivityBinding;
import amagi82.modularcharactersheetcreator.databinding.ModuleActivityNewBinding;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.ui._base.BaseActivity;

public class ModuleActivity extends BaseActivity{

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Module module = getIntent().getParcelableExtra(MODULE);

        if(module == null) {
            ModuleActivityNewBinding binding = DataBindingUtil.setContentView(this, R.layout.module_activity_new);
            binding.setNewModuleViewModel(new NewModuleViewModel());
        }else{
            ModuleActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.module_activity);
            binding.setModuleViewModel(new ModuleViewModel());
        }
    }
}
