package amagi82.modularcharactersheetcreator.ui.edit.axis;

import android.databinding.BaseObservable;

public class AxisItemViewModel extends BaseObservable {
    private String url;
    private int title;

    public AxisItemViewModel(String url, int title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public int getTitle() {
        return title;
    }
}
