package amagi82.modularcharactersheetcreator.ui._base

import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.annotation.IntDef
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu

abstract class BaseActivity : AppCompatActivity() {

    @IntDef(ADD.toLong(), MODIFY.toLong(), DEFAULT.toLong())
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class ReqCode

    @IntDef(Activity.RESULT_CANCELED.toLong(), Activity.RESULT_OK.toLong(), RESULT_DELETED.toLong(), RESULT_EDIT.toLong())
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class ResultCode

    override fun onStart() {
        super.onStart()
        Otto.BUS.get().register(this)
    }

    override fun onStop() {
        super.onStop()
        Otto.BUS.get().unregister(this)
    }

    fun finish(@ResultCode resultCode: Int) {
        setResult(resultCode)
        finish()
    }

    fun finish(@ResultCode resultCode: Int, intent: Intent) {
        setResult(resultCode, intent)
        finish()
    }

    val saved: SharedPreferences
        get() = getSharedPreferences(LIST, Context.MODE_PRIVATE)

    fun putSaved(key: String, value: String) {
        saved.edit().putString(key, value).commit()
    }

    fun setTint(icon: Drawable, @ColorInt color: Int) {
        DrawableCompat.setTint(DrawableCompat.wrap(icon), color)
    }

    fun setMenuTint(menu: Menu, @ColorInt color: Int) {
        for (i in 0..menu.size() - 1) {
            setTint(menu.getItem(i).icon, color)
        }
    }

    fun getTintedIcon(@DrawableRes res: Int, @ColorInt color: Int): Drawable {
        val icon = ContextCompat.getDrawable(this, res)
        setTint(icon, color)
        return icon
    }

    fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {

        const val ADD = 1
        const val MODIFY = 2
        const val RESULT_DELETED = 3
        const val RESULT_EDIT = 4
        const val DEFAULT = 5

        const val CHARACTER = "CHARACTER"
        const val LIST = "LIST"
        const val MODULE = "MODULE"
    }
}
