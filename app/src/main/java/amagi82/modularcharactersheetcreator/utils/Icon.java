package amagi82.modularcharactersheetcreator.utils;

import android.content.res.Resources;
import android.util.Log;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Splat;

public class Icon {

    private Resources res;
    private Splat splat;
    private int size;

    public Icon(Resources res, Splat splat){
        this(res, splat, res.getDimensionPixelSize(R.dimen.circle_icon_size));
    }

    public Icon(Resources res, Splat splat, int size) {
        this.res = res;
        this.splat = splat;
        this.size = size;
    }

    public String getUrl() {
        //The 20th Anniversary images are 500x500px, and all others are 200x200px. Request smaller images if needed.
        String url = getString(splat.getUrl());
        boolean big = url.contains("20th");
        if ((!big && size > 200) || (big && size > 500)) return url;
        int pos = url.lastIndexOf("/") + 1;
        Log.i(null, "Stringbuilder result = "+new StringBuilder(url).insert(pos, ("img.php?h=" + size + "+&w=" + size + "+&img=")).toString());
        return new StringBuilder(url).insert(pos, ("img.php?h=" + size + "+&w=" + size + "+&img=")).toString();
    }

    private String getString(int resId) {
        return res.getString(resId);
    }
}
