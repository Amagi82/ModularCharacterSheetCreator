package amagi82.modularcharactersheetcreator.models;

public class Choice {

    private int name;
    private int drawable;
    private int baseUrl;
    private int url;
    private boolean hasDrawable;

    public Choice(int name, int drawable) {
        this.name = name;
        this.drawable = drawable;
        hasDrawable = true;
    }

    public Choice(int name, int baseUrl, int url) {
        this.name = name;
        this.baseUrl = baseUrl;
        this.url = url;
        hasDrawable = false;
    }

    public int getName() {
        return name;
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
}
