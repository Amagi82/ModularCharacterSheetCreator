package amagi82.modularcharactersheetcreator.ui.edit.axis;

import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.support.annotation.StringRes;

import java.util.List;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.entities.characters.Splat;
import amagi82.modularcharactersheetcreator.entities.games.GameSystem;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ItemBinder;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ItemBinderBase;

public class AxisViewModel extends BaseObservable {
    @Bindable private ObservableArrayList<Splat> list;
    @Bindable private String title;
    private Resources res;

    public AxisViewModel(Resources res, List<Splat> list) {
        this.res = res;
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

    public void update(GameCharacter character, boolean isLeft){
        GameSystem system = character.getGameSystem();
        if (isLeft) {
            replaceAll(system.getListLeft(character.left()));
            setTitle(system.getLeftTitle());
        } else if (character.left() != null) {
            replaceAll(system.getListRight(character.left()));
            setTitle(system.getRightTitle(character.left()));
        }
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(@StringRes int title) {
        this.title = String.format(getString(R.string.choose), getString(title));
    }

    private String getString(@StringRes int title) {
        return res.getString(title);
    }

    public ItemBinder<AxisItemViewModel> itemBinder(){
        return new ItemBinderBase<>(BR.axis, R.layout.item_edit_tile_axis);
    }
}
