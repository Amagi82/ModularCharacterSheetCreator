package amagi82.modularcharactersheetcreator.models.games;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.Splat;

/*
    New World of Darkness
    Werewolf: The Forsaken
 */
public class NWerewolf extends Game {

    public NWerewolf(Resources res) {
        super();
        this.res = res;
        this.gameTitle = getString(R.string.nwod_werewolf);
        this.leftTitle = getString(R.string.tribe);
        this.rightTitle = getString(R.string.auspice);
        this.gameUrl = getString(R.string.url_game_nwod_werewolf);
        this.splashUrl = getString(R.string.url_splash_nwod_werewolf);
        this.gameColor = R.color.nwod_werewolf;
        this.splats = getSplats();
    }

    private SparseArray<Splat> getSplats(){
        SparseArray<Splat> splats = new SparseArray<>();


        return splats;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(6);
        list.add(Splat.create(R.string.blood_talons, R.string.url_nwod_werewolf_tribe_blood_talons));
        list.add(Splat.create(R.string.bone_shadows, R.string.url_nwod_werewolf_tribe_bone_shadows));
        list.add(Splat.create(R.string.ghost_wolves, R.string.url_nwod_werewolf_tribe_ghost_wolves));
        list.add(Splat.create(R.string.hunters_in_darkness, R.string.url_nwod_werewolf_tribe_hunters_in_darkness));
        list.add(Splat.create(R.string.iron_masters, R.string.url_nwod_werewolf_tribe_iron_masters));
        list.add(Splat.create(R.string.storm_lords, R.string.url_nwod_werewolf_tribe_storm_lords));
        return list;
    }

    @Override public List<Splat> getListRight(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(5);
        list.add(Splat.create(R.string.cahalith, R.string.url_nwod_werewolf_auspice_cahalith));
        list.add(Splat.create(R.string.elodoth, R.string.url_nwod_werewolf_auspice_elodoth));
        list.add(Splat.create(R.string.irraka, R.string.url_nwod_werewolf_auspice_irraka));
        list.add(Splat.create(R.string.ithaeur, R.string.url_nwod_werewolf_auspice_ithaeur));
        list.add(Splat.create(R.string.rahu, R.string.url_nwod_werewolf_auspice_rahu));
        return list;
    }
}
