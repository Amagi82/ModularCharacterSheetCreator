package amagi82.modularcharactersheetcreator.ui.main;

import amagi82.modularcharactersheetcreator.models.GameCharacter;

public class CharacterClickedEvent {
    public final GameCharacter character;

    public CharacterClickedEvent(GameCharacter character) {
        this.character = character;
    }
}
