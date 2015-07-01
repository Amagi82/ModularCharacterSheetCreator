package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Choice;

public class NWerewolf extends Onyx {

    public enum Tribe {
        BLOODTALONS(R.string.blood_talons, R.string.url_nwod_werewolf_tribe_blood_talons),
        BONESHADOWS(R.string.bone_shadows, R.string.url_nwod_werewolf_tribe_bone_shadows),
        GHOSTWOLVES(R.string.ghost_wolves, R.string.url_nwod_werewolf_tribe_ghost_wolves),
        HUNTERSINDARKNESS(R.string.hunters_in_darkness, R.string.url_nwod_werewolf_tribe_hunters_in_darkness),
        IRONMASTERS(R.string.iron_masters, R.string.url_nwod_werewolf_tribe_iron_masters),
        STORMLORDS(R.string.storm_lords, R.string.url_nwod_werewolf_tribe_storm_lords);

        private int name;
        private int url;

        Tribe(int name, int url) {
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

    public enum Auspice {
        CAHALITH(R.string.cahalith, R.string.url_nwod_werewolf_auspice_cahalith),
        ELODOTH(R.string.elodoth, R.string.url_nwod_werewolf_auspice_elodoth),
        IRRAKA(R.string.irraka, R.string.url_nwod_werewolf_auspice_irraka),
        ITHAEUR(R.string.ithaeur, R.string.url_nwod_werewolf_auspice_ithaeur),
        RAHU(R.string.rahu, R.string.url_nwod_werewolf_auspice_rahu);

        private int name;
        private int url;

        Auspice(int name, int url) {
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

    private Tribe tribe;
    private Auspice auspice;
    private Choice choiceLeft;
    private Choice choiceRight;
    private List<Choice> list = new ArrayList<>();

    public NWerewolf() {
    }

    public NWerewolf(String tribeName, String auspiceName) {
        tribe = Tribe.valueOf(tribeName);
        auspice = Auspice.valueOf(auspiceName);
    }

    @Override public String getSystemName() {
        return Game.System.NWEREWOLF.name();
    }

    @Override public int getArchetype() {
        return tribe.getName();
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
            for (Tribe tribe : Tribe.values()){
                list.add(new Choice(tribe.name(), tribe.getName(), Game.System.NWEREWOLF.getUrlBase(), tribe.getUrl()));
            }
            return list;
        }
        if(tribe == null) {
            tribe = Tribe.valueOf(eName);
            choiceLeft = new Choice(tribe.name(), tribe.getName(), Game.System.NWEREWOLF.getUrlBase(), tribe.getUrl());

            for (Auspice auspice : Auspice.values()) {
                list.add(new Choice(auspice.name(), auspice.getName(), Game.System.NWEREWOLF.getUrlBase(), auspice.getUrl()));
            }
            return list;
        }
        auspice = Auspice.valueOf(eName);
        choiceRight = new Choice(auspice.name(), auspice.getName(), Game.System.NWEREWOLF.getUrlBase(), auspice.getUrl());
        return null;
    }
}
