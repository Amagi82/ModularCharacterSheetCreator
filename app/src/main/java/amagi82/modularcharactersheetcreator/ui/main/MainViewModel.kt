package amagi82.modularcharactersheetcreator.ui.main

import amagi82.modularcharactersheetcreator.BR
import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.GameCharacter
import android.databinding.ObservableArrayList
import android.util.Log
import me.tatarka.bindingcollectionadapter.ItemView
import java.util.*

class MainViewModel {
    val list = ObservableArrayList<MainItemViewModel>()
    val itemView = ItemView.of(BR.mainItemViewModel, R.layout.main_item)

    fun addAll(characters: List<GameCharacter>) {
        this.list.clear()
        for (character in characters) {
            list.add(MainItemViewModel(character))
        }
        sort()
    }

    fun add(character: GameCharacter) {
        list.add(0, MainItemViewModel(character))
    }

    fun remove(character: GameCharacter) {
        val position = indexOf(character)
        if (position != -1) list.removeAt(position)
        else Log.i("MainViewModel", "No character with matching ID found")
    }

    fun update(character: GameCharacter) {
        val position = indexOf(character)
        if (position != -1) list[position] = MainItemViewModel(character)
        else Log.i("MainViewModel", "No character with matching ID found")
        sort()
    }

    val characters: List<GameCharacter>
        get() {
            val characters = ArrayList<GameCharacter>(list.size)
            for (item in list) {
                characters.add(item.character)
            }
            return characters
        }

    private fun indexOf(character: GameCharacter): Int {
        for (i in list.indices) {
            if (list[i].character.entityId == character.entityId) return i
        }
        return -1
    }

    private fun sort() {
        Collections.sort(list) { o1, o2 -> if (o1.character.timestamp > o2.character.timestamp) -1 else 1 }
    }
}