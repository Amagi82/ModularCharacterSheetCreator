package amagi82.modularcharactersheetcreator.ui.sheet

import amagi82.modularcharactersheetcreator.BR
import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.Sheet
import amagi82.modularcharactersheetcreator.models.modules.Module.Companion.BLOOD_MODULE
import amagi82.modularcharactersheetcreator.models.modules.Module.Companion.HEADER_MODULE
import amagi82.modularcharactersheetcreator.models.modules.Module.Companion.HEALTH_MODULE
import amagi82.modularcharactersheetcreator.models.modules.Module.Companion.IMAGE_MODULE
import amagi82.modularcharactersheetcreator.models.modules.Module.Companion.STAT_BLOCK_MODULE
import amagi82.modularcharactersheetcreator.models.modules.Module.Companion.STAT_MODULE
import amagi82.modularcharactersheetcreator.models.modules.Module.Companion.TEXT_MODULE
import amagi82.modularcharactersheetcreator.ui._base.BaseModuleViewModel
import amagi82.modularcharactersheetcreator.ui.sheet.modules.*
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import me.tatarka.bindingcollectionadapter.BaseItemViewSelector
import me.tatarka.bindingcollectionadapter.ItemView
import me.tatarka.bindingcollectionadapter.ItemViewSelector


class SheetTabViewModel(sheet: Sheet) {
    val title = ObservableField<String>()
    val modules = ObservableArrayList<BaseModuleViewModel>()
    val itemView: ItemViewSelector<BaseModuleViewModel> = object : BaseItemViewSelector<BaseModuleViewModel>() {
        override fun select(itemView: ItemView, position: Int, item: BaseModuleViewModel) {
            when (item.type) {
                HEADER_MODULE -> itemView.set(BR.headerViewModel, R.layout.sheet_item_header)
                TEXT_MODULE -> itemView.set(BR.textViewModel, R.layout.sheet_item_text)
                STAT_MODULE -> itemView.set(BR.statViewModel, R.layout.sheet_item_stat)
                STAT_BLOCK_MODULE -> itemView.set(BR.statBlockViewModel, R.layout.sheet_item_stat_block)
                HEALTH_MODULE -> itemView.set(BR.healthViewModel, R.layout.sheet_item_health)
                BLOOD_MODULE -> itemView.set(BR.bloodViewModel, R.layout.sheet_item_blood)
                IMAGE_MODULE -> itemView.set(BR.imageViewModel, R.layout.sheet_item_image)
            }
        }
    }

    init {
        title.set(sheet.title)
        for (module in sheet.modules) {
            when (module.type) {
                HEADER_MODULE -> modules.add(HeaderViewModel(module))
                TEXT_MODULE -> modules.add(TextViewModel(module))
                STAT_MODULE -> modules.add(StatViewModel(module))
                STAT_BLOCK_MODULE -> modules.add(StatBlockViewModel(module))
                HEALTH_MODULE -> modules.add(HealthViewModel(module))
                BLOOD_MODULE -> modules.add(BloodViewModel(module))
                IMAGE_MODULE -> modules.add(ImageViewModel(module))
            }
        }
    }
}
