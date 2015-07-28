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
import android.util.Log;
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
    private int rowHeight; //Standard height of a row
    private int extraRowHeight; //Extra height if needed for Specialty
    private int barWidth; //Actual width of the stat bar. Same as the width if the Category text overlaps it
    private int width; //Measured width available to the view
    private float textSize;
    private float textSizeSpecialty;
    private float gap; //Gap between stat boxes
    private float cornerRadius;
    private float xPos; //x position of the text
    private float yPosCategory;
    private float yPosSpecialty;
    private float boxOffset; //distance to offset the stat boxes in the x direction
    private boolean categoryOverlapsStatBar;
    private boolean specialtyTakesSecondLine;
    static Paint paintFill = new Paint();
    static Paint paintBorder = new Paint();
    static Path path = new Path(); //Path for the stat boxes
    static Rect textBox = new Rect(); //Used to measure text width
    static RectF statBox = new RectF(); //Used to measure stat boxes
    static TextPaint textPaint = new TextPaint();
    static TextPaint textPaintItalic = new TextPaint();

    public RoundedStatBarBlock(Context context) {
        this(context, null, null);
    }

    public RoundedStatBarBlock(Context context, List<Stat> stats) {
        this(context, stats, null);
    }

    public RoundedStatBarBlock(Context context, List<Stat> stats, AttributeSet attrs) {
        super(context, attrs);
        this.stats = stats;
        getXmlAttrs(context, attrs);

        paintFill.setStyle(Paint.Style.FILL);
        paintFill.setAntiAlias(true);
        paintBorder.setStyle(Paint.Style.STROKE);
        paintBorder.setAntiAlias(true);
        paintBorder.setStrokeWidth(2);

        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        textPaint.setLinearText(true);
        textPaint.setTextSize(textSize);
        //textPaint.setColor(textColor);
        //textPaint.setTypeface(Typeface.DEFAULT_BOLD);

        textPaintItalic.setFlags(Paint.ANTI_ALIAS_FLAG);
        textPaintItalic.setLinearText(true);
        textPaintItalic.setTextSize(textSizeSpecialty);
        textPaintItalic.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightLayout = 0;
        int maxCategoryWidth = 0;
        int maxSpecialtyWidth = 0;
        int minBarWidth = getResources().getDimensionPixelSize(R.dimen.round_stat_bar_height) * getMax();
        int textSpacing = getResources().getDimensionPixelSize(R.dimen.round_stat_bar_text_spacing);
        width = MeasureSpec.getSize(widthMeasureSpec);
        rowHeight = getResources().getDimensionPixelSize(R.dimen.round_stat_bar_height);
        extraRowHeight = (int) textSizeSpecialty + getPaddingBottom();

        //Get maximum width of Category and Specialty text
        for (Stat stat : stats) {
            textPaint.getTextBounds(stat.getCategory(), 0, stat.getCategory().length(), textBox);
            maxCategoryWidth = Math.max(maxCategoryWidth, textBox.width());
            textPaintItalic.getTextBounds(stat.getSpecialty(), 0, stat.getSpecialty().length(), textBox);
            maxSpecialtyWidth = Math.max(maxSpecialtyWidth, textBox.width());
        }
        Log.i(null, "maxCategoryWidth = " + maxCategoryWidth);
        Log.i(null, "maxSpecialtyWidth = " + maxSpecialtyWidth);

        //Determine if there is sufficient space to put the text to the left of the stat bar
        categoryOverlapsStatBar = minBarWidth + maxCategoryWidth + textSpacing > width;
        specialtyTakesSecondLine = minBarWidth + maxCategoryWidth + maxSpecialtyWidth + (2 * textSpacing) > width;

        Log.i(null, "categoryOverlapsStatBar = "+categoryOverlapsStatBar);
        Log.i(null, "specialtyTakesSecondLine = "+specialtyTakesSecondLine);

        //Determine how tall the layout needs to be
        if (specialtyTakesSecondLine && maxSpecialtyWidth > 0) {
            for (Stat stat : stats)
                heightLayout += rowHeight + (stat.getSpecialty() == null ? 0 : extraRowHeight);
        } else heightLayout = rowHeight * stats.size();

        //Get the size of the stat box
        barWidth = categoryOverlapsStatBar? width : specialtyTakesSecondLine? width - maxCategoryWidth - textSpacing :
                width - maxCategoryWidth - textSpacing - maxSpecialtyWidth - textSpacing;
        float averagePadding = (getPaddingLeft() + getPaddingRight()) / getMax();
        statBox.set(gap, getPaddingTop(), (barWidth / getMax()) - (gap + averagePadding), rowHeight - getPaddingBottom());
        boxOffset = (barWidth - getPaddingRight()) / getMax();

        //Center the text
        xPos = categoryOverlapsStatBar? statBox.centerX() * .5f : 0;
        yPosCategory = statBox.centerY() - ((textPaint.descent() + textPaint.ascent()) / 2);
        if (maxSpecialtyWidth > 0) yPosSpecialty = specialtyTakesSecondLine? rowHeight + textPaint.descent() + getPaddingBottom() : yPosCategory;

        setMeasuredDimension(width, heightLayout);
    }

    @Override
    public void onDraw(@NonNull Canvas canvas) {
        path.rewind();
        path.addRoundRect(statBox, cornerRadius, cornerRadius, Path.Direction.CW);
        path.offset(width - barWidth + getPaddingLeft() - gap, 0);

        for (Stat stat : stats) {
            for (int i = 0; i < getMax(); i++) {
                if (i < stat.getValueTemporary()) {
                    paintFill.setColor(i < stat.getValue() ? colorFill : colorFillSecondary);
                    canvas.drawPath(path, paintFill);
                }
                paintBorder.setColor(i < stat.getValueMax() ? colorBorder : colorBorderInactive);
                canvas.drawPath(path, paintBorder);

                path.offset(boxOffset, 0);
            }
            canvas.drawText(stat.getCategory(), xPos, yPosCategory, textPaint);
            if (stat.getSpecialty() != null) canvas.drawText(stat.getSpecialty(), xPos, yPosSpecialty, textPaintItalic);

            int verticalOffset = rowHeight + ((specialtyTakesSecondLine && stat.getSpecialty() != null)? extraRowHeight : 0);
            path.offset(-barWidth, verticalOffset);
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
            textColor = a.getColor(R.styleable.RoundedStatBar_rsb_textColor, getResources().getColor(R.color.white));
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