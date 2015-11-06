package amagi82.modularcharactersheetcreator.ui.create.game;

import android.view.View;

import amagi82.modularcharactersheetcreator.ui.create._events.GameSelectedEvent;
import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto;

public class GameItemViewModel {
    public final int url;
    public final int gameTitle;

    public GameItemViewModel(int url, int gameTitle) {
        this.url = url;
        this.gameTitle = gameTitle;
    }

    public void onItemClick(View view) {
        Otto.BUS.get().post(new GameSelectedEvent(gameTitle));
    }
}
