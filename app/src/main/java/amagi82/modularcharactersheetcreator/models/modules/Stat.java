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
    @JsonField private int value;

    public Stat() {
    }

    public Stat(String category, int value) {
        this(category, null, value);
    }

    public Stat(String category, String specialty) {
        this(category, specialty, 0);
    }

    public Stat(String category, String specialty, int value) {
        this.category = category;
        this.specialty = specialty;
        this.value = value;
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
        this.value = value;
    }
}
