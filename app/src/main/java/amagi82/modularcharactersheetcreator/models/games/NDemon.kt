package amagi82.modularcharactersheetcreator.models.games

import android.util.SparseArray

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.Splat

/*
    New World of Darkness
    Demon: The Descent
 */
class NDemon : Game() {

    init {
        this.setGameTitle(getString(R.string.nwod_demon))
        this.setLeftTitle(getString(R.string.incarnation))
        this.setRightTitle(getString(R.string.agenda))
        this.setGameUrl(getString(R.string.url_game_nwod_demon))
        this.setSplashUrl(getString(R.string.url_splash_nwod_demon))
        this.setGameColor(R.color.nwod_demon)
        this.setSplats(splats)
    }

    private val splats: SparseArray<Splat>
        get() {
            val splats = SparseArray<Splat>(8)

            splats.put(DESTROYER, splat(R.string.destroyer, R.string.url_nwod_demon_incarnation_destroyer))
            splats.put(GUARDIAN, splat(R.string.guardian, R.string.url_nwod_demon_incarnation_guardian))
            splats.put(MESSANGER, splat(R.string.messenger, R.string.url_nwod_demon_incarnation_messenger))
            splats.put(PSYCHOPOMP, splat(R.string.psychopomp, R.string.url_nwod_demon_incarnation_psychopomp))

            splats.put(INQUISITOR, splat(R.string.inquisitor, R.string.url_nwod_demon_agenda_inquisitor))
            splats.put(INTEGRATOR, splat(R.string.integrator, R.string.url_nwod_demon_agenda_integrator))
            splats.put(SABOTEUR, splat(R.string.saboteur, R.string.url_nwod_demon_agenda_saboteur))
            splats.put(TEMPTER, splat(R.string.tempter, R.string.url_nwod_demon_agenda_tempter))

            return splats
        }

    override fun getListLeft(splatId: Int): IntArray {
        return intArrayOf(DESTROYER, GUARDIAN, MESSANGER, PSYCHOPOMP)
    }

    override fun getListRight(splatId: Int): IntArray {
        return intArrayOf(INQUISITOR, INTEGRATOR, SABOTEUR, TEMPTER)
    }

    companion object {

        //Left
        private val DESTROYER = 1
        private val GUARDIAN = 2
        private val MESSANGER = 3
        private val PSYCHOPOMP = 4

        //Right
        private val INQUISITOR = 101
        private val INTEGRATOR = 102
        private val SABOTEUR = 103
        private val TEMPTER = 104
    }
}