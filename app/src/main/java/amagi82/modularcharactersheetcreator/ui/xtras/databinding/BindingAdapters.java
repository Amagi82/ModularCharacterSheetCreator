package amagi82.modularcharactersheetcreator.ui.xtras.databinding;

import android.databinding.BindingAdapter;
import android.support.annotation.StringRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import static amagi82.modularcharactersheetcreator.ui.main.MainActivity.NONE;

public class BindingAdapters {

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView img, @StringRes int url){
        if(url != NONE) Glide.with(img.getContext()).load(img.getContext().getString(url)).into(img);
    }
}
