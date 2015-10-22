package amagi82.modularcharactersheetcreator.ui.sheet.modules;

import android.databinding.ObservableField;

import amagi82.modularcharactersheetcreator.models.modules.Health;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.ui._base.BaseViewModel;

public class HealthViewModel extends BaseViewModel {
    public final ObservableField<Health> health = new ObservableField<>();

    public HealthViewModel(Module module) {
        update(module);
    }

    public void update(Module module){
        health.set(module.health());
    }
}
