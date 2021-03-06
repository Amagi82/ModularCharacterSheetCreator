package amagi82.modularcharactersheetcreator.models.games

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.Splat
import android.util.SparseArray

/*
    New World of Darkness
    Mummy: The Curse
 */
class NMummy : Game() {
    override val gameTitle = getString(R.string.nwod_mummy)
    override val leftTitle = getString(R.string.decree)
    override val rightTitle = getString(R.string.guild)
    override val gameUrl = getString(R.string.url_game_nwod_mummy)
    override val splashUrl = getString(R.string.url_splash_nwod_mummy)
    override val gameColor = R.color.nwod_mummy
    override val isArchetypeLeft = true
    override val checkLeft = false
    override val isLeftListFinal = true
    override val isRightListFinal = true
    override val splats: SparseArray<Splat>
        get() {
            val splats = SparseArray<Splat>(10)

            splats.put(BULL_HEADED, splat(R.string.bull_headed, R.string.url_nwod_mummy_decree_bull_headed))
            splats.put(FALCON_HEADED, splat(R.string.falcon_headed, R.string.url_nwod_mummy_decree_falcon_headed))
            splats.put(JACKAL_HEADED, splat(R.string.jackal_headed, R.string.url_nwod_mummy_decree_jackal_headed))
            splats.put(LION_HEADED, splat(R.string.lion_headed, R.string.url_nwod_mummy_decree_lion_headed))
            splats.put(SERPENT_HEADED, splat(R.string.serpent_headed, R.string.url_nwod_mummy_decree_serpent_headed))

            splats.put(MAA_KEP, splat(R.string.maa_kep, R.string.url_nwod_mummy_guild_maa_kep))
            splats.put(MESEN_NEBU, splat(R.string.mesen_nebu, R.string.url_nwod_mummy_guild_mesen_nebu))
            splats.put(SESHA_HEBSU, splat(R.string.sesha_hebsu, R.string.url_nwod_mummy_guild_sesha_hebsu))
            splats.put(SU_MENENT, splat(R.string.su_menent, R.string.url_nwod_mummy_guild_su_menent))
            splats.put(TEF_AABHI, splat(R.string.tef_aabhi, R.string.url_nwod_mummy_guild_tef_aabhi))

            return splats
        }

    override fun getListLeft(splatId: Int) = intArrayOf(BULL_HEADED, FALCON_HEADED, JACKAL_HEADED, LION_HEADED, SERPENT_HEADED)

    override fun getListRight(splatId: Int) = intArrayOf(MAA_KEP, MESEN_NEBU, SESHA_HEBSU, SU_MENENT, TEF_AABHI)

    companion object {

        //Left
        private val BULL_HEADED = 1
        private val FALCON_HEADED = 2
        private val JACKAL_HEADED = 3
        private val LION_HEADED = 4
        private val SERPENT_HEADED = 5

        //Right
        private val MAA_KEP = 101
        private val MESEN_NEBU = 102
        private val SESHA_HEBSU = 103
        private val SU_MENENT = 104
        private val TEF_AABHI = 105
    }
}
