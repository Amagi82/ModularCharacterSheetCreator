package amagi82.modularcharactersheetcreator.ui.edit.axis;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.util.Log;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.entities.characters.Splat;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.ScreenSize;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.SplatIcon;

public class AxisItemViewModel extends BaseObservable {
    static int gridImageSize;
    private String url;
    private int title;

    public AxisItemViewModel(Context context, Splat splat) {
        Resources res = context.getResources();
        if(gridImageSize < 1){
            Log.i(null, "determining new gridImageSize, currently: "+gridImageSize);
            int margins = res.getDimensionPixelSize(R.dimen.card_margin) * 2;
            int spanCount = res.getInteger(R.integer.character_axis_span_count);
            int widthAvail = new ScreenSize(context).getWidth() - margins;
            gridImageSize = (widthAvail - margins) / spanCount;
            Log.i(null, "gridImageSize now: "+gridImageSize);
        }
        url = new SplatIcon(res, splat, gridImageSize).getUrl();
        title = splat.title();
    }

    public String getUrl() {
        return url;
    }

    public int getTitle() {
        return title;
    }
}
