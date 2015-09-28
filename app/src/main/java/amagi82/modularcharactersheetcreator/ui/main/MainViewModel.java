package amagi82.modularcharactersheetcreator.ui.main;

import android.databinding.ObservableArrayList;
import android.util.Log;

import java.util.List;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ItemBinder;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ItemBinderBase;

public class MainViewModel {
    private ObservableArrayList<MainItemViewModel> list = new ObservableArrayList<>();

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

    public void remove(GameCharacter character){
        int index = getIndex(character.entityId());
        if(index >= 0) list.remove(index);
    }

    public void update(GameCharacter character){
        int index = getIndex(character.entityId());
        if(index >=0) list.set(index, new MainItemViewModel(character));
        else {
            Log.i(null, "Cannot update character- no matching EntityId found. Adding new character instead");
            add(character);
        }
    }

    private int getIndex(String entityId){
        int size = list.size();
        for (int i = 0; i < size; i++){
            if(list.get(i).getEntityId().equals(entityId)) return i;
        }
        return -1;
    }

    public ObservableArrayList<MainItemViewModel> getList() {
        return list;
    }

    public ItemBinder<MainItemViewModel> itemViewBinder(){
        return new ItemBinderBase<>(BR.mainItemViewModel, R.layout.item_main);
    }
}
