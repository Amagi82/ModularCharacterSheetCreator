package amagi82.modularcharactersheetcreator.extras

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.WindowManager
import com.trello.rxlifecycle.ActivityLifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


fun Context.color(res: Int) = ContextCompat.getColor(this, res)

fun Context.dimen(res: Int) = resources.getDimensionPixelSize(res)

fun Activity.end(@ResultCode resultCode: Int, intent: Intent? = null) {
    setResult(resultCode, intent)
    finish()
}

fun AppCompatActivity.setToolbar(toolbar: Toolbar) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
}

val Context.prefs: SharedPreferences
    get() = getSharedPreferences("Onyx",0)

fun Context.savePrefs(vararg params: Pair<String, Any>) {
    val p = prefs.edit()
    params.forEach {
        val (k, v) = it
        @Suppress("UNCHECKED_CAST")
        when (v) {
            is String -> p.putString(k, v)
            is Int -> p.putInt(k, v)
            is Float -> p.putFloat(k, v)
            is Boolean -> p.putBoolean(k, v)
            is Long -> p.putLong(k, v)
            is Set<*> -> p.putStringSet(k, v as Set<String>)
            else -> throw IllegalArgumentException("Param $k has invalid type ${v.javaClass.name}")
        }
    }
    p.apply()
}

fun Context.clearPrefs(vararg params: String) {
    val p = prefs.edit()
    if (params.isEmpty()) p.clear()
    else params.forEach { p.remove(it) }
    p.apply()
}

fun Context.hasPermission(permission: String) = ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
fun Activity.requestPermission(reqCode: Int, vararg permissions: String) = ActivityCompat.requestPermissions(this, permissions, reqCode)

fun Activity.screenWidth(): Int {
    val size = Point()
    ((getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay).getSize(size)
    return size.x
}

fun Activity.screenHeight(): Int {
    val size = Point()
    ((getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay).getSize(size)
    return size.y
}

fun Drawable.tint(@ColorInt color: Int) = DrawableCompat.setTint(DrawableCompat.wrap(this), color)

fun Menu.tint(@ColorInt color: Int) {
    for (i in 0..size() - 1) getItem(i).icon.tint(color)
}

fun Context.icon(@DrawableRes resId: Int, @ColorInt color: Int): Drawable {
    val icon = ContextCompat.getDrawable(this, resId)
    icon.tint(color)
    return icon
}

fun <T> Observable<T>.subscribeIo(): Observable<T> = subscribeOn(Schedulers.io())
fun <T> Observable<T>.subscribeComputations(): Observable<T> = subscribeOn(Schedulers.computation())
fun <T> Observable<T>.bindToLifecycle(activity: ActivityLifecycleProvider): Observable<T> = this.compose<T>(activity.bindToLifecycle<T>()).observeOn(AndroidSchedulers.mainThread())

fun Any.log(message: String) = Log.d(javaClass.simpleName, message)