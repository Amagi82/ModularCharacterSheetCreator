package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class NVampire extends GameSystem {

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

    public String clanName;
    public String covenantName;

    public NVampire() {
        super(System.NWODVAMPIRE);
    }

    public Clan getClan() {
        return Clan.valueOf(clanName);
    }

    public void setClan(Clan clan) {
        setLeft(new SubType(clan.getName(), clan.getUrl()));
        setTitle(clan.getName());
        clanName = clan.name();
    }

    public Covenant getCovenant() {
        return Covenant.valueOf(covenantName);
    }

    public void setCovenant(Covenant covenant) {
        setRight(new SubType(covenant.getName(), covenant.getUrl()));
        covenantName = covenant.name();
    }

    public List<Clan> getListClan() {
        List<Clan> list = new ArrayList<>();
        Collections.addAll(list, Clan.values());
        return list;
    }

    public List<Covenant> getListCovenant() {
        List<Covenant> list = new ArrayList<>();
        Collections.addAll(list, Covenant.values());
        return list;
    }
}
