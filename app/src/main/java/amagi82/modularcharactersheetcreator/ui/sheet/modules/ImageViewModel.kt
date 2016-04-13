package amagi82.modularcharactersheetcreator.ui.sheet.modules

import amagi82.modularcharactersheetcreator.models.modules.Module
import amagi82.modularcharactersheetcreator.ui._base.BaseModuleViewModel
import android.databinding.ObservableField

class ImageViewModel(module: Module) : BaseModuleViewModel(module) {
    val title = ObservableField<String>()
    val imageUri = ObservableField<String>()

    init {
        update(module)
    }

    override fun update(module: Module) {
        title.set(module.title)
        imageUri.set(module.imageUri)
    }
}
