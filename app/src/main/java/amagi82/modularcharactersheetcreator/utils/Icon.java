package amagi82.modularcharactersheetcreator.utils;

import android.content.res.Resources;

import amagi82.modularcharactersheetcreator.App;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Choice;

public class Icon {

    private Resources res;
    private Choice choice;
    private int size;

    public Icon(Resources res, Choice choice){
        this(res, choice, res.getDimensionPixelSize(R.dimen.circle_icon_size));
    }

    public Icon(Resources res, Choice choice, int size) {
        this.res = res;
        this.choice = choice;
        this.size = size;
    }

    public String getUrl() {
        int baseUrl = choice.getBaseUrl() == App.NONE? R.string.url_default_base : choice.getBaseUrl();
        int imageUrl = choice.getUrl() == App.NONE? R.string.url_default : choice.getUrl();
        //The 20th Anniversary images are 500x500px, and all others are 200x200px. Request smaller images if needed.
        boolean big = getString(baseUrl).contains("20th");
        if ((!big && size > 200) || (big && size > 500)) return getString(baseUrl) + getString(imageUrl);
        return getString(baseUrl) + "img.php?h=" + size + "+&w=" + size + "+&img=" + getString(imageUrl);
    }

    private String getString(int resId) {
        return res.getString(resId);
    }
}
