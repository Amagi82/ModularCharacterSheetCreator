package amagi82.modularcharactersheetcreator.models.modules;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/*
    WoD-specific module for a list of stats. Has a title up top, and text descriptions with circular rating bar dots to the right.
 */

public class WoDStatBlockModule extends Module implements Serializable {


    String title;
    ArrayList<String> stat;
    HashMap<String, Integer> statValue;

    public WoDStatBlockModule() {
        super(ViewType.WODSTATBLOCK);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getStat() {
        return stat;
    }

    public void setStat(ArrayList<String> stat) {
        this.stat = stat;
    }

    public HashMap<String, Integer> getStatValue() {
        return statValue;
    }

    public void setStatValue(HashMap<String, Integer> statValue) {
        this.statValue = statValue;
    }
}
