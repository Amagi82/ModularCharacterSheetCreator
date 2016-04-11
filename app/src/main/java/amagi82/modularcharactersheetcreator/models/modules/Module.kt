package amagi82.modularcharactersheetcreator.models.modules

import android.os.Parcelable
import android.support.annotation.IntDef

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

import auto.parcelgson.AutoParcelGson
import nz.bradcampbell.paperparcel.PaperParcel
import nz.bradcampbell.paperparcel.PaperParcelable

@PaperParcel
data class Module(@Type val type: Int, @SpanCount val spanCount: Int = 3, val title: String = "", val textBody: String? = null, val stat: Stat? = null, val statBlock: List<Stat>? = null, val health: Health? = null, val blood: Blood? = null, val imageUri: String? = null) : PaperParcelable {
    @JvmField val CREATOR = PaperParcelable.Creator(Module::class.java)

    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @IntDef(HEADER_MODULE.toLong(), TEXT_MODULE.toLong(), STAT_MODULE.toLong(), STAT_BLOCK_MODULE.toLong(), HEALTH_MODULE.toLong(), BLOOD_MODULE.toLong(), IMAGE_MODULE.toLong())
    annotation class Type

    //One-third, half, two-thirds, full width
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    @IntDef(ONE.toLong(), HALF.toLong(), TWO.toLong(), FULL.toLong())
    annotation class SpanCount

    companion object {

        fun createHeader(title: String): Module {
            return builder().type(HEADER_MODULE).title(title).spanCount(FULL).build()
        }

        fun createHeader(title: String, @SpanCount spanCount: Int): Module {
            return builder().type(HEADER_MODULE).title(title).spanCount(spanCount).build()
        }

        fun createText(title: String, textBody: String): Module {
            return builder().type(TEXT_MODULE).title(title).textBody(textBody).build()
        }

        fun createText(title: String, textBody: String, @SpanCount spanCount: Int): Module {
            return builder().type(TEXT_MODULE).title(title).textBody(textBody).spanCount(spanCount).build()
        }

        fun createStat(title: String, textBody: String, stat: Stat): Module {
            return builder().type(STAT_MODULE).title(title).textBody(textBody).stat(stat).spanCount(TWO).build()
        }

        fun createStat(title: String, textBody: String, stat: Stat, @SpanCount spanCount: Int): Module {
            return builder().type(STAT_MODULE).title(title).textBody(textBody).stat(stat).spanCount(spanCount).build()
        }

        fun createStatBlock(title: String, statBlock: List<Stat>): Module {
            return builder().type(STAT_BLOCK_MODULE).title(title).statBlock(statBlock).build()
        }

        fun createStatBlock(title: String, textBody: String, statBlock: List<Stat>): Module {
            return builder().type(STAT_BLOCK_MODULE).title(title).textBody(textBody).statBlock(statBlock).build()
        }

        fun createStatBlock(title: String, textBody: String, statBlock: List<Stat>, @SpanCount spanCount: Int): Module {
            return builder().type(STAT_BLOCK_MODULE).title(title).textBody(textBody).statBlock(statBlock).spanCount(spanCount).build()
        }

        fun createHealth(title: String, health: Health): Module {
            return builder().type(HEALTH_MODULE).title(title).health(health).build()
        }

        fun createHealth(title: String, health: Health, @SpanCount spanCount: Int): Module {
            return builder().type(HEALTH_MODULE).title(title).health(health).spanCount(spanCount).build()
        }

        fun createBlood(title: String, blood: Blood): Module {
            return builder().type(BLOOD_MODULE).title(title).blood(blood).build()
        }

        fun createBlood(title: String, blood: Blood, @SpanCount spanCount: Int): Module {
            return builder().type(BLOOD_MODULE).title(title).blood(blood).spanCount(spanCount).build()
        }

        fun createImage(title: String, imageUri: String, @SpanCount spanCount: Int): Module {
            return builder().type(IMAGE_MODULE).title(title).imageUri(imageUri).spanCount(spanCount).build()
        }

        internal fun builder(): Builder {
            return AutoParcelGson_Module.Builder().title("").spanCount(ONE)
        }

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
