package amagi82.modularcharactersheetcreator.models.games;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Splat;

/*
    Exalted 2nd Edition
    (3rd Edition will be included once available)
 */
public class Exalted extends Game {

    public Exalted() {
        super();
        this.setGameTitle(getString(R.string.exalted));
        this.setLeftTitle(getString(R.string.exalt));
        this.setRightTitle(getString(R.string.caste));
        this.setGameUrl(getString(R.string.url_game_exalted));
        this.setSplashUrl(getString(R.string.url_splash_exalted));
        this.setGameColor(R.color.exalted);
        this.setIsRightListFinal(false);
        this.setSplats(getSplats());
    }

    private SparseArray<Splat> getSplats() {
        SparseArray<Splat> splats = new SparseArray<>(41);

        splats.put(SOLAR, splat(R.string.solar_exalted));
        splats.put(ABYSSAL, splat(R.string.abyssal_exalted));
        splats.put(LUNAR, splat(R.string.lunar_exalted));
        splats.put(SIDEREAL, splat(R.string.sidereal_exalted));
        splats.put(TERRESTRIAL, splat(R.string.terrestrial_exalted));
        splats.put(ALCHEMICAL, splat(R.string.alchemical_exalted));
        splats.put(INFERNAL, splat(R.string.infernal_exalted));

        splats.put(DAWN, splat(R.string.dawn));
        splats.put(ZENITH, splat(R.string.zenith));
        splats.put(TWILIGHT, splat(R.string.twilight));
        splats.put(NIGHT, splat(R.string.night));
        splats.put(ECLIPSE, splat(R.string.eclipse));

        splats.put(DUSK, splat(R.string.dusk));
        splats.put(MIDNIGHT, splat(R.string.midnight));
        splats.put(DAYBREAK, splat(R.string.daybreak));
        splats.put(DAY, splat(R.string.day));
        splats.put(MOONSHADOW, splat(R.string.moonshadow));

        splats.put(FULL_MOON, splat(R.string.full_moon));
        splats.put(CHANGING_MOON, splat(R.string.changing_moon));
        splats.put(NO_MOON, splat(R.string.no_moon));
        splats.put(CASTELESS, splat(R.string.casteless));

        splats.put(AIR, splat(R.string.air));
        splats.put(EARTH, splat(R.string.earth));
        splats.put(FIRE, splat(R.string.fire));
        splats.put(WATER, splat(R.string.water));
        splats.put(WOOD, splat(R.string.wood));

        splats.put(CHOSEN_OF_JOURNEYS, splat(R.string.chosen_of_journeys));
        splats.put(CHOSEN_OF_SERENITY, splat(R.string.chosen_of_serenity));
        splats.put(CHOSEN_OF_BATTLES, splat(R.string.chosen_of_battles));
        splats.put(CHOSEN_OF_SECRETS, splat(R.string.chosen_of_secrets));
        splats.put(CHOSEN_OF_ENDINGS, splat(R.string.chosen_of_endings));

        splats.put(ORICHALCUM, splat(R.string.orichalcum));
        splats.put(MOONSILVER, splat(R.string.moonsilver));
        splats.put(STARMETAL, splat(R.string.starmetal));
        splats.put(JADE, splat(R.string.jade));
        splats.put(SOULSTEEL, splat(R.string.soulsteel));

        splats.put(SLAYER, splat(R.string.slayer));
        splats.put(MALEFACTOR, splat(R.string.malefactor));
        splats.put(DEFILER, splat(R.string.defiler));
        splats.put(SCOURGE, splat(R.string.scrourge));
        splats.put(FIEND, splat(R.string.fiend));

        return splats;
    }

    @Override public String getRightTitle(int leftSplatId) {
        if (leftSplatId == TERRESTRIAL) return getString(R.string.aspect);
        else return getRightTitle();
    }

    @NonNull @Override public int[] getListLeft(int splatId) {
        return new int[]{SOLAR, ABYSSAL, LUNAR, SIDEREAL, TERRESTRIAL, ALCHEMICAL, INFERNAL};
    }

    @NonNull @Override public int[] getListRight(int splatId) {
        if (splatId == SOLAR) return new int[]{DAWN, ZENITH, TWILIGHT, NIGHT, ECLIPSE};
        if (splatId == ABYSSAL) return new int[]{DUSK, MIDNIGHT, DAYBREAK, DAY, MOONSHADOW};
        if (splatId == LUNAR) return new int[]{FULL_MOON, CHANGING_MOON, NO_MOON, CASTELESS};
        if (splatId == SIDEREAL) return new int[]{AIR, EARTH, FIRE, WATER, WOOD};
        if (splatId == TERRESTRIAL) return new int[]{CHOSEN_OF_JOURNEYS, CHOSEN_OF_SERENITY, CHOSEN_OF_BATTLES, CHOSEN_OF_SECRETS, CHOSEN_OF_ENDINGS};
        if (splatId == ALCHEMICAL) return new int[]{ORICHALCUM, MOONSILVER, STARMETAL, JADE, SOULSTEEL};
        if (splatId == INFERNAL) return new int[]{SLAYER, MALEFACTOR, DEFILER, SCOURGE, FIEND};
        return new int[]{};
    }

    //Left
    private static final int SOLAR = 1;
    private static final int ABYSSAL = 2;
    private static final int LUNAR = 3;
    private static final int SIDEREAL = 4;
    private static final int TERRESTRIAL = 5;
    private static final int ALCHEMICAL = 6;
    private static final int INFERNAL = 7;

    //Right
    private static final int DAWN = 101;
    private static final int ZENITH = 102;
    private static final int TWILIGHT = 103;
    private static final int NIGHT = 104;
    private static final int ECLIPSE = 105;

    private static final int DUSK = 201;
    private static final int MIDNIGHT = 202;
    private static final int DAYBREAK = 203;
    private static final int DAY = 204;
    private static final int MOONSHADOW = 205;

    private static final int FULL_MOON = 301;
    private static final int CHANGING_MOON = 302;
    private static final int NO_MOON = 303;
    private static final int CASTELESS = 304;

    private static final int AIR = 401;
    private static final int EARTH = 402;
    private static final int FIRE = 403;
    private static final int WATER = 404;
    private static final int WOOD = 405;

    private static final int CHOSEN_OF_JOURNEYS = 501;
    private static final int CHOSEN_OF_SERENITY = 502;
    private static final int CHOSEN_OF_BATTLES = 503;
    private static final int CHOSEN_OF_SECRETS = 504;
    private static final int CHOSEN_OF_ENDINGS = 505;

    private static final int ORICHALCUM = 601;
    private static final int MOONSILVER = 602;
    private static final int STARMETAL = 603;
    private static final int JADE = 604;
    private static final int SOULSTEEL = 605;

    private static final int SLAYER = 701;
    private static final int MALEFACTOR = 702;
    private static final int DEFILER = 703;
    private static final int SCOURGE = 704;
    private static final int FIEND = 705;
}