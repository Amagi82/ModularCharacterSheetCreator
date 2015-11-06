package amagi82.modularcharactersheetcreator.ui.create.name;

import android.databinding.ObservableField;

import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.characters.Splat;
import amagi82.modularcharactersheetcreator.models.games.Game;
import amagi82.modularcharactersheetcreator.ui._base.BaseViewModel;
import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto;
import amagi82.modularcharactersheetcreator.ui._extras.widgets.callbacks.EditTextListener;
import amagi82.modularcharactersheetcreator.ui.create._events.KeyboardVisibleEvent;
import amagi82.modularcharactersheetcreator.ui.create._events.NameChangedEvent;

public class NameViewModel extends BaseViewModel{
    public final ObservableField<GameCardViewModel> gameItem = new ObservableField<>();
    public final ObservableField<AxisCardViewModel> leftItem = new ObservableField<>();
    public final ObservableField<AxisCardViewModel> rightItem = new ObservableField<>();
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> imageUri = new ObservableField<>();
    public final EditTextListener editTextListener = new EditTextListener() {
        @Override public void onTextChanged(String newText) {
            Otto.BUS.get().post(new NameChangedEvent(newText));
        }
        @Override public void onKeyboardShown() {
            Otto.BUS.get().post(new KeyboardVisibleEvent());
        }
    };

    public NameViewModel() {}

    public void update(GameCharacter character){
        Game game = character.getGameSystem();
        Splat left = character.left();
        Splat right = character.right();

        if(right != null) {
            gameItem.set(new GameCardViewModel(game.getGameUrl(), 0));
            if(game.checkLeft()) left = game.updateLeft(left, right);
            leftItem.set(new AxisCardViewModel(left, game.getLeftTitle(), true));
            rightItem.set(new AxisCardViewModel(right, game.getRightTitle(left), false));
        }
        name.set(character.name());

        //noinspection ConstantConditions
        imageUri.set(character.image() == null ? null : character.image().uri());
    }
}