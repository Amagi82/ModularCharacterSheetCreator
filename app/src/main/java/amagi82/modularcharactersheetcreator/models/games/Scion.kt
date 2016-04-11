package amagi82.modularcharactersheetcreator.models.games

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.Splat
import android.util.SparseArray

/*
    Scion 2nd Edition: Origins
    Includes Hero, Demigod, and God
 */
class Scion : Game() {
    override val gameTitle = getString(R.string.scion)
    override val leftTitle = getString(R.string.volume)
    override val rightTitle = getString(R.string.pantheon)
    override val gameUrl = getString(R.string.url_game_scion)
    override val splashUrl = getString(R.string.url_splash_scion)
    override val gameColor = R.color.scion
    override val isArchetypeLeft = false
    override val checkLeft = false
    override val isLeftListFinal = true
    override val isRightListFinal = true
    override val splats: SparseArray<Splat>
        get() {
            val splats = SparseArray<Splat>(13)

            splats.put(HERO, splat(R.string.hero))
            splats.put(DEMIGOD, splat(R.string.demigod))
            splats.put(GOD, splat(R.string.god))

            splats.put(PESEDJET, splat(R.string.pesedjet))
            splats.put(DODEKATHEON, splat(R.string.dodekatheon))
            splats.put(AESIR, splat(R.string.aesir))
            splats.put(ATZLANTI, splat(R.string.atzlanti))
            splats.put(AMATSUKAMI, splat(R.string.amatsukami))
            splats.put(LOA, splat(R.string.loa))
            splats.put(TUATHA_DE_DADANN, splat(R.string.tuatha_de_dadann))
            splats.put(CELESTIAL_BUREAUCRACY, splat(R.string.celestial_bureaucracy))
            splats.put(DEVA, splat(R.string.deva))
            splats.put(YAZATA, splat(R.string.yazata))

            return splats
        }

    override fun getListLeft(splatId: Int) = intArrayOf(HERO, DEMIGOD, GOD)

    override fun getListRight(splatId: Int) = intArrayOf(PESEDJET, DODEKATHEON, AESIR, ATZLANTI, AMATSUKAMI, LOA, TUATHA_DE_DADANN, CELESTIAL_BUREAUCRACY, DEVA, YAZATA)

    companion object {

        //Left
        private val HERO = 1
        private val DEMIGOD = 2
        private val GOD = 3

        //Right
        private val PESEDJET = 101
        private val DODEKATHEON = 102
        private val AESIR = 103
        private val ATZLANTI = 104
        private val AMATSUKAMI = 105
        private val LOA = 106
        private val TUATHA_DE_DADANN = 107
        private val CELESTIAL_BUREAUCRACY = 108
        private val DEVA = 109
        private val YAZATA = 110
    }
}
