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
    @Bindable private String title;

    public AxisViewModel(List<Splat> list) {
        this.list.addAll(list);
    }

    public void replaceAll(List<Splat> list){
        this.list.clear();
        this.list.addAll(list);
    }

    public void addAll(List<Splat> list){
        this.list.addAll(list);
    }

    public ObservableArrayList<Splat> getList() {
        return list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ItemBinder<AxisItemViewModel> itemBinder(){
        return new ItemBinderBase<>(BR.viewModel, R.layout.item_edit_tile_title);
    }
}
