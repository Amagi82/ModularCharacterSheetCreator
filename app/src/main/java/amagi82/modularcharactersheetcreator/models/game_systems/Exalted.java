package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Choice;

public class Exalted extends Onyx {

    public enum ExaltedType {
        SOLAR(R.string.solar_exalted),
        ABYSSAL(R.string.abyssal_exalted),
        LUNAR(R.string.lunar_exalted),
        SIDEREAL(R.string.sidereal_exalted),
        TERRESTRIAL(R.string.terrestrial_exalted),
        ALCHEMICAL(R.string.alchemical_exalted),
        INFERNAL(R.string.infernal_exalted, false),
        FAIRFOLK(R.string.fair_folk, false);

        private int name;
        boolean hasCastes;

        ExaltedType(int name) {
            this(name, true);
        }

        ExaltedType(int name, boolean hasCastes) {
            this.name = name;
            this.hasCastes = hasCastes;
        }

        public int getName() {
            return name;
        }

        public boolean isHasCastes() {
            return hasCastes;
        }
    }

    public enum Caste {
        DAWN(R.string.dawn, ExaltedType.SOLAR),
        ZENITH(R.string.zenith, ExaltedType.SOLAR),
        TWILIGHT(R.string.twilight, ExaltedType.SOLAR),
        NIGHT(R.string.night, ExaltedType.SOLAR),
        ECLIPSE(R.string.eclipse, ExaltedType.SOLAR),
        DUSK(R.string.dusk, ExaltedType.ABYSSAL),
        MIDNIGHT(R.string.midnight, ExaltedType.ABYSSAL),
        DAYBREAK(R.string.daybreak, ExaltedType.ABYSSAL),
        DAY(R.string.day, ExaltedType.ABYSSAL),
        MOONSHADOW(R.string.midnight, ExaltedType.ABYSSAL),
        AIR(R.string.air, ExaltedType.TERRESTRIAL),
        EARTH(R.string.earth, ExaltedType.TERRESTRIAL),
        FIRE(R.string.fire, ExaltedType.TERRESTRIAL),
        WATER(R.string.water, ExaltedType.TERRESTRIAL),
        WOOD(R.string.wood, ExaltedType.TERRESTRIAL),
        FULLMOON(R.string.full_moon, ExaltedType.LUNAR),
        CHANGINGMOON(R.string.changing_moon, ExaltedType.LUNAR),
        NOMOON(R.string.no_moon, ExaltedType.LUNAR),
        CHOSENOFJOURNEYS(R.string.chosen_of_journeys, ExaltedType.SIDEREAL),
        CHOSENOFSERENITY(R.string.chosen_of_serenity, ExaltedType.SIDEREAL),
        CHOSENOFBATTLES(R.string.chosen_of_battles, ExaltedType.SIDEREAL),
        CHOSENOFSECRETS(R.string.chosen_of_secrets, ExaltedType.SIDEREAL),
        CHOSENOFENDINGS(R.string.chosen_of_endings, ExaltedType.SIDEREAL),
        ORICHALCUM(R.string.orichalcum, ExaltedType.ALCHEMICAL),
        MOONSILVER(R.string.moonsilver, ExaltedType.ALCHEMICAL),
        STARMETAL(R.string.starmetal, ExaltedType.ALCHEMICAL),
        JADE(R.string.jade, ExaltedType.ALCHEMICAL),
        SOULSTEEL(R.string.soulsteel, ExaltedType.ALCHEMICAL);

        private int name;
        ExaltedType parent;

        Caste(int name, ExaltedType parent) {
            this.name = name;
            this.parent = parent;
        }

        public int getName() {
            return name;
        }

        public ExaltedType getParent() {
            return parent;
        }
    }

    private ExaltedType exaltedType;
    private Caste caste;
    private Choice choiceLeft;
    private Choice choiceRight;
    private List<Choice> list = new ArrayList<>();

    public Exalted() {
    }

    public Exalted(String typeName, String casteName) {
        this(ExaltedType.valueOf(typeName), Caste.valueOf(casteName));
    }

    public Exalted(ExaltedType exaltedType, Caste caste){
        this.exaltedType = exaltedType;
        this.caste = caste;
        choiceLeft = getChoice(exaltedType);
        choiceRight = getChoice(caste);
    }

    private Choice getChoice(ExaltedType exaltedType){
        return new Choice(exaltedType.name(), exaltedType.getName());
    }
    private Choice getChoice(Caste caste){
        return new Choice(caste.name(), caste.getName());
    }


    @Override public String getSystemName() {
        return Game.System.EXALTED.name();
    }

    @Override public int getArchetype() {
        return exaltedType.getName();
    }

    @Override public Choice getLeft() {
        return choiceLeft;
    }

    @Override public Choice getRight() {
        return choiceRight;
    }

    @Override public List<Choice> getList(String eName) {
        list.clear();
        if (eName == null) {
            for (ExaltedType exaltedType : ExaltedType.values()) {
                list.add(getChoice(exaltedType));
            }
            return list;
        }
        if(choiceLeft == null) {
            exaltedType = ExaltedType.valueOf(eName);
            choiceLeft = getChoice(exaltedType);
            for (Caste caste : Caste.values()) {
                if (caste.getParent().equals(ExaltedType.valueOf(eName))) list.add(getChoice(caste));
            }
            return list.size() > 0 ? list : null;
        }
        caste = Caste.valueOf(eName);
        choiceRight = getChoice(caste);
        return null;
    }
}