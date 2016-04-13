package amagi82.modularcharactersheetcreator.ui._extras.widgets

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/*
    ViewPager with swiping disabled by default
 */
class NoSwipeViewPager(context: Context, attrs: AttributeSet? = null) : ViewPager(context, attrs) {

    //private var enabled = false

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return isEnabled && super.onTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return isEnabled && super.onInterceptTouchEvent(event)
    }

    fun setPagingEnabled(enabled: Boolean) {
        isEnabled = enabled
    }
}
