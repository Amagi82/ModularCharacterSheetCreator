package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class CWoDVampire extends GameSystem {

    public enum CWoDVampireSect {
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

        public int getName() {
            return name;
        }

        public int getUrl() {
            return url;
        }
    }

    public enum CWoDVampireClan {
        AHRIMANES(R.string.ahrimanes, R.string.url_cwod_vampire_clan_ahrimanes, true),
        ANDA(R.string.anda, R.string.url_cwod_vampire_clan_anda, true),
        BAALI(R.string.baali, R.string.url_cwod_vampire_clan_baali, true),
        BLOODBROTHERS(R.string.blood_brothers, R.string.url_cwod_vampire_clan_blood_brothers, true),
        CAITIFF(R.string.caitiff, R.string.url_cwod_vampire_clan_caitiff, Member.YES, Member.YES, Member.YES, Member.YES),
        CAPPADOCIANS(R.string.cappadocians, R.string.url_cwod_vampire_clan_cappadocians, true),
        CHILDRENOFOSIRIS(R.string.children_of_osiris, R.string.url_cwod_vampire_clan_children_of_osiris, true),
        DAUGHTERSOFCACOPHONY(R.string.daughers_of_cacophony, R.string.url_cwod_vampire_clan_daughers_of_cacophony, true),
        GARGOYLE(R.string.gargoyle, R.string.url_cwod_vampire_clan_gargoyle, true),
        HARBINGERSOFSKULLS(R.string.harbingers_of_skulls, R.string.url_cwod_vampire_clan_harbingers_of_skulls, true),
        KIASYD(R.string.kiasyd, R.string.url_cwod_vampire_clan_kiasyd, true),
        LAMIA(R.string.lamia, R.string.url_cwod_vampire_clan_lamia, true),
        LHIANNAN(R.string.lhiannan, R.string.url_cwod_vampire_clan_lhiannan, true),
        NAGARAJA(R.string.nagaraja, R.string.url_cwod_vampire_clan_nagaraja, true),
        NOIAD(R.string.noiad, R.string.url_cwod_vampire_clan_noiad, true),
        PANDERS(R.string.panders, R.string.url_cwod_vampire_clan_panders, true),
        SALUBRI(R.string.salubri, R.string.url_cwod_vampire_clan_salubri, true, Member.BLOODLINE, Member.BLOODLINE, Member.NO, Member.BLOODLINE),
        SAMEDI(R.string.samedi, R.string.url_cwod_vampire_clan_samedi, true),
        TRUEBRUJAH(R.string.true_brujah, R.string.url_cwod_vampire_clan_true_brujah, true),
        ASSAMITE(R.string.assamite, R.string.url_cwod_vampire_clan_assamite, Member.OPTIONAL, Member.OPTIONAL, Member.NO, Member.YES),
        BRUJAH(R.string.brujah, R.string.url_cwod_vampire_clan_brujah, Member.YES, Member.YES, Member.NO, Member.OPTIONAL),
        FOLLOWERSOFSET(R.string.followers_of_set, R.string.url_cwod_vampire_clan_followers_of_set, Member.OPTIONAL, Member.OPTIONAL, Member.NO, Member.YES),
        GANGREL(R.string.gangrel, R.string.url_cwod_vampire_clan_gangrel, Member.YES, Member.YES, Member.NO, Member.OPTIONAL),
        GIOVANNI(R.string.giovanni, R.string.url_cwod_vampire_clan_giovanni, Member.OPTIONAL, Member.OPTIONAL, Member.OPTIONAL, Member.YES),
        LASOMBRA(R.string.lasombra, R.string.url_cwod_vampire_clan_lasombra, Member.NO, Member.NO, Member.YES, Member.NO),
        MALKAVIAN(R.string.malkavian, R.string.url_cwod_vampire_clan_malkavian, Member.YES, Member.YES, Member.NO, Member.OPTIONAL),
        NOSFERATU(R.string.nosferatu, R.string.url_cwod_vampire_clan_nosferatu, Member.YES, Member.YES, Member.NO, Member.OPTIONAL),
        RAVNOS(R.string.ravnos, R.string.url_cwod_vampire_clan_ravnos, Member.OPTIONAL, Member.OPTIONAL, Member.NO, Member.YES),
        TOREADOR(R.string.toreador, R.string.url_cwod_vampire_clan_toreador, Member.YES, Member.YES, Member.NO, Member.OPTIONAL),
        TREMERE(R.string.tremere, R.string.url_cwod_vampire_clan_tremere, Member.YES, Member.OPTIONAL, Member.NO, Member.OPTIONAL),
        TZIMISCE(R.string.tzimisce, R.string.url_cwod_vampire_clan_tzimisce, Member.OPTIONAL, Member.OPTIONAL, Member.YES, Member.OPTIONAL),
        VENTRUE(R.string.ventrue, R.string.url_cwod_vampire_clan_ventrue, Member.YES, Member.OPTIONAL, Member.NO, Member.OPTIONAL),
        ASSAMITEANTITRIBU(R.string.assamite_antitribu, R.string.url_cwod_vampire_antitribu_assamite, Member.NO, Member.NO, Member.OPTIONAL, Member.NO),
        BRUJAHANTITRIBU(R.string.brujah_antitribu, R.string.url_cwod_vampire_antitribu_brujah, Member.NO, Member.NO, Member.OPTIONAL, Member.NO),
        GANGRELANTITRIBU(R.string.gangrel_antitribu, R.string.url_cwod_vampire_antitribu_gangrel, Member.NO, Member.NO, Member.OPTIONAL, Member.NO),
        LASOMBRAANTITRIBU(R.string.lasombra_antitribu, R.string.url_cwod_vampire_antitribu_lasombra, Member.OPTIONAL, Member.OPTIONAL, Member.NO, Member.OPTIONAL),
        MALKAVIANANTITRIBU(R.string.malkavian_antitribu, R.string.url_cwod_vampire_antitribu_malkavian, Member.NO, Member.NO, Member.OPTIONAL, Member.NO),
        NOSFERATUANTITRIBU(R.string.nosferatu_antitribu, R.string.url_cwod_vampire_antitribu_nosferatu, Member.NO, Member.NO, Member.OPTIONAL, Member.NO),
        RAVNOSANTITRIBU(R.string.ravnos_antitribu, R.string.url_cwod_vampire_antitribu_ravnos, Member.NO, Member.NO, Member.OPTIONAL, Member.NO),
        SALUBRIANTITRIBU(R.string.salubri_antitribu, R.string.url_cwod_vampire_antitribu_salubri, true, Member.NO, Member.NO, Member.BLOODLINE, Member.NO),
        SERPENTSOFTHELIGHT(R.string.serpents_of_the_light, R.string.url_cwod_vampire_antitribu_serpents_of_the_light, Member.OPTIONAL, Member.OPTIONAL, Member.OPTIONAL, Member.NO),
        TOREADORANTITRIBU(R.string.toreador_antitribu, R.string.url_cwod_vampire_antitribu_toreador, Member.NO, Member.NO, Member.OPTIONAL, Member.NO),
        TREMEREANTITRIBU(R.string.tremere_antitribu, R.string.url_cwod_vampire_antitribu_tremere, Member.NO, Member.NO, Member.OPTIONAL, Member.NO),
        VENTRUEANTITRIBU(R.string.ventrue_antitribu, R.string.url_cwod_vampire_antitribu_ventrue, Member.NO, Member.NO, Member.OPTIONAL, Member.NO);

        private int name;
        private int url;
        private boolean bloodline;
        private Member camarilla, anarch, sabbat, independent;

        CWoDVampireClan(int name, int url, boolean bloodline) {
            this(name, url, bloodline, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE);
        }

        CWoDVampireClan(int name, int url, Member camarilla, Member anarch, Member sabbat, Member independent) {
            this(name, url, false, camarilla, anarch, sabbat, independent);
        }

        CWoDVampireClan(int name, int url, boolean bloodline, Member camarilla, Member anarch, Member sabbat, Member independent) {
            this.name = name;
            this.url = url;
            this.bloodline = bloodline;
            this.camarilla = camarilla;
            this.anarch = anarch;
            this.sabbat = sabbat;
            this.independent = independent;
        }

        public int getName() {
            return name;
        }

        public int getUrl() {
            return url;
        }

        public boolean isBloodline() {
            return bloodline;
        }

        public Member getCamarilla() {
            return camarilla;
        }

        public Member getAnarch() {
            return anarch;
        }

        public Member getSabbat() {
            return sabbat;
        }

        public Member getIndependent() {
            return independent;
        }

        public Member getSectMembership(CWoDVampireSect sect) {
            return sect == CWoDVampireSect.CAMARILLA ? camarilla : sect == CWoDVampireSect.ANARCH ? anarch :
                    sect == CWoDVampireSect.SABBAT ? sabbat : independent;
        }
    }

    private enum Member {
        YES, OPTIONAL, NO, BLOODLINE;
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

    public List<CWoDVampireSect> getListSect() {
        List<CWoDVampireSect> list = new ArrayList<>();
        Collections.addAll(list, CWoDVampireSect.values());
        return list;
    }

    public List<CWoDVampireClan> getListClanDefault(CWoDVampireSect sect) {
        List<CWoDVampireClan> list = new ArrayList<>();
        for (CWoDVampireClan clan : CWoDVampireClan.values()) {
            if (clan.getSectMembership(sect) == Member.YES) list.add(clan);
        }
        return list;
    }

    public List<CWoDVampireClan> getListClanOptional(CWoDVampireSect sect) {
        List<CWoDVampireClan> list = new ArrayList<>(getListClanDefault(sect));
        for (CWoDVampireClan clan : CWoDVampireClan.values()) {
            if (clan.getSectMembership(sect) == Member.OPTIONAL) list.add(clan);
        }
        return list;
    }

    public List<CWoDVampireClan> getListClanBloodlines(CWoDVampireSect sect) {
        List<CWoDVampireClan> list = new ArrayList<>(getListClanDefault(sect));
        for (CWoDVampireClan clan : CWoDVampireClan.values()) {
            if (clan.getSectMembership(sect) == Member.BLOODLINE) list.add(clan);
        }
        return list;
    }
}
