package amagi82.modularcharactersheetcreator.models;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.List;

import amagi82.modularcharactersheetcreator.models.modules.Module;
import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class Sheet implements Parcelable{
    @NonNull public abstract String title();
    @NonNull public abstract List<Module> modules();

    Sheet() {}

    public static Sheet create(String title, List<Module> modules) {
        return new AutoParcelGson_Sheet(title, modules);
    }
}