package amagi82.modularcharactersheetcreator.models

import amagi82.modularcharactersheetcreator.models.games.Game
import android.support.annotation.ColorInt
import android.support.annotation.IntDef
import nz.bradcampbell.paperparcel.PaperParcel
import nz.bradcampbell.paperparcel.PaperParcelable
import java.util.*

@PaperParcel
data class GameCharacter(var name: String = "",
                         @Game.System var gameId: Int = Game.NONE,
                         var leftId: Int = 0,
                         var rightId: Int = 0,
                         var colorScheme: ColorScheme? = null,
                         var sheets: List<Sheet> = ArrayList<Sheet>(),
                         var image: CharacterImage? = null,
                         val entityId: String = UUID.randomUUID().toString(),
                         var timestamp: Long = System.currentTimeMillis()) : PaperParcelable {



    @PaperParcel
    data class ColorScheme(@ColorInt val colorBackground: Int, @ColorInt val colorText: Int, @ColorInt val colorTextDim: Int) : PaperParcelable {
        companion object{
            @JvmField val CREATOR = PaperParcelable.Creator(ColorScheme::class.java)
        }
    }

    @PaperParcel
    data class CharacterImage(val uri: String, val height: Int, val width: Int) : PaperParcelable {
        companion object{
            @JvmField val CREATOR = PaperParcelable.Creator(CharacterImage::class.java)
        }
    }

    //Minimum requirements necessary to save the character
    fun isComplete() = name.length > 0 && progress() == FINISH

    //Used during character creation/editing. Gets the current step in the character creation process
    fun progress() = if (gameId == Game.NONE) START else if (leftId == 0) LEFT else if (rightId == 0) RIGHT else FINISH

    //Used during character creation/editing. Removes progress on back.
    fun removeProgress(@Progress toStep: Int) {
        gameId = if (toStep == START) Game.NONE else gameId
        leftId = if (toStep <= LEFT) 0 else leftId
        rightId = if (toStep <= RIGHT) 0 else rightId
    }

    fun gameSystem() = Game.getSystem(gameId)

    fun archetype(): String? {
        val system = gameSystem() ?: return null
        return if (leftId == 0 || rightId == 0 || gameId == Game.NONE) null
        else if (system.isArchetypeLeft) system.getSplat(leftId).title
        else system.getSplat(rightId).title
    }

    fun left() = gameSystem()?.getSplat(leftId)

    fun right() = gameSystem()?.getSplat(rightId)

    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @IntDef(START.toLong(), LEFT.toLong(), RIGHT.toLong(), FINISH.toLong())
    annotation class Progress

    companion object {
        @JvmField val CREATOR = PaperParcelable.Creator(GameCharacter::class.java)
        const val START = 0
        const val LEFT = 1
        const val RIGHT = 2
        const val FINISH = 3
    }
}