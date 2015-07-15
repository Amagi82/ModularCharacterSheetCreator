package amagi82.modularcharactersheetcreator.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.RatingBar;

import amagi82.modularcharactersheetcreator.R;

public class RoundedRatingBar extends RatingBar {

    private String title;
    private String specialty;
    private int ratingMin;
    private int ratingBase;
    private int colorBackground;
    private int colorFill;
    private int colorFillSecondary;
    private int textColor;
    private float textSize;
//    private int healthBashing = 0;
//    private int healthLethal = 0;
//    private int healthAgg = 0;
    private float padding;
    private float cornerRadius;
    private float xPos;
    private float yPos;
    private float dp = getResources().getDisplayMetrics().density;
    private Paint paintFillTemp = new Paint();
    private Paint paintFill = new Paint();
    private Path path = new Path();
    private RectF rect = new RectF();
    private TextPaint textPaint = new TextPaint();

    public RoundedRatingBar(Context context) {
        this(context, null);
    }

    public RoundedRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        getXmlAttrs(context, attrs);

        paintFill.setStyle(Paint.Style.FILL_AND_STROKE);
        paintFill.setAntiAlias(true);
        paintFill.setColor(colorFill);
        paintFillTemp.setStyle(Paint.Style.FILL_AND_STROKE);
        paintFillTemp.setAntiAlias(true);
        paintFillTemp.setColor(colorFillSecondary);

        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        textPaint.setLinearText(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);

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
        int desiredHeight = (int)((specialty == null? 36 : 24) * dp);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (heightMode == MeasureSpec.EXACTLY) height = heightSize;
        else if (heightMode == MeasureSpec.AT_MOST) height = Math.min(heightSize, desiredHeight);
        else height = desiredHeight;

        rect.set(0, 0, (width / getNumStars()) - padding, height - padding);
        path.addRoundRect(rect, cornerRadius, cornerRadius, Path.Direction.CW);
        xPos = rect.centerX();
        yPos = (int) (rect.centerY() - (textPaint.descent() + textPaint.ascent()) * .5);

        setMeasuredDimension(width, height);
    }

    @Override
    public void onDraw(@NonNull Canvas canvas) {
        for (int i=0;i<getRating();++i) {
            path.offset((i + .5F) * getWidth() / getNumStars() - rect.centerX(), 0);
            canvas.drawPath(path, i <= ratingBase? paintFill : paintFillTemp);

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
            title = a.getString(R.styleable.RoundedRatingBar_title);
            specialty = a.getString(R.styleable.RoundedRatingBar_specialty);
            ratingMin = a.getInt(R.styleable.RoundedRatingBar_ratingMin, 1);
            ratingBase = a.getInt(R.styleable.RoundedRatingBar_ratingBase, 1);
            colorBackground = a.getColor(R.styleable.RoundedRatingBar_colorBackground, getResources().getColor(R.color.translucent_light_grey));
            colorFill = a.getColor(R.styleable.RoundedRatingBar_colorFill, getResources().getColor(R.color.translucent_grey));
            colorFillSecondary = a.getColor(R.styleable.RoundedRatingBar_colorFillSecondary, getResources().getColor(R.color.translucent_grey));
            textColor = a.getColor(R.styleable.RoundedRatingBar_textColor, getResources().getColor(R.color.white));
            textSize = a.getDimension(R.styleable.RoundedRatingBar_textSize, getResources().getDimension(R.dimen.abc_text_size_small_material));
            padding = a.getDimension(R.styleable.RoundedRatingBar_padding, getResources().getDimension(R.dimen.default_tiny_margin));
            cornerRadius = a.getDimension(R.styleable.RoundedRatingBar_cornerRadius, getResources().getDimension(R.dimen.default_tiny_margin));
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

    public void setPadding(float padding) {
        this.padding = padding;
    }

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }
}