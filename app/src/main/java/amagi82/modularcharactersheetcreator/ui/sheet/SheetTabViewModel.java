package amagi82.modularcharactersheetcreator.ui.sheet;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.ui._base.BaseModuleViewModel;
import amagi82.modularcharactersheetcreator.ui.sheet.modules.BloodViewModel;
import amagi82.modularcharactersheetcreator.ui.sheet.modules.HeaderViewModel;
import amagi82.modularcharactersheetcreator.ui.sheet.modules.HealthViewModel;
import amagi82.modularcharactersheetcreator.ui.sheet.modules.ImageViewModel;
import amagi82.modularcharactersheetcreator.ui.sheet.modules.StatBlockViewModel;
import amagi82.modularcharactersheetcreator.ui.sheet.modules.StatViewModel;
import amagi82.modularcharactersheetcreator.ui.sheet.modules.TextViewModel;
import me.tatarka.bindingcollectionadapter.BaseItemViewSelector;
import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;

import static amagi82.modularcharactersheetcreator.models.modules.Module.BLOOD_MODULE;
import static amagi82.modularcharactersheetcreator.models.modules.Module.HEADER_MODULE;
import static amagi82.modularcharactersheetcreator.models.modules.Module.HEALTH_MODULE;
import static amagi82.modularcharactersheetcreator.models.modules.Module.IMAGE_MODULE;
import static amagi82.modularcharactersheetcreator.models.modules.Module.STAT_BLOCK_MODULE;
import static amagi82.modularcharactersheetcreator.models.modules.Module.STAT_MODULE;
import static amagi82.modularcharactersheetcreator.models.modules.Module.TEXT_MODULE;

public class SheetTabViewModel {
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableArrayList<BaseModuleViewModel> modules = new ObservableArrayList<>();
    public final ItemViewSelector<BaseModuleViewModel> itemView = new BaseItemViewSelector<BaseModuleViewModel>() {
        @Override public void select(ItemView itemView, int position, BaseModuleViewModel item) {
            switch (item.getType()) {
                case HEADER_MODULE:
                    itemView.set(BR.headerViewModel, R.layout.sheet_item_header);
                    break;
                case TEXT_MODULE:
                    itemView.set(BR.textViewModel, R.layout.sheet_item_text);
                    break;
                case STAT_MODULE:
                    itemView.set(BR.statViewModel, R.layout.sheet_item_stat);
                    break;
                case STAT_BLOCK_MODULE:
                    itemView.set(BR.statBlockViewModel, R.layout.sheet_item_stat_block);
                    break;
                case HEALTH_MODULE:
                    itemView.set(BR.healthViewModel, R.layout.sheet_item_health);
                    break;
                case BLOOD_MODULE:
                    itemView.set(BR.bloodViewModel, R.layout.sheet_item_blood);
                    break;
                case IMAGE_MODULE:
                    itemView.set(BR.imageViewModel, R.layout.sheet_item_image);
                    break;
            }
        }
    };

    public SheetTabViewModel(Sheet sheet) {
        title.set(sheet.title());
        for (Module module : sheet.modules()) {
            switch (module.type()) {
                case HEADER_MODULE:
                    modules.add(new HeaderViewModel(module));
                    break;
                case TEXT_MODULE:
                    modules.add(new TextViewModel(module));
                    break;
                case STAT_MODULE:
                    modules.add(new StatViewModel(module));
                    break;
                case STAT_BLOCK_MODULE:
                    modules.add(new StatBlockViewModel(module));
                    break;
                case HEALTH_MODULE:
                    modules.add(new HealthViewModel(module));
                    break;
                case BLOOD_MODULE:
                    modules.add(new BloodViewModel(module));
                    break;
                case IMAGE_MODULE:
                    modules.add(new ImageViewModel(module));
                    break;
            }
        }
    }
}
