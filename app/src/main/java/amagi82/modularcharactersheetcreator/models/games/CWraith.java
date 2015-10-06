package amagi82.modularcharactersheetcreator.models.games;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.Splat;

public class CWraith extends GameSystem {

    public CWraith() {
        super();
        this.gameTitle = R.string.cwod_wraith;
        this.leftTitle = R.string.legion;
        this.rightTitle = R.string.guild;
        this.gameUrl = R.string.url_game_cwod_wraith;
        this.splashUrl = R.string.url_splash_cwod_wraith;
        this.gameColor = R.color.cwod_wraith;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(8);
        list.add(Splat.create(R.string.iron_legion));
        list.add(Splat.create(R.string.skeletal_legion));
        list.add(Splat.create(R.string.grim_legion));
        list.add(Splat.create(R.string.penitent_legion));
        list.add(Splat.create(R.string.emerald_legion));
        list.add(Splat.create(R.string.silent_legion));
        list.add(Splat.create(R.string.legion_of_paupers));
        list.add(Splat.create(R.string.legion_of_fate));
        return list;
    }

    @Override public List<Splat> getListRight(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(5);
        if(splat != null){
            switch (splat.title()){
                case R.string.great_guilds:
                    list.add(Splat.create(R.string.artificers));
                    list.add(Splat.create(R.string.masquers));
                    list.add(Splat.create(R.string.pardoners));
                    list.add(Splat.create(R.string.usurers));
                    break;
                case R.string.working_guilds:
                    list.add(Splat.create(R.string.chanteurs));
                    list.add(Splat.create(R.string.harbingers));
                    list.add(Splat.create(R.string.oracles));
                    list.add(Splat.create(R.string.sandmen));
                    break;
                case R.string.criminal_guilds:
                    list.add(Splat.create(R.string.haunters));
                    list.add(Splat.create(R.string.monitors));
                    list.add(Splat.create(R.string.spooks));
                    list.add(Splat.create(R.string.proctors));
                    list.add(Splat.create(R.string.puppeteers));
                    break;
                case R.string.forbidden_guilds:
                    list.add(Splat.create(R.string.alchemists));
                    list.add(Splat.create(R.string.mnemoi));
                    list.add(Splat.create(R.string.solicitors));
                    break;
                default:
                    addDefault(list);
                    break;
            }
        }else{
            addDefault(list);
        }
        return list;
    }

    private void addDefault(List<Splat> list){
        list.add(Splat.create(R.string.none));
        list.add(Splat.create(R.string.great_guilds, false));
        list.add(Splat.create(R.string.working_guilds, false));
        list.add(Splat.create(R.string.criminal_guilds, false));
        list.add(Splat.create(R.string.forbidden_guilds, false));
    }
}
