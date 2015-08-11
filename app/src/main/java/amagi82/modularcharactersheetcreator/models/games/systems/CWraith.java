package amagi82.modularcharactersheetcreator.models.games.systems;

import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Choice;
import amagi82.modularcharactersheetcreator.models.games.Game;

@JsonObject
public class CWraith extends Onyx {

    public enum Arcanos {
        ARGOS(R.string.argos, R.string.url_cwod_wraith_arcanos_argos),
        CASTIGATE(R.string.castigate, R.string.url_cwod_wraith_arcanos_castigate),
        EMBODY(R.string.embody, R.string.url_cwod_wraith_arcanos_embody),
        FATALISM(R.string.fatalism, R.string.url_cwod_wraith_arcanos_fatalism),
        FLUX(R.string.flux, R.string.url_cwod_wraith_arcanos_flux),
        INHABIT(R.string.inhabit, R.string.url_cwod_wraith_arcanos_inhabit),
        INTIMATION(R.string.intimation, R.string.url_cwod_wraith_arcanos_intimation),
        KEENING(R.string.keening, R.string.url_cwod_wraith_arcanos_keening),
        LIFEWEB(R.string.lifeweb, R.string.url_cwod_wraith_arcanos_lifeweb),
        MNEMOSYNIS(R.string.mnemosynis, R.string.url_cwod_wraith_arcanos_mnemosynis),
        MOLIATE(R.string.moliate, R.string.url_cwod_wraith_arcanos_moliate),
        OUTRAGE(R.string.outrage, R.string.url_cwod_wraith_arcanos_outrage),
        PANDEMONIUM(R.string.pandemonium, R.string.url_cwod_wraith_arcanos_pandemonium),
        PHANTASM(R.string.phantasm, R.string.url_cwod_wraith_arcanos_phantasm),
        PUPPETRY(R.string.puppetry, R.string.url_cwod_wraith_arcanos_puppetry),
        USURY(R.string.usury, R.string.url_cwod_wraith_arcanos_usury);

        private int name;
        private int url;

        Arcanos(int name, int url) {
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

    private Arcanos arcanos;
    private Choice choiceLeft;

    public CWraith() {
    }

    public CWraith(String arcanosName) {
        arcanos = Arcanos.valueOf(arcanosName);
    }

    public CWraith(Arcanos arcanos) {
        this.arcanos = arcanos;
        choiceLeft = getChoice(arcanos);
    }

    private Choice getChoice(Arcanos arcanos) {
        return new Choice(arcanos.name(), arcanos.getName(), Game.System.CWRAITH.getUrlBase(), arcanos.getUrl());
    }

    @Override public String getSystemName() {
        return Game.System.CWRAITH.name();
    }

    @Override public int getArchetype() {
        return arcanos.getName();
    }

    @Override public Choice getLeft() {
        return choiceLeft;
    }

    @Override public void setLeft(String eName) {
        arcanos = Arcanos.valueOf(eName);
        choiceLeft = getChoice(arcanos);
    }

    @Override public Choice getRight() {
        return null;
    }

    @Override public void setRight(String eName) {

    }

    @Override public boolean hasRight() {
        return false;
    }

    @Override public List<Choice> getListLeft(String eName) {
        List<Choice> list = new ArrayList<>();
        if (eName == null) for (Arcanos arcanos : Arcanos.values()) list.add(getChoice(arcanos));
        else setLeft(eName);
        return list;
    }

    @Override public List<Choice> getListRight(String eName) {
        return null;
    }
}
