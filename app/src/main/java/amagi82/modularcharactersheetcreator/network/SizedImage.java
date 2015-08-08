package amagi82.modularcharactersheetcreator.network;

import android.content.res.Resources;

import amagi82.modularcharactersheetcreator.R;

public class SizedImage {

    private Resources res;
    private int baseUrl;
    private int imageUrl;
    private int size;

    public SizedImage(Resources res, int size){
        this(res, R.string.url_default_base, R.string.url_default, size);
    }

    public SizedImage(Resources res, int baseUrl, int imageUrl, int size) {
        this.res = res;
        this.baseUrl = baseUrl;
        this.imageUrl = imageUrl;
        this.size = size;
    }

    public String getUrl() {
        return getString(baseUrl) + "img.php?h=" + size + "+&w=" + size + "+&img=" + getString(imageUrl);
    }

    private String getString(int resId) {
        return res.getString(resId);
    }
}
