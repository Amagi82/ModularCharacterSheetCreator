package amagi82.modularcharactersheetcreator.models.games;

import android.util.SparseArray;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Splat;

/*
    New World of Darkness
    Mummy: The Curse
 */
public class NMummy extends Game {

    public NMummy() {
        super();
        this.gameTitle = getString(R.string.nwod_mummy);
        this.leftTitle = getString(R.string.decree);
        this.rightTitle = getString(R.string.guild);
        this.gameUrl = getString(R.string.url_game_nwod_mummy);
        this.splashUrl = getString(R.string.url_splash_nwod_mummy);
        this.gameColor = R.color.nwod_mummy;
        this.splats = getSplats();
    }

    private SparseArray<Splat> getSplats() {
        SparseArray<Splat> splats = new SparseArray<>(10);

        splats.put(BULL_HEADED, splat(R.string.bull_headed, R.string.url_nwod_mummy_decree_bull_headed));
        splats.put(FALCON_HEADED, splat(R.string.falcon_headed, R.string.url_nwod_mummy_decree_falcon_headed));
        splats.put(JACKAL_HEADED, splat(R.string.jackal_headed, R.string.url_nwod_mummy_decree_jackal_headed));
        splats.put(LION_HEADED, splat(R.string.lion_headed, R.string.url_nwod_mummy_decree_lion_headed));
        splats.put(SERPENT_HEADED, splat(R.string.serpent_headed, R.string.url_nwod_mummy_decree_serpent_headed));

        splats.put(MAA_KEP, splat(R.string.maa_kep, R.string.url_nwod_mummy_guild_maa_kep));
        splats.put(MESEN_NEBU, splat(R.string.mesen_nebu, R.string.url_nwod_mummy_guild_mesen_nebu));
        splats.put(SESHA_HEBSU, splat(R.string.sesha_hebsu, R.string.url_nwod_mummy_guild_sesha_hebsu));
        splats.put(SU_MENENT, splat(R.string.su_menent, R.string.url_nwod_mummy_guild_su_menent));
        splats.put(TEF_AABHI, splat(R.string.tef_aabhi, R.string.url_nwod_mummy_guild_tef_aabhi));

        return splats;
    }

    @Override public int[] getListLeft(int splatId) {
        return new int[]{BULL_HEADED, FALCON_HEADED, JACKAL_HEADED, LION_HEADED, SERPENT_HEADED};
    }

    @Override public int[] getListRight(int splatId) {
        return new int[]{MAA_KEP, MESEN_NEBU, SESHA_HEBSU, SU_MENENT, TEF_AABHI};
    }

    //Left
    private static final int BULL_HEADED = 1;
    private static final int FALCON_HEADED = 2;
    private static final int JACKAL_HEADED = 3;
    private static final int LION_HEADED = 4;
    private static final int SERPENT_HEADED = 5;

    //Right
    private static final int MAA_KEP = 101;
    private static final int MESEN_NEBU = 102;
    private static final int SESHA_HEBSU = 103;
    private static final int SU_MENENT = 104;
    private static final int TEF_AABHI = 105;
}
