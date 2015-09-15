package amagi82.modularcharactersheetcreator.ui.edit.axis;

import android.content.res.Resources;
import android.databinding.BaseObservable;

import amagi82.modularcharactersheetcreator.entities.characters.Splat;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.SplatIcon;

public class AxisItemViewModel extends BaseObservable {
    private String url;
    private int title;

    public AxisItemViewModel(Resources res, Splat splat) {
        url = new SplatIcon(res, splat).getUrl();
        title = splat.title();
    }

    public String getUrl() {
        return url;
    }

    public int getTitle() {
        return title;
    }
}
