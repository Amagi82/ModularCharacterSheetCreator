package amagi82.modularcharactersheetcreator.models.modules;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/*
    Used to fill a single row of a stat block
 */
@JsonObject
public class Stat {

    @JsonField private String category = "";
    @JsonField private String specialty;
    @JsonField private int valueMin;
    @JsonField private int value;
    @JsonField private int valueTemporary;
    @JsonField private int valueMax;

    public Stat() {
    }

    public Stat(String category, int value) {
        this(category, null, value);
    }

    public Stat(String category, String specialty) {
        this(category, specialty, 0);
    }

    public Stat(String category, String specialty, int value) {
        this(category, specialty, 0, value, value, 5);
    }

    public Stat(String category, String specialty, int valueMin, int value, int valueTemporary, int valueMax){
        this.category = category;
        this.specialty = specialty;
        this.valueMin = valueMin;
        this.value = value;
        this.valueTemporary = valueTemporary;
        this.valueMax = valueMax;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if(value < valueMin) value = valueMin;
        if(valueTemporary < value) valueTemporary = value;
        this.value = value;
    }

    public int getValueMax() {
        return valueMax;
    }

    public void setValueMax(int valueMax) {
        this.valueMax = valueMax;
    }

    public int getValueMin() {
        return valueMin;
    }

    public void setValueMin(int valueMin) {
        this.valueMin = valueMin;
    }

    public int getValueTemporary() {
        return valueTemporary;
    }

    public void setValueTemporary(int valueTemporary) {
        this.valueTemporary = valueTemporary;
    }
}
