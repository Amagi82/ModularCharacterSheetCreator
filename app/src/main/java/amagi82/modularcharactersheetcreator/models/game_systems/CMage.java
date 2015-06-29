package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class CMage extends GameSystem{

    public enum Faction {
        AKASHAYANA(R.string.akashayana, R.string.url_cwod_mage_tradition_akashayana, Group.TRADITIONS),
        CELESTIALCHORUS(R.string.celestial_chorus, R.string.url_cwod_mage_tradition_celestial_chorus, Group.TRADITIONS),
        CULTOFECSTACY(R.string.cult_of_ecstasy, R.string.url_cwod_mage_tradition_cult_of_ecstasy, Group.TRADITIONS),
        DREAMSPEAKERS(R.string.dreamspeakers, R.string.url_cwod_mage_tradition_dreamspeakers, Group.TRADITIONS),
        EUTHANATOI(R.string.euthanatoi, R.string.url_cwod_mage_tradition_euthanatoi, Group.TRADITIONS),
        HOLLOWONES(R.string.hollow_ones, R.string.url_cwod_mage_tradition_hollow_ones, Group.TRADITIONS),
        ORDEROFHERMES(R.string.order_of_hermes, R.string.url_cwod_mage_tradition_order_of_hermes, Group.TRADITIONS),
        SCIONSOFETHER(R.string.scions_of_ether, R.string.url_cwod_mage_tradition_scions_of_ether, Group.TRADITIONS),
        VERBENAE(R.string.verbenae, R.string.url_cwod_mage_tradition_verbenae, Group.TRADITIONS),
        VIRTUALADEPTS(R.string.virtual_adepts, R.string.url_cwod_mage_tradition_virtual_adepts, Group.TRADITIONS),
        ITERATIONX(R.string.iteration_x, R.string.url_cwod_mage_convention_iteration_x, Group.TECHNOCRACY),
        NEWWORLDORDER(R.string.new_world_order, R.string.url_cwod_mage_convention_new_world_order, Group.TECHNOCRACY),
        PROGENITORS(R.string.progenitors, R.string.url_cwod_mage_convention_progenitors, Group.TECHNOCRACY),
        SYNDICATE(R.string.syndicate, R.string.url_cwod_mage_convention_syndicate, Group.TECHNOCRACY),
        VOIDENGINEERS(R.string.void_engineers, R.string.url_cwod_mage_convention_void_engineers, Group.TECHNOCRACY),
        AHLIBATIN(R.string.ahl_i_batin, R.string.url_cwod_mage_craft_ahl_i_batin, Group.CRAFTS),
        BATAA(R.string.bataa, R.string.url_cwod_mage_craft_bataa, Group.CRAFTS),
        CHILDRENOFKNOWLEDGE(R.string.children_of_knowledge, R.string.url_cwod_mage_craft_children_of_knowledge, Group.CRAFTS),
        KNIGHTSTEMPLAR(R.string.knights_templar, R.string.url_cwod_mage_craft_knights_templar, Group.CRAFTS),
        KOPALOEI(R.string.kopa_loei, R.string.url_cwod_mage_craft_kopa_loei, Group.CRAFTS),
        NGOMA(R.string.ngoma, R.string.url_cwod_mage_craft_ngoma, Group.CRAFTS),
        ORPHANS(R.string.orphans, R.string.url_cwod_mage_craft_orphans, Group.CRAFTS),
        SISTERSOFHIPPOLYTA(R.string.sisters_of_hippolyta, R.string.url_cwod_mage_craft_sisters_of_hippolyta, Group.CRAFTS),
        TAFTANI(R.string.taftani, R.string.url_cwod_mage_craft_taftani, Group.CRAFTS),
        WULUNG(R.string.wu_lung, R.string.url_cwod_mage_craft_wu_lung, Group.CRAFTS);

        private int name;
        private int url;
        private Group group;

        Faction(int name, int url, Group group) {
            this.name = name;
            this.url = url;
            this.group = group;
        }

        public int getName() {
            return name;
        }

        public int getUrl() {
            return url;
        }

        public Group getGroup() {
            return group;
        }
    }

    public enum Group {
        TRADITIONS, TECHNOCRACY, CRAFTS
    }

    private Faction faction;

    public CMage() {
        super(System.CWODMAGE);
    }

    public CMage(String factionName){
        super(System.CWODMAGE);
        faction = Faction.valueOf(factionName);
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        setLeft(faction.name(), faction.getName(), faction.getUrl());
        setArchetype(faction.getName());
        this.faction = faction;
    }

    public List<Faction> getListFactions(Group group){
        List<Faction> list = new ArrayList<>();
        for(Faction f : Faction.values()){
            if(f.group == group) list.add(f);
        }
        return list;
    }
}
