package amagi82.modularcharactersheetcreator.ui.create.name;

import android.databinding.ObservableField;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.games.Game;
import amagi82.modularcharactersheetcreator.ui._base.App;
import amagi82.modularcharactersheetcreator.ui._base.BaseViewModel;
import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto;
import amagi82.modularcharactersheetcreator.ui._extras.widgets.callbacks.EditTextListener;
import amagi82.modularcharactersheetcreator.ui.create._events.KeyboardVisibleEvent;
import amagi82.modularcharactersheetcreator.ui.create._events.NameChangedEvent;

public class NameViewModel extends BaseViewModel{
    public final ObservableField<GameCardViewModel> gameItem = new ObservableField<>();
    public final ObservableField<AxisCardViewModel> leftItem = new ObservableField<>();
    public final ObservableField<AxisCardViewModel> rightItem = new ObservableField<>();
    public final ObservableField<String> title = new ObservableField<>();
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

        if(character.rightId() != 0) {
            gameItem.set(new GameCardViewModel(game.getGameUrl(), game.getGameTitle()));
            leftItem.set(new AxisCardViewModel(character.getLeft(), game.getLeftTitle(), true));
            rightItem.set(new AxisCardViewModel(character.getRight(), game.getRightTitle(character.leftId()), false));
        }
        name.set(character.name());

        title.set(App.getRes().getString(character.name().length() > 1? R.string.confirm : R.string.choose_name));

        //noinspection ConstantConditions
        imageUri.set(character.image() == null ? null : character.image().uri());
    }
}