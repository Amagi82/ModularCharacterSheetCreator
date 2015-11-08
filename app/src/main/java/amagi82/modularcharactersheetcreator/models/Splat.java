package amagi82.modularcharactersheetcreator.models;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import auto.parcelgson.AutoParcelGson;

/*
    Splats are a generic term for factions or groups in the Storyteller/Storytelling gameId. Also known as "character axis".
 */
@AutoParcelGson
public abstract class Splat implements Parcelable{
    @NonNull public abstract String title();
    @Nullable public abstract String url();
    public abstract boolean isEndPoint();

    Splat() {}

    public static Splat create(String title) {
        return new AutoParcelGson_Splat(title, null, true);
    }

    public static Splat create(String title, boolean isEndPoint) {
        return new AutoParcelGson_Splat(title, null, isEndPoint);
    }

    public static Splat create(String title, String url) {
        return new AutoParcelGson_Splat(title, url, true);
    }
}
