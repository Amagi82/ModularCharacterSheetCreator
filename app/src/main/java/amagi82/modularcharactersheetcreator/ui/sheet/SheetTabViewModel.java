package amagi82.modularcharactersheetcreator.ui.sheet;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import amagi82.modularcharactersheetcreator.models.characters.Sheet;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import me.tatarka.bindingcollectionadapter.BaseItemViewSelector;
import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;

public class SheetTabViewModel {
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableInt numColumns = new ObservableInt();
    public final ObservableArrayList<Module> modules = new ObservableArrayList<>();
    public final ItemViewSelector<Module> itemView = new BaseItemViewSelector<Module>() {
        @Override public void select(ItemView itemView, int position, Module item) {

        }
    };

    public SheetTabViewModel(Sheet sheet) {
        title.set(sheet.title());
        numColumns.set(sheet.numColumns());
        modules.addAll(sheet.modules());
    }
}
