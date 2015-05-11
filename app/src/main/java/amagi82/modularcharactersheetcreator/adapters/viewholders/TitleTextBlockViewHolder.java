package amagi82.modularcharactersheetcreator.adapters.viewholders;


import android.view.View;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;

public class TitleTextBlockViewHolder extends ContainerViewHolder {

    TextView tvText;
    TextView tvTitle;

    public TitleTextBlockViewHolder(final View itemView) {
        super(itemView);

        tvText = (TextView) itemView.findViewById(R.id.tvText);
    }
}