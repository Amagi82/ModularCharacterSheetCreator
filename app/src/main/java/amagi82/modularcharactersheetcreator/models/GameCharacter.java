package amagi82.modularcharactersheetcreator.models;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import amagi82.modularcharactersheetcreator.models.modules.Module;

public class GameCharacter implements Serializable{

    transient private Bitmap characterIcon;
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

    public GameCharacter(String characterName, String gameSystem, String characterRace, String characterClass) {
        this.characterName = characterName;
        this.characterRace = characterRace;
        this.characterClass = characterClass;
        this.gameSystem = gameSystem;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        // This will serialize all fields that you did not mark with 'transient'
        // (Java's default behaviour)
        oos.defaultWriteObject();
        // Manually serialize all transient fields that you want to be serialized
        if(characterIcon!=null){
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            boolean success = characterIcon.compress(Bitmap.CompressFormat.PNG, 100, byteStream);
            if(success){
                oos.writeObject(byteStream.toByteArray());
            }
        }
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        // Deserializing non-transient fields
        ois.defaultReadObject();
        // All other fields that you serialized
        byte[] image = (byte[]) ois.readObject();
        if(image != null && image.length > 0){
            characterIcon = BitmapFactory.decodeByteArray(image, 0, image.length);
        }
    }

    public Bitmap getCharacterIcon() {
        return characterIcon;
    }

    public void setCharacterIcon(Bitmap characterIcon) {
        this.characterIcon = characterIcon;
    }

    public String getCharacterName() {
        return characterName;
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