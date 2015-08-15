package amagi82.modularcharactersheetcreator.models.games.systems.splats;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.models.games.systems.splats.GameSys.GameCategory;

public class GameN {

    public List<GameSys> getGameList(@GameCategory int listType){
        List<GameSys> list = new ArrayList<>();

        switch(listType){
            case GameSys.CWOD:

                break;
            case GameSys.NWOD:

                break;
            default:

                list.add(new TrinityN());
                break;
        }
        return list;
    }
}
