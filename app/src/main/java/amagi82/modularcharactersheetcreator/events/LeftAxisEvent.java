package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.models.Choice;

public class LeftAxisEvent {
    public final Choice choice;

    public LeftAxisEvent(Choice choice) {
        this.choice = choice;
    }
}
