package amagi82.modularcharactersheetcreator.adapters.viewholders;

import android.view.View;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.widgets.RoundedStatBar;
import amagi82.modularcharactersheetcreator.widgets.StatRatingBar;
import butterknife.Bind;
import butterknife.ButterKnife;

public class RowStatViewHolder extends RowViewHolder {

    @Bind(R.id.statBar) public RoundedStatBar statBar;

    public RowStatViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}