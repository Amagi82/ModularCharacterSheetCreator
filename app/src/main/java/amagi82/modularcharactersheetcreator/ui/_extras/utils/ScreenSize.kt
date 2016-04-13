package amagi82.modularcharactersheetcreator.ui._extras.utils

import android.content.Context
import android.graphics.Point
import android.view.WindowManager

class ScreenSize(context: Context) {
    val width: Int
    val height: Int

    init {
        val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val size = Point()
        display.getSize(size)
        width = size.x
        height = size.y
    }
}