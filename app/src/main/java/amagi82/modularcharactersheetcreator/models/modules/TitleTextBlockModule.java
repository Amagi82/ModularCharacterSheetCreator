package amagi82.modularcharactersheetcreator.models.modules;


import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import org.parceler.Parcel;

import java.util.List;

/*
    Module with bold title on the left and normal text beside it. Used for basic character info, like "Name: Black Widow"
 */
@Parcel
@JsonObject
public class TitleTextBlockModule extends Module {

    @JsonField private List<Stat> stats;

    public TitleTextBlockModule() {
        super(Type.TITLETEXTBLOCK);
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }
}