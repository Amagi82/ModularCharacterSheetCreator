package amagi82.modularcharactersheetcreator.models.games;

import android.content.res.Resources;
import android.util.SparseArray;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.Splat;

/*
    Classic World of Darkness
    Wraith: The Oblivion
    20th Anniversary Edition
 */
public class CWraith extends Game {

    public CWraith(Resources res) {
        super();
        this.res = res;
        this.gameTitle = getString(R.string.cwod_wraith);
        this.leftTitle = getString(R.string.legion);
        this.rightTitle = getString(R.string.guild);
        this.gameUrl = getString(R.string.url_game_cwod_wraith);
        this.splashUrl = getString(R.string.url_splash_cwod_wraith);
        this.gameColor = R.color.cwod_wraith;
        this.isRightListFinal = false;
        this.splats = getSplats();
    }

    private SparseArray<Splat> getSplats(){
        SparseArray<Splat> splats = new SparseArray<>(29);

        splats.put(IRON_LEGION, splat(R.string.iron_legion));
        splats.put(SKELETAL_LEGION, splat(R.string.skeletal_legion));
        splats.put(GRIM_LEGION, splat(R.string.grim_legion));
        splats.put(PENITENT_LEGION, splat(R.string.penitent_legion));
        splats.put(EMERALD_LEGION, splat(R.string.emerald_legion));
        splats.put(SILENT_LEGION, splat(R.string.silent_legion));
        splats.put(LEGION_OF_PAUPERS, splat(R.string.legion_of_paupers));
        splats.put(LEGION_OF_FATE, splat(R.string.legion_of_fate));

        splats.put(NONE, splat(R.string.none));
        splats.put(GREAT_GUILDS, splat(R.string.great_guilds, false));
        splats.put(WORKING_GUILDS, splat(R.string.working_guilds, false));
        splats.put(CRIMINAL_GUILDS, splat(R.string.criminal_guilds, false));
        splats.put(FORBIDDEN_GUILDS, splat(R.string.forbidden_guilds, false));

        splats.put(ARTIFICERS, splat(R.string.artificers));
        splats.put(MASQUERS, splat(R.string.masquers));
        splats.put(PARDONERS, splat(R.string.pardoners));
        splats.put(USURERS, splat(R.string.usurers));

        splats.put(CHANTEURS, splat(R.string.chanteurs));
        splats.put(HARBINGERS, splat(R.string.harbingers));
        splats.put(ORACLES, splat(R.string.oracles));
        splats.put(SANDMEN, splat(R.string.sandmen));

        splats.put(HAUNTERS, splat(R.string.haunters));
        splats.put(MONITORS, splat(R.string.monitors));
        splats.put(SPOOKS, splat(R.string.spooks));
        splats.put(PROCTORS, splat(R.string.proctors));
        splats.put(PUPPETEERS, splat(R.string.puppeteers));

        splats.put(ALCHEMISTS, splat(R.string.alchemists));
        splats.put(MNEMOI, splat(R.string.mnemoi));
        splats.put(SOLICITORS, splat(R.string.solicitors));

        return splats;
    }

    @Override public int[] getListLeft(int splatId) {
        return new int[]{IRON_LEGION, SKELETAL_LEGION, GRIM_LEGION, PENITENT_LEGION, EMERALD_LEGION, SILENT_LEGION, LEGION_OF_PAUPERS, LEGION_OF_FATE};
    }

    @Override public int[] getListRight(int splatId) {
        if(splatId == GREAT_GUILDS) return new int[]{ARTIFICERS, MASQUERS, PARDONERS, USURERS};
        if(splatId == WORKING_GUILDS) return new int[]{CHANTEURS, HARBINGERS, ORACLES, SANDMEN};
        if(splatId == CRIMINAL_GUILDS) return new int[]{HAUNTERS, MONITORS, SPOOKS, PROCTORS, PUPPETEERS};
        if(splatId == FORBIDDEN_GUILDS) return new int[]{ALCHEMISTS, MNEMOI, SOLICITORS};
        return new int[]{NONE, GREAT_GUILDS, WORKING_GUILDS, CRIMINAL_GUILDS, FORBIDDEN_GUILDS};

    }

    //Left
    private static final int IRON_LEGION = 1;
    private static final int SKELETAL_LEGION = 2;
    private static final int GRIM_LEGION = 3;
    private static final int PENITENT_LEGION = 4;
    private static final int EMERALD_LEGION = 5;
    private static final int SILENT_LEGION = 6;
    private static final int LEGION_OF_PAUPERS = 7;
    private static final int LEGION_OF_FATE = 8;

    //Right
    private static final int NONE = 100;
    private static final int GREAT_GUILDS = 200;
    private static final int WORKING_GUILDS = 300;
    private static final int CRIMINAL_GUILDS = 400;
    private static final int FORBIDDEN_GUILDS = 500;

    private static final int ARTIFICERS = 201;
    private static final int MASQUERS = 202;
    private static final int PARDONERS = 203;
    private static final int USURERS = 204;

    private static final int CHANTEURS = 301;
    private static final int HARBINGERS = 302;
    private static final int ORACLES = 303;
    private static final int SANDMEN = 304;

    private static final int HAUNTERS = 401;
    private static final int MONITORS = 402;
    private static final int SPOOKS = 403;
    private static final int PROCTORS = 404;
    private static final int PUPPETEERS = 405;

    private static final int ALCHEMISTS = 501;
    private static final int MNEMOI = 502;
    private static final int SOLICITORS = 503;
}
