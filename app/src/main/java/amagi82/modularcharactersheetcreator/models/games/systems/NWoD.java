package amagi82.modularcharactersheetcreator.models.games.systems;

import android.support.annotation.Nullable;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Splat;

//Header used to hide/expand NWoD games
public class NWoD extends GameSystem{

    public NWoD() {
        super();
        this.gameTitle = R.string.nwod;
        this.gameDrawable = R.drawable.title_wod;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        return null;
    }

    @Nullable @Override public List<Splat> getListRight(@Nullable Splat splat) {
        return null;
    }
}
