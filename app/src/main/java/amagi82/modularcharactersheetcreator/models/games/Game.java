package amagi82.modularcharactersheetcreator.models.games;

import android.support.annotation.ColorRes;
import android.support.annotation.IntDef;
import android.support.annotation.StringRes;
import android.util.SparseArray;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import amagi82.modularcharactersheetcreator.models.Splat;
import amagi82.modularcharactersheetcreator.ui._base.App;

/*
    Base class implemented by all game systems
 */
public abstract class Game {
    String gameTitle;
    String leftTitle;
    String rightTitle;
    String gameUrl;
    String splashUrl;
    @ColorRes int gameColor; //Used in the list of characters in MainAdapter
    boolean isArchetypeLeft = true; //Archetype is displayed under the game gameId in the list of characters
    boolean checkLeft = false; //With CVampire, Sect must be known to determine if Clan is antitribu.
    boolean isLeftListFinal = true; //False if the list can change depending on choices
    boolean isRightListFinal = true; //False if the list can change depending on choices
    SparseArray<Splat> splats;

    public abstract int[] getListLeft(int splatId);

    public abstract int[] getListRight(int splatId);

    public Splat getSplat(int splatId){
        return splats.get(splatId);
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public String getLeftTitle() {
        return leftTitle;
    }

    public String getRightTitle(int leftSplatId) {
        return rightTitle;
    }

    public boolean isArchetypeLeft() {
        return isArchetypeLeft;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public String getSplashUrl() {
        return splashUrl;
    }

    public int getGameColor() {
        return gameColor;
    }

    public boolean checkLeft() {
        return checkLeft;
    }

    public boolean isLeftListFinal() {
        return isLeftListFinal;
    }

    public boolean isRightListFinal() {
        return isRightListFinal;
    }

    public int updateLeft(int leftSplatId, int rightSplatId) {
        return 0;
    }

    public static Game getSystem(@System int systemId) {
        switch (systemId) {
            case CMAGE:
                return new CMage();
            case CVAMPIRE:
                return new CVampire();
            case CWEREWOLF:
                return new CWerewolf();
            case CWRAITH:
                return new CWraith();
            case EXALTED:
                return new Exalted();
            case NDEMON:
                return new NDemon();
            case NMUMMY:
                return new NMummy();
            case NVAMPIRE:
                return new NVampire();
            case NWEREWOLF:
                return new NWerewolf();
            case SCION:
                return new Scion();
            case TRINITY:
                return new Trinity();
            default:
                return null;
        }
    }

    public static int[] getSystems() {
        return new int[]{CMAGE, CVAMPIRE, CWEREWOLF, CWRAITH, EXALTED, NDEMON, NMUMMY, NVAMPIRE, NWEREWOLF, SCION, TRINITY};
    }

    String getString(@StringRes int resId) {
        return App.getRes().getString(resId);
    }

    Splat splat(@StringRes int title) {
        return Splat.create(getString(title));
    }

    Splat splat(@StringRes int title, @StringRes int url) {
        return Splat.create(getString(title), getString(url));
    }

    Splat splat(@StringRes int title, boolean isEndPoint) {
        return Splat.create(getString(title), isEndPoint);
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({NONE, EXALTED, SCION, TRINITY, CMAGE, CVAMPIRE, CWEREWOLF, CWRAITH, NDEMON, NMUMMY, NVAMPIRE, NWEREWOLF})
    public @interface System {}

    public static final int NONE = 0;
    public static final int EXALTED = 10;
    public static final int SCION = 20;
    public static final int TRINITY = 30;
    public static final int CMAGE = 101;
    public static final int CVAMPIRE = 102;
    public static final int CWEREWOLF = 103;
    public static final int CWRAITH = 104;
    public static final int NDEMON = 201;
    public static final int NMUMMY = 202;
    public static final int NVAMPIRE = 203;
    public static final int NWEREWOLF = 204;
}