package amagi82.modularcharactersheetcreator.models.games

import android.util.SparseArray

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.Splat

/*
    Classic World of Darkness
    Wraith: The Oblivion
    20th Anniversary Edition
 */
class CWraith : Game() {

    init {
        this.setGameTitle(getString(R.string.cwod_wraith))
        this.setLeftTitle(getString(R.string.legion))
        this.setRightTitle(getString(R.string.guild))
        this.setGameUrl(getString(R.string.url_game_cwod_wraith))
        this.setSplashUrl(getString(R.string.url_splash_cwod_wraith))
        this.setGameColor(R.color.cwod_wraith)
        this.setIsRightListFinal(false)
        this.setSplats(splats)
    }

    private val splats: SparseArray<Splat>
        get() {
            val splats = SparseArray<Splat>(29)

            splats.put(IRON_LEGION, splat(R.string.iron_legion))
            splats.put(SKELETAL_LEGION, splat(R.string.skeletal_legion))
            splats.put(GRIM_LEGION, splat(R.string.grim_legion))
            splats.put(PENITENT_LEGION, splat(R.string.penitent_legion))
            splats.put(EMERALD_LEGION, splat(R.string.emerald_legion))
            splats.put(SILENT_LEGION, splat(R.string.silent_legion))
            splats.put(LEGION_OF_PAUPERS, splat(R.string.legion_of_paupers))
            splats.put(LEGION_OF_FATE, splat(R.string.legion_of_fate))

            splats.put(NONE, splat(R.string.none))
            splats.put(GREAT_GUILDS, splat(R.string.great_guilds, false))
            splats.put(WORKING_GUILDS, splat(R.string.working_guilds, false))
            splats.put(CRIMINAL_GUILDS, splat(R.string.criminal_guilds, false))
            splats.put(FORBIDDEN_GUILDS, splat(R.string.forbidden_guilds, false))

            splats.put(ARTIFICERS, splat(R.string.artificers))
            splats.put(MASQUERS, splat(R.string.masquers))
            splats.put(PARDONERS, splat(R.string.pardoners))
            splats.put(USURERS, splat(R.string.usurers))

            splats.put(CHANTEURS, splat(R.string.chanteurs))
            splats.put(HARBINGERS, splat(R.string.harbingers))
            splats.put(ORACLES, splat(R.string.oracles))
            splats.put(SANDMEN, splat(R.string.sandmen))

            splats.put(HAUNTERS, splat(R.string.haunters))
            splats.put(MONITORS, splat(R.string.monitors))
            splats.put(SPOOKS, splat(R.string.spooks))
            splats.put(PROCTORS, splat(R.string.proctors))
            splats.put(PUPPETEERS, splat(R.string.puppeteers))

            splats.put(ALCHEMISTS, splat(R.string.alchemists))
            splats.put(MNEMOI, splat(R.string.mnemoi))
            splats.put(SOLICITORS, splat(R.string.solicitors))

            return splats
        }

    override fun getListLeft(splatId: Int): IntArray {
        return intArrayOf(IRON_LEGION, SKELETAL_LEGION, GRIM_LEGION, PENITENT_LEGION, EMERALD_LEGION, SILENT_LEGION, LEGION_OF_PAUPERS, LEGION_OF_FATE)
    }

    override fun getListRight(splatId: Int): IntArray {
        if (splatId == GREAT_GUILDS) return intArrayOf(ARTIFICERS, MASQUERS, PARDONERS, USURERS)
        if (splatId == WORKING_GUILDS) return intArrayOf(CHANTEURS, HARBINGERS, ORACLES, SANDMEN)
        if (splatId == CRIMINAL_GUILDS) return intArrayOf(HAUNTERS, MONITORS, SPOOKS, PROCTORS, PUPPETEERS)
        if (splatId == FORBIDDEN_GUILDS) return intArrayOf(ALCHEMISTS, MNEMOI, SOLICITORS)
        return intArrayOf(NONE, GREAT_GUILDS, WORKING_GUILDS, CRIMINAL_GUILDS, FORBIDDEN_GUILDS)

    }

    companion object {

        //Left
        private val IRON_LEGION = 1
        private val SKELETAL_LEGION = 2
        private val GRIM_LEGION = 3
        private val PENITENT_LEGION = 4
        private val EMERALD_LEGION = 5
        private val SILENT_LEGION = 6
        private val LEGION_OF_PAUPERS = 7
        private val LEGION_OF_FATE = 8

        //Right
        private val NONE = 100
        private val GREAT_GUILDS = 200
        private val WORKING_GUILDS = 300
        private val CRIMINAL_GUILDS = 400
        private val FORBIDDEN_GUILDS = 500

        private val ARTIFICERS = 201
        private val MASQUERS = 202
        private val PARDONERS = 203
        private val USURERS = 204

        private val CHANTEURS = 301
        private val HARBINGERS = 302
        private val ORACLES = 303
        private val SANDMEN = 304

        private val HAUNTERS = 401
        private val MONITORS = 402
        private val SPOOKS = 403
        private val PROCTORS = 404
        private val PUPPETEERS = 405

        private val ALCHEMISTS = 501
        private val MNEMOI = 502
        private val SOLICITORS = 503
    }
}
