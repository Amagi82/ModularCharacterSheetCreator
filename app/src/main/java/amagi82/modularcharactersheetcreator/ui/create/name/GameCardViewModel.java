package amagi82.modularcharactersheetcreator.ui.create.name;

import android.view.View;

import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.ui.create._events.ResetSelectionEvent;
import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto;

public class GameCardViewModel {
    public final int url;
    public final int gameTitle;

    public GameCardViewModel(int url, int gameTitle) {
        this.url = url;
        this.gameTitle = gameTitle;
    }

    public void onResetClicked(View view) {
        Otto.BUS.get().post(new ResetSelectionEvent(GameCharacter.START));
    }
}
