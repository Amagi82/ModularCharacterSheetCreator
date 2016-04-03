package amagi82.modularcharactersheetcreator.models.games;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Splat;

/*
    Classic World of Darkness
    Mage: The Ascension
    20th Anniversary Edition
 */
public class CMage extends Game {

    public CMage() {
        super();
        this.setGameTitle(getString(R.string.cwod_mage));
        this.setLeftTitle(getString(R.string.faction));
        this.setIsArchetypeLeft(false);
        this.setGameUrl(getString(R.string.url_game_cwod_mage));
        this.setSplashUrl(getString(R.string.url_splash_cwod_mage));
        this.setGameColor(R.color.cwod_mage);
        this.setIsRightListFinal(false);
        this.setSplats(getSplats());
    }

    private SparseArray<Splat> getSplats(){
        SparseArray<Splat> splats = new SparseArray<>(27);

        splats.put(TRADITIONS, splat(R.string.traditions, R.string.url_cwod_mage_faction_traditions));
        splats.put(TECHNOCRACY, splat(R.string.technocracy, R.string.url_cwod_mage_faction_technocracy));
        splats.put(CRAFTS, splat(R.string.crafts, R.string.url_cwod_mage_faction_crafts));

        splats.put(AKASHAYANA, splat(R.string.akashayana, R.string.url_cwod_mage_tradition_akashayana));
        splats.put(CELESTIAL_CHORUS, splat(R.string.celestial_chorus, R.string.url_cwod_mage_tradition_celestial_chorus));
        splats.put(CULT_OF_ECSTASY, splat(R.string.cult_of_ecstasy, R.string.url_cwod_mage_tradition_cult_of_ecstasy));
        splats.put(DREAMSPEAKERS, splat(R.string.dreamspeakers, R.string.url_cwod_mage_tradition_dreamspeakers));
        splats.put(EUTHANOTOI, splat(R.string.euthanatoi, R.string.url_cwod_mage_tradition_euthanatoi));
        splats.put(ORDER_OF_HERMES, splat(R.string.order_of_hermes, R.string.url_cwod_mage_tradition_order_of_hermes));
        splats.put(SCIONS_OF_ETHER, splat(R.string.scions_of_ether, R.string.url_cwod_mage_tradition_scions_of_ether));
        splats.put(VERBENAE, splat(R.string.verbenae, R.string.url_cwod_mage_tradition_verbenae));
        splats.put(VIRTUAL_ADEPTS, splat(R.string.virtual_adepts, R.string.url_cwod_mage_tradition_virtual_adepts));

        splats.put(ITERATION_X, splat(R.string.iteration_x, R.string.url_cwod_mage_convention_iteration_x));
        splats.put(NEW_WORLD_ORDER, splat(R.string.new_world_order, R.string.url_cwod_mage_convention_new_world_order));
        splats.put(PROGENITORS, splat(R.string.progenitors, R.string.url_cwod_mage_convention_progenitors));
        splats.put(SYNDICATE, splat(R.string.syndicate, R.string.url_cwod_mage_convention_syndicate));
        splats.put(VOID_ENGINEERS, splat(R.string.void_engineers, R.string.url_cwod_mage_convention_void_engineers));

        splats.put(AHL_I_BATIN, splat(R.string.ahl_i_batin, R.string.url_cwod_mage_craft_ahl_i_batin));
        splats.put(BATAA, splat(R.string.bataa, R.string.url_cwod_mage_craft_bataa));
        splats.put(CHILDREN_OF_KNOWLEDGE, splat(R.string.children_of_knowledge, R.string.url_cwod_mage_craft_children_of_knowledge));
        splats.put(HOLLOW_ONES, splat(R.string.hollow_ones, R.string.url_cwod_mage_craft_hollow_ones));
        splats.put(KNIGHTS_TEMPLAR, splat(R.string.knights_templar, R.string.url_cwod_mage_craft_knights_templar));
        splats.put(KOPA_LOEI, splat(R.string.kopa_loei, R.string.url_cwod_mage_craft_kopa_loei));
        splats.put(NGOMA, splat(R.string.ngoma, R.string.url_cwod_mage_craft_ngoma));
        splats.put(SISTERS_OF_HIPPOLYTA, splat(R.string.sisters_of_hippolyta, R.string.url_cwod_mage_craft_sisters_of_hippolyta));
        splats.put(TAFTANI, splat(R.string.taftani, R.string.url_cwod_mage_craft_taftani));
        splats.put(WU_LUNG, splat(R.string.wu_lung, R.string.url_cwod_mage_craft_wu_lung));

        return splats;
    }

    @Override public String getRightTitle(int leftSplatId) {
        if(leftSplatId == TRADITIONS) return getString(R.string.tradition);
        if(leftSplatId == TECHNOCRACY) return getString(R.string.convention);
        if(leftSplatId == CRAFTS) return getString(R.string.craft);
        return getString(R.string.faction);
    }

    @NonNull @Override public int[] getListLeft(int splatId) {
        return new int[]{TRADITIONS, TECHNOCRACY, CRAFTS};
    }

    @NonNull @Override public int[] getListRight(int splatId) {
        if(splatId == TRADITIONS) return new int[]{AKASHAYANA, CELESTIAL_CHORUS, CULT_OF_ECSTASY, DREAMSPEAKERS, EUTHANOTOI, ORDER_OF_HERMES, SCIONS_OF_ETHER, VERBENAE, VIRTUAL_ADEPTS};
        if(splatId == TECHNOCRACY) return new int[]{ITERATION_X, NEW_WORLD_ORDER, PROGENITORS, SYNDICATE, VOID_ENGINEERS};
        if(splatId == CRAFTS) return new int[]{AHL_I_BATIN, BATAA, CHILDREN_OF_KNOWLEDGE, HOLLOW_ONES, KNIGHTS_TEMPLAR, KOPA_LOEI, NGOMA, SISTERS_OF_HIPPOLYTA, TAFTANI, WU_LUNG};
        return new int[]{};
    }

    //Left
    private static final int TRADITIONS = 1;
    private static final int TECHNOCRACY = 2;
    private static final int CRAFTS = 3;

    //Right
    private static final int AKASHAYANA = 101;
    private static final int CELESTIAL_CHORUS = 102;
    private static final int CULT_OF_ECSTASY = 103;
    private static final int DREAMSPEAKERS = 104;
    private static final int EUTHANOTOI = 105;
    private static final int ORDER_OF_HERMES = 106;
    private static final int SCIONS_OF_ETHER = 107;
    private static final int VERBENAE = 108;
    private static final int VIRTUAL_ADEPTS = 109;

    private static final int ITERATION_X = 201;
    private static final int NEW_WORLD_ORDER = 202;
    private static final int PROGENITORS = 203;
    private static final int SYNDICATE = 204;
    private static final int VOID_ENGINEERS = 205;

    private static final int AHL_I_BATIN = 301;
    private static final int BATAA = 302;
    private static final int CHILDREN_OF_KNOWLEDGE = 303;
    private static final int HOLLOW_ONES = 304;
    private static final int KNIGHTS_TEMPLAR = 305;
    private static final int KOPA_LOEI = 306;
    private static final int NGOMA = 307;
    private static final int SISTERS_OF_HIPPOLYTA = 308;
    private static final int TAFTANI = 309;
    private static final int WU_LUNG = 310;
}
