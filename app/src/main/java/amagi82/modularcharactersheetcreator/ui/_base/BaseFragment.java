package amagi82.modularcharactersheetcreator.ui._base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto;
import icepick.Icepick;

public abstract class BaseFragment extends Fragment{

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override public void onStart() {
        super.onStart();
        Otto.BUS.get().register(this);
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override public void onStop() {
        super.onStop();
        Otto.BUS.get().unregister(this);
    }
}
