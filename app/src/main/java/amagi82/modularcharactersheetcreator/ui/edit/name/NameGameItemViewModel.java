package amagi82.modularcharactersheetcreator.ui.edit.name;

import android.view.View;

import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.Otto;

public class NameGameItemViewModel {
    public final int url;
    public final int gameTitle;

    public NameGameItemViewModel(int url, int gameTitle) {
        this.url = url;
        this.gameTitle = gameTitle;
    }

    public void onResetClicked(View view) {
        Otto.BUS.get().post(new ResetItemEvent(GameCharacter.START));
    }
}
