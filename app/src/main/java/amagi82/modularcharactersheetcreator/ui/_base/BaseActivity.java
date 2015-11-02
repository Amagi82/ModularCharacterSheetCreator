package amagi82.modularcharactersheetcreator.ui._base;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto;
import icepick.Icepick;

public abstract class BaseActivity extends AppCompatActivity{

    @IntDef({ADD, MODIFY, DEFAULT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ReqCode {
    }

    @IntDef({RESULT_CANCELED, RESULT_OK, RESULT_DELETED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ResultCode {
    }

    public static final int ADD = 1;
    public static final int MODIFY = 2;
    public static final int RESULT_DELETED = 3;
    public static final int DEFAULT = 4;

    public static final String CHARACTER = "CHARACTER";
    public static final String POSITION = "POSITION";

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override protected void onStart() {
        super.onStart();
        Otto.BUS.get().register(this);
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override protected void onStop() {
        super.onStop();
        Otto.BUS.get().unregister(this);
    }
}
