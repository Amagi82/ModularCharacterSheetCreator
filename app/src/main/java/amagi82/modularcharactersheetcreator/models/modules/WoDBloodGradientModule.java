package amagi82.modularcharactersheetcreator.models.modules;


import java.io.Serializable;

/*
    Module for easily tracking blood pool with Vampire.
 */

public class WoDBloodGradientModule extends Module implements Serializable {

    int currentBlood;
    int maxBlood;
    String title;
    String bloodPerTurn;

    public WoDBloodGradientModule() {
        super(ViewType.WODBLOODGRADIENT);
    }

    public int getCurrentBlood() {
        return currentBlood;
    }

    public void setCurrentBlood(int currentBlood) {
        this.currentBlood = currentBlood;
    }

    public int getMaxBlood() {
        return maxBlood;
    }

    public void setMaxBlood(int maxBlood) {
        this.maxBlood = maxBlood;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBloodPerTurn() {
        return bloodPerTurn;
    }

    public void setBloodPerTurn(String bloodPerTurn) {
        this.bloodPerTurn = bloodPerTurn;
    }
}