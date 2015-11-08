package amagi82.modularcharactersheetcreator.ui.create._events;

import amagi82.modularcharactersheetcreator.models.characters.Splat;

public class AxisSelectedEvent {
    public final Splat splat;

    public AxisSelectedEvent(Splat splat) {
        this.splat = splat;
    }
}