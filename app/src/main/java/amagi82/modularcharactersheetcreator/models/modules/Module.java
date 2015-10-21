package amagi82.modularcharactersheetcreator.models.modules;


import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class Module {

    @Type public abstract int type();
    public abstract String title();

    public Module() {}


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({HEADER_MODULE, STAT_MODULE, STAT_BLOCK_MODULE, HEALTH_MODULE, BLOODPOOL_MODULE})
    public @interface Type {}

    public static final int HEADER_MODULE = 0;
    public static final int STAT_MODULE = 1;
    public static final int STAT_BLOCK_MODULE = 2;
    public static final int HEALTH_MODULE = 3;
    public static final int BLOODPOOL_MODULE = 4;
}
