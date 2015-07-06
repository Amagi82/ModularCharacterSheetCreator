package amagi82.modularcharactersheetcreator.adapters.viewholders;


import android.view.View;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.CircleProgress;

import amagi82.modularcharactersheetcreator.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ModuleBloodPoolViewHolder extends ModuleViewHolder {

    @Bind(R.id.tvBloodPerTurn) public TextView tvBloodPerTurn;
    @Bind(R.id.circleProgress) public CircleProgress circleProgress;

    public ModuleBloodPoolViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}