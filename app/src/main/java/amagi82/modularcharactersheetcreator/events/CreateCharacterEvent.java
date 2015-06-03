package amagi82.modularcharactersheetcreator.events;

import android.support.design.widget.FloatingActionButton;

public class CreateCharacterEvent {

    public final FloatingActionButton fab;

    public CreateCharacterEvent(FloatingActionButton fab) {
        this.fab = fab;
    }
}
