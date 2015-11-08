package amagi82.modularcharactersheetcreator.models.games;

import android.content.res.Resources;
import android.util.SparseArray;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.Splat;

/*
    Classic World of Darkness
    Vampire: The Masquerade
    20th Anniversary Edition
 */
public class CVampire extends Game {

    public CVampire(Resources res) {
        super();
        this.res = res;
        this.gameTitle = getString(R.string.cwod_vampire);
        this.leftTitle = getString(R.string.clan);
        this.rightTitle = getString(R.string.sect);
        this.gameUrl = getString(R.string.url_game_cwod_vampire);
        this.splashUrl = getString(R.string.url_splash_cwod_vampire);
        this.gameColor = R.color.cwod_vampire;
        this.checkLeft = true;
        this.isLeftListFinal = false;
        this.splats = getSplats();
    }

    private SparseArray<Splat> getSplats(){
        SparseArray<Splat> splats = new SparseArray<>(49);

        splats.put(ASSAMITE, splat(R.string.assamite, R.string.url_cwod_vampire_clan_assamite));
        splats.put(BRUJAH, splat(R.string.brujah, R.string.url_cwod_vampire_clan_brujah));
        splats.put(FOLLOWERS_OF_SET, splat(R.string.followers_of_set, R.string.url_cwod_vampire_clan_followers_of_set));
        splats.put(GANGREL, splat(R.string.gangrel, R.string.url_cwod_vampire_clan_gangrel));
        splats.put(GIOVANNI, splat(R.string.giovanni, R.string.url_cwod_vampire_clan_giovanni));
        splats.put(LASOMBRA, splat(R.string.lasombra, R.string.url_cwod_vampire_clan_lasombra));
        splats.put(MALKAVIAN, splat(R.string.malkavian, R.string.url_cwod_vampire_clan_malkavian));
        splats.put(NOSFERATU, splat(R.string.nosferatu, R.string.url_cwod_vampire_clan_nosferatu));
        splats.put(RAVNOS, splat(R.string.ravnos, R.string.url_cwod_vampire_clan_ravnos));
        splats.put(TOREADOR, splat(R.string.toreador, R.string.url_cwod_vampire_clan_toreador));
        splats.put(TREMERE, splat(R.string.tremere, R.string.url_cwod_vampire_clan_tremere));
        splats.put(TZIMISCE, splat(R.string.tzimisce, R.string.url_cwod_vampire_clan_tzimisce));
        splats.put(VENTRUE, splat(R.string.ventrue, R.string.url_cwod_vampire_clan_ventrue));
        splats.put(CAITIFF, splat(R.string.caitiff, R.string.url_cwod_vampire_clan_caitiff));
        splats.put(BLOODLINES, splat(R.string.bloodlines, false));

        splats.put(AHRIMANES, splat(R.string.ahrimanes, R.string.url_cwod_vampire_clan_ahrimanes));
        splats.put(ANDA, splat(R.string.anda, R.string.url_cwod_vampire_clan_anda));
        splats.put(BAALI, splat(R.string.baali, R.string.url_cwod_vampire_clan_baali));
        splats.put(BLOOD_BROTHERS, splat(R.string.blood_brothers, R.string.url_cwod_vampire_clan_blood_brothers));
        splats.put(CAPPADOCIANS, splat(R.string.cappadocians, R.string.url_cwod_vampire_clan_cappadocians));
        splats.put(CHILDREN_OF_OSIRIS, splat(R.string.children_of_osiris, R.string.url_cwod_vampire_clan_children_of_osiris));
        splats.put(DAUGHERS_OF_CACOPHONY, splat(R.string.daughers_of_cacophony, R.string.url_cwod_vampire_clan_daughers_of_cacophony));
        splats.put(GARGOYLE, splat(R.string.gargoyle, R.string.url_cwod_vampire_clan_gargoyle));
        splats.put(HARBINGERS_OF_SKULLS, splat(R.string.harbingers_of_skulls, R.string.url_cwod_vampire_clan_harbingers_of_skulls));
        splats.put(KIASYD, splat(R.string.kiasyd, R.string.url_cwod_vampire_clan_kiasyd));
        splats.put(LAMIA, splat(R.string.lamia, R.string.url_cwod_vampire_clan_lamia));
        splats.put(LHIANNAN, splat(R.string.lhiannan, R.string.url_cwod_vampire_clan_lhiannan));
        splats.put(NAGARAJA, splat(R.string.nagaraja, R.string.url_cwod_vampire_clan_nagaraja));
        splats.put(NOIAD, splat(R.string.noiad, R.string.url_cwod_vampire_clan_noiad));
        splats.put(PANDERS, splat(R.string.panders, R.string.url_cwod_vampire_clan_panders));
        splats.put(SALUBRI, splat(R.string.salubri, R.string.url_cwod_vampire_clan_salubri));
        splats.put(SAMEDI, splat(R.string.samedi, R.string.url_cwod_vampire_clan_samedi));
        splats.put(TRUE_BRUJAH, splat(R.string.true_brujah, R.string.url_cwod_vampire_clan_true_brujah));

        splats.put(ASSAMITE_ANTITRUBU, splat(R.string.assamite_antitribu, R.string.url_cwod_vampire_antitribu_assamite));
        splats.put(BRUJAH_ANTITRUBU, splat(R.string.brujah_antitribu, R.string.url_cwod_vampire_antitribu_brujah));
        splats.put(SERPENTS_OF_THE_LIGHT, splat(R.string.serpents_of_the_light, R.string.url_cwod_vampire_antitribu_serpents_of_the_light));
        splats.put(GANGREL_ANTITRUBU, splat(R.string.gangrel_antitribu, R.string.url_cwod_vampire_antitribu_gangrel));
        splats.put(LASOMBRA_ANTITRUBU, splat(R.string.lasombra_antitribu, R.string.url_cwod_vampire_antitribu_lasombra));
        splats.put(MALKAVIAN_ANTITRUBU, splat(R.string.malkavian_antitribu, R.string.url_cwod_vampire_antitribu_malkavian));
        splats.put(NOSFERATU_ANTITRUBU, splat(R.string.nosferatu_antitribu, R.string.url_cwod_vampire_antitribu_nosferatu));
        splats.put(RAVNOS_ANTITRUBU, splat(R.string.ravnos_antitribu, R.string.url_cwod_vampire_antitribu_ravnos));
        splats.put(TOREADOR_ANTITRUBU, splat(R.string.toreador_antitribu, R.string.url_cwod_vampire_antitribu_toreador));
        splats.put(TREMERE_ANTITRUBU, splat(R.string.tremere_antitribu, R.string.url_cwod_vampire_antitribu_tremere));
        splats.put(VENTRUE_ANTITRUBU, splat(R.string.ventrue_antitribu, R.string.url_cwod_vampire_antitribu_ventrue));
        splats.put(SALUBRI_ANTITRIBU, splat(R.string.salubri_antitribu, R.string.url_cwod_vampire_antitribu_salubri));

        splats.put(CAMARILLA, splat(R.string.camarilla, R.string.url_cwod_vampire_sect_camarilla));
        splats.put(ANARCHS, splat(R.string.anarchs, R.string.url_cwod_vampire_sect_anarchs));
        splats.put(SABBAT, splat(R.string.sabbat, R.string.url_cwod_vampire_sect_sabbat));
        splats.put(INDEPENDENT, splat(R.string.independent, R.string.url_cwod_vampire_sect_independent));

        return splats;
    }

