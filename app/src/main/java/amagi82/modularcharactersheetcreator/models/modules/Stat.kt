package amagi82.modularcharactersheetcreator.models.modules

import nz.bradcampbell.paperparcel.PaperParcel
import nz.bradcampbell.paperparcel.PaperParcelable

/*
    Used to fill a single row of a stat block
 */
@PaperParcel
data class Stat(val category: String = "", val specialty: String? = null, val valueMin: Int = 0, val value: Int = 0, val valueTemp: Int = 0, val valueMax: Int = 5, val numStars: Int = 5) : PaperParcelable {
    @JvmField val CREATOR = PaperParcelable.Creator(Stat::class.java)
}