package amagi82.modularcharactersheetcreator.models.games.systems;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class NMummy extends GameSys {

    public NMummy() {
        super();
        this.gameTitle = R.string.nwod_mummy;
        this.leftTitle = R.string.decree;
        this.rightTitle = R.string.guild;
        this.gameDrawable = R.drawable.title_mummy_curse;
        this.gameColor = R.color.nwod_mummy;
        this.gameCategory = NWOD;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(new Splat(R.string.bull_headed, R.string.url_nwod_mummy_decree_bull_headed));
        list.add(new Splat(R.string.falcon_headed, R.string.url_nwod_mummy_decree_falcon_headed));
        list.add(new Splat(R.string.jackal_headed, R.string.url_nwod_mummy_decree_jackal_headed));
        list.add(new Splat(R.string.lion_headed, R.string.url_nwod_mummy_decree_lion_headed));
        list.add(new Splat(R.string.serpent_headed, R.string.url_nwod_mummy_decree_serpent_headed));
        return list;
    }

    @Override public List<Splat> getListRight(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(new Splat(R.string.maa_kep, R.string.url_nwod_mummy_guild_maa_kep));
        list.add(new Splat(R.string.mesen_nebu, R.string.url_nwod_mummy_guild_mesen_nebu));
        list.add(new Splat(R.string.sesha_hebsu, R.string.url_nwod_mummy_guild_sesha_hebsu));
        list.add(new Splat(R.string.su_menent, R.string.url_nwod_mummy_guild_su_menent));
        list.add(new Splat(R.string.tef_aabhi, R.string.url_nwod_mummy_guild_tef_aabhi));
        return list;
    }
}
