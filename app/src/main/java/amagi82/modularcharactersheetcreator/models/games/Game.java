package amagi82.modularcharactersheetcreator.models.games;

import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class Game {

    public GameSystem getSystem(@StringRes int gameTitle) {
        switch (gameTitle) {
            case R.string.cwod_mage:
                return new CMage();
            case R.string.cwod_vampire:
                return new CVampire();
            case R.string.cwod_werewolf:
                return new CWerewolf();
            case R.string.cwod_wraith:
                return new CWraith();
            case R.string.exalted:
                return new Exalted();
            case R.string.nwod_demon:
                return new NDemon();
            case R.string.nwod_mummy:
                return new NMummy();
            case R.string.nwod_vampire:
                return new NVampire();
            case R.string.nwod_werewolf:
                return new NWerewolf();
            case R.string.scion:
                return new Scion();
            case R.string.trinity:
                return new Trinity();
            default:
                return null;
        }
    }

    public List<GameSystem> getList() {
        List<GameSystem> list = new ArrayList<>(11);
        list.add(new CMage());
        list.add(new CVampire());
        list.add(new CWerewolf());
        list.add(new CWraith());
        list.add(new NDemon());
        list.add(new NMummy());
        list.add(new NVampire());
        list.add(new NWerewolf());
        list.add(new Exalted());
        list.add(new Scion());
        list.add(new Trinity());
        return list;
    }
}
