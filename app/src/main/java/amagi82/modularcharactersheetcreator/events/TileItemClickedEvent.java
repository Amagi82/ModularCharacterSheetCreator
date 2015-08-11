package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.models.games.Game;

public class TileItemClickedEvent {
    public final Game.System system;

    public TileItemClickedEvent(Game.System system){
        this.system = system;
    }
}
