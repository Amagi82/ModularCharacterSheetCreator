package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.game_models.GameSystem;

public class TileGameClickedEvent {
    public final GameSystem system;

    public TileGameClickedEvent(GameSystem system){
        this.system = system;
    }
}
