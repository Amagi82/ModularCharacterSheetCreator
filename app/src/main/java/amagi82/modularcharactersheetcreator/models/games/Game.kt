package amagi82.modularcharactersheetcreator.models.games

import amagi82.modularcharactersheetcreator.App
import amagi82.modularcharactersheetcreator.extras.*
import amagi82.modularcharactersheetcreator.models.Splat
import android.support.annotation.StringRes
import android.util.SparseArray

/*
    Base class implemented by all game systems
 */
abstract class Game {
    abstract val gameTitle: String
    abstract val leftTitle: String
    abstract val rightTitle: String
    abstract val gameUrl: String
    abstract val splashUrl: String
    abstract val gameColor: Int
    abstract val isArchetypeLeft:Boolean //true
    abstract internal val checkLeft:Boolean //false //With CVampire, Sect must be known to determine if Clan is antitribu.
    abstract val isLeftListFinal:Boolean //true
    abstract val isRightListFinal:Boolean //true
    abstract val splats: SparseArray<Splat>

    abstract fun getListLeft(splatId: Int): IntArray

    abstract fun getListRight(splatId: Int): IntArray

    fun getSplat(splatId: Int)= splats.get(splatId)

    open fun getRightTitle(leftSplatId: Int)= rightTitle

    fun checkLeft()= checkLeft

    open fun updateLeft(leftSplatId: Int, rightSplatId: Int)= 0

    internal fun getString(@StringRes resId: Int)= App.res?.getString(resId) ?: ""

    internal fun splat(@StringRes title: Int) = Splat(getString(title))

    internal fun splat(@StringRes title: Int, @StringRes url: Int) = Splat(getString(title), getString(url))

    internal fun splat(@StringRes title: Int, isEndPoint: Boolean)= Splat(getString(title), isEndPoint = isEndPoint)

    companion object {

        fun getSystem(@GameSystem systemId: Int) = when (systemId) {
                CMAGE ->  CMage()
                CVAMPIRE ->  CVampire()
                CWEREWOLF ->  CWerewolf()
                CWRAITH ->  CWraith()
                EXALTED ->  Exalted()
                NDEMON ->  NDemon()
                NMUMMY ->  NMummy()
                NVAMPIRE ->  NVampire()
                NWEREWOLF ->  NWerewolf()
                SCION ->  Scion()
                TRINITY ->  Trinity()
                else ->  null
        }

        val systems = intArrayOf(CMAGE, CVAMPIRE, CWEREWOLF, CWRAITH, EXALTED, NDEMON, NMUMMY, NVAMPIRE, NWEREWOLF, SCION, TRINITY)
    }
}