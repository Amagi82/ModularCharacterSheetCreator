package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class NWerewolf extends GameSystem {

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

    public String tribeName;
    public String auspiceName;

    public NWerewolf() {
        super(System.NWODWEREWOLF);
    }

    public Tribe getTribe() {
        return Tribe.valueOf(tribeName);
    }

    public void setTribe(Tribe tribe) {
        setLeft(new SubType(tribe.getName(), tribe.getUrl()));
        setTitle(tribe.getName());
        tribeName = tribe.name();
    }

    public Auspice getAuspice() {
        return Auspice.valueOf(auspiceName);
    }

    public void setAuspice(Auspice auspice) {
        setRight(new SubType(auspice.getName(), auspice.getUrl()));
        auspiceName = auspice.name();
    }

    public List<Tribe> getListTribe() {
        List<Tribe> list = new ArrayList<>();
        Collections.addAll(list, Tribe.values());
        return list;
    }

    public List<Auspice> getListAuspice() {
        List<Auspice> list = new ArrayList<>();
        Collections.addAll(list, Auspice.values());
        return list;
    }
}