    @Override public int[] getListLeft(int splatId) {
        if (splatId == BLOODLINES) return new int[]{ANDA, BAALI, BLOOD_BROTHERS, CAPPADOCIANS, CHILDREN_OF_OSIRIS, DAUGHERS_OF_CACOPHONY, GARGOYLE,
                HARBINGERS_OF_SKULLS, KIASYD, LAMIA, LHIANNAN, NAGARAJA, NOIAD, PANDERS, SALUBRI, SAMEDI, TRUE_BRUJAH};
        else return new int[]{ASSAMITE, BRUJAH, FOLLOWERS_OF_SET, GANGREL, GIOVANNI, LASOMBRA, MALKAVIAN, NOSFERATU, RAVNOS, TOREADOR, TREMERE,
                TZIMISCE, VENTRUE, CAITIFF, BLOODLINES};
    }

    @Override public int[] getListRight(int leftSplatId) {
        return new int[]{CAMARILLA, ANARCHS, SABBAT, INDEPENDENT};
    }

    @Override public int updateLeft(int leftSplatId, int rightSplatId) {
        if (leftSplatId == ASSAMITE || leftSplatId == ASSAMITE_ANTITRUBU) return rightSplatId == SABBAT ? ASSAMITE_ANTITRUBU : ASSAMITE;
        if (leftSplatId == BRUJAH || leftSplatId == BRUJAH_ANTITRUBU) return rightSplatId == SABBAT ? BRUJAH_ANTITRUBU : BRUJAH;
        if (leftSplatId == GANGREL || leftSplatId == GANGREL_ANTITRUBU) return rightSplatId == SABBAT ? GANGREL_ANTITRUBU : GANGREL;
        if (leftSplatId == LASOMBRA || leftSplatId == LASOMBRA_ANTITRUBU) return rightSplatId != SABBAT ? LASOMBRA_ANTITRUBU : LASOMBRA;
        if (leftSplatId == MALKAVIAN || leftSplatId == MALKAVIAN_ANTITRUBU) return rightSplatId == SABBAT ? MALKAVIAN_ANTITRUBU : MALKAVIAN;
        if (leftSplatId == NOSFERATU || leftSplatId == NOSFERATU_ANTITRUBU) return rightSplatId == SABBAT ? NOSFERATU_ANTITRUBU : NOSFERATU;
        if (leftSplatId == RAVNOS || leftSplatId == RAVNOS_ANTITRUBU) return rightSplatId != INDEPENDENT ? RAVNOS_ANTITRUBU : RAVNOS;
        if (leftSplatId == FOLLOWERS_OF_SET || leftSplatId == SERPENTS_OF_THE_LIGHT)
            return rightSplatId == CAMARILLA ? SERPENTS_OF_THE_LIGHT : FOLLOWERS_OF_SET;
        if (leftSplatId == TOREADOR || leftSplatId == TOREADOR_ANTITRUBU) return rightSplatId == SABBAT ? TOREADOR_ANTITRUBU : TOREADOR;
        if (leftSplatId == TREMERE || leftSplatId == TREMERE_ANTITRUBU) return rightSplatId == SABBAT ? TREMERE_ANTITRUBU : TREMERE;
        if (leftSplatId == VENTRUE || leftSplatId == VENTRUE_ANTITRUBU) return rightSplatId == SABBAT ? VENTRUE_ANTITRUBU : VENTRUE;
        if (leftSplatId == SALUBRI || leftSplatId == SALUBRI_ANTITRIBU) return rightSplatId == SABBAT ? SALUBRI_ANTITRIBU : SALUBRI;
        return leftSplatId;
    }

