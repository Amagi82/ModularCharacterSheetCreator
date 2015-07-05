package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Choice;

public class CVampire extends Onyx {

    public enum Sect {
        CAMARILLA(R.string.camarilla, R.string.url_cwod_vampire_sect_camarilla),
        ANARCH(R.string.anarchs, R.string.url_cwod_vampire_sect_anarchs),
        SABBAT(R.string.sabbat, R.string.url_cwod_vampire_sect_sabbat),
        INDEPENDENT(R.string.independent, R.string.url_cwod_vampire_sect_independent);

        private int name;
        private int url;

        Sect(int name, int url) {
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

    public enum Clan {
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
        SERPENTSOFTHELIGHT(R.string.serpents_of_the_light, R.string.url_cwod_vampire_antitribu_serpents_of_the_light, Member.OPTIONAL, Member.OPTIONAL, Member.OPTIONAL, Member.NO),
        TOREADORANTITRIBU(R.string.toreador_antitribu, R.string.url_cwod_vampire_antitribu_toreador, Member.NO, Member.NO, Member.OPTIONAL, Member.NO),
        TREMEREANTITRIBU(R.string.tremere_antitribu, R.string.url_cwod_vampire_antitribu_tremere, Member.NO, Member.NO, Member.OPTIONAL, Member.NO),
        VENTRUEANTITRIBU(R.string.ventrue_antitribu, R.string.url_cwod_vampire_antitribu_ventrue, Member.NO, Member.NO, Member.OPTIONAL, Member.NO),
        CAITIFF(R.string.caitiff, R.string.url_cwod_vampire_clan_caitiff, Member.YES, Member.YES, Member.YES, Member.YES),
        AHRIMANES(R.string.ahrimanes, R.string.url_cwod_vampire_clan_ahrimanes, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE),
        ANDA(R.string.anda, R.string.url_cwod_vampire_clan_anda, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE),
        BAALI(R.string.baali, R.string.url_cwod_vampire_clan_baali, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE),
        BLOODBROTHERS(R.string.blood_brothers, R.string.url_cwod_vampire_clan_blood_brothers, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE),
        CAPPADOCIANS(R.string.cappadocians, R.string.url_cwod_vampire_clan_cappadocians, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE),
        CHILDRENOFOSIRIS(R.string.children_of_osiris, R.string.url_cwod_vampire_clan_children_of_osiris, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE),
        DAUGHTERSOFCACOPHONY(R.string.daughers_of_cacophony, R.string.url_cwod_vampire_clan_daughers_of_cacophony, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE),
        GARGOYLE(R.string.gargoyle, R.string.url_cwod_vampire_clan_gargoyle, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE),
        HARBINGERSOFSKULLS(R.string.harbingers_of_skulls, R.string.url_cwod_vampire_clan_harbingers_of_skulls, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE),
        KIASYD(R.string.kiasyd, R.string.url_cwod_vampire_clan_kiasyd, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE),
        LAMIA(R.string.lamia, R.string.url_cwod_vampire_clan_lamia, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE),
        LHIANNAN(R.string.lhiannan, R.string.url_cwod_vampire_clan_lhiannan, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE),
        NAGARAJA(R.string.nagaraja, R.string.url_cwod_vampire_clan_nagaraja, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE),
        NOIAD(R.string.noiad, R.string.url_cwod_vampire_clan_noiad, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE),
        PANDERS(R.string.panders, R.string.url_cwod_vampire_clan_panders, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE),
        SALUBRI(R.string.salubri, R.string.url_cwod_vampire_clan_salubri, Member.BLOODLINE, Member.BLOODLINE, Member.NO, Member.BLOODLINE),
        SALUBRIANTITRIBU(R.string.salubri_antitribu, R.string.url_cwod_vampire_antitribu_salubri, Member.NO, Member.NO, Member.BLOODLINE, Member.NO),
        SAMEDI(R.string.samedi, R.string.url_cwod_vampire_clan_samedi, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE),
        TRUEBRUJAH(R.string.true_brujah, R.string.url_cwod_vampire_clan_true_brujah, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE, Member.BLOODLINE);

        private int name;
        private int url;
        private Member camarilla, anarch, sabbat, independent;

        Clan(int name, int url, Member camarilla, Member anarch, Member sabbat, Member independent) {
            this.name = name;
            this.url = url;
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

        public Member getSectMembership(Sect sect) {
            return sect == Sect.CAMARILLA ? camarilla : sect == Sect.ANARCH ? anarch :
                    sect == Sect.SABBAT ? sabbat : independent;
        }
    }

    private enum Member {
        YES, OPTIONAL, NO, BLOODLINE;
    }

    private Sect sect;
    private Clan clan;
    private Choice choiceLeft;
    private Choice choiceRight;

    public CVampire() {
    }

    public CVampire(String sectName, String clanName) {
        this(Sect.valueOf(sectName), Clan.valueOf(clanName));
    }

    public CVampire(Sect sect, Clan clan) {
        this.sect = sect;
        this.clan = clan;
        choiceLeft = getChoice(sect);
        choiceRight = getChoice(clan);
    }

    public boolean isSect(String eName) {
        for (Sect sect : Sect.values()) {
            if (sect.name().equals(eName)) return true;
        }
        return false;
    }

    private Choice getChoice(Sect sect) {
        return new Choice(sect.name(), sect.getName(), Game.System.CVAMPIRE.getUrlBase(), sect.getUrl());
    }

    private Choice getChoice(Clan clan) {
        return new Choice(clan.name(), clan.getName(), Game.System.CVAMPIRE.getUrlBase(), clan.getUrl());
    }

    @Override public String getSystemName() {
        return Game.System.CVAMPIRE.name();
    }

    @Override public int getArchetype() {
        return clan.getName();
    }

    @Override public Choice getLeft() {
        return choiceLeft;
    }

    @Override public void setLeft(String eName) {
        sect = Sect.valueOf(eName);
        choiceLeft = getChoice(sect);
    }

    @Override public Choice getRight() {
        return choiceRight;
    }

    @Override public void setRight(String eName) {
        clan = Clan.valueOf(eName);
        choiceRight = getChoice(clan);
    }

    @Override public boolean hasRight() {
        return true;
    }

    @Override public List<Choice> getListLeft(String eName) {
        List<Choice> list = new ArrayList<>();
        if (eName == null) for (Sect sect : Sect.values()) list.add(getChoice(sect));
        else setLeft(eName);
        return list;
    }

    @Override public List<Choice> getListRight(String eName) {
        List<Choice> list = new ArrayList<>();
        if (eName == null) {
            for (Clan clan : Clan.values()) if (clan.getSectMembership(sect) == Member.YES) list.add(getChoice(clan));
            list.add(new Choice("OTHERS", R.string.others));
            list.add(new Choice("BLOODLINES", R.string.bloodlines));
        } else if (eName.equals("OTHERS")) {
            for (Clan clan : Clan.values()) if (clan.getSectMembership(sect) == Member.YES) list.add(getChoice(clan));
            for (Clan clan : Clan.values()) if (clan.getSectMembership(sect) == Member.OPTIONAL) list.add(getChoice(clan));
        } else if (eName.equals("BLOODLINES")) {
            for (Clan clan : Clan.values()) {
                if (clan.getSectMembership(sect) == Member.YES || clan.getSectMembership(sect) == Member.BLOODLINE) list.add(getChoice(clan));
            }
        } else setRight(eName);
        return list;
    }
}
