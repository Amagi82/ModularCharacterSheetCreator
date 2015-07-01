package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Choice;

public class NVampire extends Onyx {

    public enum Clan {
        DAEVA(R.string.daeva, R.string.url_nwod_vampire_clan_daeva),
        GANGREL(R.string.gangrel, R.string.url_nwod_vampire_clan_gangrel),
        MEKHET(R.string.mekhet, R.string.url_nwod_vampire_clan_mekhet),
        NOSFERATU(R.string.nosferatu, R.string.url_nwod_vampire_clan_nosferatu),
        VENTRUE(R.string.ventrue, R.string.url_nwod_vampire_clan_ventrue);

        private int name;
        private int url;

        Clan(int name, int url) {
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

    public enum Covenant {
        CARTHIANMOVEMENT(R.string.carthian_movement, R.string.url_nwod_vampire_covenant_carthian_movement),
        CIRCLEOFTHECRONE(R.string.circle_of_the_crone, R.string.url_nwod_vampire_covenant_circle_of_the_crone),
        HOLYENGINEERS(R.string.holy_engineers, R.string.url_nwod_vampire_covenant_holy_engineers),
        INVICTUS(R.string.invictus, R.string.url_nwod_vampire_covenant_invictus),
        LANCEAETSANCTUM(R.string.lancea_et_sanctum, R.string.url_nwod_vampire_covenant_lancea_et_sanctum),
        ORDODRACUL(R.string.ordo_dracul, R.string.url_nwod_vampire_covenant_ordo_dracul),
        UNALIGNED(R.string.unaligned, R.string.url_nwod_vampire_covenant_unaligned);

        private int name;
        private int url;

        Covenant(int name, int url) {
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

    private Clan clan;
    private Covenant covenant;
    private Choice choiceLeft;
    private Choice choiceRight;
    private List<Choice> list = new ArrayList<>();

    public NVampire() {
    }

    public NVampire(String clanName, String covenantName) {
        this(Clan.valueOf(clanName),Covenant.valueOf(covenantName));
    }

    public NVampire(Clan clan, Covenant covenant){
        this.clan = clan;
        this.covenant = covenant;
        choiceLeft = getChoice(clan);
        choiceRight = getChoice(covenant);
    }

    private Choice getChoice(Clan clan) {
        return new Choice(clan.name(), clan.getName(), Game.System.NVAMPIRE.getUrlBase(), clan.getUrl());
    }

    private Choice getChoice(Covenant covenant) {
        return new Choice(covenant.name(), covenant.getName(), Game.System.NVAMPIRE.getUrlBase(), covenant.getUrl());
    }

    @Override public String getSystemName() {
        return Game.System.NVAMPIRE.name();
    }

    @Override public int getArchetype() {
        return clan.getName();
    }

    @Override public Choice getLeft() {
        return choiceLeft;
    }

    @Override public Choice getRight() {
        return choiceRight;
    }

    @Override public List<Choice> getList(String eName) {
        list.clear();
        if (eName == null) {
            for (Clan clan : Clan.values()){
                list.add(getChoice(clan));
            }
            return list;
        }
        if(clan == null) {
            clan = Clan.valueOf(eName);
            choiceLeft = getChoice(clan);

            for (Covenant covenant : Covenant.values()) {
                list.add(getChoice(covenant));
            }
            return list;
        }
        covenant = Covenant.valueOf(eName);
        choiceRight = getChoice(covenant);
        return null;
    }
}
