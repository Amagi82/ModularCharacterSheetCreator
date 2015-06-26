package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class NWoDWerewolf extends GameSystem {

    public enum NWoDWerewolfTribe {
        BLOODTALONS(R.string.blood_talons, R.string.url_nwod_werewolf_tribe_blood_talons),
        BONESHADOWS(R.string.bone_shadows, R.string.url_nwod_werewolf_tribe_bone_shadows),
        GHOSTWOLVES(R.string.ghost_wolves, R.string.url_nwod_werewolf_tribe_ghost_wolves),
        HUNTERSINDARKNESS(R.string.hunters_in_darkness, R.string.url_nwod_werewolf_tribe_hunters_in_darkness),
        IRONMASTERS(R.string.iron_masters, R.string.url_nwod_werewolf_tribe_iron_masters),
        STORMLORDS(R.string.storm_lords, R.string.url_nwod_werewolf_tribe_storm_lords);

        private int name;
        private int url;

        NWoDWerewolfTribe(int name, int url) {
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

    public enum NWoDWerewolfAuspice {
        CAHALITH(R.string.cahalith, R.string.url_nwod_werewolf_auspice_cahalith),
        ELODOTH(R.string.elodoth, R.string.url_nwod_werewolf_auspice_elodoth),
        IRRAKA(R.string.irraka, R.string.url_nwod_werewolf_auspice_irraka),
        ITHAEUR(R.string.ithaeur, R.string.url_nwod_werewolf_auspice_ithaeur),
        RAHU(R.string.rahu, R.string.url_nwod_werewolf_auspice_rahu);

        private int name;
        private int url;

        NWoDWerewolfAuspice(int name, int url) {
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

    private NWoDWerewolfTribe tribe;
    private NWoDWerewolfAuspice auspice;

    public NWoDWerewolf() {
        super(System.NWODWEREWOLF);
    }

    public NWoDWerewolfTribe getTribe() {
        return tribe;
    }

    public void setTribe(NWoDWerewolfTribe tribe) {
        setLeft(tribe);
        this.tribe = tribe;
    }

    public NWoDWerewolfAuspice getAuspice() {
        return auspice;
    }

    public void setAuspice(NWoDWerewolfAuspice auspice) {
        setRight(auspice);
        this.auspice = auspice;
    }

    public List<NWoDWerewolfTribe> getListTribe() {
        List<NWoDWerewolfTribe> list = new ArrayList<>();
        Collections.addAll(list, NWoDWerewolfTribe.values());
        return list;
    }

    public List<NWoDWerewolfAuspice> getListAuspice() {
        List<NWoDWerewolfAuspice> list = new ArrayList<>();
        Collections.addAll(list, NWoDWerewolfAuspice.values());
        return list;
    }
}
