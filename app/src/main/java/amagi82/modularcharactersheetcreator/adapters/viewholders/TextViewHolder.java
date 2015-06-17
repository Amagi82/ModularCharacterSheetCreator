package amagi82.modularcharactersheetcreator.adapters.viewholders;


import android.view.View;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class TextViewHolder extends ContainerViewHolder {

    @InjectView(R.id.tvText) public TextView tvText;

    public TextViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }
}