package amagi82.modularcharactersheetcreator.models.modules;


import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class StatBlockModule extends Module {

    private List<Stat> stats;
    private boolean compact = false;

    public StatBlockModule() {
        super(Type.STATBLOCK, R.layout.module_block);
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
        for(Stat stat : stats){
            if(stat.getSpecialty() != null && stat.getSpecialty().length() > 0)
                return compact? R.layout.row_stat_compact_specialties : R.layout.row_stat_specialties;
        }
        return compact? R.layout.row_stat_compact : R.layout.row_stat;
    }
}