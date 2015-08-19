package amagi82.modularcharactersheetcreator.utils;

import android.content.res.Resources;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Splat;

public class SplatIcon {

    private Resources res;
    private Splat splat;
    private int size;

    public SplatIcon(Resources res, Splat splat){
        this(res, splat, res.getDimensionPixelSize(R.dimen.circle_icon_size));
    }

    public SplatIcon(Resources res, Splat splat, int size) {
        this.res = res;
        this.splat = splat;
        this.size = size;
    }

    public String getUrl() {
        //The 20th Anniversary images are 500x500px, and all others are 200x200px. Request smaller images if needed.
        String url = res.getString(splat.getUrl());
        boolean big = url.contains("20th");
        if ((!big && size > 200) || (big && size > 500)) return url;
        int pos = url.lastIndexOf("/") + 1;
        return new StringBuilder(url).insert(pos, ("img.php?h=" + size + "+&w=" + size + "+&img=")).toString();
    }
}
