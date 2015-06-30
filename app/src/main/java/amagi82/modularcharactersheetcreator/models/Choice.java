package amagi82.modularcharactersheetcreator.models;

public class Choice {

    private String eName;
    private int title;
    private int drawable;
    private int baseUrl;
    private int url;
    private boolean hasDrawable;

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
}
