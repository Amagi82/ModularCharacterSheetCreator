package amagi82.modularcharactersheetcreator.models.games;

import android.util.SparseArray;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Splat;

/*
    Scion 2nd Edition: Origins
    Includes Hero, Demigod, and God
 */
public class Scion extends Game {

    public Scion() {
        super();
        this.gameTitle = getString(R.string.scion);
        this.leftTitle = getString(R.string.volume);
        this.rightTitle = getString(R.string.pantheon);
        this.isArchetypeLeft = false;
        this.gameUrl = getString(R.string.url_game_scion);
        this.splashUrl = getString(R.string.url_splash_scion);
        this.gameColor = R.color.scion;
        this.splats = getSplats();
    }

    private SparseArray<Splat> getSplats() {
        SparseArray<Splat> splats = new SparseArray<>(13);

        splats.put(HERO, splat(R.string.hero));
        splats.put(DEMIGOD, splat(R.string.demigod));
        splats.put(GOD, splat(R.string.god));

        splats.put(PESEDJET, splat(R.string.pesedjet));
        splats.put(DODEKATHEON, splat(R.string.dodekatheon));
        splats.put(AESIR, splat(R.string.aesir));
        splats.put(ATZLANTI, splat(R.string.atzlanti));
        splats.put(AMATSUKAMI, splat(R.string.amatsukami));
        splats.put(LOA, splat(R.string.loa));
        splats.put(TUATHA_DE_DADANN, splat(R.string.tuatha_de_dadann));
        splats.put(CELESTIAL_BUREAUCRACY, splat(R.string.celestial_bureaucracy));
        splats.put(DEVA, splat(R.string.deva));
        splats.put(YAZATA, splat(R.string.yazata));

        return splats;
    }

    @Override public int[] getListLeft(int splatId) {
        return new int[]{HERO, DEMIGOD, GOD};
    }

    @Override public int[] getListRight(int splatId) {
        return new int[]{PESEDJET, DODEKATHEON, AESIR, ATZLANTI, AMATSUKAMI, LOA, TUATHA_DE_DADANN, CELESTIAL_BUREAUCRACY, DEVA, YAZATA};
    }

    //Left
    private static final int HERO = 1;
    private static final int DEMIGOD = 2;
    private static final int GOD = 3;

    //Right
    private static final int PESEDJET = 101;
    private static final int DODEKATHEON = 102;
    private static final int AESIR = 103;
    private static final int ATZLANTI = 104;
    private static final int AMATSUKAMI = 105;
    private static final int LOA = 106;
    private static final int TUATHA_DE_DADANN = 107;
    private static final int CELESTIAL_BUREAUCRACY = 108;
    private static final int DEVA = 109;
    private static final int YAZATA = 110;
}
