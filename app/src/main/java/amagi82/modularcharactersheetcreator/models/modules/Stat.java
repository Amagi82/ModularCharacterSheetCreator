package amagi82.modularcharactersheetcreator.models.modules;

/*
    Used to fill a single row of a stat block
 */
public class Stat {

    String category = "";
    String specialty;
    int valueMin;
    int value;
    int valueTemporary;
    int valueMax;
    int numStars;

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

    public Stat(String category, int valueMin, int valueMax){
        //Used for template generation
        this(category, null, valueMin, valueMin, valueMin, valueMax);
    }

    public Stat(String category, String specialty, int valueMin, int value, int valueTemporary, int valueMax){
        this(category, specialty, valueMin, value, valueTemporary, valueMax, valueMax);
    }

    public Stat(String category, String specialty, int valueMin, int value, int valueTemporary, int valueMax, int numStars){
        this.category = category;
        this.specialty = specialty;
        this.valueMin = valueMin;
        this.value = value;
        this.valueTemporary = valueTemporary;
        this.valueMax = valueMax;
        this.numStars = numStars;
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
        if(valueTemporary < value) valueTemporary = value;
        this.valueTemporary = valueTemporary;
    }

    public int getNumStars() {
        return numStars;
    }

    public void setNumStars(int numStars) {
        this.numStars = numStars;
    }
}
