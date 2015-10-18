package amagi82.modularcharactersheetcreator.ui.main;

import android.databinding.ObservableArrayList;

import java.util.List;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import me.tatarka.bindingcollectionadapter.ItemView;

public class MainViewModel {
    public final ObservableArrayList<MainItemViewModel> list = new ObservableArrayList<>();
    public final ItemView itemView = ItemView.of(BR.mainItemViewModel, R.layout.item_main);

    public MainViewModel(List<GameCharacter> list) {
        addAll(list);
    }

    public void addAll(List<GameCharacter> list){
        for (GameCharacter character : list) {
            this.list.add(new MainItemViewModel(character));
        }
    }

    public void add(GameCharacter character){
        this.list.add(new MainItemViewModel(character));
    }

    public void remove(int position){
        list.remove(position);
    }

    public void update(GameCharacter character, int position){
        list.set(position, new MainItemViewModel(character));
    }
}