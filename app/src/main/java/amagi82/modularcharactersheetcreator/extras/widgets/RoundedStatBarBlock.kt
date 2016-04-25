package amagi82.modularcharactersheetcreator.extras.widgets

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.modules.Stat
import android.content.Context
import android.graphics.*
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import java.util.*

class RoundedStatBarBlock @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ProgressBar(context, attrs) {

    private val res = resources
    private var stats: List<Stat> = ArrayList()
    @ColorInt private var colorFill: Int = 0
    @ColorInt private var colorFillSecondary: Int = 0
    @ColorInt private var colorBorder: Int = 0
    @ColorInt private var colorBorderInactive: Int = 0
    @ColorInt private var textColor: Int = 0
    @ColorInt private var textColorSpecialty: Int = 0
    private val textSpacing: Int
    private val rowSpacing: Int
    private val rowHeight: Int //Standard height of a row
    private val extraRowHeight: Int //Extra height if needed for Specialty
    private var barWidth: Int = 0 //Actual layoutWidth of the stat bar. Same as the layoutWidth if the Category text overlaps it
    private var layoutWidth: Int = 0 //Measured layoutWidth available to the view
    private var textSize: Float = 0.toFloat()
    private var textSizeSpecialty: Float = 0.toFloat()
    private var gap: Float = 0.toFloat() //Gap between stat boxes
    private var cornerRadius: Float = 0.toFloat()
    private var boxOffset: Float = 0.toFloat() //distance to offset the stat boxes in the x direction
    private var categoryOverlapsStatBar: Boolean = false
    private var specialtyTakesSecondLine: Boolean = false
    private val path = Path() //Path for the stat boxes
    private val statBox = RectF() //Used to measure stat boxes
    private val textBox = Rect() //Used to measure text layoutWidth
    private var paintFill: Paint = Paint()
    private var paintBorder: Paint = Paint()
    private var textPaint: TextPaint = TextPaint()
    private var textPaintItalic: TextPaint = TextPaint()

    init {
        getXmlAttrs(context, attrs)

        paintFill.style = Paint.Style.FILL
        paintFill.isAntiAlias = true

        paintBorder.style = Paint.Style.STROKE
        paintBorder.isAntiAlias = true
        paintBorder.strokeWidth = 2f

        textPaint = TextPaint()
        textPaint.flags = Paint.ANTI_ALIAS_FLAG
        textPaint.isLinearText = true
        textPaint.textSize = textSize
        textPaint.color = textColor

        textPaintItalic = TextPaint()
        textPaintItalic.flags = Paint.ANTI_ALIAS_FLAG
        textPaintItalic.isLinearText = true
        textPaintItalic.textSize = textSizeSpecialty
        textPaintItalic.typeface = Typeface.defaultFromStyle(Typeface.ITALIC)
        textPaintItalic.color = textColorSpecialty

        textSpacing = res.getDimensionPixelSize(R.dimen.round_stat_bar_text_spacing)
        rowSpacing = res.getDimensionPixelSize(R.dimen.round_stat_bar_row_spacing)
        rowHeight = res.getDimensionPixelSize(R.dimen.round_stat_bar_height)
        extraRowHeight = textSizeSpecialty.toInt() + textSpacing
    }

    @Synchronized override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var layoutHeight = 0
        var maxCategoryWidth = 0
        var maxSpecialtyWidth = 0
        val minBarWidth = (res.getDimensionPixelSize(R.dimen.round_stat_bar_height) + gap.toInt()) * max
        layoutWidth = View.MeasureSpec.getSize(widthMeasureSpec)

        //Get maximum layoutWidth of Category and Specialty text
        for (stat in stats) {
            textPaint.getTextBounds(stat.category, 0, stat.category.length, textBox)
            maxCategoryWidth = Math.max(maxCategoryWidth, textBox.width())
            if (stat.specialty != null) {
                textPaintItalic.getTextBounds(stat.specialty, 0, stat.specialty.length, textBox)
                maxSpecialtyWidth = Math.max(maxSpecialtyWidth, textBox.width())
            }
        }

        //Determine if there is sufficient space to put the text to the leftId of the stat bar
        categoryOverlapsStatBar = minBarWidth + maxCategoryWidth + textSpacing > layoutWidth
        specialtyTakesSecondLine = minBarWidth + maxCategoryWidth + maxSpecialtyWidth + 2 * textSpacing > layoutWidth

        //Determine how tall the layout needs to be
        if (specialtyTakesSecondLine && maxSpecialtyWidth > 0) {
            for (stat in stats) layoutHeight += rowHeight + rowSpacing + if (stat.specialty == null) 0 else extraRowHeight
        } else
            layoutHeight = (rowHeight + rowSpacing) * stats.size

