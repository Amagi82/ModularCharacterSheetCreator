package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.models.games.Splat;

public class SplatClickedEvent {
    public final Splat splat;
    public final boolean left;

    public SplatClickedEvent(Splat splat, boolean left) {
        this.splat = splat;
        this.left = left;
    }
}
