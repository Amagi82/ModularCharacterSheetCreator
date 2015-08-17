package amagi82.modularcharactersheetcreator.models.games.systems;

import java.util.ArrayList;
import java.util.List;

public class GameN {

    public List<GameSys> getGameList(@GameSys.GameCategory int listType){
        List<GameSys> list = new ArrayList<>();

        switch(listType){
            case GameSys.CWOD:

                break;
            case GameSys.NWOD:

                break;
            default:

                list.add(new Trinity());
                break;
        }
        return list;
    }
}
