package amagi82.modularcharactersheetcreator.ui.sheet.modules;

import android.databinding.ObservableField;
import android.net.Uri;

import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.ui._base.BaseModuleViewModel;

public class ImageViewModel extends BaseModuleViewModel {
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<Uri> image = new ObservableField<>();

    public ImageViewModel(Module module) {
        super(module);
    }

    public void update(Module module){
        title.set(module.title());
        image.set(module.image());
    }
}
