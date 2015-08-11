package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.models.Choice;

public class RightAxisEvent {
    public final Choice choice;

    public RightAxisEvent(Choice choice) {
        this.choice = choice;
    }
}
