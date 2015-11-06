package amagi82.modularcharactersheetcreator.ui.main;

import android.databinding.ObservableArrayList;
import android.util.Log;

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
    public final ItemView itemView = ItemView.of(BR.mainItemViewModel, R.layout.main_item);

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

    public void remove(GameCharacter character){
        int position = indexOf(character);
        if(position != -1) list.remove(position);
        else Log.i("MainViewModel", "No character with matching ID found");
    }

    public void update(GameCharacter character){
        int position = indexOf(character);
        if(position != -1) list.set(position, new MainItemViewModel(character));
        else Log.i("MainViewModel", "No character with matching ID found");
        sort();
    }

    public List<GameCharacter> getCharacters(){
        List<GameCharacter> characters = new ArrayList<>(list.size());
        for(MainItemViewModel item : list){
            characters.add(item.getCharacter());
        }
        return characters;
    }

    private int indexOf(GameCharacter character){
        for(int i = 0; i<list.size(); i++){
            if(list.get(i).getCharacter().entityId().equals(character.entityId())) return i;
        }
        return -1;
    }

    private void sort(){
        Collections.sort(list, new Comparator<MainItemViewModel>() {
            @Override public int compare(MainItemViewModel o1, MainItemViewModel o2) {
                return o1.getCharacter().timeStamp() > o2.getCharacter().timeStamp() ? -1 : 1;
            }
        });
    }
}