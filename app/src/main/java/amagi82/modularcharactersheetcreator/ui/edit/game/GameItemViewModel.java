package amagi82.modularcharactersheetcreator.ui.edit.game;

import amagi82.modularcharactersheetcreator.ui.edit.name.ItemViewModel;

public class GameItemViewModel extends ItemViewModel {
    private final int url;

    public GameItemViewModel(int url) {
        this.url = url;
    }

    public int getUrl() {
        return url;
    }
}
