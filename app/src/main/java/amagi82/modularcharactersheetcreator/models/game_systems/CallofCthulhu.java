package amagi82.modularcharactersheetcreator.models.game_systems;

import amagi82.modularcharactersheetcreator.R;

public class CallofCthulhu extends GameSystem {
    public CallofCthulhu() {
        super(-1, -1, -1, false, true, false);
        setCharacterClassTerm(R.string.profession);
    }
}
