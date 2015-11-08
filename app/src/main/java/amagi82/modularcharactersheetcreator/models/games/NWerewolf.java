package amagi82.modularcharactersheetcreator.models.games;

import android.util.SparseArray;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Splat;

/*
    New World of Darkness
    Werewolf: The Forsaken
 */
public class NWerewolf extends Game {

    public NWerewolf() {
        super();
        this.gameTitle = getString(R.string.nwod_werewolf);
        this.leftTitle = getString(R.string.tribe);
        this.rightTitle = getString(R.string.auspice);
        this.gameUrl = getString(R.string.url_game_nwod_werewolf);
        this.splashUrl = getString(R.string.url_splash_nwod_werewolf);
        this.gameColor = R.color.nwod_werewolf;
        this.splats = getSplats();
    }

    private SparseArray<Splat> getSplats() {
        SparseArray<Splat> splats = new SparseArray<>(11);

        splats.put(BLOOD_TALONS, splat(R.string.blood_talons, R.string.url_nwod_werewolf_tribe_blood_talons));
        splats.put(BONE_SHADOWS, splat(R.string.bone_shadows, R.string.url_nwod_werewolf_tribe_bone_shadows));
        splats.put(GHOST_WOLVES, splat(R.string.ghost_wolves, R.string.url_nwod_werewolf_tribe_ghost_wolves));
        splats.put(HUNTERS_IN_DARKNESS, splat(R.string.hunters_in_darkness, R.string.url_nwod_werewolf_tribe_hunters_in_darkness));
        splats.put(IRON_MASTERS, splat(R.string.iron_masters, R.string.url_nwod_werewolf_tribe_iron_masters));
        splats.put(STORM_LORDS, splat(R.string.storm_lords, R.string.url_nwod_werewolf_tribe_storm_lords));

        splats.put(CAHALITH, splat(R.string.cahalith, R.string.url_nwod_werewolf_auspice_cahalith));
        splats.put(ELODOTH, splat(R.string.elodoth, R.string.url_nwod_werewolf_auspice_elodoth));
        splats.put(IRRAKA, splat(R.string.irraka, R.string.url_nwod_werewolf_auspice_irraka));
        splats.put(ITHAEUR, splat(R.string.ithaeur, R.string.url_nwod_werewolf_auspice_ithaeur));
        splats.put(RAHU, splat(R.string.rahu, R.string.url_nwod_werewolf_auspice_rahu));

        return splats;
    }

    @Override public int[] getListLeft(int splatId) {
        return new int[]{BLOOD_TALONS, BONE_SHADOWS, GHOST_WOLVES, HUNTERS_IN_DARKNESS, IRON_MASTERS, STORM_LORDS};
    }

    @Override public int[] getListRight(int splatId) {
        return new int[]{CAHALITH, ELODOTH, IRRAKA, ITHAEUR, RAHU};
    }

    //Left
    private static final int BLOOD_TALONS = 1;
    private static final int BONE_SHADOWS = 2;
    private static final int GHOST_WOLVES = 3;
    private static final int HUNTERS_IN_DARKNESS = 4;
    private static final int IRON_MASTERS = 5;
    private static final int STORM_LORDS = 6;

    //Right
    private static final int CAHALITH = 101;
    private static final int ELODOTH = 102;
    private static final int IRRAKA = 103;
    private static final int ITHAEUR = 104;
    private static final int RAHU = 105;
}
