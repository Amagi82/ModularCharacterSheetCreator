package amagi82.modularcharactersheetcreator.ui.create.axis;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.games.Game;
import amagi82.modularcharactersheetcreator.ui._base.BaseViewModel;
import me.tatarka.bindingcollectionadapter.ItemView;

public class AxisViewModel extends BaseViewModel {
    public final ObservableArrayList<AxisItemViewModel> list = new ObservableArrayList<>();
    public final ItemView itemView = ItemView.of(BR.axisItemViewModel, R.layout.create_axis_item);
    public final ObservableField<String> title = new ObservableField<>();
    private final boolean isLeft;
    private GameCharacter currentCharacter;

    public AxisViewModel(GameCharacter character, boolean isLeft) {
        this.isLeft = isLeft;
        currentCharacter = character;
    }

    public void update(GameCharacter character, int splatId) {
        if (character == null || character.getGameSystem() == null) {
            list.clear();
            return;
        }
        Game updatedSystem = character.getGameSystem();
        if (currentCharacter.getGameSystem() == null || currentCharacter.getGameSystem().getClass() != updatedSystem.getClass()) list.clear();

        if (isLeft) checkLeft(updatedSystem, splatId);
        else checkRight(updatedSystem, splatId == 0 ? character.leftId() : splatId);

        currentCharacter = character;
    }

    private void checkLeft(Game system, int splatId) {
        if (list.size() == 0) addItemModels(system, splatId);
        else if (!system.isLeftListFinal() && list.get(0).id != system.getListLeft(splatId)[0]) {
            list.clear();
            addItemModels(system, splatId);
        }
        title.set(system.getLeftTitle());
    }

    private void checkRight(Game system, int splatId) {
        if (list.size() == 0 && (system.isRightListFinal() || splatId != 0)) addItemModels(system, splatId);
        else if (system.getListRight(splatId).length == 0) list.clear();
        else if (!system.isRightListFinal() && list.size() > 0 && list.get(0).id != system.getListRight(splatId)[0]) {
            list.clear();
            addItemModels(system, splatId);
        }
        title.set(system.getRightTitle(splatId));
    }

    private void addItemModels(Game system, int splatId) {
        int[] items = isLeft ? system.getListLeft(splatId) : system.getListRight(splatId);
        for (int id : items) {
            this.list.add(new AxisItemViewModel(system.getSplat(id), id));
        }
    }
}
