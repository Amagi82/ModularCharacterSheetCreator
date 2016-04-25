package amagi82.modularcharactersheetcreator.ui._base

import amagi82.modularcharactersheetcreator.extras.ModuleSpanCount
import amagi82.modularcharactersheetcreator.extras.ModuleType
import amagi82.modularcharactersheetcreator.models.modules.Module

abstract class BaseModuleViewModel(module: Module) {
    @ModuleType val type: Int
    @ModuleSpanCount val spanCount: Int

    init {
        type = module.type
        spanCount = module.spanCount
    }

    abstract fun update(module: Module)
}
