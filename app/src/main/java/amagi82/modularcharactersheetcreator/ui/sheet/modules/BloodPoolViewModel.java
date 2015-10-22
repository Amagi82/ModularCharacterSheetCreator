package amagi82.modularcharactersheetcreator.ui.sheet.modules;

import android.databinding.ObservableField;

import amagi82.modularcharactersheetcreator.models.modules.BloodPool;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.ui._base.BaseModuleViewModel;

public class BloodPoolViewModel extends BaseModuleViewModel {
    public final ObservableField<BloodPool> bloodPool = new ObservableField<>();

    public BloodPoolViewModel(Module module) {
        super(module);
    }

    public void update(Module module){
        bloodPool.set(module.bloodPool());
    }
}
