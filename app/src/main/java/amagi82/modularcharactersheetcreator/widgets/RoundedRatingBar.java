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
import android.util.Log;
import android.widget.RatingBar;

import amagi82.modularcharactersheetcreator.R;

public class RoundedRatingBar extends RatingBar {

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
//    private int healthBashing = 0;
//    private int healthLethal = 0;
//    private int healthAgg = 0;
    private float gap;
    private float cornerRadius;
    private float xPos;
    private float yPos;
    private Paint paintFill = new Paint();
    private Paint paintBorder = new Paint();
    private Path path = new Path();
    private RectF rect = new RectF();
    private TextPaint textPaint = new TextPaint();

    public RoundedRatingBar(Context context) {
        this(context, null);
    }

    public RoundedRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        getXmlAttrs(context, attrs);

        paintFill.setStyle(Paint.Style.FILL);
        paintFill.setAntiAlias(true);
        paintBorder.setStyle(Paint.Style.STROKE);
        paintBorder.setAntiAlias(true);
        paintBorder.setStrokeWidth(2);

        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        textPaint.setLinearText(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);

        if(getRating() < ratingMin) setRating(ratingMin);

        GradientDrawable gradient = new GradientDrawable();
        gradient.setShape(GradientDrawable.RECTANGLE);
        gradient.setColor(colorBackground);
        gradient.setCornerRadius(cornerRadius);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) setBackground(gradient);
        else //noinspection deprecation
            setBackgroundDrawable(gradient);

    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height;
        int desiredHeight = getResources().getDimensionPixelSize(specialty == null? R.dimen.round_rating_bar_height : R.dimen.round_rating_bar_extended_height);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (heightMode == MeasureSpec.EXACTLY) height = heightSize;
        else if (heightMode == MeasureSpec.AT_MOST) height = Math.min(heightSize, desiredHeight);
        else height = desiredHeight;

        float averagePadding = (getPaddingLeft()+getPaddingRight())/getNumStars();
        rect.set(gap + averagePadding, getPaddingTop(), (width / getNumStars()) - (gap + averagePadding), height - getPaddingBottom());

        xPos = rect.centerX() * 0.5f;
        yPos = rect.centerY() - (textPaint.descent() + textPaint.ascent()) * .5f;

        setMeasuredDimension(width, height);
    }

    @Override
    public void onDraw(@NonNull Canvas canvas) {
        path.rewind();
        path.addRoundRect(rect, cornerRadius, cornerRadius, Path.Direction.CW);

        float offset = (getWidth() - getPaddingLeft() - getPaddingRight()) / getNumStars();
        path.offset(getPaddingLeft() -gap, 0);

        for (int i=0;i<getNumStars();i++) {
            //path.offset((i + .5F) * getWidth() / getNumStars() - rect.centerX(), 0);
            if(i < getRating()) {
                paintFill.setColor(i <= ratingBase? colorFill : colorFillSecondary);
                canvas.drawPath(path, paintFill);
            }
            paintBorder.setColor(i <= ratingMax? colorBorder : colorBorderInactive);
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
        if(title != null) canvas.drawText(title, xPos, yPos, textPaint);
    }

    //Set any XML attributes that may have been specified
    private void getXmlAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundedRatingBar, 0, 0);
        try {
            title = a.getString(R.styleable.RoundedRatingBar_rrb_title);
            specialty = a.getString(R.styleable.RoundedRatingBar_rrb_specialty);
            ratingMin = a.getInt(R.styleable.RoundedRatingBar_rrb_ratingMin, 1);
            ratingBase = a.getInt(R.styleable.RoundedRatingBar_rrb_ratingBase, (int) getRating());
            ratingMax = a.getInt(R.styleable.RoundedRatingBar_rrb_ratingMax, getNumStars());
            colorBackground = a.getColor(R.styleable.RoundedRatingBar_rrb_colorBackground, getResources().getColor(R.color.round_rating_bar_background));
            colorFill = a.getColor(R.styleable.RoundedRatingBar_rrb_colorFill, getResources().getColor(R.color.round_rating_bar_fill));
            colorFillSecondary = a.getColor(R.styleable.RoundedRatingBar_rrb_colorFillSecondary, getResources().getColor(R.color.round_rating_bar_secondary));
            colorBorder = a.getColor(R.styleable.RoundedRatingBar_rrb_colorBorder, getResources().getColor(R.color.round_rating_bar_border));
            colorBorderInactive = a.getColor(R.styleable.RoundedRatingBar_rrb_colorBorderInactive, getResources().getColor(R.color.round_rating_bar_border_inactive));
            textColor = a.getColor(R.styleable.RoundedRatingBar_rrb_textColor, getResources().getColor(R.color.white));
            textSize = a.getDimension(R.styleable.RoundedRatingBar_rrb_textSize, getResources().getDimension(R.dimen.round_rating_bar_text_size));
            gap = a.getDimension(R.styleable.RoundedRatingBar_rrb_gap, getResources().getDimension(R.dimen.round_rating_bar_gap));
            cornerRadius = a.getDimension(R.styleable.RoundedRatingBar_rrb_cornerRadius, getResources().getDimension(R.dimen.round_rating_bar_corner_radius));
        } finally {
            a.recycle();
        }
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

    public void setGap(float gap) {
        this.gap = gap;
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }
}