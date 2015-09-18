package amagi82.modularcharactersheetcreator.ui.xtras.databinding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Collection;

import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.ui.base.BindingRecyclerViewAdapter;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.CircleIcon;
import de.hdodenhof.circleimageview.CircleImageView;

import static amagi82.modularcharactersheetcreator.ui.main.MainActivity.NONE;

public class BindingAdapters {
    private static final int KEY = -123;

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView img, @StringRes int url){
        if(url != NONE && url != 0) Glide.with(img.getContext()).load(img.getContext().getString(url)).into(img);
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView img, String url){
        Glide.with(img.getContext()).load(url).into(img);
    }

    @BindingAdapter("bind:loadIcon")
    public static void loadIcon(CircleImageView icon, GameCharacter character){
        if(character != null){
            Uri imageUri = character.image() == null ? null : character.image().uri();
            Context context = icon.getContext();
            if (imageUri == null) icon.setImageBitmap(new CircleIcon(context.getResources()).createIcon(character.name()));
            else Glide.with(context).load(imageUri).centerCrop().into(icon);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("items")
    public static <T> void setItems(RecyclerView recyclerView, Collection<T> items) {
        BindingRecyclerViewAdapter<T> adapter = (BindingRecyclerViewAdapter<T>) recyclerView.getAdapter();
        if (adapter != null) adapter.setItems(items);
        else recyclerView.setTag(KEY, items);
    }


    @SuppressWarnings("unchecked")
    @BindingAdapter("itemViewBinder")
    public static <T> void setItemViewBinder(RecyclerView recyclerView, ItemBinder<T> itemViewMapper) {
        Collection<T> items = (Collection<T>) recyclerView.getTag(KEY);
        recyclerView.setAdapter(new BindingRecyclerViewAdapter<>(itemViewMapper, items));
    }
}
