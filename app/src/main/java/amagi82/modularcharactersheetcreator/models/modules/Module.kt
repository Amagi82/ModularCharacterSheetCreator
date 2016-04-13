package amagi82.modularcharactersheetcreator.models.modules

import android.support.annotation.IntDef
import nz.bradcampbell.paperparcel.PaperParcel
import nz.bradcampbell.paperparcel.PaperParcelable

@PaperParcel
data class Module(@Type val type: Int, @SpanCount val spanCount: Int = ONE, val title: String = "", val textBody: String? = null, val stat: Stat? = null, val statBlock: List<Stat>? = null, val health: Health? = null, val blood: Blood? = null, val imageUri: String? = null) : PaperParcelable {

    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @IntDef(HEADER_MODULE.toLong(), TEXT_MODULE.toLong(), STAT_MODULE.toLong(), STAT_BLOCK_MODULE.toLong(), HEALTH_MODULE.toLong(), BLOOD_MODULE.toLong(), IMAGE_MODULE.toLong())
    annotation class Type

    //One-third, half, two-thirds, full width
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @IntDef(ONE.toLong(), HALF.toLong(), TWO.toLong(), FULL.toLong())
    annotation class SpanCount

    companion object {
        @JvmField val CREATOR = PaperParcelable.Creator(Module::class.java)

        fun header(title: String, spanCount: Int = FULL) = Module(type = HEADER_MODULE, title = title, spanCount = spanCount)

        fun text(title:String, textBody: String, spanCount: Int = ONE) = Module(TEXT_MODULE, title = title, textBody = textBody, spanCount = spanCount)

        fun stat(title: String, textBody: String, stat: Stat, spanCount: Int = ONE) = Module(STAT_MODULE, title = title, textBody = textBody, stat = stat, spanCount = spanCount)

        fun statBlock(title: String, textBody: String? = null, statBlock: List<Stat>, spanCount: Int = ONE) = Module(STAT_BLOCK_MODULE, title = title, textBody = textBody, statBlock = statBlock, spanCount = spanCount)

        fun health(title: String, health: Health, spanCount: Int = ONE) = Module(HEALTH_MODULE, title = title, health = health, spanCount = spanCount)

        fun blood(title: String, blood: Blood, spanCount: Int = ONE) = Module(BLOOD_MODULE, title = title, blood = blood, spanCount = spanCount)

        fun image(title: String, imageUri: String, spanCount: Int = ONE) = Module(IMAGE_MODULE, title = title, imageUri = imageUri, spanCount = spanCount)

        const val HEADER_MODULE = 1
        const val TEXT_MODULE = 2
        const val STAT_MODULE = 3
        const val STAT_BLOCK_MODULE = 4
        const val HEALTH_MODULE = 5
        const val BLOOD_MODULE = 6
        const val IMAGE_MODULE = 7

        const val ONE = 2
        const val HALF = 3
        const val TWO = 4
        const val FULL = 6
    }
}
