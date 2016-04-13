package amagi82.modularcharactersheetcreator.ui.create.name

import android.view.View

import amagi82.modularcharactersheetcreator.models.GameCharacter
import amagi82.modularcharactersheetcreator.ui.create._events.ResetSelectionEvent
import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto

class GameCardViewModel(val url: String, val gameTitle: String) {

    fun onResetClicked(view: View) {
        Otto.BUS.get().post(ResetSelectionEvent(GameCharacter.START))
    }
}
