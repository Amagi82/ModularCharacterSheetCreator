package amagi82.modularcharactersheetcreator.ui.edit.name;

import android.databinding.ObservableField;

import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.characters.Splat;
import amagi82.modularcharactersheetcreator.models.games.GameSystem;

public class NameViewModel {
    public final ObservableField<NameGameItemViewModel> gameItem = new ObservableField<>();
    public final ObservableField<NameAxisItemViewModel> leftItem = new ObservableField<>();
    public final ObservableField<NameAxisItemViewModel> rightItem = new ObservableField<>();
    public final ObservableField<NameItemViewModel> nameItem = new ObservableField<>();

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
        //noinspection ConstantConditions
        nameItem.set(new NameItemViewModel(character.name(), character.image() == null ? null : character.image().uri()));
    }
}
