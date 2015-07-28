package amagi82.modularcharactersheetcreator.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.modules.Stat;

public class RoundedStatBarBlock extends ProgressBar {

    private List<Stat> stats;
    private int colorFill;
    private int colorFillSecondary;
    private int colorBorder;
    private int colorBorderInactive;
    private int textColor;
    private int textColorSpecialty;
    private int rowHeight; //Standard height of a row
    private int extraRowHeight; //Extra height if needed for Specialty
    private int barWidth; //Actual layoutWidth of the stat bar. Same as the layoutWidth if the Category text overlaps it
    private int layoutWidth; //Measured layoutWidth available to the view
    private int textSpacing;
    private int rowSpacing;
    private float textSize;
    private float textSizeSpecialty;
    private float gap; //Gap between stat boxes
    private float cornerRadius;
    private float boxOffset; //distance to offset the stat boxes in the x direction
    private boolean categoryOverlapsStatBar;
    private boolean specialtyTakesSecondLine;
    private Path path = new Path(); //Path for the stat boxes
    private RectF statBox = new RectF(); //Used to measure stat boxes
    static Rect textBox = new Rect(); //Used to measure text layoutWidth
    static Paint paintFill = new Paint();
    static Paint paintBorder = new Paint();
    static TextPaint textPaint = new TextPaint();
    static TextPaint textPaintItalic = new TextPaint();

    public RoundedStatBarBlock(Context context) {
        this(context, null);
    }

    public RoundedStatBarBlock(Context context, AttributeSet attrs) {
        super(context, attrs);
        getXmlAttrs(context, attrs);

        paintFill.setStyle(Paint.Style.FILL);
        paintFill.setAntiAlias(true);
        paintBorder.setStyle(Paint.Style.STROKE);
        paintBorder.setAntiAlias(true);
        paintBorder.setStrokeWidth(2);

        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        textPaint.setLinearText(true);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);

        textPaintItalic.setFlags(Paint.ANTI_ALIAS_FLAG);
        textPaintItalic.setLinearText(true);
        textPaintItalic.setTextSize(textSizeSpecialty);
        textPaintItalic.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
        textPaintItalic.setColor(textColorSpecialty);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int layoutHeight = 0;
        int maxCategoryWidth = 0;
        int maxSpecialtyWidth = 0;
        int minBarWidth = (getResources().getDimensionPixelSize(R.dimen.round_stat_bar_height) + (int) gap) * getMax();
        textSpacing = getResources().getDimensionPixelSize(R.dimen.round_stat_bar_text_spacing);
        layoutWidth = MeasureSpec.getSize(widthMeasureSpec);
        rowHeight = getResources().getDimensionPixelSize(R.dimen.round_stat_bar_height);
        extraRowHeight = (int) textSizeSpecialty + textSpacing;
        rowSpacing = getResources().getDimensionPixelSize(R.dimen.round_stat_bar_row_spacing);

        //Get maximum layoutWidth of Category and Specialty text
        for (Stat stat : stats) {
            textPaint.getTextBounds(stat.getCategory(), 0, stat.getCategory().length(), textBox);
            maxCategoryWidth = Math.max(maxCategoryWidth, textBox.width());
            if (stat.getSpecialty() != null) {
                textPaintItalic.getTextBounds(stat.getSpecialty(), 0, stat.getSpecialty().length(), textBox);
                maxSpecialtyWidth = Math.max(maxSpecialtyWidth, textBox.width());
            }
        }

        //Determine if there is sufficient space to put the text to the left of the stat bar
        categoryOverlapsStatBar = minBarWidth + maxCategoryWidth + textSpacing > layoutWidth;
        specialtyTakesSecondLine = minBarWidth + maxCategoryWidth + maxSpecialtyWidth + (2 * textSpacing) > layoutWidth;

        //Determine how tall the layout needs to be
        if (specialtyTakesSecondLine && maxSpecialtyWidth > 0) {
            for (Stat stat : stats) layoutHeight += rowHeight + rowSpacing + (stat.getSpecialty() == null ? 0 : extraRowHeight);
        } else layoutHeight = (rowHeight + rowSpacing) * stats.size();

        //Get the size of the stat box
        barWidth = categoryOverlapsStatBar ? layoutWidth : minBarWidth;
        statBox.set(gap, getPaddingTop(), (barWidth / getMax()) - gap, rowHeight - getPaddingBottom());
        boxOffset = (barWidth) / getMax();

