package amagi82.modularcharactersheetcreator.models.modules;


import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
@JsonObject
public class StatBlockModule extends Module {

    @JsonField
    List<Stat> stats;

    public StatBlockModule() {
        super(Type.STATBLOCK);
    }

    public StatBlockModule(String[] array, int valueMin, int valueMax, String title) {
        //Used for template generation
        super(Type.STATBLOCK);
        stats = new ArrayList<>();
        if (array != null) for (String category : array) stats.add(new Stat(category, valueMin, valueMax));
        setTitle(title);
    }

    public StatBlockModule(List<Stat> stats, String title) {
        super(Type.STATBLOCK);
        setTitle(title);
        this.stats = stats;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }
}