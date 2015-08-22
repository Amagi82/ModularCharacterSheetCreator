package amagi82.modularcharactersheetcreator.models.games.systems;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Splat;

public class NDemon extends GameSystem {

    public NDemon() {
        super();
        this.gameTitle = R.string.nwod_demon;
        this.leftTitle = R.string.incarnation;
        this.rightTitle = R.string.agenda;
        this.gameUrl = R.string.url_game_nwod_demon;
        this.splashUrl = R.string.url_splash_nwod_demon;
        this.gameColor = R.color.nwod_demon;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(Splat.create(R.string.destroyer, R.string.url_nwod_demon_incarnation_destroyer));
        list.add(Splat.create(R.string.guardian, R.string.url_nwod_demon_incarnation_guardian));
        list.add(Splat.create(R.string.messenger, R.string.url_nwod_demon_incarnation_messenger));
        list.add(Splat.create(R.string.psychopomp, R.string.url_nwod_demon_incarnation_psychopomp));
        return list;
    }

    @Override public List<Splat> getListRight(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(Splat.create(R.string.inquisitor, R.string.url_nwod_demon_agenda_inquisitor));
        list.add(Splat.create(R.string.integrator, R.string.url_nwod_demon_agenda_integrator));
        list.add(Splat.create(R.string.saboteur, R.string.url_nwod_demon_agenda_saboteur));
        list.add(Splat.create(R.string.tempter, R.string.url_nwod_demon_agenda_tempter));
        return list;
    }
}