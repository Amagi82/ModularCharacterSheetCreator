package amagi82.modularcharactersheetcreator.extras.widgets

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/*
    ViewPager with swiping disabled by default
 */
class NoSwipeViewPager(context: Context, attrs: AttributeSet? = null) : ViewPager(context, attrs) {

    var pagingEnabled = false

    override fun onInterceptTouchEvent(event: MotionEvent) = pagingEnabled && super.onTouchEvent(event)

    override fun onTouchEvent(event: MotionEvent) = pagingEnabled && super.onInterceptTouchEvent(event)
}
