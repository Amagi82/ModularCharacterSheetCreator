package amagi82.modularcharactersheetcreator.events;

import java.util.List;

import amagi82.modularcharactersheetcreator.models.GameCharacter;

public class CharactersLoadedEvent {
    public final List<GameCharacter> characters;

    public CharactersLoadedEvent(List<GameCharacter> characters) {
        this.characters = characters;
    }
}
