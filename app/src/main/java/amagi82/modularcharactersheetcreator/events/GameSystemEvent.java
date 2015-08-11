package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.models.games.systems.Onyx;

public class GameSystemEvent {
    public final Onyx onyx;

    public GameSystemEvent(Onyx onyx) {
        this.onyx = onyx;
    }
}
