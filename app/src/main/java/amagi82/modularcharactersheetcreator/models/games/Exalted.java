package amagi82.modularcharactersheetcreator.models.games;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.Splat;

public class Exalted extends GameSystem {

    public Exalted() {
        super();
        this.gameTitle = R.string.exalted;
        this.leftTitle = R.string.exalt;
        this.rightTitle = R.string.caste;
        this.gameUrl = R.string.url_game_exalted;
        this.splashUrl = R.string.url_splash_exalted;
        this.gameColor = R.color.exalted;
    }

    @Override public int getRightTitle(Splat leftSplat) {
        return leftSplat.title() == R.string.terrestrial_exalted ? R.string.aspect : R.string.caste;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(7);
        list.add(Splat.create(R.string.solar_exalted));
        list.add(Splat.create(R.string.abyssal_exalted));
        list.add(Splat.create(R.string.lunar_exalted));
        list.add(Splat.create(R.string.sidereal_exalted));
        list.add(Splat.create(R.string.terrestrial_exalted));
        list.add(Splat.create(R.string.alchemical_exalted));
        list.add(Splat.create(R.string.infernal_exalted));
        return list;
    }

    @Override public List<Splat> getListRight(@NonNull Splat splat) {
        List<Splat> list = new ArrayList<>(5);
        switch (splat.title()) {
            case R.string.solar_exalted:
                list.add(Splat.create(R.string.dawn));
                list.add(Splat.create(R.string.zenith));
                list.add(Splat.create(R.string.twilight));
                list.add(Splat.create(R.string.night));
                list.add(Splat.create(R.string.eclipse));
                break;
            case R.string.abyssal_exalted:
                list.add(Splat.create(R.string.dusk));
                list.add(Splat.create(R.string.midnight));
                list.add(Splat.create(R.string.daybreak));
                list.add(Splat.create(R.string.day));
                list.add(Splat.create(R.string.moonshadow));
                break;
            case R.string.lunar_exalted:
                list.add(Splat.create(R.string.full_moon));
                list.add(Splat.create(R.string.changing_moon));
                list.add(Splat.create(R.string.no_moon));
                list.add(Splat.create(R.string.casteless));
                break;
            case R.string.sidereal_exalted:
                list.add(Splat.create(R.string.air));
                list.add(Splat.create(R.string.earth));
                list.add(Splat.create(R.string.fire));
                list.add(Splat.create(R.string.water));
                list.add(Splat.create(R.string.wood));
                break;
            case R.string.terrestrial_exalted:
                list.add(Splat.create(R.string.chosen_of_journeys));
                list.add(Splat.create(R.string.chosen_of_serenity));
                list.add(Splat.create(R.string.chosen_of_battles));
                list.add(Splat.create(R.string.chosen_of_secrets));
                list.add(Splat.create(R.string.chosen_of_endings));
                break;
            case R.string.alchemical_exalted:
                list.add(Splat.create(R.string.orichalcum));
                list.add(Splat.create(R.string.moonsilver));
                list.add(Splat.create(R.string.starmetal));
                list.add(Splat.create(R.string.jade));
                list.add(Splat.create(R.string.soulsteel));
                break;
            case R.string.infernal_exalted:
                list.add(Splat.create(R.string.slayer));
                list.add(Splat.create(R.string.malefactor));
                list.add(Splat.create(R.string.defiler));
                list.add(Splat.create(R.string.scrourge));
                list.add(Splat.create(R.string.fiend));
                break;
            default:
                return null;
        }
        return list;
    }
}