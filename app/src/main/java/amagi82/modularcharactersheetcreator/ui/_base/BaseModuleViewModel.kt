package amagi82.modularcharactersheetcreator.ui._base

import amagi82.modularcharactersheetcreator.models.modules.Module

abstract class BaseModuleViewModel(module: Module) {
    @Module.Type val type: Int
    @Module.SpanCount val spanCount: Int

    init {
        type = module.type
        spanCount = module.spanCount
    }

    abstract fun update(module: Module)
}
