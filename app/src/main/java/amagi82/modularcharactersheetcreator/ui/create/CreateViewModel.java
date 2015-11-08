package amagi82.modularcharactersheetcreator.ui.create;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.ui._base.BaseViewModel;
import amagi82.modularcharactersheetcreator.ui.create.axis.AxisViewModel;
import amagi82.modularcharactersheetcreator.ui.create.game.GameViewModel;
import amagi82.modularcharactersheetcreator.ui.create.name.NameViewModel;
import me.tatarka.bindingcollectionadapter.BaseItemViewSelector;
import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;

public class CreateViewModel {
    public final ObservableField<String> splashUrl = new ObservableField<>();
    public final ObservableInt page = new ObservableInt();
    public final ObservableBoolean isFabShown = new ObservableBoolean();
    public final ObservableArrayList<BaseViewModel> pages = new ObservableArrayList<>();
    public final ItemViewSelector<BaseViewModel> itemView = new BaseItemViewSelector<BaseViewModel>() {
        @Override public void select(ItemView itemView, int position, BaseViewModel item) {
            if (position == 0) itemView.set(BR.gameViewModel, R.layout.create_tab_game);
            else if (position == 3) itemView.set(BR.nameViewModel, R.layout.create_tab_name);
            else itemView.set(BR.axisViewModel, R.layout.create_tab_axis);
        }
    };

    public CreateViewModel(GameCharacter character) {
        pages.add(new GameViewModel());
        pages.add(new AxisViewModel(character, true));
        pages.add(new AxisViewModel(character, false));
        pages.add(new NameViewModel());
    }

    public void update(GameCharacter character) {
        splashUrl.set(character.getGameSystem() == null ? null : character.getGameSystem().getSplashUrl());
        page.set(character.getProgress());
        isFabShown.set(character.isComplete());

        ((AxisViewModel) pages.get(1)).update(character, 0);
        ((AxisViewModel) pages.get(2)).update(character, 0);
        ((NameViewModel) pages.get(3)).update(character);
    }

    //Selection was not an end point. Display the sub-list.
    public void update(GameCharacter character, int splatId) {
        ((AxisViewModel) pages.get(1)).update(character, splatId);
        ((AxisViewModel) pages.get(2)).update(character, splatId);
    }

    public void softKeyboardVisible() {
        isFabShown.set(false);
    }
}
