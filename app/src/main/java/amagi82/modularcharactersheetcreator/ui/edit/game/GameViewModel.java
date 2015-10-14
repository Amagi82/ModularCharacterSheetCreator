package amagi82.modularcharactersheetcreator.ui.edit.game;

import android.databinding.ObservableArrayList;

import java.util.List;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.GameSystem;
import me.tatarka.bindingcollectionadapter.ItemView;

public class GameViewModel {
    public final ObservableArrayList<GameItemViewModel> list = new ObservableArrayList<>();
    public final ItemView itemView = ItemView.of(BR.gameItemViewModel, R.layout.tile_edit_game);

    public GameViewModel(List<GameSystem> list) {
        for(GameSystem system : list){
            this.list.add(new GameItemViewModel(system.getGameUrl(), system.getGameTitle()));
        }
    }
}
