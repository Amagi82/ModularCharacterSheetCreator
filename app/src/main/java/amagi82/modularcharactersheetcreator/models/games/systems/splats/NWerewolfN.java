package amagi82.modularcharactersheetcreator.models.games.systems.splats;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class NWerewolfN extends GameSys{
    @Override public int getGameTitle() {
        return R.string.nwod_werewolf;
    }

    @Override public int getLeftTitle() {
        return R.string.tribe;
    }

    @Override public int getRightTitle() {
        return R.string.auspice;
    }

    @Override public boolean leftArchetype() {
        return true;
    }

    @Override public int getGameDrawable() {
        return R.drawable.title_werewolf_forsaken;
    }

    @Override public int getGameColor() {
        return R.color.nwod_werewolf;
    }

    @Override public int getGameCategory() {
        return NWOD;
    }

    @Override public boolean hasRight() {
        return true;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(new Splat(R.string.blood_talons, R.string.url_nwod_werewolf_base, R.string.url_nwod_werewolf_tribe_blood_talons));
        list.add(new Splat(R.string.bone_shadows, R.string.url_nwod_werewolf_base, R.string.url_nwod_werewolf_tribe_bone_shadows));
        list.add(new Splat(R.string.ghost_wolves, R.string.url_nwod_werewolf_base, R.string.url_nwod_werewolf_tribe_ghost_wolves));
        list.add(new Splat(R.string.hunters_in_darkness, R.string.url_nwod_werewolf_base, R.string.url_nwod_werewolf_tribe_hunters_in_darkness));
        list.add(new Splat(R.string.iron_masters, R.string.url_nwod_werewolf_base, R.string.url_nwod_werewolf_tribe_iron_masters));
        list.add(new Splat(R.string.storm_lords, R.string.url_nwod_werewolf_base, R.string.url_nwod_werewolf_tribe_storm_lords));
        return list;
    }

    @Override public List<Splat> getListRight(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(new Splat(R.string.cahalith, R.string.url_nwod_werewolf_base, R.string.url_nwod_werewolf_auspice_cahalith));
        list.add(new Splat(R.string.elodoth, R.string.url_nwod_werewolf_base, R.string.elodoth));
        list.add(new Splat(R.string.irraka, R.string.url_nwod_werewolf_base, R.string.irraka));
        list.add(new Splat(R.string.ithaeur, R.string.url_nwod_werewolf_base, R.string.url_nwod_werewolf_auspice_ithaeur));
        list.add(new Splat(R.string.rahu, R.string.url_nwod_werewolf_base, R.string.url_nwod_werewolf_auspice_rahu));
        return list;
    }
}
