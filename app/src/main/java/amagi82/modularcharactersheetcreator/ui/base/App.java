package amagi82.modularcharactersheetcreator.ui.base;

import android.app.Application;

public class App extends Application {
    @Override public void onCreate() {
        super.onCreate();
        //LeakCanary.install(this);
    }
}
