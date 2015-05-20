package amagi82.modularcharactersheetcreator.listeners;

import com.melnykov.fab.FloatingActionButton;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.listeners.EventListenerAdapter;

public class SnackbarEventListener extends EventListenerAdapter {
    FloatingActionButton fab;

    public SnackbarEventListener(FloatingActionButton fab) {
        super();
        this.fab = fab;
    }

    @Override
    public void onShowByReplace(Snackbar snackbar) {
        fab.hide();
        super.onShowByReplace(snackbar);
    }

    @Override
    public void onShow(Snackbar snackbar) {
        fab.hide();
        super.onShow(snackbar);
    }

    @Override
    public void onDismiss(Snackbar snackbar) {
        super.onDismiss(snackbar);
        fab.show();
    }

    @Override
    public void onDismissed(Snackbar snackbar) {
        super.onDismissed(snackbar);
    }
}
