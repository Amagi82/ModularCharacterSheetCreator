package amagi82.modularcharactersheetcreator.models.modules;


import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class StatusModule extends Module {

    @JsonField private int value;
    @JsonField private int valueMax;
    @JsonField private int numStars;
    @JsonField private boolean circle;

    public StatusModule() {
        super(Type.STATUS);
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
        return circle;
    }
    public boolean getCircle(){
        return circle;
    }

    public void setCircle(boolean circle) {
        this.circle = circle;
    }
}
