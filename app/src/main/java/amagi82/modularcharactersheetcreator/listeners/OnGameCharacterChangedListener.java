package amagi82.modularcharactersheetcreator.listeners;

import amagi82.modularcharactersheetcreator.models.GameCharacter;

public interface OnGameCharacterChangedListener {
    void OnGameCharacterAdded(GameCharacter character);
    void OnGameCharacterUpdated(int position, GameCharacter character);
    void OnGameCharacterDeleted(int position);
}