        setMeasuredDimension(layoutWidth, layoutHeight);
    }

    @Override
    public void onDraw(@NonNull Canvas canvas) {
        //Center the text
        float xPosCategory = categoryOverlapsStatBar ? statBox.centerX() * .5f : 0;
        float yPosCategory = statBox.centerY() - ((textPaint.descent() + textPaint.ascent()) / 2);
        float xPosSpecialty = statBox.centerX();
        float yPosSpecialty = specialtyTakesSecondLine ? rowHeight + (rowSpacing/2) - textPaintItalic.ascent() : yPosCategory;
        float verticalOffsetTotal = 0;

        for (Stat stat : stats) {
            path.rewind();
            path.addRoundRect(statBox, cornerRadius, cornerRadius, Path.Direction.CW);
            path.offset(layoutWidth - barWidth, verticalOffsetTotal);

            for (int i = 0; i < getMax(); i++) {
                if (i < stat.getValueTemporary()) {
                    paintFill.setColor(i < stat.getValue() ? colorFill : colorFillSecondary);
                    canvas.drawPath(path, paintFill);
                }
                paintBorder.setColor(i < stat.getValueMax() ? colorBorder : colorBorderInactive);
                canvas.drawPath(path, paintBorder);

                path.offset(boxOffset, 0);
            }
            canvas.drawText(stat.getCategory(), xPosCategory, yPosCategory, textPaint);
            if (stat.getSpecialty() != null) {
                textPaint.getTextBounds(stat.getCategory(), 0, stat.getCategory().length(), textBox);
                if (!specialtyTakesSecondLine) xPosSpecialty = textBox.width() + textSpacing;
                canvas.drawText(stat.getSpecialty(), xPosSpecialty, yPosSpecialty, textPaintItalic);
            }
            float verticalOffset = rowHeight + rowSpacing + ((specialtyTakesSecondLine && stat.getSpecialty() != null) ? extraRowHeight : 0);
            verticalOffsetTotal += verticalOffset;
            yPosCategory += verticalOffset;
            yPosSpecialty += verticalOffset;
        }
    }

    //Set any XML attributes that may have been specified
    private void getXmlAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundedStatBar, 0, 0);
        try {
            colorFill = a.getColor(R.styleable.RoundedStatBar_rsb_colorFill, getResources().getColor(R.color.round_stat_bar_fill));
            colorFillSecondary = a.getColor(R.styleable.RoundedStatBar_rsb_colorFillSecondary, getResources().getColor(R.color.round_stat_bar_secondary));
            colorBorder = a.getColor(R.styleable.RoundedStatBar_rsb_colorBorder, getResources().getColor(R.color.round_stat_bar_border));
            colorBorderInactive = a.getColor(R.styleable.RoundedStatBar_rsb_colorBorderInactive, getResources().getColor(R.color.round_stat_bar_border_inactive));
            textColor = a.getColor(R.styleable.RoundedStatBar_rsb_textColor, getResources().getColor(R.color.round_stat_bar_text_color));
            textColorSpecialty = a.getColor(R.styleable.RoundedStatBar_rsb_textColorSpecialty, getResources().getColor(R.color.round_stat_bar_text_color_specialty));
            textSize = a.getDimension(R.styleable.RoundedStatBar_rsb_textSize, getResources().getDimension(R.dimen.round_stat_bar_text_size));
            textSizeSpecialty = a.getDimension(R.styleable.RoundedStatBar_rsb_textSizeSpecialty, getResources().getDimension(R.dimen.round_stat_bar_specialty_text_size));
            gap = a.getDimension(R.styleable.RoundedStatBar_rsb_gap, getResources().getDimension(R.dimen.round_stat_bar_gap));
            cornerRadius = a.getDimension(R.styleable.RoundedStatBar_rsb_cornerRadius, getResources().getDimension(R.dimen.round_stat_bar_corner_radius));
        } finally {
            a.recycle();
        }
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
        invalidate();
    }

    public void setColorFill(int colorFill) {
        this.colorFill = colorFill;
    }

    public void setColorFillSecondary(int colorFillSecondary) {
        this.colorFillSecondary = colorFillSecondary;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setTextColorSpecialty(int textColorSpecialty) {
        this.textColorSpecialty = textColorSpecialty;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public void setTextSizeSpecialty(float textSizeSpecialty) {
        this.textSizeSpecialty = textSizeSpecialty;
    }

    public void setGap(float gap) {
        this.gap = gap;
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public void setColorBorder(int colorBorder) {
        this.colorBorder = colorBorder;
    }

    public void setColorBorderInactive(int colorBorderInactive) {
        this.colorBorderInactive = colorBorderInactive;
    }
}