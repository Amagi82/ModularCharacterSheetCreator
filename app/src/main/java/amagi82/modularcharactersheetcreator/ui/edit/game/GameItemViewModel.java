package amagi82.modularcharactersheetcreator.ui.edit.game;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import amagi82.modularcharactersheetcreator.entities.games.GameSystem;

public class GameItemViewModel extends BaseObservable {
    @Bindable private int url;

    public GameItemViewModel(GameSystem system) {
        url = system.getGameUrl();
    }

    public int getUrl() {
        return url;
    }
}
