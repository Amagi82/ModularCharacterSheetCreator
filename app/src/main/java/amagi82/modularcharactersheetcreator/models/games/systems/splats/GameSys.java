package amagi82.modularcharactersheetcreator.models.games.systems.splats;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public abstract class GameSys {
    @IntDef({DEFAULT, CWOD, NWOD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GameCategory {}
    public static final int DEFAULT = 0;
    public static final int CWOD = 1;
    public static final int NWOD = 2;

    @StringRes
    public abstract int getGameTitle(); //i.e. Werewolf the Apocalypse, used in character list

    @StringRes
    public abstract int getLeftTitle(); //i.e. Sect or Tribe

    @StringRes
    public abstract int getRightTitle(); //i.e. Clan or Auspice

    public abstract boolean leftArchetype(); //i.e. Glass Walkers, used below game system in character list- use the right or left axis?

    @DrawableRes
    public abstract int getGameDrawable();

    @ColorRes
    public abstract int getGameColor();

    @GameCategory
    public abstract int getGameCategory();

    public abstract boolean hasRight(); //does the system have only one Axis?

    public abstract List<Splat> getListLeft(@Nullable Splat splat);

    @Nullable
    public abstract List<Splat> getListRight(@Nullable Splat splat);
}