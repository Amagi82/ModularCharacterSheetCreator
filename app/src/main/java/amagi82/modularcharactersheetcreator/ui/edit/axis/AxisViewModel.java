package amagi82.modularcharactersheetcreator.ui.edit.axis;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.util.List;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.entities.characters.Splat;
import amagi82.modularcharactersheetcreator.entities.games.GameSystem;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ItemBinder;
import amagi82.modularcharactersheetcreator.ui.xtras.databinding.ItemBinderBase;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.ScreenSize;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.SplatIcon;

public class AxisViewModel extends BaseObservable {
    @Bindable private ObservableArrayList<AxisItemViewModel> list = new ObservableArrayList<>();
    @Bindable private String title;
    private int gridImageSize;
    private Resources res;
    private boolean isLeft;

    public AxisViewModel(Context context, GameCharacter character, boolean isLeft) {
        res = context.getResources();
        gridImageSize = getGridImageSize(new ScreenSize(context).getWidth());
        this.isLeft = isLeft;
        updateList(character, null);
    }

    public void updateList(GameCharacter character, @Nullable Splat splat) {
        GameSystem system = character.getGameSystem();
        if (system == null) return;
        if (isLeft) updateItemModels(system.getListLeft(splat), system.getLeftTitle());
        else if (character.left() != null)
            updateItemModels(system.getListRight(splat == null? character.left() : splat), system.getRightTitle(character.left()));
    }

    private void updateItemModels(List<Splat> list, @StringRes int title) {
        this.list.clear();
        for (Splat splat : list) {
            this.list.add(new AxisItemViewModel(new SplatIcon(getString(splat.url()), gridImageSize).getUrl(), splat));
        }
        setTitle(title);
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

    private int getGridImageSize(int screenWidth) {
        int margins = res.getDimensionPixelSize(R.dimen.card_margin) * 2;
        int spanCount = res.getInteger(R.integer.character_axis_span_count);
        int widthAvail = screenWidth - margins;
        return (widthAvail - margins) / spanCount;
    }

    public ObservableArrayList<AxisItemViewModel> getList() {
        return list;
    }

    public ItemBinder<AxisItemViewModel> itemViewBinder() {
        return new ItemBinderBase<>(BR.axisItemViewModel, R.layout.item_edit_axis);
    }
}
