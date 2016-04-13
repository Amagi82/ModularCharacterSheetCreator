package amagi82.modularcharactersheetcreator.ui.sheet.modules

import amagi82.modularcharactersheetcreator.models.modules.Blood
import amagi82.modularcharactersheetcreator.models.modules.Module
import amagi82.modularcharactersheetcreator.ui._base.BaseModuleViewModel
import android.databinding.ObservableField

class BloodViewModel(module: Module) : BaseModuleViewModel(module) {
    val blood = ObservableField<Blood>()

    init {
        update(module)
    }

    override fun update(module: Module) {
        blood.set(module.blood)
    }
}
