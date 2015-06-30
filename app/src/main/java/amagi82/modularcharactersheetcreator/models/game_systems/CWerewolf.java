package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class CWerewolf extends CWorldofDarkness {

    public enum Fera {
        GAROU(R.string.garou, R.string.url_cwod_werewolf_fera_garou),
        AJABA(R.string.ajaba, R.string.url_cwod_werewolf_fera_ajaba),
        ANANASI(R.string.ananasi, R.string.url_cwod_werewolf_fera_ananasi),
        APIS(R.string.apis, R.string.url_cwod_werewolf_fera_apis),
        BASTET(R.string.bastet, R.string.url_cwod_werewolf_fera_bastet),
        CAMAZOTZ(R.string.camazotz, R.string.url_cwod_werewolf_fera_camazotz),
        CORAX(R.string.corax, R.string.url_cwod_werewolf_fera_corax),
        GRONDR(R.string.grondr, R.string.url_cwod_werewolf_fera_grondr),
        GURAHL(R.string.gurahl, R.string.url_cwod_werewolf_fera_gurahl),
        KITSUNE(R.string.kitsune, R.string.url_cwod_werewolf_fera_kitsune),
        MOKOLE(R.string.mokole, R.string.url_cwod_werewolf_fera_mokole),
        NAGAH(R.string.nagah, R.string.url_cwod_werewolf_fera_nagah),
        NUWISHA(R.string.nuwisha, R.string.url_cwod_werewolf_fera_nuwisha),
        RATKIN(R.string.ratkin, R.string.url_cwod_werewolf_fera_ratkin),
        ROKEA(R.string.rokea, R.string.url_cwod_werewolf_fera_rokea);

        private int name;
        private int url;

        Fera(int name, int url) {
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

    public enum Tribe {
        BLACKFURIES(R.string.black_furies, R.string.url_cwod_werewolf_tribe_black_furies),
        BONEGNAWERS(R.string.bone_gnawers, R.string.url_cwod_werewolf_tribe_bone_gnawers),
        BUNYIP(R.string.bunyip, R.string.url_cwod_werewolf_tribe_bunyip),
        CHILDRENOFGAIA(R.string.children_of_gaia, R.string.url_cwod_werewolf_tribe_children_of_gaia),
        CROATAN(R.string.croatan, R.string.url_cwod_werewolf_tribe_croatan),
        FIANNA(R.string.fianna, R.string.url_cwod_werewolf_tribe_fianna),
        GETOFFENRIS(R.string.get_of_fenris, R.string.url_cwod_werewolf_tribe_get_of_fenris),
        GLASSWALKERS(R.string.glass_walkers, R.string.url_cwod_werewolf_tribe_glass_walkers),
        REDTALONS(R.string.red_talons, R.string.url_cwod_werewolf_tribe_red_talons),
        SHADOWLORDS(R.string.shadow_lords, R.string.url_cwod_werewolf_tribe_shadow_lords),
        SILENTSTRIDERS(R.string.silent_striders, R.string.url_cwod_werewolf_tribe_silent_striders),
        SILVERFANGS(R.string.silver_fangs, R.string.url_cwod_werewolf_tribe_silver_fangs),
        STARGAZERS(R.string.stargazers, R.string.url_cwod_werewolf_tribe_stargazers),
        UKTENA(R.string.uktena, R.string.url_cwod_werewolf_tribe_uktena),
        WENDIGO(R.string.wendigo, R.string.url_cwod_werewolf_tribe_wendigo),
        WHITEHOWLERS(R.string.white_howlers, R.string.url_cwod_werewolf_tribe_white_howlers);

        private int name;
        private int url;

        Tribe(int name, int url) {
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

    public enum Auspice {
        AHROUN(R.string.ahroun, R.string.url_cwod_werewolf_auspice_ahroun),
        GALLIARD(R.string.galliard, R.string.url_cwod_werewolf_auspice_galliard),
        PHILODOX(R.string.philodox, R.string.url_cwod_werewolf_auspice_philodox),
        RAGABASH(R.string.ragabash, R.string.url_cwod_werewolf_auspice_ragabash),
        THEURGE(R.string.theurge, R.string.url_cwod_werewolf_auspice_theurge);

        private int name;
        private int url;

        Auspice(int name, int url) {
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

    private Fera fera;
    private Tribe tribe;
    private Auspice auspice;

    public CWerewolf() {
        super(System.CWODWEREWOLF);
    }

    public CWerewolf(String tribeName, String auspiceName){
        super(System.CWODWEREWOLF);
        if(Tribe.valueOf(tribeName) != null) {
            fera = Fera.GAROU;
            tribe = Tribe.valueOf(tribeName);
        }else fera = Fera.valueOf(tribeName);
        auspice = Auspice.valueOf(auspiceName);
    }

    public Fera getFera() {
        return fera;
    }

    public void setFera(Fera fera) {
        setLeft(fera.name(), fera.getName(), fera.getUrl());
        setArchetype(fera.getName());
        this.fera = fera;
    }

    public Tribe getTribe() {
        return tribe;
    }

    public void setTribe(Tribe tribe) {
        setLeft(tribe.name(), tribe.getName(), tribe.getUrl());
        setArchetype(tribe.getName());
        this.tribe = tribe;
    }

    public Auspice getAuspice() {
        return auspice;
    }

    public void setAuspice(Auspice auspice) {
        setRight(auspice.name(), auspice.getName(), auspice.getUrl());
        this.auspice = auspice;
    }

    public List<Fera> getListFera() {
        List<Fera> list = new ArrayList<>();
        Collections.addAll(list, Fera.values());
        return list;
    }

    public List<Tribe> getListTribe() {
        List<Tribe> list = new ArrayList<>();
        Collections.addAll(list, Tribe.values());
        return list;
    }

    public List<Auspice> getListAuspice() {
        List<Auspice> list = new ArrayList<>();
        Collections.addAll(list, Auspice.values());
        return list;
    }
}
