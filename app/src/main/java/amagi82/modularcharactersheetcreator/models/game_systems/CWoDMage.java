package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class CWoDMage extends GameSystem {

    public enum CWoDMageFactions {
        AKASHAYANA(R.string.akashayana, R.string.url_cwod_mage_tradition_akashayana, Faction.TRADITIONS),
        CELESTIALCHORUS(R.string.celestial_chorus, R.string.url_cwod_mage_tradition_celestial_chorus, Faction.TRADITIONS),
        CULTOFECSTACY(R.string.cult_of_ecstasy, R.string.url_cwod_mage_tradition_cult_of_ecstasy, Faction.TRADITIONS),
        DREAMSPEAKERS(R.string.dreamspeakers, R.string.url_cwod_mage_tradition_dreamspeakers, Faction.TRADITIONS),
        EUTHANATOI(R.string.euthanatoi, R.string.url_cwod_mage_tradition_euthanatoi, Faction.TRADITIONS),
        HOLLOWONES(R.string.hollow_ones, R.string.url_cwod_mage_tradition_hollow_ones, Faction.TRADITIONS),
        ORDEROFHERMES(R.string.order_of_hermes, R.string.url_cwod_mage_tradition_order_of_hermes, Faction.TRADITIONS),
        SCIONSOFETHER(R.string.scions_of_ether, R.string.url_cwod_mage_tradition_scions_of_ether, Faction.TRADITIONS),
        VERBENAE(R.string.verbenae, R.string.url_cwod_mage_tradition_verbenae, Faction.TRADITIONS),
        VIRTUALADEPTS(R.string.virtual_adepts, R.string.url_cwod_mage_tradition_virtual_adepts, Faction.TRADITIONS),
        ITERATIONX(R.string.iteration_x, R.string.url_cwod_mage_convention_iteration_x, Faction.TECHNOCRACY),
        NEWWORLDORDER(R.string.new_world_order, R.string.url_cwod_mage_convention_new_world_order, Faction.TECHNOCRACY),
        PROGENITORS(R.string.progenitors, R.string.url_cwod_mage_convention_progenitors, Faction.TECHNOCRACY),
        SYNDICATE(R.string.syndicate, R.string.url_cwod_mage_convention_syndicate, Faction.TECHNOCRACY),
        VOIDENGINEERS(R.string.void_engineers, R.string.url_cwod_mage_convention_void_engineers, Faction.TECHNOCRACY),
        AHLIBATIN(R.string.ahl_i_batin, R.string.url_cwod_mage_craft_ahl_i_batin, Faction.CRAFTS),
        BATAA(R.string.bataa, R.string.url_cwod_mage_craft_bataa, Faction.CRAFTS),
        CHILDRENOFKNOWLEDGE(R.string.children_of_knowledge, R.string.url_cwod_mage_craft_children_of_knowledge, Faction.CRAFTS),
        KNIGHTSTEMPLAR(R.string.knights_templar, R.string.url_cwod_mage_craft_knights_templar, Faction.CRAFTS),
        KOPALOEI(R.string.kopa_loei, R.string.url_cwod_mage_craft_kopa_loei, Faction.CRAFTS),
        NGOMA(R.string.ngoma, R.string.url_cwod_mage_craft_ngoma, Faction.CRAFTS),
        ORPHANS(R.string.orphans, R.string.url_cwod_mage_craft_orphans, Faction.CRAFTS),
        SISTERSOFHIPPOLYTA(R.string.sisters_of_hippolyta, R.string.url_cwod_mage_craft_sisters_of_hippolyta, Faction.CRAFTS),
        TAFTANI(R.string.taftani, R.string.url_cwod_mage_craft_taftani, Faction.CRAFTS),
        WULUNG(R.string.wu_lung, R.string.url_cwod_mage_craft_wu_lung, Faction.CRAFTS);

        private int name;
        private int url;
        private Faction faction;

        CWoDMageFactions(int name, int url, Faction faction) {
            this.name = name;
            this.url = url;
            this.faction = faction;
        }

        public int getName() {
            return name;
        }

        public int getUrl() {
            return url;
        }

        public Faction getFaction() {
            return faction;
        }
    }

    public enum Faction {
        TRADITIONS, TECHNOCRACY, CRAFTS
    }

    private CWoDMageFactions faction;

    public CWoDMage() {
        super(System.CWODMAGE);
    }

    public CWoDMageFactions getFaction() {
        return faction;
    }

    public void setFaction(CWoDMageFactions faction) {
        setLeft(faction);
        this.faction = faction;
    }

    public List<CWoDMageFactions> getListFactions(Faction faction){
        List<CWoDMageFactions> list = new ArrayList<>();
        for(CWoDMageFactions f : CWoDMageFactions.values()){
            if(f.faction == faction) list.add(f);
        }
        return list;
    }
}
