package amagi82.modularcharactersheetcreator.ui.edit.axis;

import amagi82.modularcharactersheetcreator.entities.characters.Splat;

public class AxisItemViewModel {
    private final Splat splat;
    private final String url;

    public AxisItemViewModel(String url, Splat splat) {
        this.url = url;
        this.splat = splat;
    }

    public Splat getSplat() {
        return splat;
    }

    public String getUrl() {
        return url;
    }

    public int getTitle() {
        return splat.title();
    }
}
