package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.models.games.systems.GameSystem;

public class TileGameClickedEvent {
    public final GameSystem system;

    public TileGameClickedEvent(GameSystem system){
        this.system = system;
    }
}
