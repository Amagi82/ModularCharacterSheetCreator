package amagi82.modularcharactersheetcreator.ui.sheet.modules;

import android.databinding.ObservableField;

import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.models.modules.Stat;
import amagi82.modularcharactersheetcreator.ui._base.BaseViewModel;

public class StatViewModel extends BaseViewModel {
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> textBody = new ObservableField<>();
    public final ObservableField<Stat> stat = new ObservableField<>();

    public StatViewModel(Module module) {
        update(module);
    }

    public void update(Module module){
        title.set(module.title());
        textBody.set(module.textBody());
        stat.set(module.stat());
    }
}
