package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.models.GameCharacter;

public class CharacterUpdatedEvent {
    public final GameCharacter character;

    public CharacterUpdatedEvent(GameCharacter character) {
        this.character = character;
    }
}