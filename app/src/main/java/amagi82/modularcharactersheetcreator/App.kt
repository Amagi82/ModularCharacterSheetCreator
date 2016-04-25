package amagi82.modularcharactersheetcreator

import android.app.Application
import android.content.res.Resources

/*
    Currently just used to get string resources without passing contexts everywhere.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        res = resources
    }

    companion object {
        lateinit var res: Resources
            private set
    }
}
