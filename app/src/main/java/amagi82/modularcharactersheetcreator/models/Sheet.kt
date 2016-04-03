package amagi82.modularcharactersheetcreator.models

import amagi82.modularcharactersheetcreator.models.modules.Module
import nz.bradcampbell.paperparcel.PaperParcel
import nz.bradcampbell.paperparcel.PaperParcelable

@PaperParcel
data class Sheet(val title: String, val modules: List<Module>) : PaperParcelable {
    @JvmField val CREATOR = PaperParcelable.Creator(Sheet::class.java)
}
