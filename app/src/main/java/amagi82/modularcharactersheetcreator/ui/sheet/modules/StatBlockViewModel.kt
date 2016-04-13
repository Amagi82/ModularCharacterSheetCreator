package amagi82.modularcharactersheetcreator.ui.sheet.modules

import amagi82.modularcharactersheetcreator.models.modules.Module
import amagi82.modularcharactersheetcreator.models.modules.Stat
import amagi82.modularcharactersheetcreator.ui._base.BaseModuleViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableField

class StatBlockViewModel(module: Module) : BaseModuleViewModel(module) {
    val title = ObservableField<String>()
    val statBlock = ObservableArrayList<Stat>()

    init {
        update(module)
    }

    override fun update(module: Module) {
        title.set(module.title)
        statBlock.clear()
        if(module.statBlock != null) statBlock.addAll(module.statBlock)
    }
}
