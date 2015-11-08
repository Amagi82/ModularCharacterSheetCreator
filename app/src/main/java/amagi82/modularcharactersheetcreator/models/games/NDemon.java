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
    Demon: The Descent
 */
public class NDemon extends Game {

    public NDemon(Resources res) {
        super();
        this.res = res;
        this.gameTitle = getString(R.string.nwod_demon);
        this.leftTitle = getString(R.string.incarnation);
        this.rightTitle = getString(R.string.agenda);
        this.gameUrl = getString(R.string.url_game_nwod_demon);
        this.splashUrl = getString(R.string.url_splash_nwod_demon);
        this.gameColor = R.color.nwod_demon;
        this.splats = getSplats();
    }

    private SparseArray<Splat> getSplats(){
        SparseArray<Splat> splats = new SparseArray<>();


        return splats;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(4);
        list.add(Splat.create(R.string.destroyer, R.string.url_nwod_demon_incarnation_destroyer));
        list.add(Splat.create(R.string.guardian, R.string.url_nwod_demon_incarnation_guardian));
        list.add(Splat.create(R.string.messenger, R.string.url_nwod_demon_incarnation_messenger));
        list.add(Splat.create(R.string.psychopomp, R.string.url_nwod_demon_incarnation_psychopomp));
        return list;
    }

    @Override public List<Splat> getListRight(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(4);
        list.add(Splat.create(R.string.inquisitor, R.string.url_nwod_demon_agenda_inquisitor));
        list.add(Splat.create(R.string.integrator, R.string.url_nwod_demon_agenda_integrator));
        list.add(Splat.create(R.string.saboteur, R.string.url_nwod_demon_agenda_saboteur));
        list.add(Splat.create(R.string.tempter, R.string.url_nwod_demon_agenda_tempter));
        return list;
    }
}