package amagi82.modularcharactersheetcreator.models.game_systems;

import amagi82.modularcharactersheetcreator.R;

abstract class GameSystem {
    private int gameSystemsArrayId;
    private int gameSystemSubTypeArrayId;
    private int characterClassesArrayId;
    private int characterRacesArrayId;
    private boolean hasGameSystemSubTypes;
    private boolean hasCharacterClasses;
    private boolean hasCharacterRaces;
    private int characterClassTerm;

    public GameSystem(int gameSystemSubTypeArrayId, int characterClassesArrayId, int characterRacesArrayId, boolean hasGameSystemSubTypes,
                      boolean hasCharacterClasses, boolean hasCharacterRaces) {
        gameSystemsArrayId = R.array.game_systems;
        this.gameSystemSubTypeArrayId = gameSystemSubTypeArrayId;
        this.characterClassesArrayId = characterClassesArrayId;
        this.characterRacesArrayId = characterRacesArrayId;
        this.hasGameSystemSubTypes = hasGameSystemSubTypes;
        this.hasCharacterClasses = hasCharacterClasses;
        this.hasCharacterRaces = hasCharacterRaces;
    }

    public int getGameSystemsArrayId() {
        return gameSystemsArrayId;
    }

    public int getGameSystemSubTypeArrayId() {
        return gameSystemSubTypeArrayId;
    }

    public int getCharacterClassesArrayId() {
        return characterClassesArrayId;
    }

    public int getCharacterRacesArrayId() {
        return characterRacesArrayId;
    }

    public boolean hasGameSystemSubTypes() {
        return hasGameSystemSubTypes;
    }

    public boolean hasCharacterClasses() {
        return hasCharacterClasses;
    }

    public boolean hasCharacterRaces() {
        return hasCharacterRaces;
    }

    public int getCharacterClassTerm() {
        return characterClassTerm;
    }

    public void setCharacterClassTerm(int characterClassTerm) {
        this.characterClassTerm = characterClassTerm;
    }
}
