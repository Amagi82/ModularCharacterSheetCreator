package amagi82.modularcharactersheetcreator.models.modules

import nz.bradcampbell.paperparcel.PaperParcel
import nz.bradcampbell.paperparcel.PaperParcelable

@PaperParcel
data class Health(val damageText: String = "", val damagePenalty: String = "", val bashing: Int = 0, val lethal: Int = 0, val agg: Int = 0, val numBoxes: Int = 7) : PaperParcelable {
    @JvmField val CREATOR = PaperParcelable.Creator(Health::class.java)
}