package amagi82.modularcharactersheetcreator;


import android.app.Application;
import android.content.Context;

/*
    This class is used to give Volley a single instance over the lifetime of the app, ignoring screen rotation.
 */

public class App extends Application{

    public static final String CHARACTER = "character";
    public static final String CHARACTERS = "characters";
    public static final String BUCKET = "bucket";
    public static final String EDIT_MODE = "EditMode";
    public static final int NONE = -1;
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
