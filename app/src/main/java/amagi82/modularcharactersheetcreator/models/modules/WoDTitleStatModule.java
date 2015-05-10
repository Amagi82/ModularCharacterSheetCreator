package amagi82.modularcharactersheetcreator.models.modules;


import java.io.Serializable;

/*
    WoD-specific module for humanity/path, willpower, or blood pool. Has a title up top and a customizable, default 10-dot bar below
 */

public class WoDTitleStatModule extends Module implements Serializable {

    String title;
    int value;
    int valueMax;
    int numStars;
    enum Shape {CIRCLE, SQUARE, HEALTH}
    Shape shape = Shape.CIRCLE;

    public WoDTitleStatModule() {
        super(ViewType.WODTITLESTAT);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
