package amagi82.modularcharactersheetcreator.ui._extras.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.ui._extras.widgets.callbacks.EditTextListener;
import amagi82.modularcharactersheetcreator.ui._extras.widgets.callbacks.SimpleTextWatcher;

public class FocusAwareEditText extends EditText {
    private EditTextListener listener;
    private String text;

    public FocusAwareEditText(Context context) {
        super(context);
        init();
    }

    public FocusAwareEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FocusAwareEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) listener.onKeyboardShown();
                else {
                    //Not focused. Hide keyboard and notify
                    InputMethodManager imm =  (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    listener.onTextChanged(text);
                }
            }
        });

        setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                //Keyboard closed with done button
                if (i == EditorInfo.IME_ACTION_DONE) clearFocus();
                return false;
            }
        });

        addTextChangedListener(new SimpleTextWatcher() {
            @Override public void onTextChanged(String newValue) {
                text = newValue;
            }
        });
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        //Keyboard closed with back button
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) clearFocus();
        return super.dispatchKeyEvent(event);
    }

    public void setTextChangedListener(EditTextListener listener){
        this.listener = listener;
    }
}