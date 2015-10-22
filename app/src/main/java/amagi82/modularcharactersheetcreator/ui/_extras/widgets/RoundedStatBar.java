package amagi82.modularcharactersheetcreator.ui._extras.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.RatingBar;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.modules.Stat;

public class RoundedStatBar extends RatingBar {

    private String title;
    private String specialty;
    private int ratingMin;
    private int ratingBase;
    private int ratingMax;
    private int colorBackground;
    private int colorFill;
    private int colorFillSecondary;
    private int colorBorder;
    private int colorBorderInactive;
    private int textColor;
    private float textSize;
    private float textSizeSpecialty;
//    private int healthBashing = 0;
//    private int healthLethal = 0;
//    private int healthAgg = 0;
    private float gap;
    private float cornerRadius;
    private float xPos;
    private float yPosTitle;
    private float yPosSpecialty;
    private final Paint paintFill = new Paint();
    private final Paint paintBorder = new Paint();
    private final Path path = new Path();
    private final RectF rect = new RectF();
    private final TextPaint textPaint = new TextPaint();

    public RoundedStatBar(Context context) {
        this(context, null);
    }

    public RoundedStatBar(Context context, AttributeSet attrs) {
        super(context, attrs);
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

        if (getRating() < ratingMin) setRating(ratingMin);

        if(colorBackground != 0){
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
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int height;
        int desiredHeight = getResources().getDimensionPixelSize(R.dimen.round_stat_bar_height) + (specialty == null ? 0 : (int) textSize + getPaddingBottom());
        if (heightMode == MeasureSpec.EXACTLY) height = heightSize;
        else if (heightMode == MeasureSpec.AT_MOST) height = Math.min(heightSize, desiredHeight);
        else height = desiredHeight;

        int width;
        int desiredWidth = height * getNumStars();
        if (widthMode == MeasureSpec.EXACTLY) width = widthSize;
        else if (widthMode == MeasureSpec.AT_MOST) width = widthSize;
        else width = desiredWidth;

        float averagePadding = (getPaddingLeft() + getPaddingRight()) / getNumStars();
        rect.set(gap, getPaddingTop(), (width / getNumStars()) - (gap + averagePadding), height - getPaddingBottom() -
                (specialty == null ? 0 : textSize + getPaddingBottom()));

        if (title != null) {
            xPos = rect.centerX() * .5f;
            yPosTitle = rect.centerY() + textPaint.descent();
            if (specialty != null) yPosSpecialty = height - textPaint.descent() - getPaddingBottom();
        }
        setMeasuredDimension(width, height);
    }

    @Override
    public void onDraw(@NonNull Canvas canvas) {
        path.rewind();
        path.addRoundRect(rect, cornerRadius, cornerRadius, Path.Direction.CW);

        path.offset(getPaddingLeft() - gap, 0);
        float offset = (getWidth() - getPaddingRight()) / getNumStars();

        for (int i = 0; i < getNumStars(); i++) {
            if (i < getRating()) {
                paintFill.setColor(i < ratingBase ? colorFill : colorFillSecondary);
                canvas.drawPath(path, paintFill);
            }
            paintBorder.setColor(i < ratingMax ? colorBorder : colorBorderInactive);
            canvas.drawPath(path, paintBorder);

            path.offset(offset, 0);

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
            canvas.drawText(title, xPos, yPosTitle, textPaint);
            if (specialty != null) {
                textPaint.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
                canvas.drawText(specialty, xPos, yPosSpecialty, textPaint);
            }
        }
    }

    //Set any XML attributes that may have been specified
    private void getXmlAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundedStatBar, 0, 0);
        Resources res = getResources();
        try {
            title = a.getString(R.styleable.RoundedStatBar_rsb_title);
            specialty = a.getString(R.styleable.RoundedStatBar_rsb_specialty);
            ratingMin = a.getInt(R.styleable.RoundedStatBar_rsb_ratingMin, 1);
            ratingBase = a.getInt(R.styleable.RoundedStatBar_rsb_ratingBase, (int) getRating());
            ratingMax = a.getInt(R.styleable.RoundedStatBar_rsb_ratingMax, getNumStars());
            colorBackground = a.getColor(R.styleable.RoundedStatBar_rsb_colorBackground, 0);
            colorFill = a.getColor(R.styleable.RoundedStatBar_rsb_colorFill, ContextCompat.getColor(context, R.color.round_stat_bar_fill));
            colorFillSecondary = a.getColor(R.styleable.RoundedStatBar_rsb_colorFillSecondary, ContextCompat.getColor(context, R.color.round_stat_bar_secondary));
            colorBorder = a.getColor(R.styleable.RoundedStatBar_rsb_colorBorder, ContextCompat.getColor(context, R.color.round_stat_bar_border));
            colorBorderInactive = a.getColor(R.styleable.RoundedStatBar_rsb_colorBorderInactive, ContextCompat.getColor(context, R.color.round_stat_bar_border_inactive));
            textColor = a.getColor(R.styleable.RoundedStatBar_rsb_textColor, ContextCompat.getColor(context, R.color.white));
            textSize = a.getDimension(R.styleable.RoundedStatBar_rsb_textSize, res.getDimension(R.dimen.round_stat_bar_text_size));
            textSizeSpecialty = a.getDimension(R.styleable.RoundedStatBar_rsb_textSizeSpecialty, res.getDimension(R.dimen.round_stat_bar_specialty_text_size));
            gap = a.getDimension(R.styleable.RoundedStatBar_rsb_gap, res.getDimension(R.dimen.round_stat_bar_gap));
            cornerRadius = a.getDimension(R.styleable.RoundedStatBar_rsb_cornerRadius, res.getDimension(R.dimen.round_stat_bar_corner_radius));
        } finally {
            a.recycle();
        }
    }

    public void setStat(Stat stat){
        //TODO: set this up to get everything from a stat
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setRatingMin(int ratingMin) {
        this.ratingMin = ratingMin;
    }

    public void setRatingBase(int ratingBase) {
        this.ratingBase = ratingBase;
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

    public void setRatingMax(int ratingMax) {
        this.ratingMax = ratingMax;
    }

    public void setColorBorder(int colorBorder) {
        this.colorBorder = colorBorder;
    }

    public void setColorBorderInactive(int colorBorderInactive) {
        this.colorBorderInactive = colorBorderInactive;
    }
}