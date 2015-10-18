package amagi82.modularcharactersheetcreator.ui.edit.axis;

import android.view.View;

import amagi82.modularcharactersheetcreator.models.characters.Splat;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.Otto;

public class AxisItemViewModel{
    public final Splat splat;

    public AxisItemViewModel(Splat splat) {
        this.splat = splat;
    }

    public int getTitle(){
        return splat.title();
    }

    public int getUrl(){
        return splat.url();
    }

    public void onItemClick(View view) {
        Otto.BUS.get().post(splat.isEndPoint()? new AxisSelectedEvent(splat) : new AxisUpdateEvent(splat));
    }
}
