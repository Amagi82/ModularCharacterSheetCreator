package amagi82.modularcharactersheetcreator.ui.edit.name;

import android.view.View;

import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.characters.Splat;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.Otto;

public class NameAxisItemViewModel {
    public final Splat splat;
    public final int axisTitle;
    public final boolean isLeft;

    public NameAxisItemViewModel(Splat splat, int axisTitle, boolean isLeft) {
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
        Otto.BUS.get().post(new ResetItemEvent(isLeft? GameCharacter.LEFT : GameCharacter.RIGHT));
    }
}
