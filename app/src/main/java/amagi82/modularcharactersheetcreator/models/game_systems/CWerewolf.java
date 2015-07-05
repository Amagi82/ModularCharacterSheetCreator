package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Choice;

public class CWerewolf extends Onyx {

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

    private Tribe tribe;
    private Auspice auspice;
    private Choice choiceLeft;
    private Choice choiceRight;

    public CWerewolf() {
    }

    public CWerewolf(String tribeName, String auspiceName) {
        this(Tribe.valueOf(tribeName), Auspice.valueOf(auspiceName));
    }

    public CWerewolf(Tribe tribe, Auspice auspice) {
        this.tribe = tribe;
        this.auspice = auspice;
        choiceLeft = getChoice(tribe);
        choiceRight = getChoice(auspice);
    }

    private Choice getChoice(Tribe tribe) {
        return new Choice(tribe.name(), tribe.getName(), Game.System.CWEREWOLF.getUrlBase(), tribe.getUrl());
    }

    private Choice getChoice(Auspice auspice) {
        return new Choice(auspice.name(), auspice.getName(), Game.System.CWEREWOLF.getUrlBase(), auspice.getUrl());
    }

    @Override public String getSystemName() {
        return Game.System.CWEREWOLF.name();
    }

    @Override public int getArchetype() {
        return tribe.getName();
    }

    @Override public Choice getLeft() {
        return choiceLeft;
    }

    @Override public void setLeft(String eName) {
        tribe = Tribe.valueOf(eName);
        choiceLeft = getChoice(tribe);
    }

    @Override public Choice getRight() {
        return choiceRight;
    }

    @Override public void setRight(String eName) {
        auspice = Auspice.valueOf(eName);
        choiceRight = getChoice(auspice);
    }

    @Override public boolean hasRight() {
        return true;
    }

    @Override public List<Choice> getListLeft(String eName) {
        List<Choice> list = new ArrayList<>();
        if (eName == null) for (Tribe tribe : Tribe.values()) list.add(getChoice(tribe));
        else setLeft(eName);
        return list;
    }

    @Override public List<Choice> getListRight(String eName) {
        List<Choice> list = new ArrayList<>();
        if (eName == null) for (Auspice auspice : Auspice.values()) list.add(getChoice(auspice));
        else setRight(eName);
        return list;
    }
}
