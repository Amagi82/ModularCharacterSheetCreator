package amagi82.modularcharactersheetcreator.models.games.systems;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Choice;
import amagi82.modularcharactersheetcreator.models.games.Game;

public class Scion extends Onyx {

    public enum Volume {
        HERO(R.string.hero),
        DEMIGOD(R.string.demigod),
        GOD(R.string.god);

        private int name;

        Volume(int name) {
            this.name = name;
        }

        public int getName() {
            return name;
        }
    }

    public enum Pantheon {
        PESEDJET(R.string.pesedjet),
        DODEKATHEON(R.string.dodekatheon),
        AESIR(R.string.aesir),
        ATZLANTI(R.string.atzlanti),
        AMATSUKAMI(R.string.amatsukami),
        LOA(R.string.loa),
        TUATHADEDANANN(R.string.tuatha_de_dadann),
        CELESTIALBUREAUCRACY(R.string.celestial_bureaucracy),
        DEVA(R.string.deva),
        YAZATA(R.string.yazata);

        private int name;

        Pantheon(int name) {
            this.name = name;
        }

        public int getName() {
            return name;
        }
    }

    private Volume volume;
    private Pantheon pantheon;
    private Choice choiceLeft;
    private Choice choiceRight;

    public Scion() {
    }

    public Scion(String volumeName, String pantheonName) {
        this(Volume.valueOf(volumeName), Pantheon.valueOf(pantheonName));
    }

    public Scion(Volume volume, Pantheon pantheon) {
        this.volume = volume;
        this.pantheon = pantheon;
        choiceLeft = getChoice(volume);
        choiceRight = getChoice(pantheon);
    }

    private Choice getChoice(Volume volume) {
        return new Choice(volume.name(), volume.getName());
    }

    private Choice getChoice(Pantheon pantheon) {
        return new Choice(pantheon.name(), pantheon.getName());
    }

    @Override public String getSystemName() {
        return Game.System.SCION.name();
    }

    @Override public int getArchetype() {
        return pantheon.getName();
    }

    @Override public Choice getLeft() {
        return choiceLeft;
    }

    @Override public void setLeft(String eName) {
        volume = Volume.valueOf(eName);
        choiceLeft = getChoice(volume);
    }

    @Override public Choice getRight() {
        return choiceRight;
    }

    @Override public void setRight(String eName) {
        pantheon = Pantheon.valueOf(eName);
        choiceRight = getChoice(pantheon);
    }

    @Override public boolean hasRight() {
        return true;
    }

    @Override public List<Choice> getListLeft(String eName) {
        List<Choice> list = new ArrayList<>();
        if (eName == null) for (Volume volume : Volume.values()) list.add(getChoice(volume));
        else setLeft(eName);
        return list;
    }

    @Override public List<Choice> getListRight(String eName) {
        List<Choice> list = new ArrayList<>();
        if (eName == null) for (Pantheon pantheon : Pantheon.values()) list.add(getChoice(pantheon));
        else setRight(eName);
        return list;
    }
}