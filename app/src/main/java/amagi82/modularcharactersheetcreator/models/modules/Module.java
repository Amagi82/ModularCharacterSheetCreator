package amagi82.modularcharactersheetcreator.models.modules;

import android.net.Uri;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class Module {
    @Type public abstract int type();
    @SpanCount public abstract int spanCount();
    @NonNull public abstract String title();
    @Nullable public abstract String textBody();
    @Nullable public abstract Stat stat();
    @Nullable public abstract List<Stat> statBlock();
    @Nullable public abstract Health health();
    @Nullable public abstract BloodPool bloodPool();
    @Nullable public abstract Uri image();

    public static Module createHeader(String title){
        return builder().type(HEADER_MODULE).title(title).spanCount(FULL).build();
    }

    public static Module createText(String title, String textBody){
        return builder().type(TEXT_MODULE).title(title).textBody(textBody).build();
    }

    public static Module createStat(String title, Stat stat){
        return builder().type(STAT_MODULE).title(title).stat(stat).build();
    }

    public static Module createStatBlock(String title, List<Stat> statBlock){
        return builder().type(STAT_BLOCK_MODULE).title(title).statBlock(statBlock).build();
    }

    public static Module createHealth(String title, Health health){
        return builder().type(HEALTH_MODULE).title(title).health(health).build();
    }

    public static Module createBloodPool(String title, BloodPool bloodPool){
        return builder().type(BLOODPOOL_MODULE).title(title).bloodPool(bloodPool).build();
    }

    public static Module createImage(String title, Uri image){
        return builder().type(IMAGE_MODULE).title(title).image(image).build();
    }

    @AutoParcel.Builder
    abstract static class Builder {
        abstract Builder type(@Type int type);
        abstract Builder spanCount(@SpanCount int spanCount);
        abstract Builder title(String title);
        abstract Builder textBody(String textBody);
        abstract Builder stat(Stat stat);
        abstract Builder statBlock(List<Stat> statBlock);
        abstract Builder health(Health health);
        abstract Builder bloodPool(BloodPool bloodPool);
        abstract Builder image(Uri image);
        abstract Module build();

        Builder() {}
    }

    static Builder builder() {
        return new AutoParcel_Module.Builder().spanCount(ONE);
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({HEADER_MODULE, TEXT_MODULE, STAT_MODULE, STAT_BLOCK_MODULE, HEALTH_MODULE, BLOODPOOL_MODULE, IMAGE_MODULE})
    public @interface Type {}

    public static final int HEADER_MODULE = 1;
    public static final int TEXT_MODULE = 2;
    public static final int STAT_MODULE = 3;
    public static final int STAT_BLOCK_MODULE = 4;
    public static final int HEALTH_MODULE = 5;
    public static final int BLOODPOOL_MODULE = 6;
    public static final int IMAGE_MODULE = 7;


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ONE, TWO, THREE, FULL})
    public @interface SpanCount {}

    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FULL = 99;
}
