package amagi82.modularcharactersheetcreator.ui.edit.name;

import android.content.res.Resources;
import android.databinding.ObservableField;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.characters.Splat;
import amagi82.modularcharactersheetcreator.models.games.GameSystem;
import amagi82.modularcharactersheetcreator.ui.edit.axis.AxisItemViewModel;
import amagi82.modularcharactersheetcreator.ui.edit.game.GameItemViewModel;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.SplatIcon;

public class NameViewModel {
    public final ObservableField<GameItemViewModel> gameItem = new ObservableField<>();
    public final ObservableField<AxisItemViewModel> leftItem = new ObservableField<>();
    public final ObservableField<AxisItemViewModel> rightItem = new ObservableField<>();
    public final ObservableField<NameItemViewModel> nameItem = new ObservableField<>();
    private Resources res;

    public NameViewModel(Resources res, GameCharacter character) {
        this.res = res;
        update(character);
    }

    public void update(GameCharacter character){
        boolean reset = false;
        GameSystem game = character.getGameSystem();
        if(game == null) reset = true;
        gameItem.set(new GameItemViewModel(reset ? R.string.url_default : game.getGameUrl()));

        Splat left = character.left();
        if(left == null) reset = true;
        int imageSize = res.getDimensionPixelSize(R.dimen.card_max_image_height);
        leftItem.set(reset ? getDefaultAxisItem() : new AxisItemViewModel(new SplatIcon(res.getString(left.url()), imageSize).getUrl(), left, game.getLeftTitle()));

        Splat right = character.right();
        if(right == null) reset = true;
        rightItem.set(reset? getDefaultAxisItem() : new AxisItemViewModel(new SplatIcon(res.getString(right.url()), imageSize).getUrl(), right, game.getRightTitle(left)));

        //noinspection ConstantConditions
        nameItem.set(new NameItemViewModel(character.name(), character.image() == null ? null : character.image().uri()));
    }

    private AxisItemViewModel getDefaultAxisItem(){
        return new AxisItemViewModel("", Splat.create(R.string.no_data), R.string.no_data);
    }
}
