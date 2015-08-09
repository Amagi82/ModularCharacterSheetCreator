package amagi82.modularcharactersheetcreator.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;

import amagi82.modularcharactersheetcreator.R;

public class CircleIcon {

    private Resources res;
    private int circleImageSize;
    private TextPaint textPaint;
    private int textColor;

    public CircleIcon(Resources res) {
        this.res = res;
        circleImageSize = res.getDimensionPixelSize(R.dimen.circle_icon_size);
        textPaint = new TextPaint();
        textColor = getColor(R.color.white);
    }

    public Bitmap createIcon(String name){
        return createIcon(name, getColor(R.color.primary));
    }

    public Bitmap createIcon(String name, int backgroundColor){

        String firstLetter = name.length() > 0? name.substring(0,1) : "";

        textPaint.reset();
        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setLinearText(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(res.getDimension(R.dimen.circle_icon_text_size));

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
        return res.getColor(colorId);
    }
}
