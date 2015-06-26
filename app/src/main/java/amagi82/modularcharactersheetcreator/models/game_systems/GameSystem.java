package amagi82.modularcharactersheetcreator.models.game_systems;

import amagi82.modularcharactersheetcreator.R;

abstract class GameSystem {

    /*
        - System enum includes the name and drawable for each game system, along with the base url,
        and whether or not it's part of the World of Darkness
        - Enums passed in are for the left and right image flanking the character portrait
    */
    public enum System{
        CWODVAMPIRE(R.string.cwod_vampire, R.drawable.title_vampire_masquerade, R.color.cwod_vampire, R.string.url_cwod_vampire_base, true, R.string.sect, R.string.clan),
        CWODWEREWOLF(R.string.cwod_werewolf, R.drawable.title_werewolf_apocalypse, R.color.cwod_werewolf, R.string.url_cwod_werewolf_base, true, R.string.fera, R.string.tribe, R.string.auspice),
        CWODWRAITH(R.string.cwod_wraith, R.drawable.title_wraith_oblivion, R.color.cwod_wraith, R.string.url_cwod_wraith_base, true, R.string.arcanos),
        CWODMAGE(R.string.cwod_mage, R.drawable.title_mage_ascension, R.color.cwod_mage, R.string.url_cwod_mage_base, true, R.string.tradition),
        NWODVAMPIRE(R.string.nwod_vampire, R.drawable.title_vampire_requiem, R.color.nwod_vampire, R.string.url_nwod_vampire_base, true, R.string.clan, R.string.bloodline, R.string.covenant),
        NWODWEREWOLF(R.string.nwod_werewolf, R.drawable.title_werewolf_forsaken, R.color.nwod_werewolf, R.string.url_nwod_werewolf_base, true, R.string.tribe, R.string.auspice),
        NWODMUMMY(R.string.nwod_mummy, R.drawable.title_mummy_curse, R.color.nwod_mummy, R.string.url_nwod_mummy_base, true, R.string.decree, R.string.guild),
        NWODDEMON(R.string.nwod_demon, R.drawable.title_demon_descent, R.color.nwod_demon, R.string.url_nwod_demon_base, true, R.string.incarnation, R.string.agenda),
        SCION(R.string.scion, R.drawable.title_scion, R.color.scion),
        TRINITY(R.string.trinity, R.drawable.title_trinity_continuum, R.color.trinity),
        EXALTED(R.string.exalted, R.drawable.title_exalted, R.color.exalted);

        private int name;
        private int imageMain;
        private int urlBase;
        private int color;
        private boolean wod;
        private int[] categoryTitles;
        private int wodName = R.string.world_of_darkness;
        private int wodDrawable = R.drawable.title_wod;

        System(int name, int imageMain, int color) {
            this(name, imageMain, color, 0, false);
        }

        System(int name, int imageMain, int color, boolean wod){
            this(name, imageMain, color, 0, wod);
        }

        System(int name, int imageMain, int color, int urlBase, boolean wod){
            this.name = name;
            this.imageMain = imageMain;
            this.color = color;
            this.urlBase = urlBase;
            this.wod = wod;
        }

        System(int name, int imageMain, int color, int urlBase, boolean wod, int...categoryTitles){
            this(name, imageMain, color, urlBase, wod);
            this.categoryTitles = categoryTitles;
        }

        public int getName() {
            return name;
        }

        public int getImageMain() {
            return imageMain;
        }

        public int getColor() {
            return color;
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

        public int getCategoryCount(){
            return categoryTitles.length;
        }

        public int[] getCategoryTitles(){
            return categoryTitles;
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
