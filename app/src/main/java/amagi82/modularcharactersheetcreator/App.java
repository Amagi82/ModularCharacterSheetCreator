package amagi82.modularcharactersheetcreator;


import android.app.Application;
import android.content.Context;

/*
    This class is used to give Volley a single instance over the lifetime of the app, ignoring screen rotation.
 */

public class App extends Application{

    public static final String urlClassic = "http://theonyxpath.com/avatar/Classic%20World%20of%20Darkness/";
    public static final String urlNew = "http://theonyxpath.com/avatar/World%20of%20Darkness/";
    public static final String url20th = "http://theonyxpath.com/avatar/20th%20Anniversary/";
    private static App instance;

    public static Context getAppContext(){
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
