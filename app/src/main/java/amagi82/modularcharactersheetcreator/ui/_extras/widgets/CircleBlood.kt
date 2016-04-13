package amagi82.modularcharactersheetcreator.ui._extras.widgets

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.modules.Blood
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.content.ContextCompat
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

class CircleBlood @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private var textColor: Int = 0
    private var progress: Int = 0
    private var max: Int = 0
    private var colorFill: Int = 0
    private var colorEmpty: Int = 0

    private val textPaint = TextPaint()
    private val paint = Paint()
    private val rectF = RectF()

    init {
        getXmlAttrs(context, attrs, defStyleAttr)

        textPaint.color = textColor
        textPaint.isAntiAlias = true
        paint.isAntiAlias = true
    }

    fun setBlood(blood: Blood) {
        max = blood.maxBlood
        progress = blood.currentBlood
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = View.MeasureSpec.getSize(widthMeasureSpec)
        rectF.set(0f, 0f, size.toFloat(), size.toFloat())
        textPaint.textSize = size / 1.9f
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        val yHeight = progress / max.toFloat() * height
        val radius = width / 2f
        val angle = (Math.acos(((radius - yHeight) / radius).toDouble()) * 180 / Math.PI).toFloat()
        val startAngle = 90 + angle
        val sweepAngle = 360 - angle * 2
        paint.color = colorEmpty
        canvas.drawArc(rectF, startAngle, sweepAngle, false, paint)

        canvas.save()
        canvas.rotate(180f, (width / 2).toFloat(), (height / 2).toFloat())
        paint.color = colorFill
        canvas.drawArc(rectF, 270 - angle, angle * 2, false, paint)
        canvas.restore()

        val text = "$progress"
        val textHeight = textPaint.descent() + textPaint.ascent()
        canvas.drawText(text, (width - textPaint.measureText(text)) / 2.0f, (width - textHeight) / 2.0f, textPaint)
    }

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState())
        bundle.putInt(INSTANCE_TEXT_COLOR, textColor)
        bundle.putInt(INSTANCE_FINISHED_STROKE_COLOR, colorFill)
        bundle.putInt(INSTANCE_UNFINISHED_STROKE_COLOR, colorEmpty)
        bundle.putInt(INSTANCE_MAX, max)
        bundle.putInt(INSTANCE_PROGRESS, progress)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state is Bundle) {
            textColor = state.getInt(INSTANCE_TEXT_COLOR)
            colorFill = state.getInt(INSTANCE_FINISHED_STROKE_COLOR)
            colorEmpty = state.getInt(INSTANCE_UNFINISHED_STROKE_COLOR)
            max = state.getInt(INSTANCE_MAX)
            progress = state.getInt(INSTANCE_PROGRESS)
            super.onRestoreInstanceState(state.getParcelable<Parcelable>(INSTANCE_STATE))
            return
        }
        super.onRestoreInstanceState(state)
    }

    //Set any XML attributes that may have been specified
    private fun getXmlAttrs(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.CircleBlood, defStyleAttr, 0)

        colorFill = a.getColor(R.styleable.CircleBlood_colorFill, ContextCompat.getColor(context, R.color.darkRed))
        colorEmpty = a.getColor(R.styleable.CircleBlood_colorEmpty, Color.rgb(204, 204, 204))
        textColor = a.getColor(R.styleable.CircleBlood_textColor, Color.WHITE)

        a.recycle()
    }

    companion object {

        private val INSTANCE_STATE = "saved_instance"
        private val INSTANCE_TEXT_COLOR = "text_color"
        private val INSTANCE_FINISHED_STROKE_COLOR = "finished_stroke_color"
        private val INSTANCE_UNFINISHED_STROKE_COLOR = "unfinished_stroke_color"
        private val INSTANCE_MAX = "max"
        private val INSTANCE_PROGRESS = "progress"
    }
}