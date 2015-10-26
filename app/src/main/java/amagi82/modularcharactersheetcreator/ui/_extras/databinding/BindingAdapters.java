package amagi82.modularcharactersheetcreator.ui._extras.databinding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.modules.Health;
import amagi82.modularcharactersheetcreator.models.modules.Stat;
import amagi82.modularcharactersheetcreator.ui._base.BaseModuleViewModel;
import amagi82.modularcharactersheetcreator.ui._extras.utils.CircleIcon;
import amagi82.modularcharactersheetcreator.ui._extras.utils.ScreenSize;
import amagi82.modularcharactersheetcreator.ui._extras.utils.SplatIcon;
import amagi82.modularcharactersheetcreator.ui._extras.widgets.FocusAwareEditText;
import amagi82.modularcharactersheetcreator.ui._extras.widgets.NoSwipeViewPager;
import amagi82.modularcharactersheetcreator.ui._extras.widgets.RoundedStatBar;
import amagi82.modularcharactersheetcreator.ui._extras.widgets.RoundedStatBarBlock;
import amagi82.modularcharactersheetcreator.ui._extras.widgets.callbacks.EditTextListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class BindingAdapters {
    private static int screenWidth;
    private static int imageSize;

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView img, @StringRes int url) {
        if (url != 0) Glide.with(img.getContext()).load(img.getContext().getString(url)).into(img);
        else Glide.clear(img);
    }

    @BindingAdapter("splatIconUrl")
    public static void loadSplatIcon(ImageView img, @StringRes int url) {
        int size = Math.min(img.getHeight(), img.getWidth());
        if (size == 0) {
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
        if (url != 0) Glide.with(img.getContext()).load(new SplatIcon(img.getResources().getString(url), size).getUrl()).into(img);
    }

    @BindingAdapter("imageUri")
    public static void loadImage(ImageView img, Uri uri) {
        img.getLayoutParams().width = uri == null ? ViewGroup.LayoutParams.WRAP_CONTENT : ViewGroup.LayoutParams.MATCH_PARENT;
        img.setAdjustViewBounds(uri != null);
        Glide.with(img.getContext()).load(uri).placeholder(R.drawable.ic_person_24dp).into(img);
    }

    @BindingAdapter("characterPortrait")
    public static void loadCharacterPortrait(ImageView img, Uri uri) {
        Glide.with(img.getContext()).load(uri).into(img);
    }

    @BindingAdapter("visibility")
    public static void setViewVisibility(View view, Uri uri){
        view.setVisibility(uri == null? View.GONE : View.VISIBLE);
    }

    @BindingAdapter("loadIcon")
    public static void loadIcon(CircleImageView icon, GameCharacter character) {
        if (character != null) {
            @SuppressWarnings("ConstantConditions") Uri imageUri = character.image() == null ? null : character.image().uri();
            Context context = icon.getContext();
            if (imageUri == null) icon.setImageBitmap(new CircleIcon(context).createIcon(character.name()));
            else Glide.with(context).load(imageUri).centerCrop().into(icon);
        }
    }

    @BindingAdapter("page")
    public static void setCurrentPage(NoSwipeViewPager viewPager, int page) {
        viewPager.setCurrentItem(page);
    }

    @BindingAdapter("fab")
    public static void setFabVisibility(final FloatingActionButton fab, boolean isShown) {
        if (isShown) new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                fab.show();
            }
        }, 500);
        else fab.hide();
    }

    @BindingAdapter({"format", "argId"})
    public static void setFormattedText(TextView textView, String format, int argId) {
        if (argId == 0) return;
        textView.setText(String.format(format, textView.getResources().getString(argId)));
    }

    @BindingAdapter("editTextListener")
    public static void addTextWatcher(FocusAwareEditText editText, EditTextListener listener) {
        if (listener != null) editText.setTextChangedListener(listener);
    }

    @BindingAdapter("android:textColor")
    public static void setTextColor(TextView textView, @ColorRes int colorRes) {
        if (colorRes != 0) textView.setTextColor(ContextCompat.getColor(textView.getContext(), colorRes));
    }

    @BindingAdapter("android:text")
    public static void setText(TextView textView, @StringRes int stringRes) {
        if (stringRes != 0) textView.setText(stringRes);
    }

    @BindingAdapter("statBlock")
    public static void setStatBlock(RoundedStatBarBlock statBarBlock, List<Stat> statBlock){
        statBarBlock.setStats(statBlock);
    }

    @BindingAdapter("statBar")
    public static void setStatBar(RoundedStatBar statBar, Stat stat){
        statBar.setStat(stat);
    }

    @BindingAdapter("healthBar")
    public static void setHealthBar(RoundedStatBar statBar, Health health){
        statBar.setHealth(health);
    }

    @BindingAdapter({"columns", "modules"})
    public static void setLayoutManager(RecyclerView recyclerView, int numColumns, final List<BaseModuleViewModel> modules){
        final GridLayoutManager manager = new GridLayoutManager(recyclerView.getContext(), numColumns);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override public int getSpanSize(int position) {
                return Math.min(manager.getSpanCount(), modules.get(position).spanCount);
            }
        });
        recyclerView.setLayoutManager(manager);
    }
}
