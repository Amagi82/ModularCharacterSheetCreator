package amagi82.modularcharactersheetcreator.models.modules;

import amagi82.modularcharactersheetcreator.R;

public class BloodPoolModule extends Module {

    private int bloodCurrent;
    private int bloodMax;
    private int bloodPerTurn;

    public BloodPoolModule() {
        super(Type.BLOODPOOL, R.layout.module_bloodpool);
    }

    public int getBloodCurrent() {
        return bloodCurrent;
    }

    public void setBloodCurrent(int bloodCurrent) {
        if(bloodCurrent < 0) bloodCurrent = 0;
        this.bloodCurrent = bloodCurrent;
    }

    public int getBloodMax() {
        return bloodMax;
    }

    public void setBloodMax(int bloodMax) {
        if(bloodMax < 5) bloodMax = 5;
        this.bloodMax = bloodMax;
    }

    public int getBloodPerTurn() {
        return bloodPerTurn;
    }

    public void setBloodPerTurn(int bloodPerTurn) {
        if(bloodPerTurn < 1) bloodPerTurn = 1;
        this.bloodPerTurn = bloodPerTurn;
    }
}