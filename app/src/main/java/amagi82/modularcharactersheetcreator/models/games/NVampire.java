package amagi82.modularcharactersheetcreator.models.games;

import android.util.SparseArray;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Splat;

/*
    New World of Darkness
    Vampire: The Requiem
 */
public class NVampire extends Game {

    public NVampire() {
        super();
        this.gameTitle = getString(R.string.nwod_vampire);
        this.leftTitle = getString(R.string.clan);
        this.rightTitle = getString(R.string.covenant);
        this.gameUrl = getString(R.string.url_game_nwod_vampire);
        this.splashUrl = getString(R.string.url_splash_nwod_vampire);
        this.gameColor = R.color.nwod_vampire;
        this.splats = getSplats();
    }

    private SparseArray<Splat> getSplats() {
        SparseArray<Splat> splats = new SparseArray<>(12);

        splats.put(DAEVA, splat(R.string.daeva, R.string.url_nwod_vampire_clan_daeva));
        splats.put(GANGREL, splat(R.string.gangrel, R.string.url_nwod_vampire_clan_gangrel));
        splats.put(MEKHET, splat(R.string.mekhet, R.string.url_nwod_vampire_clan_mekhet));
        splats.put(NOSFERATU, splat(R.string.nosferatu, R.string.url_nwod_vampire_clan_nosferatu));
        splats.put(VENTRUE, splat(R.string.ventrue, R.string.url_nwod_vampire_clan_ventrue));

        splats.put(CARTHIAN_MOVEMENT, splat(R.string.carthian_movement, R.string.url_nwod_vampire_covenant_carthian_movement));
        splats.put(CIRCLE_OF_THE_CRONE, splat(R.string.circle_of_the_crone, R.string.url_nwod_vampire_covenant_circle_of_the_crone));
        splats.put(HOLY_ENGINEERS, splat(R.string.holy_engineers, R.string.url_nwod_vampire_covenant_holy_engineers));
        splats.put(INVICTUS, splat(R.string.invictus, R.string.url_nwod_vampire_covenant_invictus));
        splats.put(LANCEA_ET_SANCTUM, splat(R.string.lancea_et_sanctum, R.string.url_nwod_vampire_covenant_lancea_et_sanctum));
        splats.put(ORDO_DRACUL, splat(R.string.ordo_dracul, R.string.url_nwod_vampire_covenant_ordo_dracul));
        splats.put(UNALIGNED, splat(R.string.unaligned, R.string.url_nwod_vampire_covenant_unaligned));

        return splats;
    }

    @Override public int[] getListLeft(int splatId) {
        return new int[]{DAEVA, GANGREL, MEKHET, NOSFERATU, VENTRUE};
    }

    @Override public int[] getListRight(int splatId) {
        return new int[]{CARTHIAN_MOVEMENT, CIRCLE_OF_THE_CRONE, HOLY_ENGINEERS, INVICTUS, LANCEA_ET_SANCTUM, ORDO_DRACUL, UNALIGNED};
    }

    //Left
    private static final int DAEVA = 1;
    private static final int GANGREL = 2;
    private static final int MEKHET = 3;
    private static final int NOSFERATU = 4;
    private static final int VENTRUE = 5;

    //Right
    private static final int CARTHIAN_MOVEMENT = 101;
    private static final int CIRCLE_OF_THE_CRONE = 102;
    private static final int HOLY_ENGINEERS = 103;
    private static final int INVICTUS = 104;
    private static final int LANCEA_ET_SANCTUM = 105;
    private static final int ORDO_DRACUL = 106;
    private static final int UNALIGNED = 107;
}
