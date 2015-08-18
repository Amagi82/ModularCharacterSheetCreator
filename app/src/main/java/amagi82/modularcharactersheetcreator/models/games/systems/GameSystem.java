package amagi82.modularcharactersheetcreator.models.games.systems;

import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.util.List;

import amagi82.modularcharactersheetcreator.models.games.Splat;

import static amagi82.modularcharactersheetcreator.activities.MainActivity.NONE;

public abstract class GameSystem {

    @StringRes protected int gameTitle = NONE;
    @StringRes protected int leftTitle = NONE;
    @StringRes protected int rightTitle = NONE; //Note: some titles change based on category. Always call rightTitle after listRight.
    @StringRes protected int gameUrl = NONE;
    @StringRes protected int splashUrl = NONE;
    @ColorRes protected int gameColor = NONE; //Used in the list of characters @MainAdapter now.
    protected boolean isArchetypeLeft = true; //Archetype is displayed under the game system in the list of characters
    protected boolean overrideLeft = false; //With CVampire, Sect must be known to determine if Clan is antitribu.

    public abstract List<Splat> getListLeft(@Nullable Splat splat);

    @Nullable
    public abstract List<Splat> getListRight(@Nullable Splat splat);


    public int getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(int gameTitle) {
        this.gameTitle = gameTitle;
    }

    public int getLeftTitle() {
        return leftTitle;
    }

    public void setLeftTitle(int leftTitle) {
        this.leftTitle = leftTitle;
    }

    public int getRightTitle() {
        return rightTitle;
    }

    public void setRightTitle(int rightTitle) {
        this.rightTitle = rightTitle;
    }

    public boolean isArchetypeLeft() {
        return isArchetypeLeft;
    }

    public void setIsArchetypeLeft(boolean isArchetypeLeft) {
        this.isArchetypeLeft = isArchetypeLeft;
    }

    public int getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(int gameUrl) {
        this.gameUrl = gameUrl;
    }

    public int getSplashUrl() {
        return splashUrl;
    }

    public void setSplashUrl(int splashUrl) {
        this.splashUrl = splashUrl;
    }

    public int getGameColor() {
        return gameColor;
    }

    public void setGameColor(int gameColor) {
        this.gameColor = gameColor;
    }

    public boolean isOverrideLeft() {
        return overrideLeft;
    }

    public void setOverrideLeft(boolean overrideLeft) {
        this.overrideLeft = overrideLeft;
    }

    public Splat getOverriddenLeft(Splat left, Splat right){
        return null;
    }
}