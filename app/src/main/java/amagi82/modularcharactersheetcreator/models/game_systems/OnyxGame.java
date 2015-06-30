package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public abstract class OnyxGame {

    /*
        - System enum includes the name and drawable for each game system, along with the base url,
        and whether or not it's part of the World of Darkness
        - Urls and titles are for the left and right image flanking the character portrait
    */
    public enum System{
        WOD(R.string.world_of_darkness, R.drawable.title_wod),
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

        System(int name, int imageMain){
            this(name, imageMain, 0, 0, false);
        }

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

        public int getCategoryCount(){
            return categoryTitles.length;
        }

        public int[] getCategoryTitles(){
            return categoryTitles;
        }
    }
    private System system;
    private String leftCategoryName;
    private String rightCategoryName;
    private int leftUrl;
    private int leftTitle;
    private int rightUrl;
    private int rightTitle;
    private int baseUrl;
    private int archetype;

    public abstract int getName(int position);

    public abstract int getUrl(int position);

    public abstract List getListLeft();

    public OnyxGame(System system) {
        setSystem(system);
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
        baseUrl = system.getUrlBase();
    }

    public void setLeft(String leftCategoryName, int leftTitle){
        this.setLeft(leftCategoryName, leftTitle, 0);
    }

    public void setLeft(String leftCategoryName, int leftTitle, int leftUrl){
        this.leftCategoryName = leftCategoryName;
        this.leftTitle = leftTitle;
        this.leftUrl = leftUrl;
    }

    public void setRight(String rightCategoryName, int rightTitle){
        this.setRight(rightCategoryName, rightTitle, 0);
    }

    public void setRight(String rightCategoryName, int rightTitle, int rightUrl){
        this.rightCategoryName = rightCategoryName;
        this.rightTitle = leftTitle;
        this.rightUrl = leftUrl;
    }

    public String getLeftCategoryName() {
        return leftCategoryName;
    }

    public String getRightCategoryName() {
        return rightCategoryName;
    }

    public int getLeftUrl() {
        return leftUrl;
    }

    public int getLeftTitle() {
        return leftTitle;
    }

    public int getRightUrl() {
        return rightUrl;
    }

    public int getRightTitle() {
        return rightTitle;
    }

    public int getBaseUrl() {
        return baseUrl;
    }

    public int getArchetype() {
        return archetype;
    }

    public void setArchetype(int archetype) {
        this.archetype = archetype;
    }

//    public static OnyxGame getGameSystem(String system, String leftCategoryName, String rightCategoryName){
//        switch (System.valueOf(system)){
//            case CWODMAGE: return new CMage(leftCategoryName);
//            case CWODVAMPIRE: return new CVampire(leftCategoryName, rightCategoryName);
//            case CWODWEREWOLF: return new CWerewolf(leftCategoryName,rightCategoryName);
//            case CWODWRAITH: return new CWraith(leftCategoryName);
//            case EXALTED: return new Exalted(leftCategoryName,rightCategoryName);
//            case NWODDEMON: return new NDemon(leftCategoryName,rightCategoryName);
//            case NWODMUMMY: return new NMummy(leftCategoryName,rightCategoryName);
//            case NWODVAMPIRE: return new NVampire(leftCategoryName, rightCategoryName);
//            case NWODWEREWOLF: return new NWerewolf(leftCategoryName, rightCategoryName);
//            case SCION: return new Scion(leftCategoryName, rightCategoryName);
//            case TRINITY: return new Trinity(leftCategoryName, rightCategoryName);
//            default: return null;
//        }
//    }
}
