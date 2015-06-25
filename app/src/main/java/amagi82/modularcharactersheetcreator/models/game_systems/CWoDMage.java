package amagi82.modularcharactersheetcreator.models.game_systems;

import amagi82.modularcharactersheetcreator.R;

public class CWoDMage extends GameSystem{

    public CWoDMage(int urlLeft, int categoryLeft, int[] sheetLayouts) {
        super(System.CWODMAGE, R.string.url_cwod_mage_base, urlLeft, categoryLeft, sheetLayouts);
    }
}
