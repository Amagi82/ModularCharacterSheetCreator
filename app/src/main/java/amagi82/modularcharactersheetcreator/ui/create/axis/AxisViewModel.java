package amagi82.modularcharactersheetcreator.ui.create.axis;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.util.List;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.characters.Splat;
import amagi82.modularcharactersheetcreator.models.games.Game;
import amagi82.modularcharactersheetcreator.ui._base.BaseViewModel;
import me.tatarka.bindingcollectionadapter.ItemView;

public class AxisViewModel extends BaseViewModel {
    public final ObservableArrayList<AxisItemViewModel> list = new ObservableArrayList<>();
    public final ItemView itemView = ItemView.of(BR.axisItemViewModel, R.layout.create_axis_tile);
    public final ObservableInt title = new ObservableInt();
    private final boolean isLeft;
    private GameCharacter currentCharacter;

    public AxisViewModel(GameCharacter character, boolean isLeft) {
        this.isLeft = isLeft;
        currentCharacter = character;
    }

    public void update(GameCharacter character, @Nullable Splat splat) {
        if (character == null || character.getGameSystem() == null) {
            list.clear();
            return;
        }
        Game updatedSystem = character.getGameSystem();
        if (currentCharacter.getGameSystem() == null || currentCharacter.getGameSystem().getClass() != updatedSystem.getClass()) list.clear();

        if (isLeft) checkLeft(updatedSystem, splat);
        else checkRight(updatedSystem, splat == null ? character.left() : splat);

        currentCharacter = character;
    }

    private void checkLeft(Game system, Splat splat) {
        if (list.size() == 0) addItemModels(system.getListLeft(splat), system.getLeftTitle());
        else if (!system.isLeftListFinal() && list.get(0).getTitle() != system.getListLeft(splat).get(0).title()) {
            list.clear();
            addItemModels(system.getListLeft(splat), system.getLeftTitle());
        }
    }

    private void checkRight(Game system, Splat splat) {
        if (list.size() == 0) {
            if (system.isRightListFinal() || splat != null) addItemModels(system.getListRight(splat), system.getRightTitle(splat));
        }
        else if(system.getListRight(splat).size() == 0) list.clear();
        else if (!system.isRightListFinal() && list.get(0).getTitle() != system.getListRight(splat).get(0).title()) {
            list.clear();
            addItemModels(system.getListRight(splat), system.getRightTitle(splat));
        }
    }

    private void addItemModels(List<Splat> list, @StringRes int title) {
        for (Splat splat : list) {
            this.list.add(new AxisItemViewModel(splat));
        }
        this.title.set(title);
    }
}