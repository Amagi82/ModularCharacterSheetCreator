package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Choice;

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
    private List<Choice> list = new ArrayList<>();

    public NDemon() {
    }

    public NDemon(String incarnationName, String agendaName) {
        incarnation = Incarnation.valueOf(incarnationName);
        agenda = Agenda.valueOf(agendaName);
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

    @Override public Choice getRight() {
        return choiceRight;
    }

    @Override public List<Choice> getList(String eName) {
        list.clear();
        if (eName == null) {
            for (Incarnation incarnation : Incarnation.values()){
                list.add(new Choice(incarnation.name(), incarnation.getName(), Game.System.NDEMON.getUrlBase(), incarnation.getUrl()));
            }
            return list;
        }
        if(incarnation == null) {
            incarnation = Incarnation.valueOf(eName);
            choiceLeft = new Choice(incarnation.name(), incarnation.getName(), Game.System.NDEMON.getUrlBase(), incarnation.getUrl());

            for (Agenda agenda : Agenda.values()) {
                list.add(new Choice(agenda.name(), agenda.getName(), Game.System.NDEMON.getUrlBase(), agenda.getUrl()));
            }
            return list;
        }
        agenda = Agenda.valueOf(eName);
        choiceRight = new Choice(agenda.name(), agenda.getName(), Game.System.NDEMON.getUrlBase(), agenda.getUrl());
        return null;
    }
}