package amagi82.modularcharactersheetcreator.entities.games;

import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.util.List;

import amagi82.modularcharactersheetcreator.entities.characters.Splat;

import static amagi82.modularcharactersheetcreator.ui.main.MainActivity.NONE;

public abstract class GameSystem {

    @StringRes protected int gameTitle = NONE;
    @StringRes protected int leftTitle = NONE;
    @StringRes protected int rightTitle = NONE;
    @StringRes protected int gameUrl = NONE;
    @StringRes protected int splashUrl = NONE;
    @ColorRes protected int gameColor = NONE; //Used in the list of characters in MainAdapter
    protected boolean isArchetypeLeft = true; //Archetype is displayed under the game system in the list of characters
    protected boolean checkLeft = false; //With CVampire, Sect must be known to determine if Clan is antitribu.

    public abstract List<Splat> getListLeft(@Nullable Splat splat);

    public abstract List<Splat> getListRight(Splat splat);

    public int getGameTitle() {
        return gameTitle;
    }

    public int getLeftTitle() {
        return leftTitle;
    }

    public int getRightTitle(Splat leftSplat) {
        return rightTitle;
    }

    public boolean isArchetypeLeft() {
        return isArchetypeLeft;
    }

    public int getGameUrl() {
        return gameUrl;
    }

    public int getSplashUrl() {
        return splashUrl;
    }

    public int getGameColor() {
        return gameColor;
    }

    public boolean checkLeft() {
        return checkLeft;
    }

    public Splat updateLeft(Splat left, Splat right){
        return null;
    }
}