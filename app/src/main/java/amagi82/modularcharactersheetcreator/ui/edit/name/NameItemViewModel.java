package amagi82.modularcharactersheetcreator.ui.edit.name;

import android.databinding.ObservableField;
import android.net.Uri;

public class NameItemViewModel extends ItemViewModel {
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<Uri> imageUri = new ObservableField<>();

    public NameItemViewModel(String name, Uri imageUri) {
        this.name.set(name);
        this.imageUri.set(imageUri);
    }
}
