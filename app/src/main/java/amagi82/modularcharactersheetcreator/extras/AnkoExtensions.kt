package amagi82.modularcharactersheetcreator.extras

import android.app.Activity
import android.content.Context
import android.support.annotation.StyleRes
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.view.ContextThemeWrapper
import android.view.View
import android.view.ViewManager
import android.widget.ImageView
import com.isseiaoki.simplecropview.CropImageView
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.AnkoException
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.internals.AnkoInternals
import org.jetbrains.anko.wrapContent

private val defaultInit: Any.() -> Unit = {}

fun <T : View> T.lparams(
        width: Int = wrapContent, height: Int = wrapContent,
        init: CollapsingToolbarLayout.LayoutParams.() -> Unit = defaultInit): T {
    val layoutParams = CollapsingToolbarLayout.LayoutParams(width, height)
    layoutParams.init()
    this@lparams.layoutParams = layoutParams
    return this
}

fun wrapContextIfNeeded(ctx: Context, @StyleRes theme: Int) = if (theme != 0 && (ctx !is ContextThemeWrapper || ctx.themeResId != theme)) ContextThemeWrapper(ctx, theme) else ctx

inline fun <T : View> ViewManager.ankoView(@StyleRes theme: Int = 0, factory: (ctx: Context) -> T, init: T.() -> Unit): T {
    val ctx = wrapContextIfNeeded(AnkoInternals.getContext(this), theme)
    val view = factory(ctx)
    view.init()
    AnkoInternals.addView(this, view)
    return view
}

inline fun <T : View> Context.ankoView(@StyleRes theme: Int, factory: (ctx: Context) -> T, init: T.() -> Unit): T {
    val ctx = wrapContextIfNeeded(this, theme)
    val view = factory(ctx)
    view.init()
    AnkoInternals.addView(this, view)
    return view
}

inline fun <reified T : View> ViewManager.customView(@StyleRes theme: Int = 0, init: T.() -> Unit) = ankoView(theme, { ctx -> AnkoInternals.initiateView(ctx, T::class.java) }) { init() }

inline fun <reified T : View> Context.customView(@StyleRes theme: Int = 0, init: T.() -> Unit) = ankoView(theme, { ctx -> AnkoInternals.initiateView(ctx, T::class.java) }) { init() }

inline fun <reified T : View> Activity.customView(@StyleRes theme: Int = 0, init: T.() -> Unit) = ankoView(theme, { ctx -> AnkoInternals.initiateView(ctx, T::class.java) }) { init() }


var ImageView.tintResource: Int
    get() = throw AnkoException("'android.widget.ImageView.imageResource' property does not have a getter")
    set(v) = setColorFilter(ContextCompat.getColor(context, v))

fun ViewManager.circleImageView(): CircleImageView = circleImageView({})
inline fun ViewManager.circleImageView(init: CircleImageView.() -> Unit): CircleImageView {
    return ankoView({ ctx: Context -> CircleImageView(ctx) }) { init() }
}

fun ViewManager.cropImageView(): CropImageView = cropImageView({})
inline fun ViewManager.cropImageView(init: CropImageView.() -> Unit): CropImageView {
    return ankoView({ ctx: Context -> CropImageView(ctx) }) { init() }
}