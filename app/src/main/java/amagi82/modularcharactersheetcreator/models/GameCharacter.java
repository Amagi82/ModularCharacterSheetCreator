package amagi82.modularcharactersheetcreator.models;


import android.graphics.Bitmap;
import android.net.Uri;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.ArrayList;

import amagi82.modularcharactersheetcreator.models.game_systems.GameSystem;
import amagi82.modularcharactersheetcreator.models.modules.Module;

@JsonObject
public class GameCharacter {

    @JsonField private String name = "";
    @JsonField private String systemName;
    @JsonField private String leftCategoryName;
    @JsonField private String rightCategoryName;
    @JsonField private int leftUrl;
    @JsonField private int leftTitle;
    @JsonField private int rightUrl;
    @JsonField private int rightTitle;
    @JsonField private int baseUrl;
    @JsonField private int archetype;
    @JsonField private int colorPrimary;
    @JsonField private int colorBackground;
    @JsonField private int colorText;
    @JsonField private int colorTextTitle;
    @JsonField private int colorTitles;
    @JsonField private String entityId;
    @JsonField private long timeStamp;
    private Bitmap icon;
    private Uri portraitUri;
    private ArrayList<Module> moduleList = new ArrayList<>();

    public GameCharacter() {
        timeStamp = System.currentTimeMillis();
    }

    public GameCharacter(String name, GameSystem gameSystem) {
        this.name = name;
        setGameSystem(gameSystem);
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

    public GameSystem.System getGameSystem() {
        return GameSystem.System.valueOf(systemName);
    }

    public void setGameSystem(GameSystem gameSystem) {
        systemName = gameSystem.getSystem().name();
        leftCategoryName = gameSystem.getLeftCategoryName();
        rightCategoryName = gameSystem.getRightCategoryName();
        leftUrl = gameSystem.getLeftUrl();
        leftTitle = gameSystem.getLeftTitle();
        rightUrl = gameSystem.getRightUrl();
        rightTitle = gameSystem.getRightTitle();
        baseUrl = gameSystem.getBaseUrl();
        archetype = gameSystem.getArchetype();
        entityId = name + gameSystem.getLeftTitle() + gameSystem.getRightTitle();
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
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void updateTimeStamp() {
        timeStamp = System.currentTimeMillis();
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getArchetype() {
        return archetype;
    }

    public void setArchetype(int archetype) {
        this.archetype = archetype;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getLeftCategoryName() {
        return leftCategoryName;
    }

    public void setLeftCategoryName(String leftCategoryName) {
        this.leftCategoryName = leftCategoryName;
    }

    public String getRightCategoryName() {
        return rightCategoryName;
    }

    public void setRightCategoryName(String rightCategoryName) {
        this.rightCategoryName = rightCategoryName;
    }

    public int getLeftUrl() {
        return leftUrl;
    }

    public void setLeftUrl(int leftUrl) {
        this.leftUrl = leftUrl;
    }

    public int getLeftTitle() {
        return leftTitle;
    }

    public void setLeftTitle(int leftTitle) {
        this.leftTitle = leftTitle;
    }

    public int getRightUrl() {
        return rightUrl;
    }

    public void setRightUrl(int rightUrl) {
        this.rightUrl = rightUrl;
    }

    public int getRightTitle() {
        return rightTitle;
    }

    public void setRightTitle(int rightTitle) {
        this.rightTitle = rightTitle;
    }

    public int getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(int baseUrl) {
        this.baseUrl = baseUrl;
    }
}