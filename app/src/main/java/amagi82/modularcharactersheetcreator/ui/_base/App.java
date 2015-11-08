package amagi82.modularcharactersheetcreator.ui._base;

import android.app.Application;
import android.content.res.Resources;

/*
    Currently just used to get string resources without passing contexts everywhere.
 */
public class App extends Application{
    private static Resources res;

    @Override public void onCreate() {
        super.onCreate();
        res = getResources();
    }

    public static Resources getRes(){
        return res;
    }
}
