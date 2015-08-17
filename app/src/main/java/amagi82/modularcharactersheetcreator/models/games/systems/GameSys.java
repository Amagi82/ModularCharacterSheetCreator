package amagi82.modularcharactersheetcreator.models.games.systems;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import amagi82.modularcharactersheetcreator.App;

public abstract class GameSys {

    @StringRes protected int gameTitle = App.NONE;
    @StringRes protected int leftTitle = App.NONE;
    @StringRes protected int rightTitle = App.NONE; //Note: some titles change based on category. Always call rightTitle after listRight.
    protected boolean isArchetypeLeft = true;
    @DrawableRes protected int gameDrawable = App.NONE;
    @ColorRes protected int gameColor = App.NONE; //Used in the list of characters @MainAdapter now.
    @GameCategory protected int gameCategory = DEFAULT;

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

    public int getGameDrawable() {
        return gameDrawable;
    }

    public void setGameDrawable(int gameDrawable) {
        this.gameDrawable = gameDrawable;
    }

    public int getGameColor() {
        return gameColor;
    }

    public void setGameColor(int gameColor) {
        this.gameColor = gameColor;
    }

    public int getGameCategory() {
        return gameCategory;
    }

    public void setGameCategory(int gameCategory) {
        this.gameCategory = gameCategory;
    }

    @IntDef({DEFAULT, CWOD, NWOD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GameCategory {}
    public static final int DEFAULT = 0;
    public static final int CWOD = 1;
    public static final int NWOD = 2;
}