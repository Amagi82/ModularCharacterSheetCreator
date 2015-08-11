package amagi82.modularcharactersheetcreator.models.games.systems;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Choice;
import amagi82.modularcharactersheetcreator.models.games.Game;

public class NDemon extends Onyx {

    public enum Incarnation {
        DESTROYER(R.string.destroyer, R.string.url_nwod_demon_incarnation_destroyer),
        GUARDIAN(R.string.guardian, R.string.url_nwod_demon_incarnation_guardian),
        MESSENGER(R.string.messenger, R.string.url_nwod_demon_incarnation_messenger),
        PSYCHOPOMP(R.string.psychopomp, R.string.url_nwod_demon_incarnation_psychopomp);

        private int name;
        private int url;

        Incarnation(int name, int url) {
            this.name = name;
            this.url = url;
        }

        public int getName() {
            return name;
        }

        public int getUrl() {
            return url;
        }
    }

    public enum Agenda {
        INQUISITOR(R.string.inquisitor, R.string.url_nwod_demon_agenda_inquisitor),
        INTEGRATOR(R.string.integrator, R.string.url_nwod_demon_agenda_integrator),
        SABOTEUR(R.string.saboteur, R.string.url_nwod_demon_agenda_saboteur),
        TEMPTER(R.string.tempter, R.string.url_nwod_demon_agenda_tempter);

        private int name;
        private int url;

        Agenda(int name, int url) {
            this.name = name;
            this.url = url;
        }

        public int getName() {
            return name;
        }

        public int getUrl() {
            return url;
        }
    }

    private Incarnation incarnation;
    private Agenda agenda;
    private Choice choiceLeft;
    private Choice choiceRight;

    public NDemon() {
    }

    public NDemon(String incarnationName, String agendaName) {
        this(Incarnation.valueOf(incarnationName), Agenda.valueOf(agendaName));
    }

    public NDemon(Incarnation incarnation, Agenda agenda) {
        this.incarnation = incarnation;
        this.agenda = agenda;
        choiceLeft = getChoice(incarnation);
        choiceRight = getChoice(agenda);
    }
    
    private Choice getChoice(Incarnation incarnation){
        return new Choice(incarnation.name(), incarnation.getName(), Game.System.NDEMON.getUrlBase(), incarnation.getUrl());
    }

    private Choice getChoice(Agenda agenda){
        return new Choice(agenda.name(), agenda.getName(), Game.System.NDEMON.getUrlBase(), agenda.getUrl());
    }

    @Override public String getSystemName() {
        return Game.System.NDEMON.name();
    }

    @Override public int getArchetype() {
        return incarnation.getName();
    }

    @Override public Choice getLeft() {
        return choiceLeft;
    }

    @Override public void setLeft(String eName) {
        incarnation = Incarnation.valueOf(eName);
        choiceLeft = getChoice(incarnation);
    }

    @Override public Choice getRight() {
        return choiceRight;
    }

    @Override public void setRight(String eName) {
        agenda = Agenda.valueOf(eName);
        choiceRight = getChoice(agenda);
    }

    @Override public boolean hasRight() {
        return true;
    }

    @Override public List<Choice> getListLeft(String eName) {
        List<Choice> list = new ArrayList<>();
        if (eName == null) for (Incarnation incarnation : Incarnation.values()) list.add(getChoice(incarnation));
        else setLeft(eName);
        return list;
    }

    @Override public List<Choice> getListRight(String eName) {
        List<Choice> list = new ArrayList<>();
        if (eName == null) for (Agenda agenda : Agenda.values()) list.add(getChoice(agenda));
        else setRight(eName);
        return list;
    }
}