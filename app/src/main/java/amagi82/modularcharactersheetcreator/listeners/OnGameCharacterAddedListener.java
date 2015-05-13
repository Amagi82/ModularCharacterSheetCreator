package amagi82.modularcharactersheetcreator.listeners;

import amagi82.modularcharactersheetcreator.models.GameCharacter;

public interface OnGameCharacterAddedListener {
    void OnGameCharacterAdded(GameCharacter character);
    void OnGameCharacterUpdated(int position, GameCharacter character);
}
