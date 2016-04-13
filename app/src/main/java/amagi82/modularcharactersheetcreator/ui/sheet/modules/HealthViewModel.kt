package amagi82.modularcharactersheetcreator.ui.sheet.modules

import amagi82.modularcharactersheetcreator.models.modules.Health
import amagi82.modularcharactersheetcreator.models.modules.Module
import amagi82.modularcharactersheetcreator.ui._base.BaseModuleViewModel
import android.databinding.ObservableField

class HealthViewModel(module: Module) : BaseModuleViewModel(module) {
    val health = ObservableField<Health>()

    init {
        update(module)
    }

    override fun update(module: Module) {
        health.set(module.health)
    }
}
