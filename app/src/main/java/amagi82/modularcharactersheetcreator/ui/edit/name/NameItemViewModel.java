package amagi82.modularcharactersheetcreator.ui.edit.name;

import android.databinding.ObservableField;
import android.net.Uri;
import android.text.TextWatcher;

import amagi82.modularcharactersheetcreator.ui.xtras.utils.TextListener;

public class NameItemViewModel extends ItemViewModel {
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<Uri> imageUri = new ObservableField<>();

    public NameItemViewModel(String name, Uri imageUri) {
        this.name.set(name);
        this.imageUri.set(imageUri);
    }

    public TextWatcher getTextWatcher(){
        return new TextListener() {
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                name.set(s.toString());
            }
        };
    }
}
