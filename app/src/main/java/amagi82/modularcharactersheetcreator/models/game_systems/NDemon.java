package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class NDemon extends GameSystem {

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

    public NDemon() {
        super(System.NWODDEMON);
    }

    public Incarnation getIncarnation() {
        return incarnation;
    }

    public void setIncarnation(Incarnation incarnation) {
        setLeft(new SubType(incarnation.getName(), incarnation.getUrl()));
        this.incarnation = incarnation;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        setRight(new SubType(agenda.getName(), agenda.getUrl()));
        setTitle(agenda.getName());
        this.agenda = agenda;
    }

    public List<Incarnation> getListIncarnation() {
        List<Incarnation> list = new ArrayList<>();
        Collections.addAll(list, Incarnation.values());
        return list;
    }

    public List<Agenda> getListAgenda() {
        List<Agenda> list = new ArrayList<>();
        Collections.addAll(list, Agenda.values());
        return list;
    }
}