package amagi82.modularcharactersheetcreator.ui.edit.name;

import android.databinding.ObservableField;
import android.net.Uri;

import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.characters.Splat;
import amagi82.modularcharactersheetcreator.models.games.GameSystem;
import amagi82.modularcharactersheetcreator.ui.edit._events.KeyboardVisibleEvent;
import amagi82.modularcharactersheetcreator.ui.edit._events.NameChangedEvent;
import amagi82.modularcharactersheetcreator.ui._extras.widgets.callbacks.EditTextListener;
import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto;

public class NameViewModel {
    public final ObservableField<GameCardViewModel> gameItem = new ObservableField<>();
    public final ObservableField<AxisCardViewModel> leftItem = new ObservableField<>();
    public final ObservableField<AxisCardViewModel> rightItem = new ObservableField<>();
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<Uri> imageUri = new ObservableField<>();
    public final EditTextListener editTextListener = new EditTextListener() {
        @Override public void onTextChanged(String newText) {
            Otto.BUS.get().post(new NameChangedEvent(newText));
        }
        @Override public void onKeyboardShown() {
            Otto.BUS.get().post(new KeyboardVisibleEvent());
        }
    };


    public NameViewModel(GameCharacter character) {
        update(character);
    }

    public void update(GameCharacter character){
        GameSystem gameSystem = character.getGameSystem();
        Splat left = character.left();
        Splat right = character.right();

        if(right != null) {
            gameItem.set(new GameCardViewModel(gameSystem.getGameUrl(), 0));
            if(gameSystem.checkLeft()) left = gameSystem.updateLeft(left, right);
            leftItem.set(new AxisCardViewModel(left, gameSystem.getLeftTitle(), true));
            rightItem.set(new AxisCardViewModel(right, gameSystem.getRightTitle(left), false));
        }
        name.set(character.name());

        //noinspection ConstantConditions
        imageUri.set(character.image() == null ? null : character.image().uri());
    }
}