package amagi82.modularcharactersheetcreator.models.games

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.Splat
import android.util.SparseArray

/*
    Exalted 2nd Edition
    (3rd Edition will be included once available)
 */
class Exalted : Game() {
    override val gameTitle = getString(R.string.exalted)
    override val leftTitle = getString(R.string.exalt)
    override val rightTitle = getString(R.string.caste)
    override val gameUrl = getString(R.string.url_game_exalted)
    override val splashUrl = getString(R.string.url_splash_exalted)
    override val gameColor = R.color.exalted
    override val isArchetypeLeft = true
    override val checkLeft = false
    override val isLeftListFinal = true
    override val isRightListFinal = false
    override val splats: SparseArray<Splat>
        get() {
            val splats = SparseArray<Splat>(41)

            splats.put(SOLAR, splat(R.string.solar_exalted))
            splats.put(ABYSSAL, splat(R.string.abyssal_exalted))
            splats.put(LUNAR, splat(R.string.lunar_exalted))
            splats.put(SIDEREAL, splat(R.string.sidereal_exalted))
            splats.put(TERRESTRIAL, splat(R.string.terrestrial_exalted))
            splats.put(ALCHEMICAL, splat(R.string.alchemical_exalted))
            splats.put(INFERNAL, splat(R.string.infernal_exalted))

            splats.put(DAWN, splat(R.string.dawn))
            splats.put(ZENITH, splat(R.string.zenith))
            splats.put(TWILIGHT, splat(R.string.twilight))
            splats.put(NIGHT, splat(R.string.night))
            splats.put(ECLIPSE, splat(R.string.eclipse))

            splats.put(DUSK, splat(R.string.dusk))
            splats.put(MIDNIGHT, splat(R.string.midnight))
            splats.put(DAYBREAK, splat(R.string.daybreak))
            splats.put(DAY, splat(R.string.day))
            splats.put(MOONSHADOW, splat(R.string.moonshadow))

            splats.put(FULL_MOON, splat(R.string.full_moon))
            splats.put(CHANGING_MOON, splat(R.string.changing_moon))
            splats.put(NO_MOON, splat(R.string.no_moon))
            splats.put(CASTELESS, splat(R.string.casteless))

            splats.put(AIR, splat(R.string.air))
            splats.put(EARTH, splat(R.string.earth))
            splats.put(FIRE, splat(R.string.fire))
            splats.put(WATER, splat(R.string.water))
            splats.put(WOOD, splat(R.string.wood))

            splats.put(CHOSEN_OF_JOURNEYS, splat(R.string.chosen_of_journeys))
            splats.put(CHOSEN_OF_SERENITY, splat(R.string.chosen_of_serenity))
            splats.put(CHOSEN_OF_BATTLES, splat(R.string.chosen_of_battles))
            splats.put(CHOSEN_OF_SECRETS, splat(R.string.chosen_of_secrets))
            splats.put(CHOSEN_OF_ENDINGS, splat(R.string.chosen_of_endings))

            splats.put(ORICHALCUM, splat(R.string.orichalcum))
            splats.put(MOONSILVER, splat(R.string.moonsilver))
            splats.put(STARMETAL, splat(R.string.starmetal))
            splats.put(JADE, splat(R.string.jade))
            splats.put(SOULSTEEL, splat(R.string.soulsteel))

            splats.put(SLAYER, splat(R.string.slayer))
            splats.put(MALEFACTOR, splat(R.string.malefactor))
            splats.put(DEFILER, splat(R.string.defiler))
            splats.put(SCOURGE, splat(R.string.scrourge))
            splats.put(FIEND, splat(R.string.fiend))

            return splats
        }

    override fun getRightTitle(leftSplatId: Int) = if (leftSplatId == TERRESTRIAL) getString(R.string.aspect) else rightTitle

    override fun getListLeft(splatId: Int) = intArrayOf(SOLAR, ABYSSAL, LUNAR, SIDEREAL, TERRESTRIAL, ALCHEMICAL, INFERNAL)

    override fun getListRight(splatId: Int) = when (splatId) {
        SOLAR -> intArrayOf(DAWN, ZENITH, TWILIGHT, NIGHT, ECLIPSE)
        ABYSSAL -> intArrayOf(DUSK, MIDNIGHT, DAYBREAK, DAY, MOONSHADOW)
        LUNAR -> intArrayOf(FULL_MOON, CHANGING_MOON, NO_MOON, CASTELESS)
        SIDEREAL -> intArrayOf(AIR, EARTH, FIRE, WATER, WOOD)
        TERRESTRIAL -> intArrayOf(CHOSEN_OF_JOURNEYS, CHOSEN_OF_SERENITY, CHOSEN_OF_BATTLES, CHOSEN_OF_SECRETS, CHOSEN_OF_ENDINGS)
        ALCHEMICAL -> intArrayOf(ORICHALCUM, MOONSILVER, STARMETAL, JADE, SOULSTEEL)
        INFERNAL -> intArrayOf(SLAYER, MALEFACTOR, DEFILER, SCOURGE, FIEND)
        else -> intArrayOf()
    }

    companion object {

        //Left
        private val SOLAR = 1
        private val ABYSSAL = 2
        private val LUNAR = 3
        private val SIDEREAL = 4
        private val TERRESTRIAL = 5
        private val ALCHEMICAL = 6
        private val INFERNAL = 7

        //Right
        private val DAWN = 101
        private val ZENITH = 102
        private val TWILIGHT = 103
        private val NIGHT = 104
        private val ECLIPSE = 105

        private val DUSK = 201
        private val MIDNIGHT = 202
        private val DAYBREAK = 203
        private val DAY = 204
        private val MOONSHADOW = 205

        private val FULL_MOON = 301
        private val CHANGING_MOON = 302
        private val NO_MOON = 303
        private val CASTELESS = 304

        private val AIR = 401
        private val EARTH = 402
        private val FIRE = 403
        private val WATER = 404
        private val WOOD = 405

        private val CHOSEN_OF_JOURNEYS = 501
        private val CHOSEN_OF_SERENITY = 502
        private val CHOSEN_OF_BATTLES = 503
        private val CHOSEN_OF_SECRETS = 504
        private val CHOSEN_OF_ENDINGS = 505

        private val ORICHALCUM = 601
        private val MOONSILVER = 602
        private val STARMETAL = 603
        private val JADE = 604
        private val SOULSTEEL = 605

        private val SLAYER = 701
        private val MALEFACTOR = 702
        private val DEFILER = 703
        private val SCOURGE = 704
        private val FIEND = 705
    }
}