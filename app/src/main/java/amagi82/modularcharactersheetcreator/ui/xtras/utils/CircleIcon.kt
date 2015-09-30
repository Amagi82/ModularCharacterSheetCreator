package amagi82.modularcharactersheetcreator.ui.xtras.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.text.TextPaint

import amagi82.modularcharactersheetcreator.R

public class CircleIcon(private val context: Context) {

    @JvmOverloads public fun createIcon(name: String, backgroundColor: Int = getColor(R.color.primary)): Bitmap {
        val firstLetter = if (name.length() > 0) name.substring(0, 1) else ""
        val circleImageSize = context.resources.getDimensionPixelSize(R.dimen.circle_icon_size)

        val textPaint = TextPaint()
        textPaint.flags = Paint.ANTI_ALIAS_FLAG
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.isLinearText = true
        textPaint.color = getColor(R.color.white)
        textPaint.textSize = context.resources.getDimension(R.dimen.circle_icon_text_size)

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
