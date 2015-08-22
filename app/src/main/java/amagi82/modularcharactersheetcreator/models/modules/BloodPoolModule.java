package amagi82.modularcharactersheetcreator.models.modules;

public class BloodPoolModule extends Module {

    int bloodCurrent;
    int bloodMax;
    int bloodPerTurn;

    public BloodPoolModule() {
        this(null, 7, 10, 1);
    }

    public BloodPoolModule(String title) {
        this(title, 7,10,1);
    }

    public BloodPoolModule(String title, int bloodCurrent, int bloodMax, int bloodPerTurn) {
        super(Type.BLOODPOOL);
        setTitle(title);
        this.bloodCurrent = bloodCurrent;
        this.bloodMax = bloodMax;
        this.bloodPerTurn = bloodPerTurn;
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