        //Get the size of the stat box
        barWidth = if (categoryOverlapsStatBar) layoutWidth else minBarWidth
        statBox.set(gap, paddingTop.toFloat(), barWidth / max - gap, (rowHeight - paddingBottom).toFloat())
        boxOffset = (barWidth / max).toFloat()

        setMeasuredDimension(layoutWidth, layoutHeight)
    }

    public override fun onDraw(canvas: Canvas) {
        //Center the text
        val xPosCategory = if (categoryOverlapsStatBar) statBox.centerX() * .5f else 0f
        var yPosCategory = statBox.centerY() - (textPaint.descent() + textPaint.ascent()) / 2
        var xPosSpecialty = statBox.centerX()
        var yPosSpecialty = if (specialtyTakesSecondLine) rowHeight + rowSpacing / 2 - textPaintItalic.ascent() else yPosCategory
        var verticalOffsetTotal = 0f

        for (stat in stats) {
            path.rewind()
            path.addRoundRect(statBox, cornerRadius, cornerRadius, Path.Direction.CW)
            path.offset((layoutWidth - barWidth).toFloat(), verticalOffsetTotal)

            for (i in 0..max - 1) {
                if (i < stat.valueTemp) {
                    paintFill.color = if (i < stat.value) colorFill else colorFillSecondary
                    canvas.drawPath(path, paintFill)
                }
                paintBorder.color = if (i < stat.valueMax) colorBorder else colorBorderInactive
                canvas.drawPath(path, paintBorder)

                path.offset(boxOffset, 0f)
            }
            canvas.drawText(stat.category, xPosCategory, yPosCategory, textPaint)
            if (stat.specialty != null) {
                textPaint.getTextBounds(stat.category, 0, stat.category.length, textBox)
                if (!specialtyTakesSecondLine) xPosSpecialty = (textBox.width() + textSpacing).toFloat()
                canvas.drawText(stat.specialty, xPosSpecialty, yPosSpecialty, textPaintItalic)
            }
            val verticalOffset = rowHeight + rowSpacing + (if (specialtyTakesSecondLine && stat.specialty != null) extraRowHeight else 0).toFloat()
            verticalOffsetTotal += verticalOffset
            yPosCategory += verticalOffset
            yPosSpecialty += verticalOffset
        }
    }

    //Set any XML attributes that may have been specified
    private fun getXmlAttrs(context: Context, attrs: AttributeSet?) {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.RoundedStatBar, 0, 0)
        try {
            colorFill = a.getColor(R.styleable.RoundedStatBar_rsb_colorFill, ContextCompat.getColor(context, R.color.round_stat_bar_fill))
            colorFillSecondary = a.getColor(R.styleable.RoundedStatBar_rsb_colorFillSecondary, ContextCompat.getColor(context, R.color.round_stat_bar_secondary))
            colorBorder = a.getColor(R.styleable.RoundedStatBar_rsb_colorBorder, ContextCompat.getColor(context, R.color.round_stat_bar_border))
            colorBorderInactive = a.getColor(R.styleable.RoundedStatBar_rsb_colorBorderInactive, ContextCompat.getColor(context, R.color.round_stat_bar_border_inactive))
            textColor = a.getColor(R.styleable.RoundedStatBar_rsb_textColor, ContextCompat.getColor(context, R.color.round_stat_bar_text_color))
            textColorSpecialty = a.getColor(R.styleable.RoundedStatBar_rsb_textColorSpecialty, ContextCompat.getColor(context, R.color.round_stat_bar_text_color_specialty))
            textSize = a.getDimension(R.styleable.RoundedStatBar_rsb_textSize, res.getDimension(R.dimen.round_stat_bar_text_size))
            textSizeSpecialty = a.getDimension(R.styleable.RoundedStatBar_rsb_textSizeSpecialty, res.getDimension(R.dimen.round_stat_bar_specialty_text_size))
            gap = a.getDimension(R.styleable.RoundedStatBar_rsb_gap, res.getDimension(R.dimen.round_stat_bar_gap))
            cornerRadius = a.getDimension(R.styleable.RoundedStatBar_rsb_cornerRadius, res.getDimension(R.dimen.round_stat_bar_corner_radius))
        } finally {
            a.recycle()
        }
    }

    fun setStats(stats: List<Stat>) {
        this.stats = stats
        max = 1
        for (stat in stats) {
            if (stat.numStars > max) max = stat.numStars
        }
        invalidate()
    }
}