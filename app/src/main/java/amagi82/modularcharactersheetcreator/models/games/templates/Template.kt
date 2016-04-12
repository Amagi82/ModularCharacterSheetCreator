package amagi82.modularcharactersheetcreator.models.games.templates

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.GameCharacter
import amagi82.modularcharactersheetcreator.models.Sheet
import amagi82.modularcharactersheetcreator.models.games.Game.Companion.CMAGE
import amagi82.modularcharactersheetcreator.models.games.Game.Companion.CVAMPIRE
import amagi82.modularcharactersheetcreator.models.games.Game.Companion.CWEREWOLF
import amagi82.modularcharactersheetcreator.models.games.Game.Companion.CWRAITH
import amagi82.modularcharactersheetcreator.models.games.Game.Companion.EXALTED
import amagi82.modularcharactersheetcreator.models.games.Game.Companion.NDEMON
import amagi82.modularcharactersheetcreator.models.games.Game.Companion.NMUMMY
import amagi82.modularcharactersheetcreator.models.games.Game.Companion.NVAMPIRE
import amagi82.modularcharactersheetcreator.models.games.Game.Companion.NWEREWOLF
import amagi82.modularcharactersheetcreator.models.games.Game.Companion.SCION
import amagi82.modularcharactersheetcreator.models.games.Game.Companion.TRINITY
import amagi82.modularcharactersheetcreator.models.modules.Blood
import amagi82.modularcharactersheetcreator.models.modules.Health
import amagi82.modularcharactersheetcreator.models.modules.Module
import amagi82.modularcharactersheetcreator.models.modules.Stat
import android.content.res.Resources
import android.support.annotation.ArrayRes
import android.support.annotation.StringRes
import java.util.*


abstract class Template internal constructor(private val res: Resources) {

    internal abstract fun createSheet(character: GameCharacter): Sheet?

    internal fun getSystem(character: GameCharacter) = character.gameSystem()

    internal fun sheet(modules: List<Module>) = Sheet(getString(R.string.character_sheet), modules)

    internal fun bloodPool() = Module.blood(getString(R.string.blood_pool), Blood())

    internal fun header(@StringRes resId: Int, spanCount: Int = Module.ONE) = Module.header(getString(resId), spanCount)

    internal fun health() = Module.health(getString(R.string.health), Health())

    internal fun statBlock(@StringRes titleId: Int, @ArrayRes arrayId: Int, valueMin: Int, valueMax: Int) = Module.statBlock(getString(titleId), statBlock = createDefaultStats(arrayId, valueMin, valueMax))

    internal fun statBlock(@StringRes titleId: Int, @StringRes textBody: Int, @ArrayRes arrayId: Int, valueMin: Int, valueMax: Int) = Module.statBlock(getString(titleId), getString(textBody), createDefaultStats(arrayId, valueMin, valueMax))

    internal fun stat(@StringRes titleId: Int, @StringRes bodyId: Int, valueMin: Int, valueMax: Int) = Module.stat(getString(titleId), getString(bodyId), Stat("", valueMin = valueMin, valueMax = valueMax))

    internal fun text(@StringRes titleId: Int) = Module.text(getString(titleId), "")

    private fun createDefaultStats(arrayId: Int, valueMin: Int, valueMax: Int): List<Stat> {
        val stats = ArrayList<Stat>()
        val categories = getArray(arrayId)
        categories?.forEach { stats.add(Stat(it, valueMin = valueMin, valueMax = valueMax)) }
        return stats
    }

    private fun getString(@StringRes resId: Int) = if (resId == 0) "" else res.getString(resId)

    private fun getArray(@ArrayRes resId: Int) = if (resId == 0) null else res.getStringArray(resId)

    companion object {
        fun create(res: Resources, character: GameCharacter) = when (character.gameId) {
            CMAGE -> CMageTemplate(res).createSheet(character)
            CVAMPIRE -> CVampireTemplate(res).createSheet(character)
            CWEREWOLF -> CWerewolfTemplate(res).createSheet(character)
            CWRAITH -> CWraithTemplate(res).createSheet(character)
            EXALTED -> ExaltedTemplate(res).createSheet(character)
            NDEMON -> NDemonTemplate(res).createSheet(character)
            NMUMMY -> NMummyTemplate(res).createSheet(character)
            NVAMPIRE -> NVampireTemplate(res).createSheet(character)
            NWEREWOLF -> NWerewolfTemplate(res).createSheet(character)
            SCION -> ScionTemplate(res).createSheet(character)
            TRINITY -> TrinityTemplate(res).createSheet(character)
            else -> null
        }
    }
}
