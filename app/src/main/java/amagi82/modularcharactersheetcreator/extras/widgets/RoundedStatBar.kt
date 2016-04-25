package amagi82.modularcharactersheetcreator.extras.widgets

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.modules.Health
import amagi82.modularcharactersheetcreator.models.modules.Stat
import android.content.Context
import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.support.v4.content.ContextCompat
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.widget.RatingBar

class RoundedStatBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : RatingBar(context, attrs) {

    private var title: String? = null
    private var specialty: String? = null
    private var ratingMin: Int = 0
    private var ratingBase: Int = 0
    private var ratingMax: Int = 0
    private var colorBackground: Int = 0
    private var colorFill: Int = 0
    private var colorFillSecondary: Int = 0
    private var colorBorder: Int = 0
    private var colorBorderInactive: Int = 0
    private var textColor: Int = 0
    private var textSize: Float = 0.toFloat()
    private var textSizeSpecialty: Float = 0.toFloat()
    //    private int healthBashing = 0;
    //    private int healthLethal = 0;
    //    private int healthAgg = 0;
    private var gap: Float = 0.toFloat()
    private var cornerRadius: Float = 0.toFloat()
    private var xPos: Float = 0.toFloat()
    private var yPosTitle: Float = 0.toFloat()
    private var yPosSpecialty: Float = 0.toFloat()
    private val paintFill = Paint()
    private val paintBorder = Paint()
    private val path = Path()
    private val rect = RectF()
    private val textPaint = TextPaint()

    init {
        getXmlAttrs(context, attrs)

        paintFill.style = Paint.Style.FILL
        paintFill.isAntiAlias = true
        paintBorder.style = Paint.Style.STROKE
        paintBorder.isAntiAlias = true
        paintBorder.strokeWidth = 2f

        textPaint.flags = Paint.ANTI_ALIAS_FLAG
        textPaint.isLinearText = true
        //textPaint.setColor(textColor);
        textPaint.textSize = textSize
        //textPaint.setTypeface(Typeface.DEFAULT_BOLD);

        if (rating < ratingMin) rating = ratingMin.toFloat()

        if (colorBackground != 0) {
            val gradient = GradientDrawable()
            gradient.setShape(GradientDrawable.RECTANGLE)
            gradient.setColor(colorBackground)
            gradient.setCornerRadius(cornerRadius + (gap + paddingTop) / 2)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                background = gradient
            else
            //noinspection deprecation
                setBackgroundDrawable(gradient)
        }
    }

