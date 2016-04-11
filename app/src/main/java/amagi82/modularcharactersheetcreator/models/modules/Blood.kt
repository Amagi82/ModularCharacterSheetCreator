package amagi82.modularcharactersheetcreator.models.modules

import nz.bradcampbell.paperparcel.PaperParcel
import nz.bradcampbell.paperparcel.PaperParcelable

@PaperParcel
data class Blood(val currentBlood: Int = 7, val maxBlood: Int = 10, val bloodPerTurn: Int = 1) : PaperParcelable {
    @JvmField val CREATOR = PaperParcelable.Creator(Blood::class.java)
}