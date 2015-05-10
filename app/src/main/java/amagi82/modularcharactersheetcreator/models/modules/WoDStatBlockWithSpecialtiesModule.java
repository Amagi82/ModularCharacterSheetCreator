package amagi82.modularcharactersheetcreator.models.modules;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/*
    WoD-specific module for a list of stats. Has a title up top, and text descriptions with circular rating bar dots to the right.
    This module also includes specialties between the two.
 */

public class WoDStatBlockWithSpecialtiesModule extends Module implements Serializable {


    String title;
    ArrayList<String> stat;
    HashMap<String, Integer> statValue;
    HashMap<String, String> statSpecialties;

    public WoDStatBlockWithSpecialtiesModule() {
        super(ViewType.WODSTATBLOCKWITHSPECIALTIES);
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

    public HashMap<String, String> getStatSpecialties() {
        return statSpecialties;
    }

    public void setStatSpecialties(HashMap<String, String> statSpecialties) {
        this.statSpecialties = statSpecialties;
    }
}