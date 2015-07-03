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

    public Trinity() {
    }

    public Trinity(String volumeName, String orderName) {
        this(Volume.valueOf(volumeName), Order.valueOf(orderName));
    }

    public Trinity(Volume volume, Order order){
        this.volume = volume;
        this.order = order;
        choiceLeft = getChoice(volume);
        choiceRight = getChoice(order);
    }
    private Choice getChoice(Volume volume){
        return new Choice(volume.name(), volume.getName());
    }

    private Choice getChoice(Order order){
        return new Choice(order.name(), order.getName());
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

    @Override public boolean hasRight() {
        return true;
    }

    @Override public List<Choice> getListLeft(String eName) {
        List<Choice> list = new ArrayList<>();
        if (eName == null) {
            for (Volume volume : Volume.values()) list.add(getChoice(volume));
        }else {
            volume = Volume.valueOf(eName);
            choiceLeft = getChoice(volume);
        }
        return list;
    }

    @Override public List<Choice> getListRight(String eName) {
        List<Choice> list = new ArrayList<>();
        if(eName == null){
            for (Order order : Order.values()) list.add(getChoice(order));
        }else{
            order = Order.valueOf(eName);
            choiceRight = getChoice(order);
        }
        return list;
    }
}