package amagi82.modularcharactersheetcreator.ui.create.game

import android.view.View

import amagi82.modularcharactersheetcreator.ui.create._events.GameSelectedEvent
import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto

class GameItemViewModel(val gameTitle: String, val url: String, private val gameId: Int) {

    fun onItemClick(view: View) {
        Otto.BUS.get().post(GameSelectedEvent(gameId))
    }
}
