package amagi82.modularcharactersheetcreator.extras

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.extras.utils.CircleIcon
import amagi82.modularcharactersheetcreator.extras.utils.ScreenSize
import amagi82.modularcharactersheetcreator.extras.utils.SplatIcon
import amagi82.modularcharactersheetcreator.extras.widgets.*
import amagi82.modularcharactersheetcreator.extras.widgets.callbacks.EditTextListener
import amagi82.modularcharactersheetcreator.models.GameCharacter
import amagi82.modularcharactersheetcreator.models.modules.Blood
import amagi82.modularcharactersheetcreator.models.modules.Health
import amagi82.modularcharactersheetcreator.models.modules.Stat
import amagi82.modularcharactersheetcreator.ui._base.BaseModuleViewModel
import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.os.Handler
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

object BindingAdapters {
    private var screenWidth: Int = 0
    private var imageSize: Int = 0

    //Load image from url, do not resize.
    @BindingAdapter("url")
    fun loadImage(img: ImageView, url: String) {
        Glide.with(img.context).load(url).into(img)
    }

    //Load splat image at a specific size. If size unknown, calculate best estimate before downloading.
    @BindingAdapter("url", "size")
    fun loadImage(img: ImageView, url: String?, size: Float) {
        var url = url
        var size = size
        if (size < 1) {
            if (url == null) url = img.resources.getString(R.string.url_default)
            size = Math.min(img.height, img.width).toFloat()
            if (size < 1) {
                val width = ScreenSize(img.context).width
                if (imageSize == 0 || screenWidth != width) {
                    screenWidth = width
                    val margins = img.resources.getDimensionPixelSize(R.dimen.card_margin) * 2
                    val spanCount = img.resources.getInteger(R.integer.character_axis_span_count)
                    val widthAvail = screenWidth - margins
                    imageSize = (widthAvail - margins) / spanCount
                }
                size = imageSize.toFloat()
            }
        }
        Glide.with(img.context).load(SplatIcon.getUrl(url, size.toInt())).into(img)
    }

    //Load image from uri, with placeholder. Layout params and view bounds must be changed to size the image and placeholder correctly.
    @BindingAdapter("url", "placeholder")
    fun loadImage(img: ImageView, url: String?, placeholder: Drawable) {
        img.layoutParams.width = if (url == null) ViewGroup.LayoutParams.WRAP_CONTENT else ViewGroup.LayoutParams.MATCH_PARENT
        img.adjustViewBounds = url != null
        Glide.with(img.context).load(url).placeholder(placeholder).into(img)
    }

    //Load circle icon for items in MainActivity
    @BindingAdapter("icon")
    fun loadIcon(icon: CircleImageView, character: GameCharacter) {
        //noinspection ConstantConditions
        val imageUri = character.image?.uri
        if (imageUri == null)
            icon.setImageBitmap(CircleIcon(icon.context).createIcon(character.name))
        else
            Glide.with(icon.context).load(imageUri).centerCrop().into(icon)
    }

    //Tracks current page in EditActivity
    @BindingAdapter("page")
    fun setCurrentPage(viewPager: NoSwipeViewPager, page: Int) {
        val smoothScroll = Math.abs(page - viewPager.currentItem) < 2
        viewPager.setCurrentItem(page, smoothScroll)
    }

    @BindingAdapter("fabShown")
    fun setFabVisibility(fab: FloatingActionButton, isShown: Boolean) {
        if (isShown)
            Handler().postDelayed({ fab.show() }, 500)
        else
            fab.hide()
    }

    @BindingAdapter("editTextListener")
    fun addTextWatcher(editText: FocusAwareEditText, listener: EditTextListener?) {
        if (listener != null) editText.setTextChangedListener(listener)
    }

    //Make sure the color resource exists before applying
    @BindingAdapter("android:textColor")
    fun setTextColor(textView: TextView, @ColorRes colorRes: Int) {
        if (colorRes != 0) textView.setTextColor(ContextCompat.getColor(textView.context, colorRes))
    }

    //Make sure the string resource exists before applying
    @BindingAdapter("android:text")
    fun setText(textView: TextView, @StringRes stringRes: Int) {
        if (stringRes != 0) textView.setText(stringRes)
    }

    @BindingAdapter("statBlock")
    fun setStatBlock(statBarBlock: RoundedStatBarBlock, statBlock: List<Stat>) {
        statBarBlock.setStats(statBlock)
    }

    @BindingAdapter("statBar")
    fun setStatBar(statBar: RoundedStatBar, stat: Stat) {
        statBar.setStat(stat)
    }

    @BindingAdapter("healthBar")
    fun setHealthBar(statBar: RoundedStatBar, health: Health) {
        statBar.setHealth(health)
    }

    @BindingAdapter("blood")
    fun setBlood(circle: CircleBlood, blood: Blood) {
        circle.setBlood(blood)
    }

    //Set grid layout manager to apply span count for each module
    @BindingAdapter("modules")
    fun setLayoutManager(recyclerView: RecyclerView, modules: List<BaseModuleViewModel>) {
        val manager = GridLayoutManager(recyclerView.context, FULL)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return modules[position].spanCount
            }
        }

        recyclerView.layoutManager = manager
    }
}
