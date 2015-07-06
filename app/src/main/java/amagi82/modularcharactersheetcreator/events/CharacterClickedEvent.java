package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.models.GameCharacter;

public class CharacterClickedEvent {
    public final GameCharacter gameCharacter;

    public CharacterClickedEvent(GameCharacter gameCharacter) {
        this.gameCharacter = gameCharacter;
    }
}
