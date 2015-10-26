package amagi82.modularcharactersheetcreator.models.games;

import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.Splat;

/*
    Base class implemented by all game systems
 */
public abstract class Game {
    @StringRes int gameTitle;
    @StringRes int leftTitle;
    @StringRes int rightTitle;
    @StringRes int gameUrl;
    @StringRes int splashUrl;
    @ColorRes int gameColor; //Used in the list of characters in MainAdapter
    boolean isArchetypeLeft = true; //Archetype is displayed under the game system in the list of characters
    boolean checkLeft = false; //With CVampire, Sect must be known to determine if Clan is antitribu.
    boolean isLeftListFinal = true; //False if the list can change depending on choices
    boolean isRightListFinal = true; //False if the list can change depending on choices

    public abstract List<Splat> getListLeft(@Nullable Splat splat);

    public abstract List<Splat> getListRight(@Nullable Splat splat);

    public int getGameTitle() {
        return gameTitle;
    }

    public int getLeftTitle() {
        return leftTitle;
    }

    public int getRightTitle(@Nullable Splat leftSplat) {
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

    public boolean isLeftListFinal() {
        return isLeftListFinal;
    }

    public boolean isRightListFinal() {
        return isRightListFinal;
    }

    public Splat updateLeft(Splat left, Splat right){
        return null;
    }

    public static Game getSystem(@StringRes int gameTitle) {
        switch (gameTitle) {
            case R.string.cwod_mage:
                return new CMage();
            case R.string.cwod_vampire:
                return new CVampire();
            case R.string.cwod_werewolf:
                return new CWerewolf();
            case R.string.cwod_wraith:
                return new CWraith();
            case R.string.exalted:
                return new Exalted();
            case R.string.nwod_demon:
                return new NDemon();
            case R.string.nwod_mummy:
                return new NMummy();
            case R.string.nwod_vampire:
                return new NVampire();
            case R.string.nwod_werewolf:
                return new NWerewolf();
            case R.string.scion:
                return new Scion();
            case R.string.trinity:
                return new Trinity();
            default:
                return null;
        }
    }

    public static List<Game> getSystems() {
        List<Game> list = new ArrayList<>(11);
        list.add(new CMage());
        list.add(new CVampire());
        list.add(new CWerewolf());
        list.add(new CWraith());
        list.add(new NDemon());
        list.add(new NMummy());
        list.add(new NVampire());
        list.add(new NWerewolf());
        list.add(new Exalted());
        list.add(new Scion());
        list.add(new Trinity());
        return list;
    }
}