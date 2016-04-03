package amagi82.modularcharactersheetcreator.models.games;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Splat;

/*
    Classic World of Darkness
    Werewolf: The Apocalypse
    20th Anniversary Edition
 */
public class CWerewolf extends Game {

    public CWerewolf() {
        super();
        this.setGameTitle(getString(R.string.cwod_werewolf));
        this.setLeftTitle(getString(R.string.tribe));
        this.setRightTitle(getString(R.string.auspice));
        this.setGameUrl(getString(R.string.url_game_cwod_werewolf));
        this.setSplashUrl(getString(R.string.url_splash_cwod_werewolf));
        this.setGameColor(R.color.cwod_werewolf);
        this.setSplats(getSplats());
    }

    private SparseArray<Splat> getSplats(){
        SparseArray<Splat> splats = new SparseArray<>(21);

        splats.put(BLACK_FURIES, splat(R.string.black_furies, R.string.url_cwod_werewolf_tribe_black_furies));
        splats.put(BONE_GNAWERS, splat(R.string.bone_gnawers, R.string.url_cwod_werewolf_tribe_bone_gnawers));
        splats.put(BUNYIP, splat(R.string.bunyip, R.string.url_cwod_werewolf_tribe_bunyip));
        splats.put(CHILDREN_OF_GAIA, splat(R.string.children_of_gaia, R.string.url_cwod_werewolf_tribe_children_of_gaia));
        splats.put(CROATAN, splat(R.string.croatan, R.string.url_cwod_werewolf_tribe_croatan));
        splats.put(FIANNA, splat(R.string.fianna, R.string.url_cwod_werewolf_tribe_fianna));
        splats.put(GET_OF_FENRIS, splat(R.string.get_of_fenris, R.string.url_cwod_werewolf_tribe_get_of_fenris));
        splats.put(GLASS_WALKERS, splat(R.string.glass_walkers, R.string.url_cwod_werewolf_tribe_glass_walkers));
        splats.put(RED_TALONS, splat(R.string.red_talons, R.string.url_cwod_werewolf_tribe_red_talons));
        splats.put(SHADOW_LORDS, splat(R.string.shadow_lords, R.string.url_cwod_werewolf_tribe_shadow_lords));
        splats.put(SILENT_STRIDERS, splat(R.string.silent_striders, R.string.url_cwod_werewolf_tribe_silent_striders));
        splats.put(SILVER_FANGS, splat(R.string.silver_fangs, R.string.url_cwod_werewolf_tribe_silver_fangs));
        splats.put(STARGAZERS, splat(R.string.stargazers, R.string.url_cwod_werewolf_tribe_stargazers));
        splats.put(UKTENA, splat(R.string.uktena, R.string.url_cwod_werewolf_tribe_uktena));
        splats.put(WENDIGO, splat(R.string.wendigo, R.string.url_cwod_werewolf_tribe_wendigo));
        splats.put(WHITE_HOWLERS, splat(R.string.white_howlers, R.string.url_cwod_werewolf_tribe_white_howlers));

        splats.put(AHROUN, splat(R.string.ahroun, R.string.url_cwod_werewolf_auspice_ahroun));
        splats.put(GALLIARD, splat(R.string.galliard, R.string.url_cwod_werewolf_auspice_galliard));
        splats.put(PHILODOX, splat(R.string.philodox, R.string.url_cwod_werewolf_auspice_philodox));
        splats.put(RAGABASH, splat(R.string.ragabash, R.string.url_cwod_werewolf_auspice_ragabash));
        splats.put(THEURGE, splat(R.string.theurge, R.string.url_cwod_werewolf_auspice_theurge));

        return splats;
    }

    @NonNull @Override public int[] getListLeft(int splatId) {
        return new int[]{BLACK_FURIES, BONE_GNAWERS, BUNYIP, CHILDREN_OF_GAIA, CROATAN, FIANNA, GET_OF_FENRIS, GLASS_WALKERS, RED_TALONS,
                SHADOW_LORDS, SILENT_STRIDERS, SILVER_FANGS, STARGAZERS, UKTENA, WENDIGO, WHITE_HOWLERS};
    }

    @NonNull @Override public int[] getListRight(int splatId) {
        return new int[]{AHROUN, GALLIARD, PHILODOX, RAGABASH, THEURGE};
    }

    //Left
    private static final int BLACK_FURIES = 1;
    private static final int BONE_GNAWERS = 2;
    private static final int BUNYIP = 3;
    private static final int CHILDREN_OF_GAIA = 4;
    private static final int CROATAN = 5;
    private static final int FIANNA = 6;
    private static final int GET_OF_FENRIS = 7;
    private static final int GLASS_WALKERS = 8;
    private static final int RED_TALONS = 9;
    private static final int SHADOW_LORDS = 10;
    private static final int SILENT_STRIDERS = 11;
    private static final int SILVER_FANGS = 12;
    private static final int STARGAZERS = 13;
    private static final int UKTENA = 14;
    private static final int WENDIGO = 15;
    private static final int WHITE_HOWLERS = 16;

    //Right
    private static final int AHROUN = 101;
    private static final int GALLIARD = 102;
    private static final int PHILODOX = 103;
    private static final int RAGABASH = 104;
    private static final int THEURGE = 105;
}
