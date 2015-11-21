package amagi82.modularcharactersheetcreator.ui._extras.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.modules.Blood;

public class CircleBlood extends View {
    private int textColor;
    private int progress;
    private int max;
    private int colorFill;
    private int colorEmpty;

    private final Paint textPaint = new TextPaint();
    private final Paint paint = new Paint();
    private final RectF rectF = new RectF();

    private static final String INSTANCE_STATE = "saved_instance";
    private static final String INSTANCE_TEXT_COLOR = "text_color";
    private static final String INSTANCE_FINISHED_STROKE_COLOR = "finished_stroke_color";
    private static final String INSTANCE_UNFINISHED_STROKE_COLOR = "unfinished_stroke_color";
    private static final String INSTANCE_MAX = "max";
    private static final String INSTANCE_PROGRESS = "progress";

    public CircleBlood(Context context) {
        this(context, null);
    }

    public CircleBlood(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleBlood(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getXmlAttrs(context, attrs, defStyleAttr);

        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);
        paint.setAntiAlias(true);
    }

    public void setBlood(Blood blood){
        max = blood.maxBlood();
        progress = blood.currentBlood();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = MeasureSpec.getSize(widthMeasureSpec);
        rectF.set(0, 0, size, size);
        textPaint.setTextSize(size / 1.9f);
        setMeasuredDimension(size, size);
    }

    @Override protected void onDraw(Canvas canvas) {
        float yHeight = progress / (float) max * getHeight();
        float radius = getWidth() / 2f;
        float angle = (float) (Math.acos((radius - yHeight) / radius) * 180 / Math.PI);
        float startAngle = 90 + angle;
        float sweepAngle = 360 - angle * 2;
        paint.setColor(colorEmpty);
        canvas.drawArc(rectF, startAngle, sweepAngle, false, paint);

        canvas.save();
        canvas.rotate(180, getWidth() / 2, getHeight() / 2);
        paint.setColor(colorFill);
        canvas.drawArc(rectF, 270 - angle, angle * 2, false, paint);
        canvas.restore();

        String text = progress + "";
        float textHeight = textPaint.descent() + textPaint.ascent();
        canvas.drawText(text, (getWidth() - textPaint.measureText(text)) / 2.0f, (getWidth() - textHeight) / 2.0f, textPaint);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putInt(INSTANCE_TEXT_COLOR, textColor);
        bundle.putInt(INSTANCE_FINISHED_STROKE_COLOR, colorFill);
        bundle.putInt(INSTANCE_UNFINISHED_STROKE_COLOR, colorEmpty);
        bundle.putInt(INSTANCE_MAX, max);
        bundle.putInt(INSTANCE_PROGRESS, progress);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if(state instanceof Bundle) {
            final Bundle bundle = (Bundle) state;
            textColor = bundle.getInt(INSTANCE_TEXT_COLOR);
            colorFill = bundle.getInt(INSTANCE_FINISHED_STROKE_COLOR);
            colorEmpty = bundle.getInt(INSTANCE_UNFINISHED_STROKE_COLOR);
            max = bundle.getInt(INSTANCE_MAX);
            progress = bundle.getInt(INSTANCE_PROGRESS);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATE));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    //Set any XML attributes that may have been specified
    private void getXmlAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleBlood, defStyleAttr, 0);

        colorFill = a.getColor(R.styleable.CircleBlood_colorFill, ContextCompat.getColor(context, R.color.darkRed));
        colorEmpty = a.getColor(R.styleable.CircleBlood_colorEmpty, Color.rgb(204, 204, 204));
        textColor = a.getColor(R.styleable.CircleBlood_textColor, Color.WHITE);

        a.recycle();
    }
}