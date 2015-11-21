package amagi82.modularcharactersheetcreator.ui._extras;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.modules.Blood;
import amagi82.modularcharactersheetcreator.models.modules.Health;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.models.modules.Stat;
import amagi82.modularcharactersheetcreator.ui._base.BaseModuleViewModel;
import amagi82.modularcharactersheetcreator.ui._extras.utils.CircleIcon;
import amagi82.modularcharactersheetcreator.ui._extras.utils.ScreenSize;
import amagi82.modularcharactersheetcreator.ui._extras.utils.SplatIcon;
import amagi82.modularcharactersheetcreator.ui._extras.widgets.CircleBlood;
import amagi82.modularcharactersheetcreator.ui._extras.widgets.FocusAwareEditText;
import amagi82.modularcharactersheetcreator.ui._extras.widgets.NoSwipeViewPager;
import amagi82.modularcharactersheetcreator.ui._extras.widgets.RoundedStatBar;
import amagi82.modularcharactersheetcreator.ui._extras.widgets.RoundedStatBarBlock;
import amagi82.modularcharactersheetcreator.ui._extras.widgets.callbacks.EditTextListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class BindingAdapters {
    private static int screenWidth;
    private static int imageSize;

    //Load image from url, do not resize.
    @BindingAdapter("url")
    public static void loadImage(ImageView img, String url) {
        Glide.with(img.getContext()).load(url).into(img);
    }

    //Load splat image at a specific size. If size unknown, calculate best estimate before downloading.
    @BindingAdapter({"url", "size"})
    public static void loadImage(ImageView img, String url, float size) {
        if(size < 1){
            if(url == null) url = img.getResources().getString(R.string.url_default);
            size = Math.min(img.getHeight(), img.getWidth());
            if (size < 1) {
                int width = new ScreenSize(img.getContext()).getWidth();
                if (imageSize == 0 || screenWidth != width) {
                    screenWidth = width;
                    int margins = img.getResources().getDimensionPixelSize(R.dimen.card_margin) * 2;
                    int spanCount = img.getResources().getInteger(R.integer.character_axis_span_count);
                    int widthAvail = screenWidth - margins;
                    imageSize = (widthAvail - margins) / spanCount;
                }
                size = imageSize;
            }
        }
        Glide.with(img.getContext()).load(SplatIcon.getUrl(url, (int) size)).into(img);
    }

    //Load image from uri, with placeholder. Layout params and view bounds must be changed to size the image and placeholder correctly.
    @BindingAdapter({"url", "placeholder"})
    public static void loadImage(ImageView img, String url, Drawable placeholder) {
        img.getLayoutParams().width = (url == null) ? ViewGroup.LayoutParams.WRAP_CONTENT : ViewGroup.LayoutParams.MATCH_PARENT;
        img.setAdjustViewBounds(url != null);
        Glide.with(img.getContext()).load(url).placeholder(placeholder).into(img);
    }

    //Load circle icon for items in MainActivity
    @BindingAdapter("icon")
    public static void loadIcon(CircleImageView icon, GameCharacter character) {
        //noinspection ConstantConditions
        String imageUri = character.image() == null ? null : character.image().uri();
        if (imageUri == null) icon.setImageBitmap(new CircleIcon(icon.getContext()).createIcon(character.name()));
        else Glide.with(icon.getContext()).load(imageUri).centerCrop().into(icon);
    }

    //Tracks current page in EditActivity
    @BindingAdapter("page")
    public static void setCurrentPage(NoSwipeViewPager viewPager, int page) {
        boolean smoothScroll = Math.abs(page-viewPager.getCurrentItem()) < 2;
        viewPager.setCurrentItem(page, smoothScroll);
    }

    @BindingAdapter("fabShown")
    public static void setFabVisibility(final FloatingActionButton fab, boolean isShown) {
        if (isShown) new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                fab.show();
            }
        }, 500);
        else fab.hide();
    }

    @BindingAdapter("editTextListener")
    public static void addTextWatcher(FocusAwareEditText editText, EditTextListener listener) {
        if (listener != null) editText.setTextChangedListener(listener);
    }

    //Make sure the color resource exists before applying
    @BindingAdapter("android:textColor")
    public static void setTextColor(TextView textView, @ColorRes int colorRes) {
        if (colorRes != 0) textView.setTextColor(ContextCompat.getColor(textView.getContext(), colorRes));
    }

    //Make sure the string resource exists before applying
    @BindingAdapter("android:text")
    public static void setText(TextView textView, @StringRes int stringRes) {
        if (stringRes != 0) textView.setText(stringRes);
    }

    @BindingAdapter("statBlock")
    public static void setStatBlock(RoundedStatBarBlock statBarBlock, List<Stat> statBlock) {
        statBarBlock.setStats(statBlock);
    }

    @BindingAdapter("statBar")
    public static void setStatBar(RoundedStatBar statBar, Stat stat) {
        statBar.setStat(stat);
    }

    @BindingAdapter("healthBar")
    public static void setHealthBar(RoundedStatBar statBar, Health health) {
        statBar.setHealth(health);
    }

    @BindingAdapter("blood")
    public static void setBlood(CircleBlood circle, Blood blood){
        circle.setBlood(blood);
    }

    //Set grid layout manager to apply span count for each module
    @BindingAdapter("modules")
    public static void setLayoutManager(RecyclerView recyclerView, final List<BaseModuleViewModel> modules) {
        final GridLayoutManager manager = new GridLayoutManager(recyclerView.getContext(), Module.FULL);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override public int getSpanSize(int position) {
                return modules.get(position).spanCount;
            }
        });

        recyclerView.setLayoutManager(manager);
    }
}
