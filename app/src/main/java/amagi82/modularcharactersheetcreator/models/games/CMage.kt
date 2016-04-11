package amagi82.modularcharactersheetcreator.models.games

import android.util.SparseArray

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.Splat

/*
    Classic World of Darkness
    Mage: The Ascension
    20th Anniversary Edition
 */
class CMage : Game() {

    init {
        this.setGameTitle(getString(R.string.cwod_mage))
        this.setLeftTitle(getString(R.string.faction))
        this.setIsArchetypeLeft(false)
        this.setGameUrl(getString(R.string.url_game_cwod_mage))
        this.setSplashUrl(getString(R.string.url_splash_cwod_mage))
        this.setGameColor(R.color.cwod_mage)
        this.setIsRightListFinal(false)
        this.setSplats(splats)
    }

    private val splats: SparseArray<Splat>
        get() {
            val splats = SparseArray<Splat>(27)

            splats.put(TRADITIONS, splat(R.string.traditions, R.string.url_cwod_mage_faction_traditions))
            splats.put(TECHNOCRACY, splat(R.string.technocracy, R.string.url_cwod_mage_faction_technocracy))
            splats.put(CRAFTS, splat(R.string.crafts, R.string.url_cwod_mage_faction_crafts))

            splats.put(AKASHAYANA, splat(R.string.akashayana, R.string.url_cwod_mage_tradition_akashayana))
            splats.put(CELESTIAL_CHORUS, splat(R.string.celestial_chorus, R.string.url_cwod_mage_tradition_celestial_chorus))
            splats.put(CULT_OF_ECSTASY, splat(R.string.cult_of_ecstasy, R.string.url_cwod_mage_tradition_cult_of_ecstasy))
            splats.put(DREAMSPEAKERS, splat(R.string.dreamspeakers, R.string.url_cwod_mage_tradition_dreamspeakers))
            splats.put(EUTHANOTOI, splat(R.string.euthanatoi, R.string.url_cwod_mage_tradition_euthanatoi))
            splats.put(ORDER_OF_HERMES, splat(R.string.order_of_hermes, R.string.url_cwod_mage_tradition_order_of_hermes))
            splats.put(SCIONS_OF_ETHER, splat(R.string.scions_of_ether, R.string.url_cwod_mage_tradition_scions_of_ether))
            splats.put(VERBENAE, splat(R.string.verbenae, R.string.url_cwod_mage_tradition_verbenae))
            splats.put(VIRTUAL_ADEPTS, splat(R.string.virtual_adepts, R.string.url_cwod_mage_tradition_virtual_adepts))

            splats.put(ITERATION_X, splat(R.string.iteration_x, R.string.url_cwod_mage_convention_iteration_x))
            splats.put(NEW_WORLD_ORDER, splat(R.string.new_world_order, R.string.url_cwod_mage_convention_new_world_order))
            splats.put(PROGENITORS, splat(R.string.progenitors, R.string.url_cwod_mage_convention_progenitors))
            splats.put(SYNDICATE, splat(R.string.syndicate, R.string.url_cwod_mage_convention_syndicate))
            splats.put(VOID_ENGINEERS, splat(R.string.void_engineers, R.string.url_cwod_mage_convention_void_engineers))

            splats.put(AHL_I_BATIN, splat(R.string.ahl_i_batin, R.string.url_cwod_mage_craft_ahl_i_batin))
            splats.put(BATAA, splat(R.string.bataa, R.string.url_cwod_mage_craft_bataa))
            splats.put(CHILDREN_OF_KNOWLEDGE, splat(R.string.children_of_knowledge, R.string.url_cwod_mage_craft_children_of_knowledge))
            splats.put(HOLLOW_ONES, splat(R.string.hollow_ones, R.string.url_cwod_mage_craft_hollow_ones))
            splats.put(KNIGHTS_TEMPLAR, splat(R.string.knights_templar, R.string.url_cwod_mage_craft_knights_templar))
            splats.put(KOPA_LOEI, splat(R.string.kopa_loei, R.string.url_cwod_mage_craft_kopa_loei))
            splats.put(NGOMA, splat(R.string.ngoma, R.string.url_cwod_mage_craft_ngoma))
            splats.put(SISTERS_OF_HIPPOLYTA, splat(R.string.sisters_of_hippolyta, R.string.url_cwod_mage_craft_sisters_of_hippolyta))
            splats.put(TAFTANI, splat(R.string.taftani, R.string.url_cwod_mage_craft_taftani))
            splats.put(WU_LUNG, splat(R.string.wu_lung, R.string.url_cwod_mage_craft_wu_lung))

            return splats
        }

    override fun getRightTitle(leftSplatId: Int): String {
        if (leftSplatId == TRADITIONS) return getString(R.string.tradition)
        if (leftSplatId == TECHNOCRACY) return getString(R.string.convention)
        if (leftSplatId == CRAFTS) return getString(R.string.craft)
        return getString(R.string.faction)
    }

    override fun getListLeft(splatId: Int): IntArray {
        return intArrayOf(TRADITIONS, TECHNOCRACY, CRAFTS)
    }

    override fun getListRight(splatId: Int): IntArray {
        if (splatId == TRADITIONS) return intArrayOf(AKASHAYANA, CELESTIAL_CHORUS, CULT_OF_ECSTASY, DREAMSPEAKERS, EUTHANOTOI, ORDER_OF_HERMES, SCIONS_OF_ETHER, VERBENAE, VIRTUAL_ADEPTS)
        if (splatId == TECHNOCRACY) return intArrayOf(ITERATION_X, NEW_WORLD_ORDER, PROGENITORS, SYNDICATE, VOID_ENGINEERS)
        if (splatId == CRAFTS) return intArrayOf(AHL_I_BATIN, BATAA, CHILDREN_OF_KNOWLEDGE, HOLLOW_ONES, KNIGHTS_TEMPLAR, KOPA_LOEI, NGOMA, SISTERS_OF_HIPPOLYTA, TAFTANI, WU_LUNG)
        return intArrayOf()
    }

    companion object {

        //Left
        private val TRADITIONS = 1
        private val TECHNOCRACY = 2
        private val CRAFTS = 3

        //Right
        private val AKASHAYANA = 101
        private val CELESTIAL_CHORUS = 102
        private val CULT_OF_ECSTASY = 103
        private val DREAMSPEAKERS = 104
        private val EUTHANOTOI = 105
        private val ORDER_OF_HERMES = 106
        private val SCIONS_OF_ETHER = 107
        private val VERBENAE = 108
        private val VIRTUAL_ADEPTS = 109

        private val ITERATION_X = 201
        private val NEW_WORLD_ORDER = 202
        private val PROGENITORS = 203
        private val SYNDICATE = 204
        private val VOID_ENGINEERS = 205

        private val AHL_I_BATIN = 301
        private val BATAA = 302
        private val CHILDREN_OF_KNOWLEDGE = 303
        private val HOLLOW_ONES = 304
        private val KNIGHTS_TEMPLAR = 305
        private val KOPA_LOEI = 306
        private val NGOMA = 307
        private val SISTERS_OF_HIPPOLYTA = 308
        private val TAFTANI = 309
        private val WU_LUNG = 310
    }
}
