package amagi82.modularcharactersheetcreator.ui.create.name;

import android.view.View;

import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.characters.Splat;
import amagi82.modularcharactersheetcreator.ui.create._events.ResetSelectionEvent;
import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto;

public class AxisCardViewModel {
    public final Splat splat;
    public final int axisTitle;
    public final boolean isLeft;

    public AxisCardViewModel(Splat splat, int axisTitle, boolean isLeft) {
        this.axisTitle = axisTitle;
        this.splat = splat;
        this.isLeft = isLeft;
    }

    public int getTitle(){
        return splat.title();
    }

    public int getUrl(){
        return splat.url();
    }

    public void onResetClicked(View view) {
        Otto.BUS.get().post(new ResetSelectionEvent(isLeft? GameCharacter.LEFT : GameCharacter.RIGHT));
    }
}
