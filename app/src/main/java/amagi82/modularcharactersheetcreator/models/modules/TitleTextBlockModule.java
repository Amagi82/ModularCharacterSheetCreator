package amagi82.modularcharactersheetcreator.models.modules;


import java.util.List;

import amagi82.modularcharactersheetcreator.R;

/*
    Module with bold title on the left and normal text beside it. Used for basic character info, like "Name: Black Widow"
 */

public class TitleTextBlockModule extends Module {

    private List<Stat> stats;

    public TitleTextBlockModule() {
        super(Type.TITLETEXTBLOCK, R.layout.module_block);
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public int getRowLayoutId() {
        return R.layout.row_boldtext_text;
    }
}