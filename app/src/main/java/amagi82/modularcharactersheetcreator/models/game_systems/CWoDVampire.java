package amagi82.modularcharactersheetcreator.models.game_systems;

import amagi82.modularcharactersheetcreator.R;

public class CWoDVampire extends GameSystem{

    public CWoDVampire(int urlLeft, int urlRight, int urlLeftPortrait, int urlRightPortrait, int categoryLeft, int categoryRight) {
        super(System.CWODVAMPIRE, R.string.url_cwod_vampire_base, R.string.url_cwod_vampire_avatar_base, urlLeft, urlRight, urlLeftPortrait,
                urlRightPortrait, categoryLeft, categoryRight);
    }
}
