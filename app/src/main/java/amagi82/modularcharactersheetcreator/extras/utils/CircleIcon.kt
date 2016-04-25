package amagi82.modularcharactersheetcreator.extras.utils

import amagi82.modularcharactersheetcreator.R
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.text.TextPaint

/*
    Creates a colored circle with the first letter of your character's name in the center
    Used in the Main character list by default if the user has not added a character photo
 */
class CircleIcon(private val context: Context) {
    private val circleImageSize: Int

    init {
        circleImageSize = context.resources.getDimensionPixelSize(R.dimen.main_circle_icon_size)
    }

    @JvmOverloads fun createIcon(name: String, backgroundColor: Int = getColor(R.color.primary)): Bitmap {
        val firstLetter = if (name.length > 0) name.substring(0, 1) else ""

        val textPaint = TextPaint()
        textPaint.flags = Paint.ANTI_ALIAS_FLAG
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.isLinearText = true
        textPaint.color = getColor(R.color.white)
        textPaint.textSize = context.resources.getDimension(R.dimen.main_circle_icon_text_size)

        val rect = Rect()
        rect.set(0, 0, circleImageSize, circleImageSize)

        val xPos = rect.centerX()
        val yPos = (rect.centerY() - (textPaint.descent() + textPaint.ascent()) * .5).toInt()

        val bitmap = Bitmap.createBitmap(circleImageSize, circleImageSize, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        canvas.drawColor(backgroundColor)
        canvas.drawText(firstLetter, xPos.toFloat(), yPos.toFloat(), textPaint)

        return bitmap
    }

    private fun getColor(colorId: Int): Int {
        return ContextCompat.getColor(context, colorId)
    }
}