package amagi82.modularcharactersheetcreator.models.games;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.Splat;

/*
    Scion 2nd Edition: Origins
    Includes Hero, Demigod, and God
 */
public class Scion extends Game {

    public Scion(Resources res) {
        super();
        this.res = res;
        this.gameTitle = getString(R.string.scion);
        this.leftTitle = getString(R.string.volume);
        this.rightTitle = getString(R.string.pantheon);
        this.isArchetypeLeft = false;
        this.gameUrl = getString(R.string.url_game_scion);
        this.splashUrl = getString(R.string.url_splash_scion);
        this.gameColor = R.color.scion;
        this.splats = getSplats();
    }

    private SparseArray<Splat> getSplats(){
        SparseArray<Splat> splats = new SparseArray<>();


        return splats;
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
