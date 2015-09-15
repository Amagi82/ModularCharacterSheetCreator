package amagi82.modularcharactersheetcreator.ui.extras.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/*
    Convenience class to measure an ImageView for the aspect ratio of the Game System splash screens
 */
public class ImageBackdrop extends ImageView {

    public ImageBackdrop(Context context) {
        super(context);
    }

    public ImageBackdrop(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageBackdrop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP) public ImageBackdrop(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width * 154 / 305;
        setMeasuredDimension(width, height);
    }
}
