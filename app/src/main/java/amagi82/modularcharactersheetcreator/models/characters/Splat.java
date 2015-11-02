package amagi82.modularcharactersheetcreator.models.characters;

import android.os.Parcelable;
import android.support.annotation.StringRes;

import amagi82.modularcharactersheetcreator.R;
import auto.parcelgson.AutoParcelGson;

/*
    Splats are a generic term for factions or groups in the Storyteller/Storytelling system. Also known as "character axis".
 */
@AutoParcelGson
public abstract class Splat implements Parcelable{

    private static final int DEFAULT_URL = R.string.url_default;

    public abstract @StringRes int title();
    public abstract @StringRes int url();
    public abstract boolean isEndPoint();

    Splat() {}

    public static Splat create(@StringRes int title) {
        return new AutoParcelGson_Splat(title, DEFAULT_URL, true);
    }

    public static Splat create(@StringRes int title, boolean isEndPoint) {
        return new AutoParcelGson_Splat(title, DEFAULT_URL, isEndPoint);
    }

    public static Splat create(@StringRes int title, @StringRes int url) {
        return new AutoParcelGson_Splat(title, url, true);
    }
}
