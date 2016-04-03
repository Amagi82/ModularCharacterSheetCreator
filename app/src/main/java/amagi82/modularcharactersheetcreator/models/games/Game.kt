package amagi82.modularcharactersheetcreator.models.games

import amagi82.modularcharactersheetcreator.models.Splat
import amagi82.modularcharactersheetcreator.ui._base.App
import android.support.annotation.ColorRes
import android.support.annotation.IntDef
import android.support.annotation.StringRes
import android.util.SparseArray

/*
    Base class implemented by all game systems
 */
abstract class Game {
    abstract var gameTitle: String
        internal set
    abstract var leftTitle: String
        internal set
    abstract internal var rightTitle: String
    abstract var gameUrl: String
        internal set
    abstract var splashUrl: String
        internal set
    @ColorRes var gameColor: Int = 0
        internal set //Used in the list of characters in MainAdapter
    var isArchetypeLeft = true
        internal set //Archetype is displayed under the game gameId in the list of characters
    internal var checkLeft = false //With CVampire, Sect must be known to determine if Clan is antitribu.
    var isLeftListFinal = true
        internal set //False if the list can change depending on choices
    var isRightListFinal = true
        internal set //False if the list can change depending on choices
    abstract internal var splats: SparseArray<Splat>

    abstract fun getListLeft(splatId: Int): IntArray

    abstract fun getListRight(splatId: Int): IntArray

    fun getSplat(splatId: Int)= splats.get(splatId)

    open fun getRightTitle(leftSplatId: Int)= rightTitle

    fun checkLeft()= checkLeft

    open fun updateLeft(leftSplatId: Int, rightSplatId: Int)= 0

    internal fun getString(@StringRes resId: Int)=App.res?.getString(resId) ?: ""

    internal fun splat(@StringRes title: Int) = Splat(getString(title))

    internal fun splat(@StringRes title: Int, @StringRes url: Int) = Splat(getString(title), getString(url))

    internal fun splat(@StringRes title: Int, isEndPoint: Boolean)= Splat(getString(title), isEndPoint = isEndPoint)

    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @IntDef(NONE.toLong(), EXALTED.toLong(), SCION.toLong(), TRINITY.toLong(), CMAGE.toLong(), CVAMPIRE.toLong(), CWEREWOLF.toLong(), CWRAITH.toLong(), NDEMON.toLong(), NMUMMY.toLong(), NVAMPIRE.toLong(), NWEREWOLF.toLong())
    annotation class System

    companion object {

        fun getSystem(@System systemId: Int) = when (systemId) {
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

        val systems: IntArray
            get() = intArrayOf(CMAGE, CVAMPIRE, CWEREWOLF, CWRAITH, EXALTED, NDEMON, NMUMMY, NVAMPIRE, NWEREWOLF, SCION, TRINITY)

        const val NONE = 0
        const val EXALTED = 10
        const val SCION = 20
        const val TRINITY = 30
        const val CMAGE = 101
        const val CVAMPIRE = 102
        const val CWEREWOLF = 103
        const val CWRAITH = 104
        const val NDEMON = 201
        const val NMUMMY = 202
        const val NVAMPIRE = 203
        const val NWEREWOLF = 204
    }
}