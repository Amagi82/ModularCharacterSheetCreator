package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Choice;

public class CMage extends Onyx {

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
    private Choice choiceLeft;
    private List<Choice> list = new ArrayList<>();

    public CMage() {
    }

    public CMage(String factionName){
        this(Faction.valueOf(factionName));
    }

    public CMage(Faction faction){
        this.faction = faction;
        choiceLeft = getChoice(faction);
    }

    private Choice getChoice(Faction faction){
        return new Choice(faction.name(), faction.getName(), Game.System.CMAGE.getUrlBase(), faction.getUrl());
    }

    @Override public String getSystemName() {
        return Game.System.CMAGE.name();
    }

    @Override public int getArchetype() {
        return faction.getName();
    }

    @Override public Choice getLeft() {
        return choiceLeft;
    }

    @Override public Choice getRight() {
        return null;
    }

    @Override public List<Choice> getList(String eName) {
        list.clear();
        if (eName == null) {
            for (Faction faction : Faction.values()) {
                if(faction.getGroup() == Group.TRADITIONS) list.add(getChoice(faction));
            }
            list.add(new Choice("TECHNOCRACY", R.string.technocracy, Game.System.CMAGE.getUrlBase(), R.string.url_cwod_mage_sect_technocracy));
            list.add(new Choice("CRAFTS", R.string.crafts));
            return list;
        }
        if(eName.equals("TECHNOCRACY")){
            for (Faction faction : Faction.values()) {
                if(faction.getGroup() == Group.TECHNOCRACY) list.add(getChoice(faction));
            }
            return list;
        }
        if(eName.equals("CRAFTS")){
            for (Faction faction : Faction.values()) {
                if(faction.getGroup() == Group.CRAFTS) list.add(getChoice(faction));
            }
            return list;
        }
        faction = Faction.valueOf(eName);
        choiceLeft = getChoice(faction);
        return null;
    }
}
