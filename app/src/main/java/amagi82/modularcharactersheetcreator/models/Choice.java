package amagi82.modularcharactersheetcreator.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import amagi82.modularcharactersheetcreator.App;

@JsonObject
public class Choice {

    @JsonField private String eName;
    @JsonField private int title;
    @JsonField private int drawable = App.NONE;
    @JsonField private int baseUrl = App.NONE;
    @JsonField private int url = App.NONE;
    private int position;

    public Choice() {
    }

    public Choice(String eName, int title){
        this.eName = eName;
        this.title = title;
    }

    public Choice(String eName, int title, int drawable) {
        this.eName = eName;
        this.title = title;
        this.drawable = drawable;
    }

    public Choice(String eName, int title, int baseUrl, int url) {
        this.eName = eName;
        this.title = title;
        this.baseUrl = baseUrl;
        this.url = url;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