    //Left
    static final int ASSAMITE = 101;
    static final int BRUJAH = 102;
    static final int FOLLOWERS_OF_SET = 103;
    static final int GANGREL = 104;
    static final int GIOVANNI = 105;
    static final int LASOMBRA = 106;
    static final int MALKAVIAN = 107;
    static final int NOSFERATU = 108;
    static final int RAVNOS = 109;
    static final int TOREADOR = 120;
    static final int TREMERE = 121;
    static final int TZIMISCE = 122;
    static final int VENTRUE = 123;
    static final int CAITIFF = 124;
    static final int BLOODLINES = 125;

    static final int AHRIMANES = 201;
    static final int ANDA = 202;
    static final int BAALI = 203;
    static final int BLOOD_BROTHERS = 204;
    static final int CAPPADOCIANS = 205;
    static final int CHILDREN_OF_OSIRIS = 206;
    static final int DAUGHERS_OF_CACOPHONY = 207;
    static final int GARGOYLE = 208;
    static final int HARBINGERS_OF_SKULLS = 209;
    static final int KIASYD = 210;
    static final int LAMIA = 211;
    static final int LHIANNAN = 212;
    static final int NAGARAJA = 213;
    static final int NOIAD = 214;
    static final int PANDERS = 215;
    static final int SALUBRI = 216;
    static final int SAMEDI = 217;
    static final int TRUE_BRUJAH = 218;

    static final int ASSAMITE_ANTITRUBU = 301;
    static final int BRUJAH_ANTITRUBU = 302;
    static final int SERPENTS_OF_THE_LIGHT = 303;
    static final int GANGREL_ANTITRUBU = 304;
    static final int LASOMBRA_ANTITRUBU = 305;
    static final int MALKAVIAN_ANTITRUBU = 306;
    static final int NOSFERATU_ANTITRUBU = 307;
    static final int RAVNOS_ANTITRUBU = 308;
    static final int TOREADOR_ANTITRUBU = 309;
    static final int TREMERE_ANTITRUBU = 310;
    static final int VENTRUE_ANTITRUBU = 311;
    static final int SALUBRI_ANTITRIBU = 312;

    //Right
    static final int CAMARILLA = 1001;
    static final int ANARCHS = 1002;
    static final int SABBAT = 1003;
    static final int INDEPENDENT = 1004;
}
