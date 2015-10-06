package amagi82.modularcharactersheetcreator.ui.edit.axis;

import amagi82.modularcharactersheetcreator.models.characters.Splat;

public class AxisItemViewModel{
    public final String url;
    public final Splat splat;
    public final int axisTitle;

    public AxisItemViewModel(String url, Splat splat) {
        this(url, splat, 0);
    }

    public AxisItemViewModel(String url, Splat splat, int axisTitle) {
        this.axisTitle = axisTitle;
        this.url = url;
        this.splat = splat;
    }

    public int getTitle() {
        return splat.title();
    }
}
