package amagi82.modularcharactersheetcreator.adapters.viewholders;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;

public class TextViewHolder extends RecyclerView.ViewHolder {

    TextView tvText;
    View container;

    public TextViewHolder(final View itemView) {
        super(itemView);

        container = itemView;
        tvText = (TextView) itemView.findViewById(R.id.tvText);
    }
}