package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.models.games.systems.GameSystem;

public class GameClickedEvent {
    public final GameSystem system;

    public GameClickedEvent(GameSystem system){
        this.system = system;
    }
}
