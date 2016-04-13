package amagi82.modularcharactersheetcreator.ui.sheet.modules

import amagi82.modularcharactersheetcreator.models.modules.Module
import amagi82.modularcharactersheetcreator.models.modules.Stat
import amagi82.modularcharactersheetcreator.ui._base.BaseModuleViewModel
import android.databinding.ObservableField

class StatViewModel(module: Module) : BaseModuleViewModel(module) {
    val title = ObservableField<String>()
    val textBody = ObservableField<String>()
    val stat = ObservableField<Stat>()

    init {
        update(module)
    }

    override fun update(module: Module) {
        title.set(module.title)
        textBody.set(module.textBody)
        stat.set(module.stat)
    }
}
