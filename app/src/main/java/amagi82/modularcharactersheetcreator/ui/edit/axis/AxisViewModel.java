package amagi82.modularcharactersheetcreator.ui.edit.axis;

import android.content.Context;
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
import amagi82.modularcharactersheetcreator.ui.xtras.utils.ScreenSize;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.SplatIcon;

public class AxisViewModel extends BaseObservable {
    @Bindable private ObservableArrayList<AxisItemViewModel> list = new ObservableArrayList<>();
    @Bindable private String title;
    private Context context;
    private Resources res;
    private boolean isLeft;

    public AxisViewModel(Context context, GameCharacter character, boolean isLeft) {
        this.context = context;
        res = context.getResources();
        this.isLeft = isLeft;
        update(character);
    }

    public void update(GameCharacter character) {
        GameSystem system = character.getGameSystem();
        if (system == null) return;
        if (isLeft) updateModel(system.getListLeft(character.left()), system.getLeftTitle());
        else if (character.left() != null) updateModel(system.getListRight(character.left()), system.getRightTitle(character.left()));
    }

    private void updateModel(List<Splat> list, @StringRes int title) {
        this.list.clear();
        int gridImageSize = getGridImageSize();
        for (Splat splat : list) {
            this.list.add(new AxisItemViewModel(new SplatIcon(getString(splat.url()), gridImageSize).getUrl(), splat.title()));
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

    private int getGridImageSize() {
        int margins = res.getDimensionPixelSize(R.dimen.card_margin) * 2;
        int spanCount = res.getInteger(R.integer.character_axis_span_count);
        int widthAvail = new ScreenSize(context).getWidth() - margins;
        return (widthAvail - margins) / spanCount;
    }

    public ObservableArrayList<AxisItemViewModel> getList() {
        return list;
    }

    public ItemBinder<AxisItemViewModel> itemViewBinder() {
        return new ItemBinderBase<>(BR.axisItemViewModel, R.layout.item_edit_axis);
    }
}
