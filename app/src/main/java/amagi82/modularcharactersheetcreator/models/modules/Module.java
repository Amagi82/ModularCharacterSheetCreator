package amagi82.modularcharactersheetcreator.models.modules;


import java.io.Serializable;

/*
    Base class for all card modules
 */

public abstract class Module implements Serializable{

    public enum ViewType {TEXT, HEADER, TEXTTITLE, WODSTATBLOCK, WODSTATBLOCKWITHSPECIALTIES, TITLETEXTBLOCK, WODTITLESTAT, WODBLOODGRADIENT}
    private ViewType viewType;
    private int desiredWidth;
    private int desiredHeight;

    public Module(ViewType viewType) {
        this.viewType = viewType;
    }

    public int getDesiredWidth() {
        return desiredWidth;
    }

    public void setDesiredWidth(int desiredWidth) {
        this.desiredWidth = desiredWidth;
    }

    public int getDesiredHeight() {
        return desiredHeight;
    }

    public void setDesiredHeight(int desiredHeight) {
        this.desiredHeight = desiredHeight;
    }

    public ViewType getViewType() {
        return viewType;
    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }
}
