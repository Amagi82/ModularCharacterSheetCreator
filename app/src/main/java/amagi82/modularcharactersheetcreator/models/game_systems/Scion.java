package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class Scion extends GameSystem {

    public enum ScionVolume {
        HERO(R.string.hero),
        DEMIGOD(R.string.demigod),
        GOD(R.string.god);

        private int name;

        ScionVolume(int name) {
            this.name = name;
        }

        public int getName() {
            return name;
        }
    }

    public enum ScionPantheon {
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

        ScionPantheon(int name) {
            this.name = name;
        }

        public int getName() {
            return name;
        }
    }

    private ScionVolume volume;
    private ScionPantheon pantheon;

    public Scion() {
        super(System.SCION);
    }

    public ScionVolume getVolume() {
        return volume;
    }

    public void setVolume(ScionVolume volume) {
        setLeft(volume);
        this.volume = volume;
    }

    public ScionPantheon getPantheon() {
        return pantheon;
    }

    public void setPantheon(ScionPantheon pantheon) {
        setRight(pantheon);
        this.pantheon = pantheon;
    }

    public List<ScionVolume> getListVolume() {
        List<ScionVolume> list = new ArrayList<>();
        Collections.addAll(list, ScionVolume.values());
        return list;
    }

    public List<ScionPantheon> getListPantheon() {
        List<ScionPantheon> list = new ArrayList<>();
        Collections.addAll(list, ScionPantheon.values());
        return list;
    }
}