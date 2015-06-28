package amagi82.modularcharactersheetcreator.models.game_systems;

public class SubType {

    private int url;
    private int title;

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
