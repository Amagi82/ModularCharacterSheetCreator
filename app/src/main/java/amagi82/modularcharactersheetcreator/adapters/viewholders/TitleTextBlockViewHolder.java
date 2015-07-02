package amagi82.modularcharactersheetcreator.adapters.viewholders;


import android.view.View;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TitleTextBlockViewHolder extends ContainerViewHolder {

    @Bind(R.id.tvText) public TextView tvText;
    @Bind(R.id.tvTitle) public TextView tvTitle;

    public TitleTextBlockViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}