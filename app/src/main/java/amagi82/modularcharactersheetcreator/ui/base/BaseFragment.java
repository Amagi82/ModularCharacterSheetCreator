package amagi82.modularcharactersheetcreator.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import icepick.Icepick;

import static amagi82.modularcharactersheetcreator.ui.xtras.utils.Otto.BUS;

public abstract class BaseFragment extends Fragment{

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override public void onStart() {
        super.onStart();
        BUS.getBus().register(this);
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override public void onStop() {
        super.onStop();
        BUS.getBus().unregister(this);
    }
}
