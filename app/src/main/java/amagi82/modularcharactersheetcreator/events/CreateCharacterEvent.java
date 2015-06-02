package amagi82.modularcharactersheetcreator.events;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;

public class CreateCharacterEvent {

    public final Toolbar toolbar;
    public final FloatingActionButton fab;

    public CreateCharacterEvent(Toolbar toolbar, FloatingActionButton fab) {
        this.toolbar = toolbar;
        this.fab = fab;
    }
}
