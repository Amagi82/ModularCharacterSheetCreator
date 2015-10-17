package amagi82.modularcharactersheetcreator.ui.xtras.databinding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.CircleIcon;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.EditTextListener;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.ScreenSize;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.SplatIcon;
import amagi82.modularcharactersheetcreator.ui.xtras.widgets.FocusAwareEditText;
import de.hdodenhof.circleimageview.CircleImageView;

import static amagi82.modularcharactersheetcreator.ui.main.MainActivity.NONE;

public class BindingAdapters {
    private static int screenWidth;
    private static int imageSize;

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView img, @StringRes int url) {
        if (url != NONE && url != 0) Glide.with(img.getContext()).load(img.getContext().getString(url)).into(img);
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
        if (url != NONE && url != 0) Glide.with(img.getContext()).load(new SplatIcon(img.getResources().getString(url), size).getUrl()).into(img);
    }

    @BindingAdapter("imageUri")
    public static void loadImage(ImageView img, Uri uri) {
        Glide.with(img.getContext()).load(uri).into(img);
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

    @BindingAdapter({"format", "argId"})
    public static void setFormattedText(TextView textView, String format, int argId){
        if(argId == 0) return;
        textView.setText(String.format(format, textView.getResources().getString(argId)));
    }

    @BindingAdapter("editTextListener")
    public static void addTextWatcher(FocusAwareEditText editText, EditTextListener listener){
        if(listener != null) editText.setTextChangedListener(listener);
    }

    @BindingAdapter("android:textColor")
    public static void setTextColor(TextView textView, @ColorRes int colorRes) {
        if (colorRes != 0) textView.setTextColor(ContextCompat.getColor(textView.getContext(), colorRes));
    }

    @BindingAdapter("android:text")
    public static void setText(TextView textView, @StringRes int stringRes) {
        if (stringRes != 0) textView.setText(stringRes);
    }
}
