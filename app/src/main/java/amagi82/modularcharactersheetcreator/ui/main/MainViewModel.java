package amagi82.modularcharactersheetcreator.ui.main;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.util.Log;

import java.util.List;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ItemBinder;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ItemBinderBase;

public class MainViewModel extends BaseObservable {

    @Bindable private ObservableArrayList<GameCharacter> list;

    public MainViewModel(List<GameCharacter> list) {
        this.list.addAll(list);
    }

    public void add(GameCharacter character){
        list.add(character);
    }

    public void remove(GameCharacter character){
        list.remove(character);
    }

    public void update(GameCharacter character){
        int index = getIndex(character);
        if(index >=0) list.set(index, character);
        else {
            Log.i(null, "Cannot update character- no matching EntityId found. Adding new character instead");
            list.add(character);
        }
    }

    private int getIndex(GameCharacter character){
        int size = list.size();
        for (int i = 0; i < size; i++){
            if(list.get(i).entityId().equals(character.entityId())) return i;
        }
        return -1;
    }

    public ObservableArrayList<GameCharacter> getList() {
        return list;
    }

    public ItemBinder<MainItemViewModel> itemBinder(){
        return new ItemBinderBase<>(BR.character, R.layout.item_main);
    }
}
