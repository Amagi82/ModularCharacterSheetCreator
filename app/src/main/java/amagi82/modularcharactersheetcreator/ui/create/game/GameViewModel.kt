package amagi82.modularcharactersheetcreator.ui.create.game

import android.databinding.ObservableArrayList

import amagi82.modularcharactersheetcreator.BR
import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.games.Game
import amagi82.modularcharactersheetcreator.ui._base.BaseViewModel
import me.tatarka.bindingcollectionadapter.ItemView

class GameViewModel : BaseViewModel() {
    val list = ObservableArrayList<GameItemViewModel>()
    val itemView = ItemView.of(BR.gameItemViewModel, R.layout.create_game_item)

    init {
        val items = Game.systems
        for (gameId in items) {
            val system = Game.getSystem(gameId)
            if (system != null) this.list.add(GameItemViewModel(system.gameTitle, system.gameUrl, gameId))
        }
    }
}
