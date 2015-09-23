package amagi82.modularcharactersheetcreator.ui.edit.game;

import android.databinding.BaseObservable;

public class GameItemViewModel extends BaseObservable {
    private int url;

    public GameItemViewModel(int url) {
        this.url = url;
    }

    public int getUrl() {
        return url;
    }
}
