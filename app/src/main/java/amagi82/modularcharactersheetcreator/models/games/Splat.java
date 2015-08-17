package amagi82.modularcharactersheetcreator.models.games;

import android.support.annotation.StringRes;

import amagi82.modularcharactersheetcreator.R;

import static amagi82.modularcharactersheetcreator.App.NONE;

/*
    Splats are a generic term for factions or groups in the Storyteller/Storytelling system. Also known as "character axis".
 */
public class Splat {
    @StringRes private int title;
    @StringRes private int url;
    private boolean endPoint;

    public Splat() {
        this(NONE);
    }

    public Splat(@StringRes int title) {
        this(title, R.string.url_default);
    }

    public Splat(@StringRes int title, boolean endPoint) {
        this(title, R.string.url_default, endPoint);
    }

    public Splat(@StringRes int title, @StringRes int url) {
        this(title, url, true);
    }

    public Splat(@StringRes int title, @StringRes int url, boolean endPoint) {
        this.title = title;
        this.url = url;
        this.endPoint = endPoint;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(@StringRes int title) {
        this.title = title;
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