package amagi82.modularcharactersheetcreator.models.game_systems;

import amagi82.modularcharactersheetcreator.R;

public class CWoDWerewolf extends GameSystem{

    public CWoDWerewolf(int urlLeft, int urlRight, int urlLeftPortrait, int urlRightPortrait, int categoryLeft, int categoryRight, int[] sheetLayouts) {

        super(System.CWODWEREWOLF, R.string.url_cwod_werewolf_base, R.string.url_cwod_werewolf_avatar_base, urlLeft, urlRight, urlLeftPortrait,
                urlRightPortrait, categoryLeft, categoryRight, sheetLayouts);
    }
}
