package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class NWoDVampire extends GameSystem {

    public enum NWoDVampireClan {
        DAEVA(R.string.daeva, R.string.url_nwod_vampire_clan_daeva),
        GANGREL(R.string.gangrel, R.string.url_nwod_vampire_clan_gangrel),
        MEKHET(R.string.mekhet, R.string.url_nwod_vampire_clan_mekhet),
        NOSFERATU(R.string.nosferatu, R.string.url_nwod_vampire_clan_nosferatu),
        VENTRUE(R.string.ventrue, R.string.url_nwod_vampire_clan_ventrue);

        private int name;
        private int url;

        NWoDVampireClan(int name, int url) {
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

    public enum NWoDVampireCovenant {
        CARTHIANMOVEMENT(R.string.carthian_movement, R.string.url_nwod_vampire_covenant_carthian_movement),
        CIRCLEOFTHECRONE(R.string.circle_of_the_crone, R.string.url_nwod_vampire_covenant_circle_of_the_crone),
        HOLYENGINEERS(R.string.holy_engineers, R.string.url_nwod_vampire_covenant_holy_engineers),
        INVICTUS(R.string.invictus, R.string.url_nwod_vampire_covenant_invictus),
        LANCEAETSANCTUM(R.string.lancea_et_sanctum, R.string.url_nwod_vampire_covenant_lancea_et_sanctum),
        ORDODRACUL(R.string.ordo_dracul, R.string.url_nwod_vampire_covenant_ordo_dracul),
        UNALIGNED(R.string.unaligned, R.string.url_nwod_vampire_covenant_unaligned);

        private int name;
        private int url;

        NWoDVampireCovenant(int name, int url) {
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

    private NWoDVampireClan clan;
    private NWoDVampireCovenant covenant;

    public NWoDVampire() {
        super(System.NWODVAMPIRE);
    }

    public NWoDVampireClan getClan() {
        return clan;
    }

    public void setClan(NWoDVampireClan clan) {
        setLeft(clan);
        this.clan = clan;
    }

    public NWoDVampireCovenant getCovenant() {
        return covenant;
    }

    public void setCovenant(NWoDVampireCovenant covenant) {
        setRight(covenant);
        this.covenant = covenant;
    }

    public List<NWoDVampireClan> getListClan() {
        List<NWoDVampireClan> list = new ArrayList<>();
        Collections.addAll(list, NWoDVampireClan.values());
        return list;
    }

    public List<NWoDVampireCovenant> getListCovenant() {
        List<NWoDVampireCovenant> list = new ArrayList<>();
        Collections.addAll(list, NWoDVampireCovenant.values());
        return list;
    }
}
