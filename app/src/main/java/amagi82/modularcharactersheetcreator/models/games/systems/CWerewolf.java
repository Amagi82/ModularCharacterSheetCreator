package amagi82.modularcharactersheetcreator.models.games.systems;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Splat;

public class CWerewolf extends GameSystem {

    public CWerewolf() {
        super();
        this.gameTitle = R.string.cwod_werewolf;
        this.leftTitle = R.string.tribe;
        this.rightTitle = R.string.auspice;
        this.gameUrl = R.string.url_game_cwod_werewolf;
        this.splashUrl = R.string.url_splash_cwod_werewolf;
        this.gameColor = R.color.cwod_werewolf;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(16);
        list.add(Splat.create(R.string.black_furies, R.string.url_cwod_werewolf_tribe_black_furies));
        list.add(Splat.create(R.string.bone_gnawers, R.string.url_cwod_werewolf_tribe_bone_gnawers));
        list.add(Splat.create(R.string.bunyip, R.string.url_cwod_werewolf_tribe_bunyip));
        list.add(Splat.create(R.string.children_of_gaia, R.string.url_cwod_werewolf_tribe_children_of_gaia));
        list.add(Splat.create(R.string.croatan, R.string.url_cwod_werewolf_tribe_croatan));
        list.add(Splat.create(R.string.fianna, R.string.url_cwod_werewolf_tribe_fianna));
        list.add(Splat.create(R.string.get_of_fenris, R.string.url_cwod_werewolf_tribe_get_of_fenris));
        list.add(Splat.create(R.string.glass_walkers, R.string.url_cwod_werewolf_tribe_glass_walkers));
        list.add(Splat.create(R.string.red_talons, R.string.url_cwod_werewolf_tribe_red_talons));
        list.add(Splat.create(R.string.shadow_lords, R.string.url_cwod_werewolf_tribe_shadow_lords));
        list.add(Splat.create(R.string.silent_striders, R.string.url_cwod_werewolf_tribe_silent_striders));
        list.add(Splat.create(R.string.silver_fangs, R.string.url_cwod_werewolf_tribe_silver_fangs));
        list.add(Splat.create(R.string.stargazers, R.string.url_cwod_werewolf_tribe_stargazers));
        list.add(Splat.create(R.string.uktena, R.string.url_cwod_werewolf_tribe_uktena));
        list.add(Splat.create(R.string.wendigo, R.string.url_cwod_werewolf_tribe_wendigo));
        list.add(Splat.create(R.string.white_howlers, R.string.url_cwod_werewolf_tribe_white_howlers));
        return list;
    }

    @Override public List<Splat> getListRight(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(5);
        list.add(Splat.create(R.string.ahroun, R.string.url_cwod_werewolf_auspice_ahroun));
        list.add(Splat.create(R.string.galliard, R.string.url_cwod_werewolf_auspice_galliard));
        list.add(Splat.create(R.string.philodox, R.string.url_cwod_werewolf_auspice_philodox));
        list.add(Splat.create(R.string.ragabash, R.string.url_cwod_werewolf_auspice_ragabash));
        list.add(Splat.create(R.string.theurge, R.string.url_cwod_werewolf_auspice_theurge));
        return list;
    }
}
