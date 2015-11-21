package amagi82.modularcharactersheetcreator.models.modules;

import android.os.Parcelable;
import android.support.annotation.IntRange;

import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class Blood implements Parcelable{
    @IntRange(from = 0, to = 50) public abstract int currentBlood();
    @IntRange(from = 10, to = 50) public abstract int maxBlood();
    @IntRange(from = 1, to = 10) public abstract int bloodPerTurn();

    public Blood() {}

    public static Blood createDefault(){
        return builder().build();
    }

    @AutoParcelGson.Builder
    public abstract static class Builder {
        public abstract Builder currentBlood(int currentBlood);
        public abstract Builder maxBlood(int maxBlood);
        public abstract Builder bloodPerTurn(int bloodPerTurn);
        public abstract Blood build();

        Builder() {}
    }

    public static Builder builder() {
        return new AutoParcelGson_Blood.Builder()
                .currentBlood(7)
                .maxBlood(10)
                .bloodPerTurn(1);
    }

    public abstract Builder toBuilder();
}