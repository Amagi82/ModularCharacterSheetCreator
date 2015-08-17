package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.models.games.systems.GameSystem;

public class GameSystemEvent {
    public final GameSystem gameSystem;

    public GameSystemEvent(GameSystem gameSystem) {
        this.gameSystem = gameSystem;
    }
}
