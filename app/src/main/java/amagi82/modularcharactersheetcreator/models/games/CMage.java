package amagi82.modularcharactersheetcreator.models.games;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.Splat;

public class CMage extends GameSystem {

    public CMage() {
        super();
        this.gameTitle = R.string.cwod_mage;
        this.leftTitle = R.string.faction;
        this.isArchetypeLeft = false;
        this.gameUrl = R.string.url_game_cwod_mage;
        this.splashUrl = R.string.url_splash_cwod_mage;
        this.gameColor = R.color.cwod_mage;
    }

    @Override public int getRightTitle(Splat leftSplat) {
        switch (leftSplat.title()){
            case R.string.traditions:
                return R.string.tradition;
            case R.string.technocracy:
                return R.string.convention;
            case R.string.crafts:
                return R.string.craft;
        }
        return R.string.faction;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(3);
        list.add(Splat.create(R.string.traditions, R.string.url_cwod_mage_faction_traditions));
        list.add(Splat.create(R.string.technocracy, R.string.url_cwod_mage_faction_technocracy));
        list.add(Splat.create(R.string.crafts, R.string.url_cwod_mage_faction_crafts));
        return list;
    }

    @Override public List<Splat> getListRight(@NonNull Splat splat) {
        List<Splat> list = new ArrayList<>(10);
        switch (splat.title()){
            case R.string.traditions:
                list.add(Splat.create(R.string.akashayana, R.string.url_cwod_mage_tradition_akashayana));
                list.add(Splat.create(R.string.celestial_chorus, R.string.url_cwod_mage_tradition_celestial_chorus));
                list.add(Splat.create(R.string.cult_of_ecstasy, R.string.url_cwod_mage_tradition_cult_of_ecstasy));
                list.add(Splat.create(R.string.dreamspeakers, R.string.url_cwod_mage_tradition_dreamspeakers));
                list.add(Splat.create(R.string.euthanatoi, R.string.url_cwod_mage_tradition_euthanatoi));
                list.add(Splat.create(R.string.order_of_hermes, R.string.url_cwod_mage_tradition_order_of_hermes));
                list.add(Splat.create(R.string.scions_of_ether, R.string.url_cwod_mage_tradition_scions_of_ether));
                list.add(Splat.create(R.string.verbenae, R.string.url_cwod_mage_tradition_verbenae));
                list.add(Splat.create(R.string.virtual_adepts, R.string.url_cwod_mage_tradition_virtual_adepts));
                break;
            case R.string.technocracy:
                list.add(Splat.create(R.string.iteration_x, R.string.url_cwod_mage_convention_iteration_x));
                list.add(Splat.create(R.string.new_world_order, R.string.url_cwod_mage_convention_new_world_order));
                list.add(Splat.create(R.string.progenitors, R.string.url_cwod_mage_convention_progenitors));
                list.add(Splat.create(R.string.syndicate, R.string.url_cwod_mage_convention_syndicate));
                list.add(Splat.create(R.string.void_engineers, R.string.url_cwod_mage_convention_void_engineers));
                break;
            case R.string.crafts:
                list.add(Splat.create(R.string.ahl_i_batin, R.string.url_cwod_mage_craft_ahl_i_batin));
                list.add(Splat.create(R.string.bataa, R.string.url_cwod_mage_craft_bataa));
                list.add(Splat.create(R.string.children_of_knowledge, R.string.url_cwod_mage_craft_children_of_knowledge));
                list.add(Splat.create(R.string.hollow_ones, R.string.url_cwod_mage_craft_hollow_ones));
                list.add(Splat.create(R.string.knights_templar, R.string.url_cwod_mage_craft_knights_templar));
                list.add(Splat.create(R.string.kopa_loei, R.string.url_cwod_mage_craft_kopa_loei));
                list.add(Splat.create(R.string.ngoma, R.string.url_cwod_mage_craft_ngoma));
                list.add(Splat.create(R.string.sisters_of_hippolyta, R.string.url_cwod_mage_craft_sisters_of_hippolyta));
                list.add(Splat.create(R.string.taftani, R.string.url_cwod_mage_craft_taftani));
                list.add(Splat.create(R.string.wu_lung, R.string.url_cwod_mage_craft_wu_lung));
                break;
        }
        return list;
    }
}
