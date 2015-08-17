package amagi82.modularcharactersheetcreator.models.games.systems;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class CWraith extends GameSys {

    public CWraith() {
        super();
        this.gameTitle = R.string.cwod_wraith;
        this.leftTitle = R.string.legion;
        this.rightTitle = R.string.guild;
        this.gameDrawable = R.drawable.title_wraith_oblivion;
        this.gameColor = R.color.cwod_wraith;
        this.gameCategory = CWOD;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(new Splat(R.string.iron_legion));
        list.add(new Splat(R.string.skeletal_legion));
        list.add(new Splat(R.string.grim_legion));
        list.add(new Splat(R.string.penitent_legion));
        list.add(new Splat(R.string.emerald_legion));
        list.add(new Splat(R.string.silent_legion));
        list.add(new Splat(R.string.legion_of_paupers));
        list.add(new Splat(R.string.legion_of_fate));
        return list;
    }

    @Nullable @Override public List<Splat> getListRight(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        if(splat != null){
            switch (splat.getTitle()){
                case R.string.great_guilds:
                    list.add(new Splat(R.string.artificers));
                    list.add(new Splat(R.string.masquers));
                    list.add(new Splat(R.string.pardoners));
                    list.add(new Splat(R.string.usurers));
                    break;
                case R.string.working_guilds:
                    list.add(new Splat(R.string.chanteurs));
                    list.add(new Splat(R.string.harbingers));
                    list.add(new Splat(R.string.oracles));
                    list.add(new Splat(R.string.sandmen));
                    break;
                case R.string.criminal_guilds:
                    list.add(new Splat(R.string.haunters));
                    list.add(new Splat(R.string.monitors));
                    list.add(new Splat(R.string.spooks));
                    list.add(new Splat(R.string.proctors));
                    list.add(new Splat(R.string.puppeteers));
                    break;
                case R.string.forbidden_guilds:
                    list.add(new Splat(R.string.alchemists));
                    list.add(new Splat(R.string.mnemoi));
                    list.add(new Splat(R.string.solicitors));
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
        list.add(new Splat(R.string.none));
        list.add(new Splat(R.string.great_guilds, false));
        list.add(new Splat(R.string.working_guilds, false));
        list.add(new Splat(R.string.criminal_guilds, false));
        list.add(new Splat(R.string.forbidden_guilds, false));
    }
}
