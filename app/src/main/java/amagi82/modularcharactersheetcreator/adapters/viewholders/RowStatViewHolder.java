package amagi82.modularcharactersheetcreator.adapters.viewholders;

import android.view.View;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.widgets.StatRatingBar;
import butterknife.Bind;
import butterknife.ButterKnife;

public class RowStatViewHolder extends RowViewHolder {

    @Bind(R.id.statRatingBar) public StatRatingBar statRatingBar;

    public RowStatViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}