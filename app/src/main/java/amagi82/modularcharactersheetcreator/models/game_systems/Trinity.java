package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Choice;

public class Trinity extends Onyx {

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

    private Volume volume;
    private Order order;
    private Choice choiceLeft;
    private Choice choiceRight;
    private List<Choice> list = new ArrayList<>();

    public Trinity() {
    }

    public Trinity(String volumeName, String orderName) {
        volume = Volume.valueOf(volumeName);
        order = Order.valueOf(orderName);
    }

    @Override public String getSystemName() {
        return Game.System.TRINITY.name();
    }

    @Override public int getArchetype() {
        return order.getName();
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

            for (Order order : Order.values()) {
                list.add(new Choice(order.name(), order.getName()));
            }
            return list;
        }
        order = Order.valueOf(eName);
        choiceRight = new Choice(order.name(), order.getName());
        return null;
    }
}