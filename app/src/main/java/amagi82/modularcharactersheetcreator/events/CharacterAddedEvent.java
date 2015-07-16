package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.models.GameCharacter;

public class CharacterAddedEvent {
    public final GameCharacter character;

    public CharacterAddedEvent(GameCharacter character) {
        this.character = character;
    }
}