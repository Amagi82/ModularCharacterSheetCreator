package amagi82.modularcharactersheetcreator.ui.create.axis

import amagi82.modularcharactersheetcreator.models.Splat
import amagi82.modularcharactersheetcreator.extras.utils.Otto
import amagi82.modularcharactersheetcreator.ui.create._events.AxisSelectedEvent
import amagi82.modularcharactersheetcreator.ui.create._events.AxisUpdateEvent
import android.view.View

class AxisItemViewModel(val splat: Splat, val id: Int) {

    fun onItemClick(view: View) {
        Otto.BUS.get().post(if (splat.isEndPoint) AxisSelectedEvent(id) else AxisUpdateEvent(id))
    }
}
