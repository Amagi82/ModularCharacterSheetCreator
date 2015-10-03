package amagi82.modularcharactersheetcreator.ui.edit.game;

import android.databinding.ObservableArrayList;

import java.util.List;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.entities.games.GameSystem;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.Binding;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ItemBinder;

public class GameViewModel {
    public final ObservableArrayList<GameItemViewModel> list = new ObservableArrayList<>();

    public GameViewModel(List<GameSystem> list) {
        for(GameSystem system : list){
            this.list.add(new GameItemViewModel(system.getGameUrl()));
        }
    }

    public Binding<GameItemViewModel> itemViewBinder(){
        return new ItemBinder<>(BR.gameItemViewModel, R.layout.tile_edit_game);
    }
}
