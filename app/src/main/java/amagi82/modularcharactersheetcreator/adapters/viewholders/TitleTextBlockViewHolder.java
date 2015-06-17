package amagi82.modularcharactersheetcreator.adapters.viewholders;


import android.view.View;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class TitleTextBlockViewHolder extends ContainerViewHolder {

    @InjectView(R.id.tvText) public TextView tvText;
    @InjectView(R.id.tvTitle) public TextView tvTitle;

    public TitleTextBlockViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }
}