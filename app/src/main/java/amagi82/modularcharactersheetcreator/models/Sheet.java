package amagi82.modularcharactersheetcreator.models;

import android.os.Parcelable;

import java.util.List;

import amagi82.modularcharactersheetcreator.models.modules.Module;
import auto.parcel.AutoParcel;

@AutoParcel
public abstract class Sheet implements Parcelable{

    public abstract String title();
    public abstract int numColumns();
    public abstract List<Module> modules();

    public static Sheet create(String title, int numColumns, List<Module> modules) {
        return new AutoParcel_Sheet(title, numColumns, modules);
    }

    public static Sheet create(String title, List<Module> modules) {
        return new AutoParcel_Sheet(title, 3, modules);
    }
}
