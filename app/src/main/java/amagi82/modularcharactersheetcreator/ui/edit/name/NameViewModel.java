package amagi82.modularcharactersheetcreator.ui.edit.name;

import android.content.res.Resources;
import android.databinding.ObservableArrayList;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.entities.characters.Splat;
import amagi82.modularcharactersheetcreator.entities.games.GameSystem;
import amagi82.modularcharactersheetcreator.ui.edit.axis.AxisItemViewModel;
import amagi82.modularcharactersheetcreator.ui.edit.game.GameItemViewModel;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.CompositeItemBinder;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ConditionalDataBinder;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ItemBinder;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.SplatIcon;

public class NameViewModel {
    private ObservableArrayList<ItemViewModel> list = new ObservableArrayList<>();
    private Resources res;

    @SuppressWarnings("ConstantConditions")
    public NameViewModel(Resources res, GameCharacter character) {
        this.res = res;
        update(character);
    }

    public void update(GameCharacter character){
        list.clear();
        int imageSize = res.getDimensionPixelSize(R.dimen.character_circle_icon_size);
        GameSystem system = character.getGameSystem();
        Splat left = character.left();
        Splat right = character.right();
        if(system != null) {
            list.add(new GameItemViewModel(system.getGameUrl()));
            if(left != null) {
                list.add(new AxisItemViewModel(new SplatIcon(res.getString(left.url()), imageSize).getUrl(), left, system.getLeftTitle()));
                if(right != null) {
                    list.add(new AxisItemViewModel(new SplatIcon(res.getString(right.url()), imageSize).getUrl(), right, system.getRightTitle(left)));
                    list.add(new NameItemViewModel(character.name(), character.image() == null? null : character.image().uri()));
                }
            }
        }
    }

    public ObservableArrayList<ItemViewModel> getList() {
        return list;
    }

    public ItemBinder<ItemViewModel> itemViewBinder(){
        return new CompositeItemBinder<>(
                new GameItemBinder(BR.gameItemViewModel, R.layout.item_edit_name_game),
                new AxisItemBinder(BR.axisItemViewModel, R.layout.item_edit_name_axis),
                new NameItemBinder(BR.nameItemViewModel, R.layout.item_edit_name)
        );
    }

    public class GameItemBinder extends ConditionalDataBinder<ItemViewModel>{
        public GameItemBinder(int binding, int layoutId) {
            super(binding, layoutId);
        }

        @Override public boolean canHandle(ItemViewModel model) {
            return model instanceof GameItemViewModel;
        }
    }

    public class AxisItemBinder extends ConditionalDataBinder<ItemViewModel>{
        public AxisItemBinder(int binding, int layoutId) {
            super(binding, layoutId);
        }

        @Override public boolean canHandle(ItemViewModel model) {
            return model instanceof AxisItemViewModel;
        }
    }

    public class NameItemBinder extends ConditionalDataBinder<ItemViewModel>{
        public NameItemBinder(int binding, int layoutId) {
            super(binding, layoutId);
        }

        @Override public boolean canHandle(ItemViewModel model) {
            return model instanceof NameItemViewModel;
        }
    }
}
