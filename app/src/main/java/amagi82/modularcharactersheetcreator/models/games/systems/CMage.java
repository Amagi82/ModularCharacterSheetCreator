package amagi82.modularcharactersheetcreator.models.games.systems;

import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Splat;

public class CMage extends GameSystem {

    @CMageFaction private int cMageFaction;

    public CMage() {
        super();
        this.gameTitle = R.string.cwod_mage;
        this.leftTitle = R.string.faction;
        this.isArchetypeLeft = false;
        this.gameUrl = R.string.url_game_cwod_mage;
        this.splashUrl = R.string.url_splash_cwod_mage;
        this.gameColor = R.color.cwod_mage;
    }

    @Override public int getRightTitle() {
        switch (cMageFaction){
            case TRADITIONS:
                return R.string.tradition;
            case TECHNOCRACY:
                return R.string.convention;
            case CRAFTS:
                return R.string.craft;
        }
        return R.string.faction;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(new Splat(R.string.traditions, R.string.url_cwod_mage_faction_traditions));
        list.add(new Splat(R.string.technocracy, R.string.url_cwod_mage_faction_technocracy));
        list.add(new Splat(R.string.crafts, R.string.url_cwod_mage_faction_crafts));
        return list;
    }

    @Nullable @Override public List<Splat> getListRight(Splat splat) {
        List<Splat> list = new ArrayList<>();
        switch (splat.getTitle()){
            case R.string.traditions:
                cMageFaction = TRADITIONS;
                list.add(new Splat(R.string.akashayana, R.string.url_cwod_mage_tradition_akashayana));
                list.add(new Splat(R.string.celestial_chorus, R.string.url_cwod_mage_tradition_celestial_chorus));
                list.add(new Splat(R.string.cult_of_ecstasy, R.string.url_cwod_mage_tradition_cult_of_ecstasy));
                list.add(new Splat(R.string.dreamspeakers, R.string.url_cwod_mage_tradition_dreamspeakers));
                list.add(new Splat(R.string.euthanatoi, R.string.url_cwod_mage_tradition_euthanatoi));
                list.add(new Splat(R.string.order_of_hermes, R.string.url_cwod_mage_tradition_order_of_hermes));
                list.add(new Splat(R.string.scions_of_ether, R.string.url_cwod_mage_tradition_scions_of_ether));
                list.add(new Splat(R.string.verbenae, R.string.url_cwod_mage_tradition_verbenae));
                list.add(new Splat(R.string.virtual_adepts, R.string.url_cwod_mage_tradition_virtual_adepts));
                break;
            case R.string.technocracy:
                cMageFaction = TECHNOCRACY;
                list.add(new Splat(R.string.iteration_x, R.string.url_cwod_mage_convention_iteration_x));
                list.add(new Splat(R.string.new_world_order, R.string.url_cwod_mage_convention_new_world_order));
                list.add(new Splat(R.string.progenitors, R.string.url_cwod_mage_convention_progenitors));
                list.add(new Splat(R.string.syndicate, R.string.url_cwod_mage_convention_syndicate));
                list.add(new Splat(R.string.void_engineers, R.string.url_cwod_mage_convention_void_engineers));
                break;
            case R.string.crafts:
                cMageFaction = CRAFTS;
                list.add(new Splat(R.string.ahl_i_batin, R.string.url_cwod_mage_craft_ahl_i_batin));
                list.add(new Splat(R.string.bataa, R.string.url_cwod_mage_craft_bataa));
                list.add(new Splat(R.string.children_of_knowledge, R.string.url_cwod_mage_craft_children_of_knowledge));
                list.add(new Splat(R.string.hollow_ones, R.string.url_cwod_mage_craft_hollow_ones));
                list.add(new Splat(R.string.knights_templar, R.string.url_cwod_mage_craft_knights_templar));
                list.add(new Splat(R.string.kopa_loei, R.string.url_cwod_mage_craft_kopa_loei));
                list.add(new Splat(R.string.ngoma, R.string.url_cwod_mage_craft_ngoma));
                list.add(new Splat(R.string.sisters_of_hippolyta, R.string.url_cwod_mage_craft_sisters_of_hippolyta));
                list.add(new Splat(R.string.taftani, R.string.url_cwod_mage_craft_taftani));
                list.add(new Splat(R.string.wu_lung, R.string.url_cwod_mage_craft_wu_lung));
                break;
        }
        return list;
    }

    @IntDef({TRADITIONS, TECHNOCRACY, CRAFTS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CMageFaction {}
    public static final int TRADITIONS = 0;
    public static final int TECHNOCRACY = 1;
    public static final int CRAFTS = 2;
}
