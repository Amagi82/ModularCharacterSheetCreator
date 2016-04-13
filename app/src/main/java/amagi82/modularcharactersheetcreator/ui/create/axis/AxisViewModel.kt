package amagi82.modularcharactersheetcreator.ui.create.axis

import amagi82.modularcharactersheetcreator.BR
import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.GameCharacter
import amagi82.modularcharactersheetcreator.models.games.Game
import amagi82.modularcharactersheetcreator.ui._base.BaseViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import me.tatarka.bindingcollectionadapter.ItemView

class AxisViewModel(private var currentCharacter: GameCharacter?, private val isLeft: Boolean) : BaseViewModel() {
    val list = ObservableArrayList<AxisItemViewModel>()
    val itemView = ItemView.of(BR.axisItemViewModel, R.layout.create_axis_item)
    val title = ObservableField<String>()

    fun update(character: GameCharacter?, splatId: Int) {
        if (character == null || character.gameSystem() == null) {
            list.clear()
            return
        }
        val updatedSystem = character.gameSystem() ?: return
        if (currentCharacter?.gameSystem() == null || currentCharacter?.gameSystem()?.javaClass !== updatedSystem.javaClass) list.clear()

        if (isLeft)
            checkLeft(updatedSystem, splatId)
        else
            checkRight(updatedSystem, if (splatId == 0) character.leftId else splatId)

        currentCharacter = character
    }

    private fun checkLeft(system: Game, splatId: Int) {
        if (list.size == 0)
            addItemModels(system, splatId)
        else if (!system.isLeftListFinal && list[0].id != system.getListLeft(splatId)[0]) {
            list.clear()
            addItemModels(system, splatId)
        }
        title.set(system.leftTitle)
    }

    private fun checkRight(system: Game, splatId: Int) {
        if (list.size == 0 && (system.isRightListFinal || splatId != 0))
            addItemModels(system, splatId)
        else if (system.getListRight(splatId).size == 0)
            list.clear()
        else if (!system.isRightListFinal && list.size > 0 && list[0].id != system.getListRight(splatId)[0]) {
            list.clear()
            addItemModels(system, splatId)
        }
        title.set(system.getRightTitle(splatId))
    }

    private fun addItemModels(system: Game, splatId: Int) {
        val items = if (isLeft) system.getListLeft(splatId) else system.getListRight(splatId)
        for (id in items) {
            this.list.add(AxisItemViewModel(system.getSplat(id), id))
        }
    }
}
