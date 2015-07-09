package amagi82.modularcharactersheetcreator.models.modules;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class BloodPoolModule extends Module {

    @JsonField private int bloodCurrent;
    @JsonField private int bloodMax;
    @JsonField private int bloodPerTurn;

    public BloodPoolModule() {
        this(10,10,1);
    }

    public BloodPoolModule(int bloodCurrent, int bloodMax, int bloodPerTurn) {
        super(Type.BLOODPOOL);
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