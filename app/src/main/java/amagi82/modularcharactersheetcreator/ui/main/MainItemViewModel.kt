package amagi82.modularcharactersheetcreator.ui.main

import amagi82.modularcharactersheetcreator.models.GameCharacter
import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto
import android.view.View

class MainItemViewModel(val character: GameCharacter) {

    val gameTitle: String
        get() = character.gameSystem()?.gameTitle ?: ""

    val systemColor: Int
        get() = character.gameSystem()?.gameColor ?: 0

    fun onItemClick(view: View) {
        Otto.BUS.get().post(CharacterClickedEvent(character))
    }
}

