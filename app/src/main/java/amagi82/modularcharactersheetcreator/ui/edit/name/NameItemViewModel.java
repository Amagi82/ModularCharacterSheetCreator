package amagi82.modularcharactersheetcreator.ui.edit.name;

import android.databinding.ObservableField;
import android.net.Uri;
import android.support.annotation.Nullable;

public class NameItemViewModel {
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<Uri> imageUri = new ObservableField<>();

    public NameItemViewModel(String name, @Nullable Uri imageUri) {
        this.name.set(name);
        this.imageUri.set(imageUri);
    }
}
