package amagi82.modularcharactersheetcreator.models.modules;

import android.os.Parcelable;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class Health implements Parcelable{
    public abstract String damageText();
    public abstract String damagePenalty();
    public abstract int bashing();
    public abstract int lethal();
    public abstract int agg();
    public abstract int numBoxes();

    public Health() {}

    public static Health createDefault(){
        return builder().build();
    }

    @AutoParcel.Builder
    public abstract static class Builder {
        public abstract Builder damageText(String damageText);
        public abstract Builder damagePenalty(String damagePenalty);
        public abstract Builder bashing(int bashing);
        public abstract Builder lethal(int lethal);
        public abstract Builder agg(int agg);
        public abstract Builder numBoxes(int numBoxes);
        public abstract Health build();

        Builder() {}
    }

    public static Builder builder() {
        return new AutoParcel_Health.Builder()
                .damageText("")
                .damagePenalty("")
                .numBoxes(7);
    }

    public abstract Builder toBuilder();
}