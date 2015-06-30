package amagi82.modularcharactersheetcreator;


import android.app.Application;
import android.content.Context;

/*
    This class is used to give Volley a single instance over the lifetime of the app, ignoring screen rotation.
 */

public class App extends Application{

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
