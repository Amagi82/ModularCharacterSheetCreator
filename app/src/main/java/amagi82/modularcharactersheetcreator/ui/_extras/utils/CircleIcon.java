package amagi82.modularcharactersheetcreator.ui._extras.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;

import amagi82.modularcharactersheetcreator.R;

public class CircleIcon {
    private final Context context;
    private final int circleImageSize;

    public CircleIcon(Context context) {
        this.context = context;
        circleImageSize = context.getResources().getDimensionPixelSize(R.dimen.circle_icon_size);
    }

    public Bitmap createIcon(String name){
        return createIcon(name, getColor(R.color.primary));
    }

    public Bitmap createIcon(String name, int backgroundColor){
        String firstLetter = name.length() > 0? name.substring(0,1) : "";

        TextPaint textPaint = new TextPaint();
        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setLinearText(true);
        textPaint.setColor(getColor(R.color.white));
        textPaint.setTextSize(context.getResources().getDimension(R.dimen.circle_icon_text_size));

        Rect rect = new Rect();
        rect.set(0, 0, circleImageSize, circleImageSize);

        int xPos = rect.centerX();
        int yPos = (int) (rect.centerY() - (textPaint.descent() + textPaint.ascent()) * .5);

        Bitmap bitmap = Bitmap.createBitmap(circleImageSize, circleImageSize, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(backgroundColor);
        canvas.drawText(firstLetter, xPos, yPos, textPaint);

        return bitmap;
    }

    private int getColor(int colorId){
        return ContextCompat.getColor(context, colorId);
    }
}