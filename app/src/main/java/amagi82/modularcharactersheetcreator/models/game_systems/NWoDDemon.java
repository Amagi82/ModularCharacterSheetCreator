package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class NWoDDemon extends GameSystem {

    public enum NWoDDemonIncarnation {
        DESTROYER(R.string.destroyer, R.string.url_nwod_demon_incarnation_destroyer),
        GUARDIAN(R.string.guardian, R.string.url_nwod_demon_incarnation_guardian),
        MESSENGER(R.string.messenger, R.string.url_nwod_demon_incarnation_messenger),
        PSYCHOPOMP(R.string.psychopomp, R.string.url_nwod_demon_incarnation_psychopomp);

        private int name;
        private int url;

        NWoDDemonIncarnation(int name, int url) {
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

    public enum NWoDDemonAgenda {
        INQUISITOR(R.string.inquisitor, R.string.url_nwod_demon_agenda_inquisitor),
        INTEGRATOR(R.string.integrator, R.string.url_nwod_demon_agenda_integrator),
        SABOTEUR(R.string.saboteur, R.string.url_nwod_demon_agenda_saboteur),
        TEMPTER(R.string.tempter, R.string.url_nwod_demon_agenda_tempter);

        private int name;
        private int url;

        NWoDDemonAgenda(int name, int url) {
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

    private NWoDDemonIncarnation incarnation;
    private NWoDDemonAgenda agenda;

    public NWoDDemon() {
        super(System.NWODDEMON);
    }

    public NWoDDemonIncarnation getIncarnation() {
        return incarnation;
    }

    public void setIncarnation(NWoDDemonIncarnation incarnation) {
        setLeft(incarnation);
        this.incarnation = incarnation;
    }

    public NWoDDemonAgenda getAgenda() {
        return agenda;
    }

    public void setAgenda(NWoDDemonAgenda agenda) {
        setRight(agenda);
        this.agenda = agenda;
    }

    public List<NWoDDemonIncarnation> getListIncarnation() {
        List<NWoDDemonIncarnation> list = new ArrayList<>();
        Collections.addAll(list, NWoDDemonIncarnation.values());
        return list;
    }

    public List<NWoDDemonAgenda> getListAgenda() {
        List<NWoDDemonAgenda> list = new ArrayList<>();
        Collections.addAll(list, NWoDDemonAgenda.values());
        return list;
    }
}