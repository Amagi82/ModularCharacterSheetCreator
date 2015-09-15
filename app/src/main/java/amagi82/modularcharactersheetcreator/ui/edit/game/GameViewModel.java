package amagi82.modularcharactersheetcreator.ui.edit.game;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import java.util.List;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.entities.games.GameSystem;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ItemBinder;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ItemBinderBase;

public class GameViewModel extends BaseObservable {
    @Bindable private ObservableArrayList<GameSystem> list;

    public GameViewModel(List<GameSystem> list) {
        this.list.addAll(list);
    }

    public void add(GameSystem character){
        list.add(character);
    }

    public void remove(GameSystem character){
        list.remove(character);
    }

    public ObservableArrayList<GameSystem> getList() {
        return list;
    }

    public ItemBinder<GameItemViewModel> itemBinder(){
        return new ItemBinderBase<>(BR.viewModel, R.layout.item_edit_tile);
    }
}
