package amagi82.modularcharactersheetcreator.ui._extras.widgets.callbacks

import android.text.Editable
import android.text.TextWatcher

abstract class SimpleTextWatcher : TextWatcher {

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable) {
        onTextChanged(s.toString())
    }

    abstract fun onTextChanged(newValue: String)
}
