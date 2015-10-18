package amagi82.modularcharactersheetcreator.ui.edit;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.characters.Splat;
import amagi82.modularcharactersheetcreator.ui.edit._base.BaseViewModel;
import amagi82.modularcharactersheetcreator.ui.edit.axis.AxisViewModel;
import amagi82.modularcharactersheetcreator.ui.edit.game.GameViewModel;
import amagi82.modularcharactersheetcreator.ui.edit.name.NameViewModel;
import me.tatarka.bindingcollectionadapter.BaseItemViewSelector;
import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;

public class EditViewModel {
    public final ObservableInt splashUrl = new ObservableInt();
    public final ObservableInt page = new ObservableInt();
    public final ObservableBoolean isFabShown = new ObservableBoolean();
    public final ObservableArrayList<BaseViewModel> pages = new ObservableArrayList<>();
    public final ItemViewSelector<BaseViewModel> itemView = new BaseItemViewSelector<BaseViewModel>() {
        @Override public void select(ItemView itemView, int position, BaseViewModel item) {
            if (position == 0) itemView.set(BR.gameViewModel, R.layout.view_edit_game);
            else if (position == 3) itemView.set(BR.nameViewModel, R.layout.view_edit_name);
            else itemView.set(BR.axisViewModel, R.layout.view_edit_axis);
        }
    };

    public EditViewModel(GameCharacter character) {
        pages.add(new GameViewModel());
        pages.add(new AxisViewModel(character, true));
        pages.add(new AxisViewModel(character, false));
        pages.add(new NameViewModel());
    }

    public void update(GameCharacter character) {
        splashUrl.set(character.getGameSystem() == null ? 0 : character.getGameSystem().getSplashUrl());
        page.set(character.getProgress());
        isFabShown.set(character.isComplete());

        ((AxisViewModel) pages.get(1)).update(character, null);
        ((AxisViewModel) pages.get(2)).update(character, null);
        ((NameViewModel) pages.get(3)).update(character);
    }

    public void update(GameCharacter character, Splat splat) {
        ((AxisViewModel) pages.get(1)).update(character, splat);
        ((AxisViewModel) pages.get(2)).update(character, splat);
    }

    public void softKeyboardVisible() {
        isFabShown.set(false);
    }
}
