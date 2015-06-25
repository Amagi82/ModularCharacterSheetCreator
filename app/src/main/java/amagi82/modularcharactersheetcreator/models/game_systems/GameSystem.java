package amagi82.modularcharactersheetcreator.models.game_systems;

import amagi82.modularcharactersheetcreator.R;

abstract class GameSystem {

    /*
        - System enum includes the name and drawable for each game system, along with the base url,
        and whether or not it's part of the World of Darkness
        - Enums passed in are for the left and right image flanking the character portrait
    */
    public enum System{
        CWODVAMPIRE(R.string.cwod_vampire, R.drawable.title_vampire_masquerade, R.string.url_cwod_vampire_base, true),
        CWODWEREWOLF(R.string.cwod_werewolf, R.drawable.title_werewolf_apocalypse, R.string.url_cwod_werewolf_base, true),
        CWODWRAITH(R.string.cwod_wraith, R.drawable.title_wraith_oblivion, R.string.url_cwod_wraith_base, true),
        CWODMAGE(R.string.cwod_mage, R.drawable.title_mage_ascension, R.string.url_cwod_mage_base, true),
        NWODVAMPIRE(R.string.nwod_vampire, R.drawable.title_vampire_requiem, R.string.url_nwod_vampire_base, true),
        NWODWEREWOLF(R.string.nwod_werewolf, R.drawable.title_werewolf_forsaken, R.string.url_nwod_werewolf_base, true),
        NWODMUMMY(R.string.nwod_mummy, R.drawable.title_mummy_curse, R.string.url_nwod_mummy_base, true),
        NWODDEMON(R.string.nwod_demon, R.drawable.title_demon_descent, R.string.url_nwod_demon_base, true),
        SCION(R.string.scion, R.drawable.title_scion),
        TRINITY(R.string.trinity, R.drawable.title_trinity_continuum),
        EXALTED(R.string.exalted, R.drawable.title_exalted);

        private int name;
        private int imageMain;
        private int urlBase;
        private boolean wod;
        private int wodName = R.string.world_of_darkness;
        private int wodDrawable = R.drawable.title_wod;

        System(int name, int imageMain) {
            this(name, imageMain, 0, false);
        }

        System(int name, int imageMain, boolean wod){
            this(name, imageMain, 0, wod);
        }

        System(int name, int imageMain, int urlBase, boolean wod){
            this.name = name;
            this.imageMain = imageMain;
            this.urlBase = urlBase;
            this.wod = wod;
        }

        public int getName() {
            return name;
        }

        public int getImageMain() {
            return imageMain;
        }

        public int getUrlBase() {
            return urlBase;
        }

        public boolean isWod() {
            return wod;
        }

        public int getWodName() {
            return wodName;
        }

        public int getWodDrawable() {
            return wodDrawable;
        }
    }
    private System system;
    private Enum left;
    private Enum right;

    public GameSystem(System system) {
        this(system, null, null);
    }

    public GameSystem(System system, Enum left){
        this(system, left, null);
    }
    public GameSystem(System system, Enum left, Enum right){
        this.system = system;
        this.left = left;
        this.right = right;
    }

    public System getSystem() {
        return system;
    }

    public Enum getLeft() {
        return left;
    }

    public void setLeft(Enum left) {
        this.left = left;
    }

    public Enum getRight() {
        return right;
    }

    public void setRight(Enum right) {
        this.right = right;
    }
}
