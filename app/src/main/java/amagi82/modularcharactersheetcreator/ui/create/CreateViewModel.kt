package amagi82.modularcharactersheetcreator.ui.create

import amagi82.modularcharactersheetcreator.BR
import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.GameCharacter
import amagi82.modularcharactersheetcreator.ui._base.BaseViewModel
import amagi82.modularcharactersheetcreator.ui.create.axis.AxisViewModel
import amagi82.modularcharactersheetcreator.ui.create.game.GameViewModel
import amagi82.modularcharactersheetcreator.ui.create.name.NameViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import me.tatarka.bindingcollectionadapter.BaseItemViewSelector
import me.tatarka.bindingcollectionadapter.ItemView
import me.tatarka.bindingcollectionadapter.ItemViewSelector

class CreateViewModel(character: GameCharacter) {
    val splashUrl = ObservableField<String>()
    val page = ObservableInt()
    val isFabShown = ObservableBoolean()
    val pages = ObservableArrayList<BaseViewModel>()
    val itemView: ItemViewSelector<BaseViewModel> = object : BaseItemViewSelector<BaseViewModel>() {
        override fun select(itemView: ItemView, position: Int, item: BaseViewModel) {
            if (position == 0)
                itemView.set(BR.gameViewModel, R.layout.create_tab_game)
            else if (position == 3)
                itemView.set(BR.nameViewModel, R.layout.create_tab_name)
            else
                itemView.set(BR.axisViewModel, R.layout.create_tab_axis)
        }
    }

    init {
        pages.add(GameViewModel())
        pages.add(AxisViewModel(character, true))
        pages.add(AxisViewModel(character, false))
        pages.add(NameViewModel())
    }

    fun update(character: GameCharacter) {
        splashUrl.set(if (character.gameSystem() == null) null else character.gameSystem()?.splashUrl)
        page.set(character.progress())
        isFabShown.set(character.isComplete())

        (pages[1] as AxisViewModel).update(character, 0)
        (pages[2] as AxisViewModel).update(character, 0)
        (pages[3] as NameViewModel).update(character)
    }

    //Selection was not an end point. Display the sub-list.
    fun update(character: GameCharacter, splatId: Int) {
        (pages[1] as AxisViewModel).update(character, splatId)
        (pages[2] as AxisViewModel).update(character, splatId)
    }

    fun softKeyboardVisible() {
        isFabShown.set(false)
    }
}
