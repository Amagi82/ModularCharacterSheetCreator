package amagi82.modularcharactersheetcreator.models.modules;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import auto.parcel.AutoParcel;

/*
    Used to fill a single row of a stat block
 */
@AutoParcel
public abstract class Stat implements Parcelable {
    @NonNull public abstract String category();
    @Nullable public abstract String specialty();
    public abstract int valueMin();
    public abstract int value();
    public abstract int valueTemp();
    public abstract int valueMax();
    public abstract int numStars();

    public Stat() {}

    @AutoParcel.Builder
    public abstract static class Builder {
        public abstract Builder category(String category);
        public abstract Builder specialty(String specialty);
        public abstract Builder valueMin(int valueMin);
        public abstract Builder value(int value);
        public abstract Builder valueTemp(int valueTemp);
        public abstract Builder valueMax(int valueMax);
        public abstract Builder numStars(int numStars);
        public abstract Stat build();

        Builder() {}
    }

    public static Builder builder() {
        return new AutoParcel_Stat.Builder()
                .category("")
                .numStars(5);
    }

    public abstract Builder toBuilder();
}
