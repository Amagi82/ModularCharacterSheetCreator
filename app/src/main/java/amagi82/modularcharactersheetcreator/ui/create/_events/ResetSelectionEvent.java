package amagi82.modularcharactersheetcreator.ui.create._events;

import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;

public class ResetSelectionEvent {
    public final int toPage;

    public ResetSelectionEvent(@GameCharacter.Progress int toPage) {
        this.toPage = toPage;
    }
}
