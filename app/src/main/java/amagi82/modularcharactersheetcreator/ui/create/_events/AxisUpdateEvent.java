package amagi82.modularcharactersheetcreator.ui.create._events;

import amagi82.modularcharactersheetcreator.models.characters.Splat;

public class AxisUpdateEvent {
    public final Splat splat;

    public AxisUpdateEvent(Splat splat) {
        this.splat = splat;
    }
}
