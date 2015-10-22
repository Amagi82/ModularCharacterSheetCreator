package amagi82.modularcharactersheetcreator.ui.sheet.modules;

import android.databinding.ObservableField;

import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.models.modules.Stat;
import amagi82.modularcharactersheetcreator.ui._base.BaseModuleViewModel;

public class StatViewModel extends BaseModuleViewModel {
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> textBody = new ObservableField<>();
    public final ObservableField<Stat> stat = new ObservableField<>();

    public StatViewModel(Module module) {
        super(module);
    }

    public void update(Module module){
        title.set(module.title());
        textBody.set(module.textBody());
        stat.set(module.stat());
    }
}
