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

import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.CircleIcon;
import de.hdodenhof.circleimageview.CircleImageView;

import static amagi82.modularcharactersheetcreator.ui.main.MainActivity.NONE;

public class BindingAdapters {

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView img, @StringRes int url){
        if(url != NONE && url != 0) Glide.with(img.getContext()).load(img.getContext().getString(url)).into(img);
        else Glide.clear(img);
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView img, String url){
        Glide.with(img.getContext()).load(url).into(img);
    }

    @BindingAdapter("bind:imageUri")
    public static void loadImage(ImageView img, Uri uri){
        Glide.with(img.getContext()).load(uri).into(img);
    }

    @BindingAdapter("bind:loadIcon")
    public static void loadIcon(CircleImageView icon, GameCharacter character){
        if(character != null){
            @SuppressWarnings("ConstantConditions") Uri imageUri = character.image() == null ? null : character.image().uri();
            Context context = icon.getContext();
            if (imageUri == null) icon.setImageBitmap(new CircleIcon(context).createIcon(character.name()));
            else Glide.with(context).load(imageUri).centerCrop().into(icon);
        }
    }

    @BindingAdapter("android:textColor")
    public static void setTextColor(TextView textView, @ColorRes int colorRes){
        if(colorRes != 0) textView.setTextColor(ContextCompat.getColor(textView.getContext(), colorRes));
    }

    @BindingAdapter("android:text")
    public static void setText(TextView textView, @StringRes int stringRes){
        if(stringRes != 0) textView.setText(stringRes);
    }
}
