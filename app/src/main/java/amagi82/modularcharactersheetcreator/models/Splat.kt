package amagi82.modularcharactersheetcreator.models

import nz.bradcampbell.paperparcel.PaperParcel
import nz.bradcampbell.paperparcel.PaperParcelable

/*
    Splats are a generic term for factions or groups in the Storyteller/Storytelling gameId. Also known as "character axis".
 */
@PaperParcel
data class Splat(val title: String, val url: String? = null, val isEndPoint: Boolean = true) : PaperParcelable {
    companion object{
        @JvmField val CREATOR = PaperParcelable.Creator(Splat::class.java)
    }
}
