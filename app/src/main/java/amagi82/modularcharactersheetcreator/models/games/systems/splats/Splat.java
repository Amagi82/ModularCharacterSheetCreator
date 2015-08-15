package amagi82.modularcharactersheetcreator.models.games.systems.splats;

import android.support.annotation.StringRes;

import static amagi82.modularcharactersheetcreator.App.NONE;

public class Splat {
    @StringRes private int title;
    @StringRes private int baseUrl;
    @StringRes private int url;
    private boolean endPoint;

    public Splat() {
        this(NONE);
    }

    public Splat(@StringRes int title) {
        this(title, NONE, NONE);
    }

    public Splat(@StringRes int title, @StringRes int baseUrl, @StringRes int url) {
        this(title, baseUrl, url, true);
    }

    public Splat(@StringRes int title, @StringRes int baseUrl, @StringRes int url, boolean endPoint) {
        this.title = title;
        this.baseUrl = baseUrl;
        this.url = url;
        this.endPoint = endPoint;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(@StringRes int title) {
        this.title = title;
    }

    public int getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(@StringRes int baseUrl) {
        this.baseUrl = baseUrl;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(@StringRes int url) {
        this.url = url;
    }

    public boolean isEndPoint() {
        return endPoint;
    }

    public void setEndPoint(boolean endPoint) {
        this.endPoint = endPoint;
    }
}
