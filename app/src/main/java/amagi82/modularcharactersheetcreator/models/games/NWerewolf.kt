package amagi82.modularcharactersheetcreator.models.games

import android.util.SparseArray

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.Splat

/*
    New World of Darkness
    Werewolf: The Forsaken
 */
class NWerewolf : Game() {

    init {
        this.setGameTitle(getString(R.string.nwod_werewolf))
        this.setLeftTitle(getString(R.string.tribe))
        this.setRightTitle(getString(R.string.auspice))
        this.setGameUrl(getString(R.string.url_game_nwod_werewolf))
        this.setSplashUrl(getString(R.string.url_splash_nwod_werewolf))
        this.setGameColor(R.color.nwod_werewolf)
        this.setSplats(splats)
    }

    private val splats: SparseArray<Splat>
        get() {
            val splats = SparseArray<Splat>(11)

            splats.put(BLOOD_TALONS, splat(R.string.blood_talons, R.string.url_nwod_werewolf_tribe_blood_talons))
            splats.put(BONE_SHADOWS, splat(R.string.bone_shadows, R.string.url_nwod_werewolf_tribe_bone_shadows))
            splats.put(GHOST_WOLVES, splat(R.string.ghost_wolves, R.string.url_nwod_werewolf_tribe_ghost_wolves))
            splats.put(HUNTERS_IN_DARKNESS, splat(R.string.hunters_in_darkness, R.string.url_nwod_werewolf_tribe_hunters_in_darkness))
            splats.put(IRON_MASTERS, splat(R.string.iron_masters, R.string.url_nwod_werewolf_tribe_iron_masters))
            splats.put(STORM_LORDS, splat(R.string.storm_lords, R.string.url_nwod_werewolf_tribe_storm_lords))

            splats.put(CAHALITH, splat(R.string.cahalith, R.string.url_nwod_werewolf_auspice_cahalith))
            splats.put(ELODOTH, splat(R.string.elodoth, R.string.url_nwod_werewolf_auspice_elodoth))
            splats.put(IRRAKA, splat(R.string.irraka, R.string.url_nwod_werewolf_auspice_irraka))
            splats.put(ITHAEUR, splat(R.string.ithaeur, R.string.url_nwod_werewolf_auspice_ithaeur))
            splats.put(RAHU, splat(R.string.rahu, R.string.url_nwod_werewolf_auspice_rahu))

            return splats
        }

    override fun getListLeft(splatId: Int): IntArray {
        return intArrayOf(BLOOD_TALONS, BONE_SHADOWS, GHOST_WOLVES, HUNTERS_IN_DARKNESS, IRON_MASTERS, STORM_LORDS)
    }

    override fun getListRight(splatId: Int): IntArray {
        return intArrayOf(CAHALITH, ELODOTH, IRRAKA, ITHAEUR, RAHU)
    }

    companion object {

        //Left
        private val BLOOD_TALONS = 1
        private val BONE_SHADOWS = 2
        private val GHOST_WOLVES = 3
        private val HUNTERS_IN_DARKNESS = 4
        private val IRON_MASTERS = 5
        private val STORM_LORDS = 6

        //Right
        private val CAHALITH = 101
        private val ELODOTH = 102
        private val IRRAKA = 103
        private val ITHAEUR = 104
        private val RAHU = 105
    }
}
