package amagi82.modularcharactersheetcreator.models.games;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.models.games.systems.CMage;
import amagi82.modularcharactersheetcreator.models.games.systems.CVampire;
import amagi82.modularcharactersheetcreator.models.games.systems.CWerewolf;
import amagi82.modularcharactersheetcreator.models.games.systems.CWoD;
import amagi82.modularcharactersheetcreator.models.games.systems.CWraith;
import amagi82.modularcharactersheetcreator.models.games.systems.Exalted;
import amagi82.modularcharactersheetcreator.models.games.systems.GameSystem;
import amagi82.modularcharactersheetcreator.models.games.systems.NDemon;
import amagi82.modularcharactersheetcreator.models.games.systems.NMummy;
import amagi82.modularcharactersheetcreator.models.games.systems.NVampire;
import amagi82.modularcharactersheetcreator.models.games.systems.NWerewolf;
import amagi82.modularcharactersheetcreator.models.games.systems.NWoD;
import amagi82.modularcharactersheetcreator.models.games.systems.Scion;
import amagi82.modularcharactersheetcreator.models.games.systems.Trinity;

public class Game {

    public List<GameSystem> getGameList(@Category int listType){
        List<GameSystem> list = new ArrayList<>();
        switch(listType){
            case CWOD:
                list.add(new CMage());
                list.add(new CVampire());
                list.add(new CWerewolf());
                list.add(new CWraith());
                break;
            case NWOD:
                list.add(new NDemon());
                list.add(new NMummy());
                list.add(new NVampire());
                list.add(new NWerewolf());
                break;
            default:
                list.add(new CWoD());
                list.add(new NWoD());
                list.add(new Exalted());
                list.add(new Scion());
                list.add(new Trinity());
                break;
        }
        return list;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DEFAULT, CWOD, NWOD})
    public @interface Category {}
    public static final int DEFAULT = 0;
    public static final int CWOD = 1;
    public static final int NWOD = 2;
}
