package amagi82.modularcharactersheetcreator.ui.edit.name;

import android.databinding.ObservableField;
import android.net.Uri;
import android.text.TextWatcher;

import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.characters.Splat;
import amagi82.modularcharactersheetcreator.models.games.GameSystem;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.Otto;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.SimpleTextWatcher;

public class NameViewModel {
    public final ObservableField<NameGameItemViewModel> gameItem = new ObservableField<>();
    public final ObservableField<NameAxisItemViewModel> leftItem = new ObservableField<>();
    public final ObservableField<NameAxisItemViewModel> rightItem = new ObservableField<>();
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<Uri> imageUri = new ObservableField<>();
    public final TextWatcher textWatcher = new SimpleTextWatcher() {
        @Override public void onTextChanged(String newValue) {
            Otto.BUS.getBus().post(new NameChangedEvent(newValue));
        }
    };

    public NameViewModel(GameCharacter character) {
        update(character);
    }

    public void update(GameCharacter character){
        GameSystem system = character.getGameSystem();
        Splat left = character.left();
        Splat right = character.right();

        if(right != null) {
            gameItem.set(new NameGameItemViewModel(system.getGameUrl(), 0));
            if(system.checkLeft()) left = system.updateLeft(left, right);
            leftItem.set(new NameAxisItemViewModel(left, system.getLeftTitle(), true));
            rightItem.set(new NameAxisItemViewModel(right, system.getRightTitle(left), false));
        }
        name.set(character.name());

        //noinspection ConstantConditions
        imageUri.set(character.image() == null ? null : character.image().uri());
    }
}