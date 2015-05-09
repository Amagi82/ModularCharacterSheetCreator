package amagi82.modularcharactersheetcreator.models;


import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

import amagi82.modularcharactersheetcreator.models.modules.Module;

public class GameCharacter implements Serializable{

    private Bitmap imageCharacterIcon;
    private String characterName = "";
    private String characterRace = "";
    private String characterClass = "";
    private String gameSystem = "";
    private int colorPrimary;
    private int colorBackground;
    private int colorText;
    private int colorTextTitle;
    private int colorTitles;
    private ArrayList<Module> moduleList = new ArrayList<>();

    public GameCharacter() {
    }

    public String getCharacterName() {
        return characterName;
    }

    public GameCharacter(String characterName, String gameSystem, String characterClass) {
        this.characterName = characterName;
        this.characterClass = characterClass;
        this.gameSystem = gameSystem;
    }

    public GameCharacter(String characterName, String gameSystem, String characterClass, int colorPrimary, int colorBackground, int colorText,
                         int colorTextTitle, int colorTitles) {
        this.characterName = characterName;
        this.characterClass = characterClass;
        this.gameSystem = gameSystem;
        this.colorPrimary = colorPrimary;
        this.colorBackground = colorBackground;
        this.colorText = colorText;
        this.colorTextTitle = colorTextTitle;
        this.colorTitles = colorTitles;
    }

    public Bitmap getImageCharacterIcon() {
        return imageCharacterIcon;
    }

    public void setImageCharacterIcon(Bitmap imageCharacterIcon) {
        this.imageCharacterIcon = imageCharacterIcon;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterRace() {
        return characterRace;
    }

    public void setCharacterRace(String characterRace) {
        this.characterRace = characterRace;
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

    public int getColorPrimary() {
        return colorPrimary;
    }

    public void setColorPrimary(int colorPrimary) {
        this.colorPrimary = colorPrimary;
    }

    public int getColorBackground() {
        return colorBackground;
    }

    public void setColorBackground(int colorBackground) {
        this.colorBackground = colorBackground;
    }

    public int getColorText() {
        return colorText;
    }

    public void setColorText(int colorText) {
        this.colorText = colorText;
    }

    public int getColorTextTitle() {
        return colorTextTitle;
    }

    public void setColorTextTitle(int colorTextTitle) {
        this.colorTextTitle = colorTextTitle;
    }

    public int getColorTitles() {
        return colorTitles;
    }

    public void setColorTitles(int colorTitles) {
        this.colorTitles = colorTitles;
    }
}