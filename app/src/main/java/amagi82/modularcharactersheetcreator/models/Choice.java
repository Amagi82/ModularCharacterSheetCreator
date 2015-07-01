package amagi82.modularcharactersheetcreator.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class Choice {

    @JsonField private String eName;
    @JsonField private int title;
    @JsonField private int drawable;
    @JsonField private int baseUrl;
    @JsonField private int url;
    @JsonField private boolean hasDrawable;

    public Choice(String eName, int title){
        this(eName, title, 0, 0);
    }

    public Choice(String eName, int title, int drawable) {
        this.eName = eName;
        this.title = title;
        this.drawable = drawable;
        hasDrawable = true;
    }

    public Choice(String eName, int title, int baseUrl, int url) {
        this.eName = eName;
        this.title = title;
        this.baseUrl = baseUrl;
        this.url = url;
        hasDrawable = false;
    }

    public String geteName() {
        return eName;
    }

    public int getTitle() {
        return title;
    }

    public int getDrawable() {
        return drawable;
    }

    public int getBaseUrl() {
        return baseUrl;
    }

    public int getUrl() {
        return url;
    }

    public boolean hasDrawable() {
        return hasDrawable;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public void setBaseUrl(int baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public boolean isHasDrawable() {
        return hasDrawable;
    }

    public void setHasDrawable(boolean hasDrawable) {
        this.hasDrawable = hasDrawable;
    }
}
