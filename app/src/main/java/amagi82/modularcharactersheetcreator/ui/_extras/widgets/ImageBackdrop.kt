package amagi82.modularcharactersheetcreator.ui._extras.widgets

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.view.View

/*
    Convenience class to measure an ImageView for the aspect ratio of the Game System splash screens
 */
class ImageBackdrop(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AppCompatImageView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = View.MeasureSpec.getSize(widthMeasureSpec)
        val height = width * 154 / 305
        setMeasuredDimension(width, height)
    }
}
