package amagi82.modularcharactersheetcreator.models.games;

import android.util.SparseArray;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Splat;

/*
    New World of Darkness
    Demon: The Descent
 */
public class NDemon extends Game {

    public NDemon() {
        super();
        this.gameTitle = getString(R.string.nwod_demon);
        this.leftTitle = getString(R.string.incarnation);
        this.rightTitle = getString(R.string.agenda);
        this.gameUrl = getString(R.string.url_game_nwod_demon);
        this.splashUrl = getString(R.string.url_splash_nwod_demon);
        this.gameColor = R.color.nwod_demon;
        this.splats = getSplats();
    }

    private SparseArray<Splat> getSplats(){
        SparseArray<Splat> splats = new SparseArray<>(8);

        splats.put(DESTROYER, splat(R.string.destroyer, R.string.url_nwod_demon_incarnation_destroyer));
        splats.put(GUARDIAN, splat(R.string.guardian, R.string.url_nwod_demon_incarnation_guardian));
        splats.put(MESSANGER, splat(R.string.messenger, R.string.url_nwod_demon_incarnation_messenger));
        splats.put(PSYCHOPOMP, splat(R.string.psychopomp, R.string.url_nwod_demon_incarnation_psychopomp));

        splats.put(INQUISITOR, splat(R.string.inquisitor, R.string.url_nwod_demon_agenda_inquisitor));
        splats.put(INTEGRATOR, splat(R.string.integrator, R.string.url_nwod_demon_agenda_integrator));
        splats.put(SABOTEUR, splat(R.string.saboteur, R.string.url_nwod_demon_agenda_saboteur));
        splats.put(TEMPTER, splat(R.string.tempter, R.string.url_nwod_demon_agenda_tempter));

        return splats;
    }

    @Override public int[] getListLeft(int splatId) {
        return new int[]{DESTROYER, GUARDIAN, MESSANGER, PSYCHOPOMP};
    }

    @Override public int[] getListRight(int splatId) {
        return new int[]{INQUISITOR, INTEGRATOR, SABOTEUR, TEMPTER};
    }

    //Left
    private static final int DESTROYER = 1;
    private static final int GUARDIAN = 2;
    private static final int MESSANGER = 3;
    private static final int PSYCHOPOMP = 4;

    //Right
    private static final int INQUISITOR = 101;
    private static final int INTEGRATOR = 102;
    private static final int SABOTEUR = 103;
    private static final int TEMPTER = 104;
}