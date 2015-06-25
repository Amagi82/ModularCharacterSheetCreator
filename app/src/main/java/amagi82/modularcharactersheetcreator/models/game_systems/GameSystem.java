package amagi82.modularcharactersheetcreator.models.game_systems;

import amagi82.modularcharactersheetcreator.R;

abstract class GameSystem {

    /*
        - Base url + url = full url for image.
        - Left and right refer to the location of the symbol on the sides of the character portrait. Systems may have none, left, or both.
        - Portrait is used for systems with different symbols in the selection process vs what is displayed flanking the character portrait
        - category refers to the term for the symbol selected in the right or left, e.g. Sect and Clan for Vampire the Masquerade
        - sheetLayouts includes all default character sheet templates for that game system
        - System enum includes the name and drawable for each game system
    */
    public enum System{
        CWODVAMPIRE(R.string.cwod_vampire, R.drawable.title_vampire_masquerade, true),
        CWODWEREWOLF(R.string.cwod_werewolf, R.drawable.title_werewolf_apocalypse, true),
        CWODWRAITH(R.string.cwod_wraith, R.drawable.title_wraith_oblivion, true),
        CWODMAGE(R.string.cwod_mage, R.drawable.title_mage_ascension, true),
        NWODVAMPIRE(R.string.nwod_vampire, R.drawable.title_vampire_requiem, true),
        NWODWEREWOLF(R.string.nwod_werewolf, R.drawable.title_werewolf_forsaken, true),
        NWODMUMMY(R.string.nwod_mummy, R.drawable.title_mummy_curse, true),
        NWODDEMON(R.string.nwod_demon, R.drawable.title_demon_descent, true),
        SCION(R.string.scion, R.drawable.title_scion),
        TRINITY(R.string.trinity, R.drawable.title_trinity_continuum),
        EXALTED(R.string.exalted, R.drawable.title_exalted);

        private int name;
        private int imageMain;
        private boolean wod;
        private int wodName = R.string.world_of_darkness;
        private int wodDrawable = R.drawable.title_wod;

        System(int name, int imageMain) {
            this(name, imageMain, false);
        }
        System(int name, int imageMain, boolean wod){
            this.name = name;
            this.imageMain = imageMain;
            this.wod = wod;
        }

        public int getName() {
            return name;
        }

        public int getImageMain() {
            return imageMain;
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
    private int urlBase;
    private int urlBasePortrait;
    private int urlLeft;
    private int urlRight;
    private int urlLeftPortrait;
    private int urlRightPortrait;
    private int categoryLeft;
    private int categoryRight;
    private int[] sheetLayouts;

    public GameSystem(System system, int[] sheetLayouts) {
        this(system, 0, 0, 0, 0, 0, 0, 0, 0, sheetLayouts);
    }

    public GameSystem(System system, int categoryLeft, int[] sheetLayouts) {
        this(system, 0, 0, 0, 0, 0, 0, categoryLeft, 0, sheetLayouts);
    }

    public GameSystem(System system, int categoryLeft, int categoryRight, int[] sheetLayouts) {
        this(system, 0, 0, 0, 0, 0, 0, categoryLeft, categoryRight, sheetLayouts);
    }

    public GameSystem(System system, int urlBase, int urlLeft, int categoryLeft, int[] sheetLayouts) {
        this(system, urlBase, urlBase, urlLeft, 0, urlLeft, 0, categoryLeft, 0, sheetLayouts);
    }

    public GameSystem(System system, int urlBase, int urlLeft, int urlRight, int categoryLeft, int categoryRight, int[] sheetLayouts) {
        this(system, urlBase, urlBase, urlLeft, urlRight, urlLeft, urlRight, categoryLeft, categoryRight, sheetLayouts);
    }

    public GameSystem(System system, int urlBase, int urlBasePortrait, int urlLeft, int urlRight, int urlLeftPortrait, int urlRightPortrait, int categoryLeft, int categoryRight, int[] sheetLayouts) {
        this.system = system;
        this.urlBase = urlBase;
        this.urlBasePortrait = urlBasePortrait;
        this.urlLeft = urlLeft;
        this.urlRight = urlRight;
        this.urlLeftPortrait = urlLeftPortrait;
        this.urlRightPortrait = urlRightPortrait;
        this.categoryLeft = categoryLeft;
        this.categoryRight = categoryRight;
        this.sheetLayouts = sheetLayouts;
    }

    public System getSystem() {
        return system;
    }

    public int getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(int urlBase) {
        this.urlBase = urlBase;
    }

    public int getUrlBasePortrait() {
        return urlBasePortrait;
    }

    public void setUrlBasePortrait(int urlBasePortrait) {
        this.urlBasePortrait = urlBasePortrait;
    }

    public int getUrlLeft() {
        return urlLeft;
    }

    public void setUrlLeft(int urlLeft) {
        this.urlLeft = urlLeft;
    }

    public int getUrlRight() {
        return urlRight;
    }

    public void setUrlRight(int urlRight) {
        this.urlRight = urlRight;
    }

    public int getUrlLeftPortrait() {
        return urlLeftPortrait;
    }

    public void setUrlLeftPortrait(int urlLeftPortrait) {
        this.urlLeftPortrait = urlLeftPortrait;
    }

    public int getUrlRightPortrait() {
        return urlRightPortrait;
    }

    public void setUrlRightPortrait(int urlRightPortrait) {
        this.urlRightPortrait = urlRightPortrait;
    }

    public int getCategoryLeft() {
        return categoryLeft;
    }

    public void setCategoryLeft(int categoryLeft) {
        this.categoryLeft = categoryLeft;
    }

    public int getCategoryRight() {
        return categoryRight;
    }

    public void setCategoryRight(int categoryRight) {
        this.categoryRight = categoryRight;
    }

    public int[] getSheetLayouts() {
        return sheetLayouts;
    }

    public void setSheetLayouts(int[] sheetLayouts) {
        this.sheetLayouts = sheetLayouts;
    }
}
