package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

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

    public Scion() {
    }

    public Scion(String volumeName, String pantheonName) {
        volume = Volume.valueOf(volumeName);
        pantheon = Pantheon.valueOf(pantheonName);
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        setLeft(volume.name(), volume.getName());
        this.volume = volume;
    }

    public Pantheon getPantheon() {
        return pantheon;
    }

    public void setPantheon(Pantheon pantheon) {
        setRight(pantheon.name(), pantheon.getName());
        setArchetype(pantheon.getName());
        this.pantheon = pantheon;
    }

    public List<Volume> getListVolume() {
        List<Volume> list = new ArrayList<>();
        Collections.addAll(list, Volume.values());
        return list;
    }

    public List<Pantheon> getListPantheon() {
        List<Pantheon> list = new ArrayList<>();
        Collections.addAll(list, Pantheon.values());
        return list;
    }
}