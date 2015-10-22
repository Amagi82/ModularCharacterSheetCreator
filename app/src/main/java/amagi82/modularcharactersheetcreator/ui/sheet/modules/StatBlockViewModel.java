package amagi82.modularcharactersheetcreator.ui.sheet.modules;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.models.modules.Stat;
import amagi82.modularcharactersheetcreator.ui._base.BaseViewModel;

public class StatBlockViewModel extends BaseViewModel {
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableArrayList<Stat> statBlock = new ObservableArrayList<>();

    public StatBlockViewModel(Module module) {
        update(module);
    }

    public void update(Module module){
        title.set(module.title());
        statBlock.clear();
        statBlock.addAll(module.statBlock());
    }
}
