package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.models.GameCharacter;

public class CharacterChangedEvent {
    public final GameCharacter character;

    public CharacterChangedEvent(GameCharacter character) {
        this.character = character;
    }
}