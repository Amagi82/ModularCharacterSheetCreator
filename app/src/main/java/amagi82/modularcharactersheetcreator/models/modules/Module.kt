package amagi82.modularcharactersheetcreator.models.modules

import amagi82.modularcharactersheetcreator.extras.*
import nz.bradcampbell.paperparcel.PaperParcel
import nz.bradcampbell.paperparcel.PaperParcelable

@PaperParcel
data class Module(@ModuleType val type: Int, @ModuleSpanCount val spanCount: Int = ONE_THIRD, val title: String = "", val textBody: String? = null, val stat: Stat? = null, val statBlock: List<Stat>? = null, val health: Health? = null, val blood: Blood? = null, val imageUri: String? = null) : PaperParcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelable.Creator(Module::class.java)

        fun header(title: String, spanCount: Int = FULL) = Module(type = HEADER_MODULE, title = title, spanCount = spanCount)

        fun text(title:String, textBody: String, spanCount: Int = ONE_THIRD) = Module(TEXT_MODULE, title = title, textBody = textBody, spanCount = spanCount)

        fun stat(title: String, textBody: String, stat: Stat, spanCount: Int = ONE_THIRD) = Module(STAT_MODULE, title = title, textBody = textBody, stat = stat, spanCount = spanCount)

        fun statBlock(title: String, textBody: String? = null, statBlock: List<Stat>, spanCount: Int = ONE_THIRD) = Module(STAT_BLOCK_MODULE, title = title, textBody = textBody, statBlock = statBlock, spanCount = spanCount)

        fun health(title: String, health: Health, spanCount: Int = ONE_THIRD) = Module(HEALTH_MODULE, title = title, health = health, spanCount = spanCount)

        fun blood(title: String, blood: Blood, spanCount: Int = ONE_THIRD) = Module(BLOOD_MODULE, title = title, blood = blood, spanCount = spanCount)

        fun image(title: String, imageUri: String, spanCount: Int = ONE_THIRD) = Module(IMAGE_MODULE, title = title, imageUri = imageUri, spanCount = spanCount)
    }
}
