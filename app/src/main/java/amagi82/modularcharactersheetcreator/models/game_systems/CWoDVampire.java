package amagi82.modularcharactersheetcreator.models.game_systems;

import amagi82.modularcharactersheetcreator.R;

public class CWoDVampire extends GameSystem{

    public enum CWoDVampireSect{
        CAMARILLA(R.string.camarilla, R.string.url_cwod_vampire_sect_camarilla),
        ANARCH(R.string.anarchs, R.string.url_cwod_vampire_sect_anarchs),
        SABBAT(R.string.sabbat, R.string.url_cwod_vampire_sect_sabbat),
        INDEPENDENT(R.string.independent, R.string.url_cwod_vampire_sect_independent);

        private int name;
        private int url;

        CWoDVampireSect(int name, int url) {
            this.name = name;
            this.url = url;
        }
    }

    public enum CWoDVampireClan{
        AHRIMANES(R.string.ahrimanes, R.string.url_cwod_vampire_clan_ahrimanes),
        ANDA(R.string.anda, R.string.url_cwod_vampire_clan_anda),
        BAALI(R.string.baali, R.string.url_cwod_vampire_clan_baali),
        BLOODBROTHERS(R.string.blood_brothers, R.string.url_cwod_vampire_clan_blood_brothers),
        CAITIFF(R.string.caitiff, R.string.url_cwod_vampire_clan_caitiff),
        CAPPADOCIANS(R.string.cappadocians, R.string.url_cwod_vampire_clan_cappadocians),
        CHILDRENOFOSIRIS(R.string.children_of_osiris, R.string.url_cwod_vampire_clan_children_of_osiris),
        DAUGHTERSOFCACOPHONY(R.string.daughers_of_cacophony, R.string.url_cwod_vampire_clan_daughers_of_cacophony),
        GARGOYLE(R.string.gargoyle, R.string.url_cwod_vampire_clan_gargoyle),
        HARBINGERSOFSKULLS(R.string.harbingers_of_skulls, R.string.url_cwod_vampire_clan_harbingers_of_skulls),
        KIASYD(R.string.kiasyd, R.string.url_cwod_vampire_clan_kiasyd),
        LAMIA(R.string.lamia, R.string.url_cwod_vampire_clan_lamia),
        LHIANNAN(R.string.lhiannan, R.string.url_cwod_vampire_clan_lhiannan),
        NAGARAJA(R.string.nagaraja, R.string.url_cwod_vampire_clan_nagaraja),
        NOIAD(R.string.noiad, R.string.url_cwod_vampire_clan_noiad),
        PANDERS(R.string.panders, R.string.url_cwod_vampire_clan_panders),
        SALUBRI(R.string.salubri, R.string.url_cwod_vampire_clan_salubri),
        SAMEDI(R.string.samedi, R.string.url_cwod_vampire_clan_samedi),
        TRUEBRUJAH(R.string.true_brujah, R.string.url_cwod_vampire_clan_true_brujah),
        ASSAMITE(R.string.assamite, R.string.url_cwod_vampire_clan_assamite),
        BRUJAH(R.string.brujah, R.string.url_cwod_vampire_clan_brujah),
        FOLLOWERSOFSET(R.string.followers_of_set, R.string.url_cwod_vampire_clan_followers_of_set),
        GANGREL(R.string.gangrel, R.string.url_cwod_vampire_clan_gangrel),
        GIOVANNI(R.string.giovanni, R.string.url_cwod_vampire_clan_giovanni),
        LASOMBRA(R.string.lasombra, R.string.url_cwod_vampire_clan_lasombra),
        MALKAVIAN(R.string.malkavian, R.string.url_cwod_vampire_clan_malkavian),
        NOSFERATU(R.string.nosferatu, R.string.url_cwod_vampire_clan_nosferatu),
        RAVNOS(R.string.ravnos, R.string.url_cwod_vampire_clan_ravnos),
        TOREADOR(R.string.toreador, R.string.url_cwod_vampire_clan_toreador),
        TREMERE(R.string.tremere, R.string.url_cwod_vampire_clan_tremere),
        TZIMISCE(R.string.tzimisce, R.string.url_cwod_vampire_clan_tzimisce),
        VENTRUE(R.string.ventrue, R.string.url_cwod_vampire_clan_ventrue),
        ASSAMITEANTITRIBU(R.string.assamite_antitribu, R.string.url_cwod_vampire_antitribu_assamite),
        BRUJAHANTITRIBU(R.string.brujah_antitribu, R.string.url_cwod_vampire_antitribu_brujah),
        GANGRELANTITRIBU(R.string.gangrel_antitribu, R.string.url_cwod_vampire_antitribu_gangrel),
        LASOMBRAANTITRIBU(R.string.lasombra_antitribu, R.string.url_cwod_vampire_antitribu_lasombra),
        MALKAVIANANTITRIBU(R.string.malkavian_antitribu, R.string.url_cwod_vampire_antitribu_malkavian),
        NOSFERATUANTITRIBU(R.string.nosferatu_antitribu, R.string.url_cwod_vampire_antitribu_nosferatu),
        RAVNOSANTITRIBU(R.string.ravnos_antitribu, R.string.url_cwod_vampire_antitribu_ravnos),
        SALUBRIANTITRIBU(R.string.salubri_antitribu, R.string.url_cwod_vampire_antitribu_salubri),
        SERPENTSOFTHELIGHT(R.string.serpents_of_the_light, R.string.url_cwod_vampire_antitribu_serpents_of_the_light),
        TOREADORANTITRIBU(R.string.toreador_antitribu, R.string.url_cwod_vampire_antitribu_toreador),
        TREMEREANTITRIBU(R.string.tremere_antitribu, R.string.url_cwod_vampire_antitribu_tremere),
        VENTRUEANTITRIBU(R.string.ventrue_antitribu, R.string.url_cwod_vampire_antitribu_ventrue);

        private int name;
        private int url;

        CWoDVampireClan(int name, int url) {
            this.name = name;
            this.url = url;
        }
    }

    private CWoDVampireSect sect;
    private CWoDVampireClan clan;

    public CWoDVampire() {
        super(System.CWODVAMPIRE);
    }

    public CWoDVampireSect getSect() {
        return sect;
    }

    public void setSect(CWoDVampireSect sect) {
        setLeft(sect);
        this.sect = sect;
    }

    public CWoDVampireClan getClan() {
        return clan;
    }

    public void setClan(CWoDVampireClan clan) {
        setRight(clan);
        this.clan = clan;
    }
}
