package amagi82.modularcharactersheetcreator.models.games.systems.splats;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class ScionN extends GameSys{
    @Override public int getGameTitle() {
        return R.string.scion;
    }

    @Override public int getLeftTitle() {
        return R.string.volume;
    }

    @Override public int getRightTitle() {
        return R.string.pantheon;
    }

    @Override public boolean leftArchetype() {
        return false;
    }

    @Override public int getGameDrawable() {
        return R.drawable.title_scion;
    }

    @Override public int getGameColor() {
        return R.color.scion;
    }

    @Override public int getGameCategory() {
        return DEFAULT;
    }

    @Override public boolean hasRight() {
        return true;
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
