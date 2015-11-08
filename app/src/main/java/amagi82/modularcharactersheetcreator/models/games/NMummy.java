package amagi82.modularcharactersheetcreator.models.games;

import android.content.res.Resources;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.Splat;

/*
    New World of Darkness
    Mummy: The Curse
 */
public class NMummy extends Game {

    public NMummy(Resources res) {
        super();
        this.res = res;
        this.gameTitle = getString(R.string.nwod_mummy);
        this.leftTitle = getString(R.string.decree);
        this.rightTitle = getString(R.string.guild);
        this.gameUrl = getString(R.string.url_game_nwod_mummy);
        this.splashUrl = getString(R.string.url_splash_nwod_mummy);
        this.gameColor = R.color.nwod_mummy;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(5);
        list.add(Splat.create(R.string.bull_headed, R.string.url_nwod_mummy_decree_bull_headed));
        list.add(Splat.create(R.string.falcon_headed, R.string.url_nwod_mummy_decree_falcon_headed));
        list.add(Splat.create(R.string.jackal_headed, R.string.url_nwod_mummy_decree_jackal_headed));
        list.add(Splat.create(R.string.lion_headed, R.string.url_nwod_mummy_decree_lion_headed));
        list.add(Splat.create(R.string.serpent_headed, R.string.url_nwod_mummy_decree_serpent_headed));
        return list;
    }

    @Override public List<Splat> getListRight(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(5);
        list.add(Splat.create(R.string.maa_kep, R.string.url_nwod_mummy_guild_maa_kep));
        list.add(Splat.create(R.string.mesen_nebu, R.string.url_nwod_mummy_guild_mesen_nebu));
        list.add(Splat.create(R.string.sesha_hebsu, R.string.url_nwod_mummy_guild_sesha_hebsu));
        list.add(Splat.create(R.string.su_menent, R.string.url_nwod_mummy_guild_su_menent));
        list.add(Splat.create(R.string.tef_aabhi, R.string.url_nwod_mummy_guild_tef_aabhi));
        return list;
    }
}
