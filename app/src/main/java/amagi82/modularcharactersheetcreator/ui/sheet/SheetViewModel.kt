package amagi82.modularcharactersheetcreator.ui.sheet

import amagi82.modularcharactersheetcreator.BR
import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.GameCharacter
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import me.tatarka.bindingcollectionadapter.BindingViewPagerAdapter
import me.tatarka.bindingcollectionadapter.ItemView

class SheetViewModel(character: GameCharacter) {
    val character = ObservableField<GameCharacter>()
    val tabs = ObservableArrayList<SheetTabViewModel>()
    val itemView = ItemView.of(BR.sheetTabViewModel, R.layout.sheet_tab)
    val tabTitles: BindingViewPagerAdapter.PageTitles<SheetTabViewModel> = BindingViewPagerAdapter.PageTitles<amagi82.modularcharactersheetcreator.ui.sheet.SheetTabViewModel> { position, tab -> tab.title.get() }

    init {
        //noinspection ConstantConditions
        for (sheet in character.sheets) {
            tabs.add(SheetTabViewModel(sheet))
        }
        this.character.set(character)
    }
}
