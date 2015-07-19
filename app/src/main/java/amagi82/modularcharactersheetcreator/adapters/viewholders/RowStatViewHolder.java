package amagi82.modularcharactersheetcreator.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.widgets.RoundedStatBar;
import butterknife.Bind;
import butterknife.ButterKnife;

public class RowStatViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.statBar) public RoundedStatBar statBar;

    public RowStatViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}