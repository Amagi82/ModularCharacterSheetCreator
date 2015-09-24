package amagi82.modularcharactersheetcreator.ui.edit.axis;

import android.databinding.BaseObservable;

import amagi82.modularcharactersheetcreator.entities.characters.Splat;

public class AxisItemViewModel extends BaseObservable {
    private Splat splat;
    private String url;

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
