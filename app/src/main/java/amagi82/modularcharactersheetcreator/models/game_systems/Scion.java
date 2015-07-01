package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Choice;

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
    private List<Choice> list = new ArrayList<>();

    public Scion() {
    }

    public Scion(String volumeName, String pantheonName) {
        volume = Volume.valueOf(volumeName);
        pantheon = Pantheon.valueOf(pantheonName);
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

    @Override public Choice getRight() {
        return choiceRight;
    }

    @Override public List<Choice> getList(String eName) {
        list.clear();
        if (eName == null) {
            for (Volume volume : Volume.values()){
                list.add(new Choice(volume.name(), volume.getName()));
            }
            return list;
        }
        if(volume == null) {
            volume = Volume.valueOf(eName);
            choiceLeft = new Choice(volume.name(), volume.getName());

            for (Pantheon pantheon : Pantheon.values()) {
                list.add(new Choice(pantheon.name(), pantheon.getName()));
            }
            return list;
        }
        pantheon = Pantheon.valueOf(eName);
        choiceRight = new Choice(pantheon.name(), pantheon.getName());
        return null;
    }
}