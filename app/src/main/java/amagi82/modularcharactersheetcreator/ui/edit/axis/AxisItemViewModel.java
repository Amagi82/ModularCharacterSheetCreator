package amagi82.modularcharactersheetcreator.ui.edit.axis;

import amagi82.modularcharactersheetcreator.entities.characters.Splat;
import amagi82.modularcharactersheetcreator.ui.edit.name.ItemViewModel;

public class AxisItemViewModel extends ItemViewModel{
    private final String url;
    private final Splat splat;
    private final int axisTitle;

    public AxisItemViewModel(String url, Splat splat) {
        this(url, splat, 0);
    }

    public AxisItemViewModel(String url, Splat splat, int axisTitle) {
        this.axisTitle = axisTitle;
        this.url = url;
        this.splat = splat;
    }

    public String getUrl() {
        return url;
    }

    public Splat getSplat() {
        return splat;
    }

    public int getAxisTitle() {
        return axisTitle;
    }

    public int getTitle() {
        return splat.title();
    }
}
