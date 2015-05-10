package amagi82.modularcharactersheetcreator.models.modules;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/*
    Module with bold title on the left and normal text beside it. Text can be immediately beside it or right-aligned. Used for basic character
    info, like "Name: Black Widow"
 */

public class TitleTextBlockModule extends Module implements Serializable {


    ArrayList<String> titles;
    HashMap<String, String> textMap;
    boolean rightAlign;

    public TitleTextBlockModule() {
        super(ViewType.TITLETEXTBLOCK);
    }

    public ArrayList<String> getTitles() {
        return titles;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public HashMap<String, String> getTextMap() {
        return textMap;
    }

    public void setTextMap(HashMap<String, String> textMap) {
        this.textMap = textMap;
    }

    public boolean isRightAlign() {
        return rightAlign;
    }

    public void setRightAlign(boolean rightAlign) {
        this.rightAlign = rightAlign;
    }
}