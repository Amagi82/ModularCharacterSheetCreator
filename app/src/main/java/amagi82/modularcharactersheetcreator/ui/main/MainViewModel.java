package amagi82.modularcharactersheetcreator.ui.main;

import android.databinding.ObservableArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import me.tatarka.bindingcollectionadapter.ItemView;

public class MainViewModel {
    public final ObservableArrayList<MainItemViewModel> list = new ObservableArrayList<>();
    public final ItemView itemView = ItemView.of(BR.mainItemViewModel, R.layout.item_main);

    public MainViewModel(){}

    public MainViewModel(List<GameCharacter> characters) {
        addAll(characters);
    }

    public void addAll(List<GameCharacter> characters){
        this.list.clear();
        for (GameCharacter character : characters) {
            list.add(new MainItemViewModel(character));
        }
        sort();
    }

    public void add(GameCharacter character){
        list.add(0, new MainItemViewModel(character));
    }

    public void remove(int position){
        list.remove(position);
    }

    public void update(GameCharacter character, int position){
        list.set(position, new MainItemViewModel(character));
        sort();
    }

    public List<GameCharacter> getCharacters(){
        List<GameCharacter> characters = new ArrayList<>(list.size());
        for(MainItemViewModel item : list){
            characters.add(item.getCharacter());
        }
        return characters;
    }

    private void sort(){
        Collections.sort(list, new Comparator<MainItemViewModel>() {
            @Override public int compare(MainItemViewModel o1, MainItemViewModel o2) {
                return o1.getCharacter().timeStamp() > o2.getCharacter().timeStamp() ? -1 : 1;
            }
        });
    }
}