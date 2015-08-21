package amagi82.modularcharactersheetcreator.models;


import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;
import android.util.Log;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import amagi82.modularcharactersheetcreator.models.games.Game;
import amagi82.modularcharactersheetcreator.models.games.Splat;
import amagi82.modularcharactersheetcreator.models.games.systems.GameSystem;

import static amagi82.modularcharactersheetcreator.activities.MainActivity.NONE;

@Parcel
@JsonObject
public class GameCharacter {
    @JsonField private String name = "";
    @JsonField private Splat left;
    @JsonField private Splat right;
    @JsonField @StringRes private int gameTitle = NONE;
    @JsonField @ColorInt private int colorBackground = NONE;
    @JsonField @ColorInt private int colorText = NONE;
    @JsonField @ColorInt private int colorTextDim = NONE;
    @JsonField private String entityId = UUID.randomUUID().toString();
    @JsonField private long timeStamp;
    @JsonField private List<Sheet> sheets = new ArrayList<>();
    @JsonField private String imageUriPortString;
    @JsonField private String imageUriLandString;
    private Uri imageUriPort;
    private Uri imageUriLand;

    public GameCharacter() {
        timeStamp = System.currentTimeMillis();
    }

    public GameCharacter(String name, GameSystem system, Splat left, Splat right){
        this.name = name;
        gameTitle = system.getGameTitle();
        this.left = left;
        this.right = right;
    }

    //Minimum requirements necessary to save the character
    public boolean isComplete() {
        return name.length() > 0 && gameTitle != NONE && left != null && right != null;
    }

    //Used during character creation/editing. Gets the current step in the character creation process
    public int getProgress(){
        return gameTitle == NONE ? 0 : left == null? 1 : right == null? 2 : 3;
    }

    //Used during character creation/editing. Removes progress on back.
    public void removeProgress(int fromStep){
        Log.i(null, "removeProgress from step: "+fromStep);
        switch (fromStep){
            case 1:
                gameTitle = NONE;
            case 2:
                left = null;
            case 3:
                right = null;
                break;
            default:
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Splat getLeft() {
        return left;
    }

    public void setLeft(Splat left) {
        this.left = left;
    }

    public Splat getRight() {
        return right;
    }

    public void setRight(Splat right) {
        this.right = right;
    }

    public int getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(int gameTitle) {
        this.gameTitle = gameTitle;
    }

    public GameSystem getGameSystem(){
        return gameTitle == NONE? null : new Game().getSystem(gameTitle);
    }

    public int getArchetype() {
        if(left == null || right == null || gameTitle == NONE) return NONE;
        return new Game().getSystem(gameTitle).isArchetypeLeft()? left.getTitle() : right.getTitle();
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

    public int getColorTextDim() {
        return colorTextDim;
    }

    public void setColorTextDim(int colorTextDim) {
        this.colorTextDim = colorTextDim;
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

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void updateTimeStamp() {
        timeStamp = System.currentTimeMillis();
    }

    public List<Sheet> getSheets() {
        return sheets;
    }

    public void setSheets(List<Sheet> sheets) {
        this.sheets = sheets;
    }

    public String getImageUriPortString() {
        return imageUriPortString;
    }

    public void setImageUriPortString(String imageUriPortString) {
        this.imageUriPortString = imageUriPortString;
        if(imageUriPort == null) imageUriPort = Uri.parse(imageUriPortString);
    }

    public String getImageUriLandString() {
        return imageUriLandString;
    }

    public void setImageUriLandString(String imageUriLandString) {
        this.imageUriLandString = imageUriLandString;
        if(imageUriLand == null) imageUriLand = Uri.parse(imageUriLandString);
    }

    public Uri getImageUriPort() {
        return imageUriPort;
    }

    public void setImageUriPort(Uri imageUriPort) {
        this.imageUriPort = imageUriPort;
        this.imageUriPortString = imageUriPort.toString();
        if(imageUriLand == null) setImageUriLand(imageUriPort);
    }

    public Uri getImageUriLand() {
        return imageUriLand;
    }

    public void setImageUriLand(Uri imageUriLand) {
        this.imageUriLand = imageUriLand;
        this.imageUriLandString = imageUriLand.toString();
        if(imageUriPort == null) setImageUriPort(imageUriLand);
    }
}