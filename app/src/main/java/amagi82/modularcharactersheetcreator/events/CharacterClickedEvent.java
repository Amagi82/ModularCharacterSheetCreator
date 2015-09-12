package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.core_models.GameCharacter;

public class CharacterClickedEvent {
    public final GameCharacter character;

    public CharacterClickedEvent(GameCharacter gameCharacter) {
        this.character = gameCharacter;
    }
}
