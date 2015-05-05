package amagi82.modularcharactersheetcreator;


public class Character {

    private String name = "";
    private String characterClass = "";
    private String gameSystem = "";

    public Character() {
    }

    public String getName() {
        return name;
    }

    public Character(String name, String gameSystem, String characterClass) {
        this.name = name;
        this.characterClass = characterClass;
        this.gameSystem = gameSystem;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public String getGameSystem() {
        return gameSystem;
    }

    public void setGameSystem(String gameSystem) {
        this.gameSystem = gameSystem;
    }
}