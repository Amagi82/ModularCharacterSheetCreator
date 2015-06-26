package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class Trinity extends GameSystem {

    public enum TrinityVolume {
        ADVENTURE(R.string.adventure),
        ABERRANT(R.string.aberrant),
        AEON(R.string.aeon);

        private int name;

        TrinityVolume(int name) {
            this.name = name;
        }

        public int getName() {
            return name;
        }
    }

    public enum TrinityOrder {
        AESCULAPIANORDER(R.string.aesculapian_order),
        ISRA(R.string.isra),
        LEGIONS(R.string.the_legions),
        MINISTRYOFPSIONICAFFAIRS(R.string.the_ministry_of_noetic_affairs),
        NOVAFORCADASNACOES(R.string.nova_forca_das_nacoes),
        ORGOTEK(R.string.orgotek),
        UPEOWAMACHO(R.string.the_upeo_wa_macho),
        CHITRABHANU(R.string.chitra_bhanu);

        private int name;

        TrinityOrder(int name) {
            this.name = name;
        }

        public int getName() {
            return name;
        }
    }

    private TrinityVolume volume;
    private TrinityOrder order;

    public Trinity() {
        super(System.TRINITY);
    }

    public TrinityVolume getVolume() {
        return volume;
    }

    public void setVolume(TrinityVolume volume) {
        setLeft(volume);
        this.volume = volume;
    }

    public TrinityOrder getOrder() {
        return order;
    }

    public void setOrder(TrinityOrder order) {
        setRight(order);
        this.order = order;
    }

    public List<TrinityVolume> getListVolume() {
        List<TrinityVolume> list = new ArrayList<>();
        Collections.addAll(list, TrinityVolume.values());
        return list;
    }

    public List<TrinityOrder> getListOrder() {
        List<TrinityOrder> list = new ArrayList<>();
        Collections.addAll(list, TrinityOrder.values());
        return list;
    }
}