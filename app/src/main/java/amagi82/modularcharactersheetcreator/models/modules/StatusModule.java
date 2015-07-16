package amagi82.modularcharactersheetcreator.models.modules;


import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class StatusModule extends Module {

    @JsonField private int value;
    @JsonField private int valueMax;
    @JsonField private int numStars;

    public StatusModule() {
        super(Type.STATUS);
    }

    public StatusModule(String title){
        this(title, null, 10);
    }

    public StatusModule(String title, String text){
        this(title, text, 10);
    }

    public StatusModule(String title, int numStars){
        this(title, null, numStars);
    }

    public StatusModule(String title, String text, int numStars){
        super(Type.STATUS);
        setTitle(title);
        setText(text);
        this.numStars = numStars;
        value = 1;
        valueMax = numStars;
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

}
