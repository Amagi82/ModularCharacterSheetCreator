package amagi82.modularcharactersheetcreator.ui.create.name;

import android.view.View;

import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Splat;
import amagi82.modularcharactersheetcreator.ui.create._events.ResetSelectionEvent;
import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto;

public class AxisCardViewModel {
    public final Splat splat;
    public final String axisTitle;
    public final boolean isLeft;

    public AxisCardViewModel(Splat splat, String axisTitle, boolean isLeft) {
        this.axisTitle = axisTitle;
        this.splat = splat;
        this.isLeft = isLeft;
    }

    public void onResetClicked(View view) {
        Otto.BUS.get().post(new ResetSelectionEvent(isLeft? GameCharacter.LEFT : GameCharacter.RIGHT));
    }
}
