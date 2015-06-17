package amagi82.modularcharactersheetcreator.adapters.viewholders;


import android.view.View;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class HeaderViewHolder extends ContainerViewHolder {

    @InjectView(R.id.tvHeader) public TextView tvHeader;

    public HeaderViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }
}