package amagi82.modularcharactersheetcreator.ui.edit.axis;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.util.List;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.characters.Splat;
import amagi82.modularcharactersheetcreator.models.games.GameSystem;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.ScreenSize;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.SplatIcon;
import me.tatarka.bindingcollectionadapter.ItemView;

public class AxisViewModel {
    public final ObservableArrayList<AxisItemViewModel> list = new ObservableArrayList<>();
    public final ItemView itemView = ItemView.of(BR.axisItemViewModel, R.layout.tile_edit_axis);
    public final ObservableField<String> title = new ObservableField<>();
    private final int imageSize;
    private final Resources res;
    private final boolean isLeft;
    private GameCharacter currentCharacter;

    public AxisViewModel(Context context, GameCharacter character, boolean isLeft) {
        res = context.getResources();
        imageSize = getImageSize(new ScreenSize(context).getWidth());
        this.isLeft = isLeft;
        currentCharacter = character;
        update(character, null);
    }

    public void update(GameCharacter character, @Nullable Splat splat) {
        if (character == null || character.getGameSystem() == null) {
            list.clear();
            return;
        }
        GameSystem updatedSystem = character.getGameSystem();
        if (currentCharacter.getGameSystem() == null || currentCharacter.getGameSystem().getClass() != updatedSystem.getClass()) list.clear();

        if (isLeft) checkLeft(updatedSystem, splat);
        else checkRight(updatedSystem, splat == null ? character.left() : splat);

        currentCharacter = character;
    }

    private void checkLeft(GameSystem system, Splat splat) {
        if (list.size() == 0) addItemModels(system.getListLeft(splat), system.getLeftTitle());
        else if (!system.isLeftListFinal() && list.get(0).getTitle() != system.getListLeft(splat).get(0).title()) {
            list.clear();
            addItemModels(system.getListLeft(splat), system.getLeftTitle());
        }
    }

    private void checkRight(GameSystem system, Splat splat) {
        if (list.size() == 0) {
            if (system.isRightListFinal() || splat != null)
                addItemModels(system.getListRight(splat), system.getRightTitle(splat));
        } else if (!system.isRightListFinal() && !system.getListRight(currentCharacter.left()).equals(system.getListRight(splat))) {
            list.clear();
            addItemModels(system.getListRight(splat), system.getRightTitle(splat));
        }
    }

    private void addItemModels(List<Splat> list, @StringRes int title) {
        for (Splat splat : list) {
            this.list.add(new AxisItemViewModel(new SplatIcon(getString(splat.url()), imageSize).getUrl(), splat));
        }
        this.title.set(String.format(getString(R.string.choose), getString(title)));
    }

    private String getString(@StringRes int title) {
        return res.getString(title);
    }

    private int getImageSize(int screenWidth) {
        int margins = res.getDimensionPixelSize(R.dimen.card_margin) * 2;
        int spanCount = res.getInteger(R.integer.character_axis_span_count);
        int widthAvail = screenWidth - margins;
        return (widthAvail - margins) / spanCount;
    }
}
