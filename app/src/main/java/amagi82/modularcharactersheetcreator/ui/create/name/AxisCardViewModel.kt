package amagi82.modularcharactersheetcreator.ui.create.name

import amagi82.modularcharactersheetcreator.models.GameCharacter
import amagi82.modularcharactersheetcreator.models.Splat
import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto
import amagi82.modularcharactersheetcreator.ui.create._events.ResetSelectionEvent
import android.view.View

class AxisCardViewModel(val splat: Splat?, val axisTitle: String, val isLeft: Boolean) {

    fun onResetClicked(view: View) {
        Otto.BUS.get().post(ResetSelectionEvent(if (isLeft) GameCharacter.LEFT else GameCharacter.RIGHT))
    }
}
