package amagi82.modularcharactersheetcreator.models.games;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.Splat;

/*
    New World of Darkness
    Vampire: The Requiem
 */
public class NVampire extends GameSystem {

    public NVampire() {
        super();
        this.gameTitle = R.string.nwod_vampire;
        this.leftTitle = R.string.clan;
        this.rightTitle = R.string.covenant;
        this.gameUrl = R.string.url_game_nwod_vampire;
        this.splashUrl = R.string.url_splash_nwod_vampire;
        this.gameColor = R.color.nwod_vampire;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(5);
        list.add(Splat.create(R.string.daeva, R.string.url_nwod_vampire_clan_daeva));
        list.add(Splat.create(R.string.gangrel, R.string.url_nwod_vampire_clan_gangrel));
        list.add(Splat.create(R.string.mekhet, R.string.url_nwod_vampire_clan_mekhet));
        list.add(Splat.create(R.string.nosferatu, R.string.url_nwod_vampire_clan_nosferatu));
        list.add(Splat.create(R.string.ventrue, R.string.url_nwod_vampire_clan_ventrue));
        return list;
    }

    @Override public List<Splat> getListRight(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(7);
        list.add(Splat.create(R.string.carthian_movement, R.string.url_nwod_vampire_covenant_carthian_movement));
        list.add(Splat.create(R.string.circle_of_the_crone, R.string.url_nwod_vampire_covenant_circle_of_the_crone));
        list.add(Splat.create(R.string.holy_engineers, R.string.url_nwod_vampire_covenant_holy_engineers));
        list.add(Splat.create(R.string.invictus, R.string.url_nwod_vampire_covenant_invictus));
        list.add(Splat.create(R.string.lancea_et_sanctum, R.string.url_nwod_vampire_covenant_lancea_et_sanctum));
        list.add(Splat.create(R.string.ordo_dracul, R.string.url_nwod_vampire_covenant_ordo_dracul));
        list.add(Splat.create(R.string.unaligned, R.string.url_nwod_vampire_covenant_unaligned));
        return list;
    }
}
