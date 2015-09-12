package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.core_models.GameCharacter;

public class CharacterDeletedEvent {
    public final GameCharacter character;

    public CharacterDeletedEvent(GameCharacter gameCharacter) {
        this.character = gameCharacter;
    }
}
