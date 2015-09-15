package amagi82.modularcharactersheetcreator.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import icepick.Icepick;

import static amagi82.modularcharactersheetcreator.ui.xtras.utils.Otto.BUS;

public abstract class BaseActivity extends AppCompatActivity{

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override protected void onStart() {
        super.onStart();
        BUS.getBus().register(this);
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override protected void onStop() {
        super.onStop();
        BUS.getBus().unregister(this);
    }
}
