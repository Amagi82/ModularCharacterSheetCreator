package amagi82.modularcharactersheetcreator.ui.create.game;

import android.databinding.ObservableArrayList;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Game;
import amagi82.modularcharactersheetcreator.ui._base.BaseViewModel;
import me.tatarka.bindingcollectionadapter.ItemView;

public class GameViewModel extends BaseViewModel{
    public final ObservableArrayList<GameItemViewModel> list = new ObservableArrayList<>();
    public final ItemView itemView = ItemView.of(BR.gameItemViewModel, R.layout.create_game_item);

    public GameViewModel() {
        int[] items = Game.getSystems();
        for(int gameId : items){
            Game system = Game.getSystem(gameId);
            if(system != null) this.list.add(new GameItemViewModel(system.getGameTitle(), system.getGameUrl(), gameId));
        }
    }
}
