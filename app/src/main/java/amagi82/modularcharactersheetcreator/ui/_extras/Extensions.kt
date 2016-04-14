package amagi82.modularcharactersheetcreator.ui._extras

import android.content.Context
import android.support.v4.content.ContextCompat


fun Context.color(res: Int) = ContextCompat.getColor(this, res)

fun Context.dimen(res: Int) = resources.getDimensionPixelSize(res)

