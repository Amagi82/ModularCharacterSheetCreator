package amagi82.modularcharactersheetcreator.models;


import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;

import amagi82.modularcharactersheetcreator.models.game_systems.GameSystem;
import amagi82.modularcharactersheetcreator.models.modules.Module;

public class GameCharacter implements Serializable{

    private Bitmap icon;
    private String entityId;
    private String name = "";
    private GameSystem gameSystem;
    private Uri portraitUri;
    private int colorPrimary;
    private int colorBackground;
    private int colorText;
    private int colorTextTitle;
    private int colorTitles;
    private long timeStamp;

    private ArrayList<Module> moduleList = new ArrayList<>();

    public GameCharacter() {
        timeStamp = System.currentTimeMillis();
    }

    public GameCharacter(String name, GameSystem gameSystem) {
        this.name = name;
        this.gameSystem = gameSystem;
        entityId = name + gameSystem.getLeft() + gameSystem.getRight();
        timeStamp = System.currentTimeMillis();
    }

//    private void writeObject(ObjectOutputStream oos) throws IOException {
//        // This will serialize all fields that you did not mark with 'transient'
//        // (Java's default behaviour)
//        oos.defaultWriteObject();
//        // Manually serialize all transient fields that you want to be serialized
//        if(icon !=null){
//            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
//            boolean success = icon.compress(Bitmap.CompressFormat.PNG, 100, byteStream);
//            if(success){
//                oos.writeObject(byteStream.toByteArray());
//            }
//        }
//    }
//
//    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
//        // Deserializing non-transient fields
//        ois.defaultReadObject();
//        // All other fields that you serialized
//        byte[] image = (byte[]) ois.readObject();
//        if(image != null && image.length > 0){
//            icon = BitmapFactory.decodeByteArray(image, 0, image.length);
//        }
//    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameSystem getGameSystem() {
        return gameSystem;
    }

    public void setGameSystem(GameSystem gameSystem) {
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

    public Uri getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(Uri portraitUri) {
        this.portraitUri = portraitUri;
    }

    public String getEntityId() {
        if(entityId == null)entityId = name + gameSystem.getLeft() + gameSystem.getRight();
        return entityId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp() {
        timeStamp = System.currentTimeMillis();
    }
}