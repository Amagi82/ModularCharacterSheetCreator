package amagi82.modularcharactersheetcreator.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.RatingBar;

import java.util.List;

import amagi82.modularcharactersheetcreator.App;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.modules.Stat;

public class RoundedStatBarBlock extends RatingBar {

    private List<Stat> stats;
    private int heightLayout = 0;

    private int colorBackground;
    private int colorFill;
    private int colorFillSecondary;
    private int colorBorder;
    private int colorBorderInactive;
    private int textColor;
    private float textSize;
    private float textSizeSpecialty;
    private float gap;
    private float cornerRadius;
    private float xPos;
    private float yPosTitle;
    private float yPosSpecialty;
    private Paint paintFill = new Paint();
    private Paint paintBorder = new Paint();
    private Path path = new Path();
    private RectF rect = new RectF();
    private TextPaint textPaint = new TextPaint();

    public RoundedStatBarBlock(Context context){
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
        //textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        //textPaint.setTypeface(Typeface.DEFAULT_BOLD);

        for(Stat stat : stats){
            heightLayout += getResources().getDimensionPixelSize(R.dimen.round_stat_bar_height) + (stat.getSpecialty() == null ? 0 : (int) textSize + getPaddingBottom());
        }

        if (getRating() < ratingMin) setRating(ratingMin);

        if(colorBackground != App.NONE){
            GradientDrawable gradient = new GradientDrawable();
            gradient.setShape(GradientDrawable.RECTANGLE);
            gradient.setColor(colorBackground);
            gradient.setCornerRadius(cornerRadius + (gap + getPaddingTop()) / 2);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) setBackground(gradient);
            else //noinspection deprecation
                setBackgroundDrawable(gradient);
        }
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        float averagePadding = (getPaddingLeft() + getPaddingRight()) / getNumStars();
        rect.set(gap, getPaddingTop(), (width / getNumStars()) - (gap + averagePadding), height - getPaddingBottom() -
                (specialty == null ? 0 : textSize + getPaddingBottom()));

        if (title != null) {
            xPos = rect.centerX() * .5f;
            yPosTitle = rect.centerY() + textPaint.descent();
            if (specialty != null) yPosSpecialty = height - textPaint.descent() - getPaddingBottom();
        }

        setMeasuredDimension(widthSize, heightLayout);
    }

    @Override
    public void onDraw(@NonNull Canvas canvas) {

        path.addRoundRect(rect, cornerRadius, cornerRadius, Path.Direction.CW);

        for(Stat stat : stats){
            path.offset(getPaddingLeft() - gap, 0);
            float offset = (getWidth() - getPaddingRight()) / getNumStars();

            for (int i = 0; i < getNumStars(); i++) {
                if (i < getRating()) {
                    paintFill.setColor(i < stat.getValue() ? colorFill : colorFillSecondary);
                    canvas.drawPath(path, paintFill);
                }
                paintBorder.setColor(i < stat.getValueMax() ? colorBorder : colorBorderInactive);
                canvas.drawPath(path, paintBorder);

                path.offset(offset, 0);
            }
            if (stat.getCategory() != null) {
                canvas.drawText(stat.getCategory(), xPos, yPosTitle, textPaint);
                if (stat.getSpecialty() != null) {
                    textPaint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                    canvas.drawText(stat.getSpecialty(), xPos, yPosSpecialty, textPaint);
                }
            }

            path.rewind();
        }

    }

    //Set any XML attributes that may have been specified
    private void getXmlAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundedStatBar, 0, 0);
        try {
            colorBackground = a.getColor(R.styleable.RoundedStatBar_rsb_colorBackground, App.NONE);
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

    public void setColorBackground(int colorBackground) {
        this.colorBackground = colorBackground;
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