package amagi82.modularcharactersheetcreator.ui.edit.game;

import android.databinding.ObservableArrayList;

import java.util.List;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Game;
import amagi82.modularcharactersheetcreator.models.games.GameSystem;
import amagi82.modularcharactersheetcreator.ui.edit._base.BaseViewModel;
import me.tatarka.bindingcollectionadapter.ItemView;

public class GameViewModel extends BaseViewModel{
    public final ObservableArrayList<GameItemViewModel> list = new ObservableArrayList<>();
    public final ItemView itemView = ItemView.of(BR.gameItemViewModel, R.layout.tile_edit_game);

    public GameViewModel() {
        List<GameSystem> list = new Game().getList();
        for(GameSystem system : list){
            this.list.add(new GameItemViewModel(system.getGameUrl(), system.getGameTitle()));
        }
    }
}
