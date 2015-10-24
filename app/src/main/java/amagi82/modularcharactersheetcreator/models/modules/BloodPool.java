package amagi82.modularcharactersheetcreator.models.modules;

import android.os.Parcelable;
import android.support.annotation.IntRange;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class BloodPool implements Parcelable{
    @IntRange(from = 0, to = 50) public abstract int currentBlood();
    @IntRange(from = 10, to = 50) public abstract int maxBlood();
    @IntRange(from = 1, to = 10) public abstract int bloodPerTurn();

    public BloodPool() {}

    public static BloodPool createDefault(){
        return builder().build();
    }

    @AutoParcel.Builder
    public abstract static class Builder {
        public abstract Builder currentBlood(int currentBlood);
        public abstract Builder maxBlood(int maxBlood);
        public abstract Builder bloodPerTurn(int bloodPerTurn);
        public abstract BloodPool build();

        Builder() {}
    }

    public static Builder builder() {
        return new AutoParcel_BloodPool.Builder()
                .currentBlood(7)
                .maxBlood(10)
                .bloodPerTurn(1);
    }

    public abstract Builder toBuilder();
}