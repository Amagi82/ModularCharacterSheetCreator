package amagi82.modularcharactersheetcreator.ui.edit.name;

import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;

public class ResetItemEvent {
    public final int toPage;

    public ResetItemEvent(@GameCharacter.Progress int toPage) {
        this.toPage = toPage;
    }
}
