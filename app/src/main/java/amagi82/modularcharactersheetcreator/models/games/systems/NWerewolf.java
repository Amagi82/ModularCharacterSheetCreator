package amagi82.modularcharactersheetcreator.models.games.systems;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Splat;

public class NWerewolf extends GameSystem {

    public NWerewolf() {
        super();
        this.gameTitle = R.string.nwod_werewolf;
        this.leftTitle = R.string.tribe;
        this.rightTitle = R.string.auspice;
        this.gameUrl = R.string.url_game_nwod_werewolf;
        this.splashUrl = R.string.url_splash_nwod_werewolf;
        this.gameColor = R.color.nwod_werewolf;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(Splat.create(R.string.blood_talons, R.string.url_nwod_werewolf_tribe_blood_talons));
        list.add(Splat.create(R.string.bone_shadows, R.string.url_nwod_werewolf_tribe_bone_shadows));
        list.add(Splat.create(R.string.ghost_wolves, R.string.url_nwod_werewolf_tribe_ghost_wolves));
        list.add(Splat.create(R.string.hunters_in_darkness, R.string.url_nwod_werewolf_tribe_hunters_in_darkness));
        list.add(Splat.create(R.string.iron_masters, R.string.url_nwod_werewolf_tribe_iron_masters));
        list.add(Splat.create(R.string.storm_lords, R.string.url_nwod_werewolf_tribe_storm_lords));
        return list;
    }

    @Override public List<Splat> getListRight(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(Splat.create(R.string.cahalith, R.string.url_nwod_werewolf_auspice_cahalith));
        list.add(Splat.create(R.string.elodoth, R.string.url_nwod_werewolf_auspice_elodoth));
        list.add(Splat.create(R.string.irraka, R.string.url_nwod_werewolf_auspice_irraka));
        list.add(Splat.create(R.string.ithaeur, R.string.url_nwod_werewolf_auspice_ithaeur));
        list.add(Splat.create(R.string.rahu, R.string.url_nwod_werewolf_auspice_rahu));
        return list;
    }
}
