package amagi82.modularcharactersheetcreator.models.games.systems;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Splat;

public class Exalted extends GameSystem {

    private boolean isCaste = true;

    public Exalted() {
        super();
        this.gameTitle = R.string.exalted;
        this.leftTitle = R.string.exalt;
        this.rightTitle = R.string.caste;
        this.gameUrl = R.string.url_game_exalted;
        this.splashUrl = R.string.url_splash_exalted;
        this.gameColor = R.color.exalted;
    }

    @Override public int getRightTitle() {
        return isCaste? R.string.caste : R.string.aspect;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(new Splat(R.string.solar_exalted));
        list.add(new Splat(R.string.abyssal_exalted));
        list.add(new Splat(R.string.lunar_exalted));
        list.add(new Splat(R.string.sidereal_exalted));
        list.add(new Splat(R.string.terrestrial_exalted));
        list.add(new Splat(R.string.alchemical_exalted));
        list.add(new Splat(R.string.infernal_exalted));
        return list;
    }

    @Override public List<Splat> getListRight(Splat splat) {
        List<Splat> list = new ArrayList<>();
        switch (splat.getTitle()){
            case R.string.solar_exalted:
                list.add(new Splat(R.string.dawn));
                list.add(new Splat(R.string.zenith));
                list.add(new Splat(R.string.twilight));
                list.add(new Splat(R.string.night));
                list.add(new Splat(R.string.eclipse));
                break;
            case R.string.abyssal_exalted:
                list.add(new Splat(R.string.dusk));
                list.add(new Splat(R.string.midnight));
                list.add(new Splat(R.string.daybreak));
                list.add(new Splat(R.string.day));
                list.add(new Splat(R.string.moonshadow));
                break;
            case R.string.lunar_exalted:
                list.add(new Splat(R.string.full_moon));
                list.add(new Splat(R.string.changing_moon));
                list.add(new Splat(R.string.no_moon));
                list.add(new Splat(R.string.casteless));
                break;
            case R.string.sidereal_exalted:
                list.add(new Splat(R.string.air));
                list.add(new Splat(R.string.earth));
                list.add(new Splat(R.string.fire));
                list.add(new Splat(R.string.water));
                list.add(new Splat(R.string.wood));
                break;
            case R.string.terrestrial_exalted:
                isCaste = false;
                list.add(new Splat(R.string.chosen_of_journeys));
                list.add(new Splat(R.string.chosen_of_serenity));
                list.add(new Splat(R.string.chosen_of_battles));
                list.add(new Splat(R.string.chosen_of_secrets));
                list.add(new Splat(R.string.chosen_of_endings));
                break;
            case R.string.alchemical_exalted:
                list.add(new Splat(R.string.orichalcum));
                list.add(new Splat(R.string.moonsilver));
                list.add(new Splat(R.string.starmetal));
                list.add(new Splat(R.string.jade));
                list.add(new Splat(R.string.soulsteel));
                break;
            case R.string.infernal_exalted:
                list.add(new Splat(R.string.slayer));
                list.add(new Splat(R.string.malefactor));
                list.add(new Splat(R.string.defiler));
                list.add(new Splat(R.string.scrourge));
                list.add(new Splat(R.string.fiend));
                break;
            default:
                return null;
        }
        return list;
    }
}