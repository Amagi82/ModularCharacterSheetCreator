package amagi82.modularcharactersheetcreator.ui.sheet.modules;

import android.databinding.ObservableField;

import amagi82.modularcharactersheetcreator.models.modules.BloodPool;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.ui._base.BaseViewModel;

public class BloodPoolViewModel extends BaseViewModel {
    public final ObservableField<BloodPool> bloodPool = new ObservableField<>();

    public BloodPoolViewModel(Module module) {
        update(module);
    }

    public void update(Module module){
        bloodPool.set(module.bloodPool());
    }
}
