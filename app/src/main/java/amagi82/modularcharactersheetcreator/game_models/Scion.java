package amagi82.modularcharactersheetcreator.game_models;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.core_models.Splat;

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
        List<Splat> list = new ArrayList<>(3);
        list.add(Splat.create(R.string.hero));
        list.add(Splat.create(R.string.demigod));
        list.add(Splat.create(R.string.god));
        return list;
    }

    @Override public List<Splat> getListRight(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(10);
        list.add(Splat.create(R.string.pesedjet));
        list.add(Splat.create(R.string.dodekatheon));
        list.add(Splat.create(R.string.aesir));
        list.add(Splat.create(R.string.atzlanti));
        list.add(Splat.create(R.string.amatsukami));
        list.add(Splat.create(R.string.loa));
        list.add(Splat.create(R.string.tuatha_de_dadann));
        list.add(Splat.create(R.string.celestial_bureaucracy));
        list.add(Splat.create(R.string.deva));
        list.add(Splat.create(R.string.yazata));
        return list;
    }
}
