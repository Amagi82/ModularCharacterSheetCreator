package amagi82.modularcharactersheetcreator.bindings;

import android.databinding.BindingAdapter;
import android.support.annotation.StringRes;
import android.util.Log;

import com.bumptech.glide.Glide;

import amagi82.modularcharactersheetcreator.widgets.ImageBackdrop;

import static amagi82.modularcharactersheetcreator.activities.MainActivity.NONE;

public class CustomBindings {

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageBackdrop img, @StringRes int url){
        Log.i(null, "loadImage called");
        if(url != NONE) Glide.with(img.getContext()).load(img.getContext().getString(url)).into(img);
        else Log.i(null, "loadImage not completed: NONE");
    }
}