    @Synchronized override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)

        val height: Int
        val desiredHeight = resources.getDimensionPixelSize(R.dimen.round_stat_bar_height) + if (specialty == null) 0 else textSize.toInt() + paddingBottom
        if (heightMode == View.MeasureSpec.EXACTLY)
            height = heightSize
        else if (heightMode == View.MeasureSpec.AT_MOST)
            height = Math.min(heightSize, desiredHeight)
        else
            height = desiredHeight

        val width: Int
        val desiredWidth = height * numStars
        if (widthMode == View.MeasureSpec.EXACTLY)
            width = widthSize
        else if (widthMode == View.MeasureSpec.AT_MOST)
            width = widthSize
        else
            width = desiredWidth

        val averagePadding = ((paddingLeft + paddingRight) / numStars).toFloat()
        rect.set(gap, paddingTop.toFloat(), width / numStars - (gap + averagePadding), height.toFloat() - paddingBottom.toFloat() - (if (specialty == null) 0f else textSize + paddingBottom))

        if (title != null) {
            xPos = rect.centerX() * .5f
            yPosTitle = rect.centerY() + textPaint.descent()
            if (specialty != null) yPosSpecialty = height.toFloat() - textPaint.descent() - paddingBottom.toFloat()
        }
        setMeasuredDimension(width, height)
    }

    public override fun onDraw(canvas: Canvas) {
        path.rewind()
        path.addRoundRect(rect, cornerRadius, cornerRadius, Path.Direction.CW)

        path.offset(paddingLeft - gap, 0f)
        val offset = ((width - paddingRight) / numStars).toFloat()

        for (i in 0..numStars - 1) {
            if (i < rating) {
                paintFill.color = if (i < ratingBase) colorFill else colorFillSecondary
                canvas.drawPath(path, paintFill)
            }
            paintBorder.color = if (i < ratingMax) colorBorder else colorBorderInactive
            canvas.drawPath(path, paintBorder)

            path.offset(offset, 0f)

            //            if(barType == BarType.HEALTHBAR){
            //                boolean agg = i<healthAgg;
            //                boolean lethal = i<healthAgg+healthLethal;
            //                boolean bashing = i<healthAgg+healthLethal+healthBashing;
            //
            //                path = createPathHealthBox(shapeSize, agg? Healthbox.EMPTY : lethal? Healthbox.X : bashing? Healthbox.CHECK : Healthbox.EMPTY);
            //
            //                path.computeBounds(rectangle, true);
            //                if(!vertical){
            //                    path.offset((i + .5F) * getWidth() / getNumStars() - rectangle.centerX(), getHeight() / 2 - rectangle.centerY());
            //                }else path.offset(getWidth() / 2 - rectangle.centerX(), (i + .5F) * getHeight() / getNumStars() - rectangle.centerY());
            //
            //                if(agg) canvas.drawPath(path, paintInside);
            //                canvas.drawPath(path, paintOutline);
            //            }else {
        }
        if (title != null) {
            canvas.drawText(title, xPos, yPosTitle, textPaint)
            if (specialty != null) {
                textPaint.typeface = Typeface.defaultFromStyle(Typeface.ITALIC)
                canvas.drawText(specialty, xPos, yPosSpecialty, textPaint)
            }
        }
    }

    //Set any XML attributes that may have been specified
    private fun getXmlAttrs(context: Context, attrs: AttributeSet?) {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.RoundedStatBar, 0, 0)
        val res = resources
        try {
            title = a.getString(R.styleable.RoundedStatBar_rsb_title)
            specialty = a.getString(R.styleable.RoundedStatBar_rsb_specialty)
            ratingMin = a.getInt(R.styleable.RoundedStatBar_rsb_ratingMin, 1)
            ratingBase = a.getInt(R.styleable.RoundedStatBar_rsb_ratingBase, rating.toInt())
            ratingMax = a.getInt(R.styleable.RoundedStatBar_rsb_ratingMax, numStars)
            colorBackground = a.getColor(R.styleable.RoundedStatBar_rsb_colorBackground, 0)
            colorFill = a.getColor(R.styleable.RoundedStatBar_rsb_colorFill, ContextCompat.getColor(context, R.color.round_stat_bar_fill))
            colorFillSecondary = a.getColor(R.styleable.RoundedStatBar_rsb_colorFillSecondary, ContextCompat.getColor(context, R.color.round_stat_bar_secondary))
            colorBorder = a.getColor(R.styleable.RoundedStatBar_rsb_colorBorder, ContextCompat.getColor(context, R.color.round_stat_bar_border))
            colorBorderInactive = a.getColor(R.styleable.RoundedStatBar_rsb_colorBorderInactive, ContextCompat.getColor(context, R.color.round_stat_bar_border_inactive))
            textColor = a.getColor(R.styleable.RoundedStatBar_rsb_textColor, ContextCompat.getColor(context, R.color.white))
            textSize = a.getDimension(R.styleable.RoundedStatBar_rsb_textSize, res.getDimension(R.dimen.round_stat_bar_text_size))
            textSizeSpecialty = a.getDimension(R.styleable.RoundedStatBar_rsb_textSizeSpecialty, res.getDimension(R.dimen.round_stat_bar_specialty_text_size))
            gap = a.getDimension(R.styleable.RoundedStatBar_rsb_gap, res.getDimension(R.dimen.round_stat_bar_gap))
            cornerRadius = a.getDimension(R.styleable.RoundedStatBar_rsb_cornerRadius, res.getDimension(R.dimen.round_stat_bar_corner_radius))
        } finally {
            a.recycle()
        }
    }

    fun setStat(stat: Stat) {
        //TODO: set this up to get everything from a stat
    }

    fun setHealth(health: Health) {
        //TODO: set this up
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun setSpecialty(specialty: String) {
        this.specialty = specialty
    }

}