package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.core_models.GameCharacter;

public class EditCharacterEvent {
    public final GameCharacter character;

    public EditCharacterEvent(GameCharacter character) {
        this.character = character;
    }
}
