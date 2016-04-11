package amagi82.modularcharactersheetcreator.models.games

import android.util.SparseArray

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.Splat

/*
    Trinity Continuum
    Includes Aeon, Aberrant, and Adventure
 */
class Trinity : Game() {

    init {
        this.setGameTitle(getString(R.string.trinity))
        this.setLeftTitle(getString(R.string.age))
        this.setIsArchetypeLeft(false)
        this.setGameUrl(getString(R.string.url_game_trinity))
        this.setSplashUrl(getString(R.string.url_splash_trinity))
        this.setGameColor(R.color.trinity)
        this.setIsRightListFinal(false)
        this.setSplats(splats)
    }

    private val splats: SparseArray<Splat>
        get() {
            val splats = SparseArray<Splat>(26)

            splats.put(ADVENTURE, splat(R.string.adventure))
            splats.put(ABERRANT, splat(R.string.aberrant))
            splats.put(AEON, splat(R.string.aeon))

            splats.put(THE_AEON_SOCIETY_FOR_GENTLEMEN, splat(R.string.the_aeon_society_for_gentlemen))
            splats.put(THE_AIR_CIRCUS, splat(R.string.the_air_circus))
            splats.put(BRANCH_9, splat(R.string.branch_9))
            splats.put(THE_INTERNATIONAL_DETECTIVE_AGENCY, splat(R.string.the_international_detective_agency))
            splats.put(THE_PONATOWSKI_FOUNDATION, splat(R.string.the_ponatowski_foundation))

            splats.put(ABERRANTS, splat(R.string.aberrants))
            splats.put(PROJECT_UTOPIA, splat(R.string.project_utopia))
            splats.put(TEAM_TOMORROW, splat(R.string.team_tomorrow))
            splats.put(PROJECT_PROTEUS, splat(R.string.project_proteus))
            splats.put(THE_TERAGEN, splat(R.string.the_teragen))
            splats.put(THE_DIRECTIVE, splat(R.string.the_directive))
            splats.put(CORPORATE, splat(R.string.corporate))
            splats.put(GOVERNMENT, splat(R.string.government))
            splats.put(OTHER, splat(R.string.other))
            splats.put(INDEPENDENT, splat(R.string.independent))

            splats.put(AESCULAPIAN_ORDER, splat(R.string.aesculapian_order))
            splats.put(CHITRA_BHANU, splat(R.string.chitra_bhanu))
            splats.put(ISRA, splat(R.string.isra))
            splats.put(THE_LEGIONS, splat(R.string.the_legions))
            splats.put(THE_MINISTRY_OF_NOETIC_AFFAIRS, splat(R.string.the_ministry_of_noetic_affairs))
            splats.put(NOVA_FORCA_DAS_NACOES, splat(R.string.nova_forca_das_nacoes))
            splats.put(ORGOTEK, splat(R.string.orgotek))
            splats.put(THE_UPEO_WA_MACHO, splat(R.string.the_upeo_wa_macho))

            return splats
        }

    override fun getRightTitle(leftSplatId: Int): String {
        if (leftSplatId == AEON) return getString(R.string.psi_order)
        return getString(R.string.allegiance)
    }

    override fun getListLeft(splatId: Int): IntArray {
        return intArrayOf(ADVENTURE, ABERRANT, AEON)
    }

    override fun getListRight(splatId: Int): IntArray {
        if (splatId == ADVENTURE) return intArrayOf(THE_AEON_SOCIETY_FOR_GENTLEMEN, THE_AIR_CIRCUS, BRANCH_9, THE_INTERNATIONAL_DETECTIVE_AGENCY, THE_PONATOWSKI_FOUNDATION)
        if (splatId == ABERRANT) return intArrayOf(ABERRANTS, PROJECT_UTOPIA, TEAM_TOMORROW, PROJECT_PROTEUS, THE_TERAGEN, THE_DIRECTIVE, CORPORATE, GOVERNMENT, OTHER, INDEPENDENT)
        return intArrayOf(AESCULAPIAN_ORDER, CHITRA_BHANU, ISRA, THE_LEGIONS, THE_MINISTRY_OF_NOETIC_AFFAIRS, NOVA_FORCA_DAS_NACOES, ORGOTEK, THE_UPEO_WA_MACHO)
    }

    companion object {

        //Left
        val ADVENTURE = 1
        val ABERRANT = 2
        val AEON = 3

        //Right
        private val THE_AEON_SOCIETY_FOR_GENTLEMEN = 101
        private val THE_AIR_CIRCUS = 102
        private val BRANCH_9 = 103
        private val THE_INTERNATIONAL_DETECTIVE_AGENCY = 104
        private val THE_PONATOWSKI_FOUNDATION = 105

        private val ABERRANTS = 201
        private val PROJECT_UTOPIA = 202
        private val TEAM_TOMORROW = 203
        private val PROJECT_PROTEUS = 204
        private val THE_TERAGEN = 205
        private val THE_DIRECTIVE = 206
        private val CORPORATE = 207
        private val GOVERNMENT = 208
        private val OTHER = 209
        private val INDEPENDENT = 210

        private val AESCULAPIAN_ORDER = 301
        private val CHITRA_BHANU = 302
        private val ISRA = 303
        private val THE_LEGIONS = 304
        private val THE_MINISTRY_OF_NOETIC_AFFAIRS = 305
        private val NOVA_FORCA_DAS_NACOES = 306
        private val ORGOTEK = 307
        private val THE_UPEO_WA_MACHO = 308
    }
}
