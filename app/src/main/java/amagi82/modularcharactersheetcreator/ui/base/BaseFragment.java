package amagi82.modularcharactersheetcreator.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import amagi82.modularcharactersheetcreator.ui.xtras.utils.Otto;
import icepick.Icepick;

public abstract class BaseFragment extends Fragment{

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override public void onStart() {
        super.onStart();
        Otto.bus.register(this);
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override public void onStop() {
        super.onStop();
        Otto.bus.unregister(this);
    }
}
