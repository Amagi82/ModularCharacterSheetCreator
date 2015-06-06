package amagi82.modularcharactersheetcreator.widgets;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;
import android.widget.EditText;

public class CollapsingEditTextToolbar extends CollapsingToolbarLayout implements AppBarLayout.OnOffsetChangedListener{

    EditText editText;
    Context context;

    public CollapsingEditTextToolbar(Context context) {
        super(context);
    }

    public CollapsingEditTextToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CollapsingEditTextToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.context = context;
        editText = new EditText(context);
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

    }
}
