package amagi82.modularcharactersheetcreator.ui.xtras.utils

import android.content.Context
import android.graphics.Point
import android.view.Display
import android.view.WindowManager

public class ScreenSize(context: Context) {

    public val width: Int
    public val height: Int

    init {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        width = size.x
        height = size.y
    }
}
