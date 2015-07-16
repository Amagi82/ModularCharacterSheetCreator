package amagi82.modularcharactersheetcreator.models.modules;


import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;

@JsonObject
public class StatBlockModule extends Module {

    @JsonField private List<Stat> stats;
    @JsonField private boolean compact = false;

    public StatBlockModule() {
        super(Type.STATBLOCK);
    }

    public StatBlockModule(String[] array, int valueMin, int valueMax, String title, boolean compact){
        //Used for template generation
        super(Type.STATBLOCK);
        for (String category : array) {
            stats.add(new Stat(category, valueMin, valueMax));
        }
        setTitle(title);
        this.compact = compact;
    }

    public StatBlockModule(List<Stat> stats, String title, boolean compact) {
        super(Type.STATBLOCK);
        setTitle(title);
        this.stats = stats;
        this.compact = compact;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public boolean isCompact() {
        return compact;
    }

    public void setCompact(boolean compact) {
        this.compact = compact;
    }

    public int getRowLayoutId(){
        return compact? R.layout.row_stat_compact : R.layout.row_stat_compact;
    }
}