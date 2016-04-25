package amagi82.modularcharactersheetcreator.extras.widgets

import amagi82.modularcharactersheetcreator.extras.widgets.callbacks.EditTextListener
import amagi82.modularcharactersheetcreator.extras.widgets.callbacks.SimpleTextWatcher
import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText

class FocusAwareEditText : EditText {
    private var listener: EditTextListener? = null
    private var text: String? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
//        onFocusChangeListener = { view, hasFocus ->
//            if (hasFocus)
//                listener.onKeyboardShown()
//            else {
//                //Not focused. Hide keyboard and notify
//                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//                imm.hideSoftInputFromWindow(view.windowToken, 0)
//                listener.onTextChanged(text)
//            }
//        }

        setOnEditorActionListener { textView, i, keyEvent ->
            //Keyboard closed with done button
            if (i == EditorInfo.IME_ACTION_DONE) clearFocus()
            false
        }

        addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(newValue: String) {
                text = newValue
            }
        })
    }

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent): Boolean {
        //Keyboard closed with back button
        if (event.keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) clearFocus()
        return super.dispatchKeyEvent(event)
    }

    fun setTextChangedListener(listener: EditTextListener) {
        this.listener = listener
    }
}