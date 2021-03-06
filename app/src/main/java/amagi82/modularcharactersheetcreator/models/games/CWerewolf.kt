package amagi82.modularcharactersheetcreator.models.games

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.Splat
import android.util.SparseArray

/*
    Classic World of Darkness
    Werewolf: The Apocalypse
    20th Anniversary Edition
 */
class CWerewolf : Game() {
    override val gameTitle = getString(R.string.cwod_werewolf)
    override val leftTitle = getString(R.string.tribe)
    override val rightTitle = getString(R.string.auspice)
    override val gameUrl = getString(R.string.url_game_cwod_werewolf)
    override val splashUrl = getString(R.string.url_splash_cwod_werewolf)
    override val gameColor = R.color.cwod_werewolf
    override val isArchetypeLeft = true
    override val checkLeft = false
    override val isLeftListFinal = true
    override val isRightListFinal = true
    override val splats: SparseArray<Splat>
        get() {
            val splats = SparseArray<Splat>(21)

            splats.put(BLACK_FURIES, splat(R.string.black_furies, R.string.url_cwod_werewolf_tribe_black_furies))
            splats.put(BONE_GNAWERS, splat(R.string.bone_gnawers, R.string.url_cwod_werewolf_tribe_bone_gnawers))
            splats.put(BUNYIP, splat(R.string.bunyip, R.string.url_cwod_werewolf_tribe_bunyip))
            splats.put(CHILDREN_OF_GAIA, splat(R.string.children_of_gaia, R.string.url_cwod_werewolf_tribe_children_of_gaia))
            splats.put(CROATAN, splat(R.string.croatan, R.string.url_cwod_werewolf_tribe_croatan))
            splats.put(FIANNA, splat(R.string.fianna, R.string.url_cwod_werewolf_tribe_fianna))
            splats.put(GET_OF_FENRIS, splat(R.string.get_of_fenris, R.string.url_cwod_werewolf_tribe_get_of_fenris))
            splats.put(GLASS_WALKERS, splat(R.string.glass_walkers, R.string.url_cwod_werewolf_tribe_glass_walkers))
            splats.put(RED_TALONS, splat(R.string.red_talons, R.string.url_cwod_werewolf_tribe_red_talons))
            splats.put(SHADOW_LORDS, splat(R.string.shadow_lords, R.string.url_cwod_werewolf_tribe_shadow_lords))
            splats.put(SILENT_STRIDERS, splat(R.string.silent_striders, R.string.url_cwod_werewolf_tribe_silent_striders))
            splats.put(SILVER_FANGS, splat(R.string.silver_fangs, R.string.url_cwod_werewolf_tribe_silver_fangs))
            splats.put(STARGAZERS, splat(R.string.stargazers, R.string.url_cwod_werewolf_tribe_stargazers))
            splats.put(UKTENA, splat(R.string.uktena, R.string.url_cwod_werewolf_tribe_uktena))
            splats.put(WENDIGO, splat(R.string.wendigo, R.string.url_cwod_werewolf_tribe_wendigo))
            splats.put(WHITE_HOWLERS, splat(R.string.white_howlers, R.string.url_cwod_werewolf_tribe_white_howlers))

            splats.put(AHROUN, splat(R.string.ahroun, R.string.url_cwod_werewolf_auspice_ahroun))
            splats.put(GALLIARD, splat(R.string.galliard, R.string.url_cwod_werewolf_auspice_galliard))
            splats.put(PHILODOX, splat(R.string.philodox, R.string.url_cwod_werewolf_auspice_philodox))
            splats.put(RAGABASH, splat(R.string.ragabash, R.string.url_cwod_werewolf_auspice_ragabash))
            splats.put(THEURGE, splat(R.string.theurge, R.string.url_cwod_werewolf_auspice_theurge))

            return splats
        }

    override fun getListLeft(splatId: Int) = intArrayOf(BLACK_FURIES, BONE_GNAWERS, BUNYIP, CHILDREN_OF_GAIA, CROATAN, FIANNA, GET_OF_FENRIS, GLASS_WALKERS, RED_TALONS, SHADOW_LORDS, SILENT_STRIDERS, SILVER_FANGS, STARGAZERS, UKTENA, WENDIGO, WHITE_HOWLERS)

    override fun getListRight(splatId: Int) = intArrayOf(AHROUN, GALLIARD, PHILODOX, RAGABASH, THEURGE)

    companion object {

        //Left
        private val BLACK_FURIES = 1
        private val BONE_GNAWERS = 2
        private val BUNYIP = 3
        private val CHILDREN_OF_GAIA = 4
        private val CROATAN = 5
        private val FIANNA = 6
        private val GET_OF_FENRIS = 7
        private val GLASS_WALKERS = 8
        private val RED_TALONS = 9
        private val SHADOW_LORDS = 10
        private val SILENT_STRIDERS = 11
        private val SILVER_FANGS = 12
        private val STARGAZERS = 13
        private val UKTENA = 14
        private val WENDIGO = 15
        private val WHITE_HOWLERS = 16

        //Right
        private val AHROUN = 101
        private val GALLIARD = 102
        private val PHILODOX = 103
        private val RAGABASH = 104
        private val THEURGE = 105
    }
}
