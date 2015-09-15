package amagi82.modularcharactersheetcreator.ui.edit.axis;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;

import java.util.List;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.entities.characters.Splat;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ItemBinder;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ItemBinderBase;

public class AxisViewModel extends BaseObservable {
    @Bindable private ObservableArrayList<Splat> list;

    public AxisViewModel(List<Splat> list) {
        this.list.addAll(list);
    }

    public void add(Splat character){
        list.add(character);
    }

    public void remove(Splat character){
        list.remove(character);
    }

    public ObservableArrayList<Splat> getList() {
        return list;
    }

    public ItemBinder<AxisItemViewModel> itemBinder(){
        return new ItemBinderBase<>(BR.splat, R.layout.item_edit_tile_title);
    }
}
