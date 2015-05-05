package amagi82.modularcharactersheetcreator;


import android.graphics.Bitmap;

import java.util.ArrayList;

public class Character {

    private Bitmap imageCharacterIcon;
    private String name = "";
    private String characterClass = "";
    private String gameSystem = "";
    private ArrayList<Module> moduleList = new ArrayList<>();

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

    public Bitmap getImageCharacterIcon() {
        return imageCharacterIcon;
    }

    public void setImageCharacterIcon(Bitmap imageCharacterIcon) {
        this.imageCharacterIcon = imageCharacterIcon;
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

    public ArrayList<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(ArrayList<Module> moduleList) {
        this.moduleList = moduleList;
    }
}