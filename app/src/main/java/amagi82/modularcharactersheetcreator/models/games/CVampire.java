package amagi82.modularcharactersheetcreator.models.games;

import android.support.annotation.ArrayRes;
import android.util.SparseArray;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Splat;

/*
    Classic World of Darkness
    Vampire: The Masquerade
    20th Anniversary Edition
 */
public class CVampire extends Game {

    public CVampire() {
        super();
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
        if (leftSplatId == FOLLOWERS_OF_SET || leftSplatId == SERPENTS_OF_THE_LIGHT) return rightSplatId == CAMARILLA ? SERPENTS_OF_THE_LIGHT : FOLLOWERS_OF_SET;
        if (leftSplatId == TOREADOR || leftSplatId == TOREADOR_ANTITRUBU) return rightSplatId == SABBAT ? TOREADOR_ANTITRUBU : TOREADOR;
        if (leftSplatId == TREMERE || leftSplatId == TREMERE_ANTITRUBU) return rightSplatId == SABBAT ? TREMERE_ANTITRUBU : TREMERE;
        if (leftSplatId == VENTRUE || leftSplatId == VENTRUE_ANTITRUBU) return rightSplatId == SABBAT ? VENTRUE_ANTITRUBU : VENTRUE;
        if (leftSplatId == SALUBRI || leftSplatId == SALUBRI_ANTITRIBU) return rightSplatId == SABBAT ? SALUBRI_ANTITRIBU : SALUBRI;
        return leftSplatId;
    }

    @ArrayRes public int getDisciplines(int clanId){
        switch (clanId){
            case AHRIMANES:
                return R.array.CVampire_Disc_Ahrimanes;
            case ANDA:
                return R.array.CVampire_Disc_Anda;
            case ASSAMITE:
            case ASSAMITE_ANTITRUBU:
                return R.array.CVampire_Disc_Assamite;
            case BAALI:
                return R.array.CVampire_Disc_Baali;
            case BLOOD_BROTHERS:
                return R.array.CVampire_Disc_BloodBrothers;
            case BRUJAH:
            case BRUJAH_ANTITRUBU:
                return R.array.CVampire_Disc_Brujah;
            case CAPPADOCIANS:
                return R.array.CVampire_Disc_Cappadocians;
            case DAUGHERS_OF_CACOPHONY:
                return R.array.CVampire_Disc_DaughtersOfCacophony;
            case FOLLOWERS_OF_SET:
            case SERPENTS_OF_THE_LIGHT:
                return R.array.CVampire_Disc_FollowersOfSet;
            case GANGREL:
            case GANGREL_ANTITRUBU:
                return R.array.CVampire_Disc_Gangrel;
            case GARGOYLE:
                return R.array.CVampire_Disc_Gargoyles;
            case GIOVANNI:
                return R.array.CVampire_Disc_Giovanni;
            case HARBINGERS_OF_SKULLS:
                return R.array.CVampire_Disc_HarbingersOfSkulls;
            case KIASYD:
                return R.array.CVampire_Disc_Kiasyd;
            case LAMIA:
                return R.array.CVampire_Disc_Lamia;
            case LASOMBRA:
            case LASOMBRA_ANTITRUBU:
                return R.array.CVampire_Disc_Lasombra;
            case LHIANNAN:
                return R.array.CVampire_Disc_Lhiannan;
            case MALKAVIAN:
            case MALKAVIAN_ANTITRUBU:
                return R.array.CVampire_Disc_Malkavian;
            case NAGARAJA:
                return R.array.CVampire_Disc_Nagaraja;
            case NOIAD:
                return R.array.CVampire_Disc_Noiad;
            case NOSFERATU:
            case NOSFERATU_ANTITRUBU:
                return R.array.CVampire_Disc_Nosferatu;
            case RAVNOS:
            case RAVNOS_ANTITRUBU:
                return R.array.CVampire_Disc_Ravnos;
            case SALUBRI:
                return R.array.CVampire_Disc_Salubri;
            case SALUBRI_ANTITRIBU:
                return R.array.CVampire_Disc_Valaren;
            case SAMEDI:
                return R.array.CVampire_Disc_Samedi;
            case TOREADOR:
            case TOREADOR_ANTITRUBU:
                return R.array.CVampire_Disc_Toreador;
            case TREMERE:
            case TREMERE_ANTITRUBU:
                return R.array.CVampire_Disc_Tremere;
            case TRUE_BRUJAH:
                return R.array.CVampire_Disc_TrueBrujah;
            case TZIMISCE:
                return R.array.CVampire_Disc_Tzimisce;
            case VENTRUE:
            case VENTRUE_ANTITRUBU:
                return R.array.CVampire_Disc_Ventrue;
            default:
                return 0;
        }
    }

    //Left
    private static final int ASSAMITE = 101;
    private static final int BRUJAH = 102;
    private static final int FOLLOWERS_OF_SET = 103;
    private static final int GANGREL = 104;
    private static final int GIOVANNI = 105;
    private static final int LASOMBRA = 106;
    private static final int MALKAVIAN = 107;
    private static final int NOSFERATU = 108;
    private static final int RAVNOS = 109;
    private static final int TOREADOR = 120;
    private static final int TREMERE = 121;
    private static final int TZIMISCE = 122;
    private static final int VENTRUE = 123;
    private static final int CAITIFF = 124;
    private static final int BLOODLINES = 125;

    private static final int AHRIMANES = 201;
    private static final int ANDA = 202;
    private static final int BAALI = 203;
    private static final int BLOOD_BROTHERS = 204;
    private static final int CAPPADOCIANS = 205;
    private static final int CHILDREN_OF_OSIRIS = 206;
    private static final int DAUGHERS_OF_CACOPHONY = 207;
    private static final int GARGOYLE = 208;
    private static final int HARBINGERS_OF_SKULLS = 209;
    private static final int KIASYD = 210;
    private static final int LAMIA = 211;
    private static final int LHIANNAN = 212;
    private static final int NAGARAJA = 213;
    private static final int NOIAD = 214;
    private static final int PANDERS = 215;
    private static final int SALUBRI = 216;
    private static final int SAMEDI = 217;
    private static final int TRUE_BRUJAH = 218;

    private static final int ASSAMITE_ANTITRUBU = 301;
    private static final int BRUJAH_ANTITRUBU = 302;
    private static final int SERPENTS_OF_THE_LIGHT = 303;
    private static final int GANGREL_ANTITRUBU = 304;
    private static final int LASOMBRA_ANTITRUBU = 305;
    private static final int MALKAVIAN_ANTITRUBU = 306;
    private static final int NOSFERATU_ANTITRUBU = 307;
    private static final int RAVNOS_ANTITRUBU = 308;
    private static final int TOREADOR_ANTITRUBU = 309;
    private static final int TREMERE_ANTITRUBU = 310;
    private static final int VENTRUE_ANTITRUBU = 311;
    private static final int SALUBRI_ANTITRIBU = 312;

    //Right
    private static final int CAMARILLA = 1001;
    private static final int ANARCHS = 1002;
    private static final int SABBAT = 1003;
    private static final int INDEPENDENT = 1004;
}
