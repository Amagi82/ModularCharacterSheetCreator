package amagi82.modularcharactersheetcreator.ui.sheet.modules;

import android.databinding.ObservableField;

import amagi82.modularcharactersheetcreator.models.modules.Blood;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.ui._base.BaseModuleViewModel;

public class BloodViewModel extends BaseModuleViewModel {
    public final ObservableField<Blood> blood = new ObservableField<>();

    public BloodViewModel(Module module) {
        super(module);
        update(module);
    }

    public void update(Module module) {
        blood.set(module.blood());
    }
}
