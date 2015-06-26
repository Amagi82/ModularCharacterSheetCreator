package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class CWoDWraith extends GameSystem {

    public enum CWoDWraithArcanos {
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

        CWoDWraithArcanos(int name, int url) {
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

    private CWoDWraithArcanos arcanos;

    public CWoDWraith() {
        super(System.CWODWRAITH);
    }

    public CWoDWraithArcanos getArcanos() {
        return arcanos;
    }

    public void setArcanos(CWoDWraithArcanos arcanos) {
        setLeft(arcanos);
        this.arcanos = arcanos;
    }

    public List<CWoDWraithArcanos> getListArcanos() {
        List<CWoDWraithArcanos> list = new ArrayList<>();
        Collections.addAll(list, CWoDWraithArcanos.values());
        return list;
    }
}
