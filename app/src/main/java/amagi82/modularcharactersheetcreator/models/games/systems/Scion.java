package amagi82.modularcharactersheetcreator.models.games.systems;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Splat;

public class Scion extends GameSystem {

    public Scion() {
        super();
        this.gameTitle = R.string.scion;
        this.leftTitle = R.string.volume;
        this.rightTitle = R.string.pantheon;
        this.isArchetypeLeft = false;
        this.gameUrl = R.string.url_game_scion;
        this.splashUrl = R.string.url_splash_scion;
        this.gameColor = R.color.scion;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(new Splat(R.string.hero));
        list.add(new Splat(R.string.demigod));
        list.add(new Splat(R.string.god));
        return list;
    }

    @Override public List<Splat> getListRight(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(new Splat(R.string.pesedjet));
        list.add(new Splat(R.string.dodekatheon));
        list.add(new Splat(R.string.aesir));
        list.add(new Splat(R.string.atzlanti));
        list.add(new Splat(R.string.amatsukami));
        list.add(new Splat(R.string.loa));
        list.add(new Splat(R.string.tuatha_de_dadann));
        list.add(new Splat(R.string.celestial_bureaucracy));
        list.add(new Splat(R.string.deva));
        list.add(new Splat(R.string.yazata));
        return list;
    }
}
