package amagi82.modularcharactersheetcreator.ui.edit.game;

import android.databinding.ObservableArrayList;

import java.util.List;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.entities.games.GameSystem;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ItemBinder;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ItemBinderBase;

public class GameViewModel {
    private ObservableArrayList<GameItemViewModel> list = new ObservableArrayList<>();

    public GameViewModel(List<GameSystem> list) {
        for(GameSystem system : list){
            this.list.add(new GameItemViewModel(system.getGameUrl()));
        }
    }

    public ObservableArrayList<GameItemViewModel> getList() {
        return list;
    }

    public ItemBinder<GameItemViewModel> itemViewBinder(){
        return new ItemBinderBase<>(BR.gameItemViewModel, R.layout.tile_edit_game);
    }
}
