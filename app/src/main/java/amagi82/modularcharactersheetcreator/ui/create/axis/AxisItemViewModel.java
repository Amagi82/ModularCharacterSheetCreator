package amagi82.modularcharactersheetcreator.ui.create.axis;

import android.view.View;

import amagi82.modularcharactersheetcreator.models.Splat;
import amagi82.modularcharactersheetcreator.ui.create._events.AxisSelectedEvent;
import amagi82.modularcharactersheetcreator.ui.create._events.AxisUpdateEvent;
import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto;

public class AxisItemViewModel{
    public final Splat splat;
    public final int id;

    public AxisItemViewModel(Splat splat, int id) {
        this.splat = splat;
        this.id = id;
    }

    public void onItemClick(View view) {
        Otto.BUS.get().post(splat.isEndPoint()? new AxisSelectedEvent(id) : new AxisUpdateEvent(id));
    }
}
