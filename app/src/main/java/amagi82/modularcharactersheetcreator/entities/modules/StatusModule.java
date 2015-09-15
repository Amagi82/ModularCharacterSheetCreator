package amagi82.modularcharactersheetcreator.entities.modules;


public class StatusModule extends Module {

    int valueMin;
    int value;
    int valueMax;
    int numStars;

    public StatusModule() {
        super(Type.STATUS);
    }

    public StatusModule(String title) {
        this(title, null);
    }

    public StatusModule(String title, String text) {
        this(title, text, 0, 10);
    }

    public StatusModule(String title, int valueMin, int valueMax) {
        this(title, null, valueMin, valueMax);
    }

    public StatusModule(String title, String text, int valueMin, int valueMax) {
        super(Type.STATUS);
        setTitle(title);
        setText(text);
        this.valueMin = valueMin;
        value = valueMin;
        this.valueMax = valueMax;
        this.numStars = valueMax;
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

    public int getValueMin() {
        return valueMin;
    }

    public void setValueMin(int valueMin) {
        this.valueMin = valueMin;
    }
}