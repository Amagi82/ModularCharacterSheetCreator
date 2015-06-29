package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class Trinity extends GameSystem {

    public enum Volume {
        ADVENTURE(R.string.adventure),
        ABERRANT(R.string.aberrant),
        AEON(R.string.aeon);

        private int name;

        Volume(int name) {
            this.name = name;
        }

        public int getName() {
            return name;
        }
    }

    public enum Order {
        AESCULAPIANORDER(R.string.aesculapian_order),
        ISRA(R.string.isra),
        LEGIONS(R.string.the_legions),
        MINISTRYOFPSIONICAFFAIRS(R.string.the_ministry_of_noetic_affairs),
        NOVAFORCADASNACOES(R.string.nova_forca_das_nacoes),
        ORGOTEK(R.string.orgotek),
        UPEOWAMACHO(R.string.the_upeo_wa_macho),
        CHITRABHANU(R.string.chitra_bhanu);

        private int name;

        Order(int name) {
            this.name = name;
        }

        public int getName() {
            return name;
        }
    }

    public String volumeName;
    public String orderName;

    public Trinity() {
        super(System.TRINITY);
    }

    public Volume getVolume() {
        return Volume.valueOf(volumeName);
    }

    public void setVolume(Volume volume) {
        setLeft(new SubType(volume.getName()));
        volumeName = volume.name();
    }

    public Order getOrder() {
        return Order.valueOf(orderName);
    }

    public void setOrder(Order order) {
        setRight(new SubType(order.getName()));
        setTitle(order.getName());
        orderName = order.name();
    }

    public List<Volume> getListVolume() {
        List<Volume> list = new ArrayList<>();
        Collections.addAll(list, Volume.values());
        return list;
    }

    public List<Order> getListOrder() {
        List<Order> list = new ArrayList<>();
        Collections.addAll(list, Order.values());
        return list;
    }
}