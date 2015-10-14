package amagi82.modularcharactersheetcreator.ui.edit.game;

import android.view.View;

import amagi82.modularcharactersheetcreator.ui.xtras.utils.Otto;

public class GameItemViewModel {
    public final int url;
    public final int gameTitle;

    public GameItemViewModel(int url, int gameTitle) {
        this.url = url;
        this.gameTitle = gameTitle;
    }

    public void onItemClick(View view) {
        Otto.BUS.getBus().post(new GameSelectedEvent(gameTitle));
    }
}
