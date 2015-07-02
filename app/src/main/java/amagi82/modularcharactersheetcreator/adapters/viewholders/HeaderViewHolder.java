package amagi82.modularcharactersheetcreator.adapters.viewholders;


import android.view.View;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class HeaderViewHolder extends ContainerViewHolder {

    @Bind(R.id.tvHeader) public TextView tvHeader;

    public HeaderViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}