package amagi82.modularcharactersheetcreator.models.games.systems;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Game;
import amagi82.modularcharactersheetcreator.models.games.Splat;

public class CWerewolf extends GameSystem {

    public CWerewolf() {
        super();
        this.gameTitle = R.string.cwod_werewolf;
        this.leftTitle = R.string.tribe;
        this.rightTitle = R.string.auspice;
        this.gameDrawable = R.drawable.title_werewolf_apocalypse;
        this.gameColor = R.color.cwod_werewolf;
        this.gameCategory = Game.CWOD;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(new Splat(R.string.black_furies, R.string.url_cwod_werewolf_tribe_black_furies));
        list.add(new Splat(R.string.bone_gnawers, R.string.url_cwod_werewolf_tribe_bone_gnawers));
        list.add(new Splat(R.string.bunyip, R.string.url_cwod_werewolf_tribe_bunyip));
        list.add(new Splat(R.string.children_of_gaia, R.string.url_cwod_werewolf_tribe_children_of_gaia));
        list.add(new Splat(R.string.croatan, R.string.url_cwod_werewolf_tribe_croatan));
        list.add(new Splat(R.string.fianna, R.string.url_cwod_werewolf_tribe_fianna));
        list.add(new Splat(R.string.get_of_fenris, R.string.url_cwod_werewolf_tribe_get_of_fenris));
        list.add(new Splat(R.string.glass_walkers, R.string.url_cwod_werewolf_tribe_glass_walkers));
        list.add(new Splat(R.string.red_talons, R.string.url_cwod_werewolf_tribe_red_talons));
        list.add(new Splat(R.string.shadow_lords, R.string.url_cwod_werewolf_tribe_shadow_lords));
        list.add(new Splat(R.string.silent_striders, R.string.url_cwod_werewolf_tribe_silent_striders));
        list.add(new Splat(R.string.silver_fangs, R.string.url_cwod_werewolf_tribe_silver_fangs));
        list.add(new Splat(R.string.stargazers, R.string.url_cwod_werewolf_tribe_stargazers));
        list.add(new Splat(R.string.uktena, R.string.url_cwod_werewolf_tribe_uktena));
        list.add(new Splat(R.string.wendigo, R.string.url_cwod_werewolf_tribe_wendigo));
        list.add(new Splat(R.string.white_howlers, R.string.url_cwod_werewolf_tribe_white_howlers));
        return list;
    }

    @Nullable @Override public List<Splat> getListRight(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(new Splat(R.string.ahroun, R.string.url_cwod_werewolf_auspice_ahroun));
        list.add(new Splat(R.string.galliard, R.string.url_cwod_werewolf_auspice_galliard));
        list.add(new Splat(R.string.philodox, R.string.url_cwod_werewolf_auspice_philodox));
        list.add(new Splat(R.string.ragabash, R.string.url_cwod_werewolf_auspice_ragabash));
        list.add(new Splat(R.string.theurge, R.string.url_cwod_werewolf_auspice_theurge));
        return list;
    }
}
