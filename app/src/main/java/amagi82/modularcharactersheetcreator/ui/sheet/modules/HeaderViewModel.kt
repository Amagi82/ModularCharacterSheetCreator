package amagi82.modularcharactersheetcreator.ui.sheet.modules

import amagi82.modularcharactersheetcreator.models.modules.Module
import amagi82.modularcharactersheetcreator.ui._base.BaseModuleViewModel
import android.databinding.ObservableField

class HeaderViewModel(module: Module) : BaseModuleViewModel(module) {
    val title = ObservableField<String>()

    init {
        update(module)
    }

    override fun update(module: Module) {
        title.set(module.title)
    }
}
