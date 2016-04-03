package amagi82.modularcharactersheetcreator.ui._base

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
        var res: Resources? = null
            private set
    }
}
