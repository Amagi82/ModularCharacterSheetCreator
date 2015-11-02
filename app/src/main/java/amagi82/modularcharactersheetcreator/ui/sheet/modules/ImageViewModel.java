package amagi82.modularcharactersheetcreator.ui.sheet.modules;

import android.databinding.ObservableField;

import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.ui._base.BaseModuleViewModel;

public class ImageViewModel extends BaseModuleViewModel {
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> imageUri = new ObservableField<>();

    public ImageViewModel(Module module) {
        super(module);
        update(module);
    }

    public void update(Module module){
        title.set(module.title());
        imageUri.set(module.imageUri());
    }
}
