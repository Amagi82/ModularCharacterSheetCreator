package amagi82.modularcharactersheetcreator.models.modules;


import amagi82.modularcharactersheetcreator.R;

public class StatusModule extends Module {

    private int value;
    private int valueMax;
    private int numStars;
    private boolean isCircle;

    public StatusModule() {
        super(Type.STATUS, R.layout.module_status);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValueMax() {
        return valueMax;
    }

    public void setValueMax(int valueMax) {
        this.valueMax = valueMax;
    }

    public int getNumStars() {
        return numStars;
    }

    public void setNumStars(int numStars) {
        this.numStars = numStars;
    }

    public boolean isCircle() {
        return isCircle;
    }

    public void setIsCircle(boolean isCircle) {
        this.isCircle = isCircle;
    }
}
