package amagi82.modularcharactersheetcreator.models.game_systems;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class SubType {

    @JsonField private int url;
    @JsonField private int title;

    public SubType() {
    }

    public SubType(int title) {
        this.title = title;
    }

    public SubType(int title, int url) {
        this.title = title;
        this.url = url;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }
}
