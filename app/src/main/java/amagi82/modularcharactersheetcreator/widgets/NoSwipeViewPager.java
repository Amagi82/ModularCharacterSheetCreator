package amagi82.modularcharactersheetcreator.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/*
    ViewPager with swiping disabled by default
 */
public class NoSwipeViewPager extends ViewPager{

    private boolean enabled = false;

    public NoSwipeViewPager(Context context) {
        super(context);
    }

    public NoSwipeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override public boolean onInterceptTouchEvent(MotionEvent event) {
        return enabled && super.onTouchEvent(event);
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        return enabled && super.onInterceptTouchEvent(event);
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    //Convenience methods
    public void nextPage(){
        setCurrentItem(getCurrentItem() + 1);
    }

    public void previousPage(){
        setCurrentItem(getCurrentItem() - 1);
    }
}
