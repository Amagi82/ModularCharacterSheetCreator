package amagi82.modularcharactersheetcreator.ui.sheet;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.characters.Sheet;
import me.tatarka.bindingcollectionadapter.BindingViewPagerAdapter;
import me.tatarka.bindingcollectionadapter.ItemView;

public class SheetViewModel {
    public final ObservableField<GameCharacter> character = new ObservableField<>();
    public final ObservableArrayList<SheetTabViewModel> tabs = new ObservableArrayList<>();
    public final ItemView itemView = ItemView.of(BR.sheetTabViewModel, R.layout.view_sheet_tab);
    public final BindingViewPagerAdapter.PageTitles<SheetTabViewModel> tabTitles = new BindingViewPagerAdapter.PageTitles<SheetTabViewModel>() {
        @Override public CharSequence getPageTitle(int position, SheetTabViewModel tab) {
            return tab.title.get();
        }
    };

    public SheetViewModel(GameCharacter character) {
        //noinspection ConstantConditions
        for (Sheet sheet : character.sheets()) {
            Log.i("SheetViewModel", "Adding tab for: "+sheet);
            tabs.add(new SheetTabViewModel(sheet));
        }
        this.character.set(character);
    }
}
