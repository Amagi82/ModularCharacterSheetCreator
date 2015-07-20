package amagi82.modularcharactersheetcreator.models;


import android.net.Uri;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import amagi82.modularcharactersheetcreator.models.game_systems.Game;
import amagi82.modularcharactersheetcreator.models.game_systems.Onyx;

@JsonObject
public class GameCharacter {
    @JsonField private String name = "";
    @JsonField private String gameEName;
    @JsonField private Choice left;
    @JsonField private Choice right;
    @JsonField private int archetype;
    @JsonField private int colorPrimary;
    @JsonField private int colorBackground;
    @JsonField private int colorText;
    @JsonField private int colorTextTitle;
    @JsonField private int colorTitles;
    @JsonField private String entityId = UUID.randomUUID().toString();
    @JsonField private long timeStamp;
    @JsonField private List<Sheet> sheets = new ArrayList<>();
    @JsonField private String portraitUriString;
    private Uri portraitUri;

    public GameCharacter() {
        timeStamp = System.currentTimeMillis();
    }

    public GameCharacter(String name, Onyx onyx) {
        this.name = name;
        setOnyx(onyx);
        timeStamp = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game.System getGameSystem() {
        return Game.System.valueOf(gameEName);
    }

    public void setOnyx(Onyx onyx) {
        gameEName = onyx.getSystemName();
        left = onyx.getLeft();
        right = onyx.getRight();
        archetype = onyx.getArchetype();
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

    public String getGameEName() {
        return gameEName;
    }

    public void setGameEName(String gameEName) {
        this.gameEName = gameEName;
    }

    public Choice getLeft() {
        return left;
    }

    public void setLeft(Choice left) {
        this.left = left;
    }

    public Choice getRight() {
        return right;
    }

    public void setRight(Choice right) {
        this.right = right;
    }

    public boolean isComplete() {
        return name.length() > 0 && left != null && gameEName != null && (!getGameSystem().getOnyx().hasRight() || right != null);
    }

    public List<Sheet> getSheets() {
        return sheets;
    }

    public void setSheets(List<Sheet> sheets) {
        this.sheets = sheets;
    }

    public Uri getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(Uri portraitUri) {
        this.portraitUri = portraitUri;
        portraitUriString = portraitUri.getEncodedPath();
    }

    public String getPortraitUriString() {
        return portraitUriString;
    }

    public void setPortraitUriString(String portraitUriString) {
        this.portraitUriString = portraitUriString;
        portraitUri = Uri.parse(portraitUriString);
    }
}