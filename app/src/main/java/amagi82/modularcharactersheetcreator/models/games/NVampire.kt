package amagi82.modularcharactersheetcreator.models.games

import android.util.SparseArray

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.Splat

/*
    New World of Darkness
    Vampire: The Requiem
 */
class NVampire : Game() {

    init {
        this.setGameTitle(getString(R.string.nwod_vampire))
        this.setLeftTitle(getString(R.string.clan))
        this.setRightTitle(getString(R.string.covenant))
        this.setGameUrl(getString(R.string.url_game_nwod_vampire))
        this.setSplashUrl(getString(R.string.url_splash_nwod_vampire))
        this.setGameColor(R.color.nwod_vampire)
        this.setSplats(splats)
    }

    private val splats: SparseArray<Splat>
        get() {
            val splats = SparseArray<Splat>(12)

            splats.put(DAEVA, splat(R.string.daeva, R.string.url_nwod_vampire_clan_daeva))
            splats.put(GANGREL, splat(R.string.gangrel, R.string.url_nwod_vampire_clan_gangrel))
            splats.put(MEKHET, splat(R.string.mekhet, R.string.url_nwod_vampire_clan_mekhet))
            splats.put(NOSFERATU, splat(R.string.nosferatu, R.string.url_nwod_vampire_clan_nosferatu))
            splats.put(VENTRUE, splat(R.string.ventrue, R.string.url_nwod_vampire_clan_ventrue))

            splats.put(CARTHIAN_MOVEMENT, splat(R.string.carthian_movement, R.string.url_nwod_vampire_covenant_carthian_movement))
            splats.put(CIRCLE_OF_THE_CRONE, splat(R.string.circle_of_the_crone, R.string.url_nwod_vampire_covenant_circle_of_the_crone))
            splats.put(HOLY_ENGINEERS, splat(R.string.holy_engineers, R.string.url_nwod_vampire_covenant_holy_engineers))
            splats.put(INVICTUS, splat(R.string.invictus, R.string.url_nwod_vampire_covenant_invictus))
            splats.put(LANCEA_ET_SANCTUM, splat(R.string.lancea_et_sanctum, R.string.url_nwod_vampire_covenant_lancea_et_sanctum))
            splats.put(ORDO_DRACUL, splat(R.string.ordo_dracul, R.string.url_nwod_vampire_covenant_ordo_dracul))
            splats.put(UNALIGNED, splat(R.string.unaligned, R.string.url_nwod_vampire_covenant_unaligned))

            return splats
        }

    override fun getListLeft(splatId: Int): IntArray {
        return intArrayOf(DAEVA, GANGREL, MEKHET, NOSFERATU, VENTRUE)
    }

    override fun getListRight(splatId: Int): IntArray {
        return intArrayOf(CARTHIAN_MOVEMENT, CIRCLE_OF_THE_CRONE, HOLY_ENGINEERS, INVICTUS, LANCEA_ET_SANCTUM, ORDO_DRACUL, UNALIGNED)
    }

    companion object {

        //Left
        private val DAEVA = 1
        private val GANGREL = 2
        private val MEKHET = 3
        private val NOSFERATU = 4
        private val VENTRUE = 5

        //Right
        private val CARTHIAN_MOVEMENT = 101
        private val CIRCLE_OF_THE_CRONE = 102
        private val HOLY_ENGINEERS = 103
        private val INVICTUS = 104
        private val LANCEA_ET_SANCTUM = 105
        private val ORDO_DRACUL = 106
        private val UNALIGNED = 107
    }
}
