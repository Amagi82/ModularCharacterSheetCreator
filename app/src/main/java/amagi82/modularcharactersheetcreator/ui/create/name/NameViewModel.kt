package amagi82.modularcharactersheetcreator.ui.create.name

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.GameCharacter
import amagi82.modularcharactersheetcreator.ui._base.App
import amagi82.modularcharactersheetcreator.ui._base.BaseViewModel
import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto
import amagi82.modularcharactersheetcreator.ui._extras.widgets.callbacks.EditTextListener
import amagi82.modularcharactersheetcreator.ui.create._events.KeyboardVisibleEvent
import amagi82.modularcharactersheetcreator.ui.create._events.NameChangedEvent
import android.databinding.ObservableField

class NameViewModel : BaseViewModel() {
    val gameItem = ObservableField<GameCardViewModel>()
    val leftItem = ObservableField<AxisCardViewModel>()
    val rightItem = ObservableField<AxisCardViewModel>()
    val title = ObservableField<String>()
    val name = ObservableField<String>()
    val imageUri = ObservableField<String?>()
    val editTextListener: EditTextListener = object : EditTextListener {
        override fun onTextChanged(newText: String) {
            Otto.BUS.get().post(NameChangedEvent(newText))
        }

        override fun onKeyboardShown() {
            Otto.BUS.get().post(KeyboardVisibleEvent())
        }
    }

    fun update(character: GameCharacter) {
        val game = character.gameSystem() ?: return

        if (character.rightId != 0) {
            gameItem.set(GameCardViewModel(game.gameUrl, game.gameTitle))
            leftItem.set(AxisCardViewModel(character.left(), game.leftTitle, true))
            rightItem.set(AxisCardViewModel(character.right(), game.getRightTitle(character.leftId), false))
        }
        name.set(character.name)

        title.set(App.res?.getString(if (character.name.length > 1) R.string.confirm else R.string.choose_name))

        //noinspection ConstantConditions
        imageUri.set(character.image?.uri)
    }
}