package amagi82.modularcharactersheetcreator.models.games.systems;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class NVampire extends GameSys{

    public NVampire() {
        super();
        this.gameTitle = R.string.nwod_vampire;
        this.leftTitle = R.string.clan;
        this.rightTitle = R.string.covenant;
        this.gameDrawable = R.drawable.title_vampire_requiem;
        this.gameColor = R.color.nwod_vampire;
        this.gameCategory = NWOD;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(new Splat(R.string.daeva, R.string.url_nwod_vampire_clan_daeva));
        list.add(new Splat(R.string.gangrel, R.string.url_nwod_vampire_clan_gangrel));
        list.add(new Splat(R.string.mekhet, R.string.url_nwod_vampire_clan_mekhet));
        list.add(new Splat(R.string.nosferatu, R.string.url_nwod_vampire_clan_nosferatu));
        list.add(new Splat(R.string.ventrue, R.string.url_nwod_vampire_clan_ventrue));
        return list;
    }

    @Override public List<Splat> getListRight(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(new Splat(R.string.carthian_movement, R.string.url_nwod_vampire_covenant_carthian_movement));
        list.add(new Splat(R.string.circle_of_the_crone, R.string.url_nwod_vampire_covenant_circle_of_the_crone));
        list.add(new Splat(R.string.holy_engineers, R.string.url_nwod_vampire_covenant_holy_engineers));
        list.add(new Splat(R.string.invictus, R.string.url_nwod_vampire_covenant_invictus));
        list.add(new Splat(R.string.lancea_et_sanctum, R.string.url_nwod_vampire_covenant_lancea_et_sanctum));
        list.add(new Splat(R.string.ordo_dracul, R.string.url_nwod_vampire_covenant_ordo_dracul));
        list.add(new Splat(R.string.unaligned, R.string.url_nwod_vampire_covenant_unaligned));
        return list;
    }
}
