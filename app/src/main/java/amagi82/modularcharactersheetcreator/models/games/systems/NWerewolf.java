package amagi82.modularcharactersheetcreator.models.games.systems;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Choice;
import amagi82.modularcharactersheetcreator.models.games.Game;

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

    public NWerewolf() {
    }

    public NWerewolf(String tribeName, String auspiceName) {
        this(Tribe.valueOf(tribeName), Auspice.valueOf(auspiceName));
    }

    public NWerewolf(Tribe tribe, Auspice auspice) {
        this.tribe = tribe;
        this.auspice = auspice;
        choiceLeft = getChoice(tribe);
        choiceRight = getChoice(auspice);
    }

    private Choice getChoice(Tribe tribe) {
        return new Choice(tribe.name(), tribe.getName(), Game.System.NWEREWOLF.getUrlBase(), tribe.getUrl());
    }

    private Choice getChoice(Auspice auspice) {
        return new Choice(auspice.name(), auspice.getName(), Game.System.NWEREWOLF.getUrlBase(), auspice.getUrl());
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

    @Override public void setLeft(String eName) {
        tribe = Tribe.valueOf(eName);
        choiceLeft = getChoice(tribe);
    }

    @Override public Choice getRight() {
        return choiceRight;
    }

    @Override public void setRight(String eName) {
        auspice = Auspice.valueOf(eName);
        choiceRight = getChoice(auspice);
    }

    @Override public boolean hasRight() {
        return true;
    }

    @Override public List<Choice> getListLeft(String eName) {
        List<Choice> list = new ArrayList<>();
        if (eName == null) for (Tribe tribe : Tribe.values()) list.add(getChoice(tribe));
        else setLeft(eName);
        return list;
    }

    @Override public List<Choice> getListRight(String eName) {
        List<Choice> list = new ArrayList<>();
        if (eName == null) for (Auspice auspice : Auspice.values()) list.add(getChoice(auspice));
        else setRight(eName);
        return list;
    }
}
