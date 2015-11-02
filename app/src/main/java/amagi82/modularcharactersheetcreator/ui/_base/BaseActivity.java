package amagi82.modularcharactersheetcreator.ui._base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

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

    public void setTint(@NonNull Drawable icon, @ColorInt int color){
        icon = DrawableCompat.wrap(icon);
        DrawableCompat.setTint(icon, color);
    }

    public void setMenuTint(@NonNull Menu menu, @ColorInt int color){
        for(int i = 0; i< menu.size(); i++){
            setTint(menu.getItem(i).getIcon(), color);
        }
    }

    public Drawable getTintedIcon(@DrawableRes int res, @ColorInt int color){
        Drawable icon = ContextCompat.getDrawable(this, res);
        setTint(icon, color);
        return icon;
    }

    public void setToolbar(@NonNull Toolbar toolbar){
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
