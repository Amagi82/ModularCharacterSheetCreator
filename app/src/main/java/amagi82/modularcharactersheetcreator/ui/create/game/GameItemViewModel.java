package amagi82.modularcharactersheetcreator.ui.create.game;

import android.view.View;

import amagi82.modularcharactersheetcreator.ui.create._events.GameSelectedEvent;
import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto;

public class GameItemViewModel {
    public final String gameTitle;
    public final String url;
    private final int gameId;

    public GameItemViewModel(String gameTitle, String url, int gameId) {
        this.gameTitle = gameTitle;
        this.url = url;
        this.gameId = gameId;
    }

    public void onItemClick(View view) {
        Otto.BUS.get().post(new GameSelectedEvent(gameId));
    }
}
