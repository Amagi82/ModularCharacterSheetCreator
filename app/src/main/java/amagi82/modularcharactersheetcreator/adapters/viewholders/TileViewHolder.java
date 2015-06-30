package amagi82.modularcharactersheetcreator.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.events.TileClickedEvent;
import amagi82.modularcharactersheetcreator.utils.Otto;
import amagi82.modularcharactersheetcreator.widgets.AnimatedNetworkImageView;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class TileViewHolder extends RecyclerView.ViewHolder{

    @InjectView(R.id.imageTitle) public AnimatedNetworkImageView imageTitle;
    @InjectView(R.id.tvTitle) public TextView tvTitle;

    public TileViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Otto.BUS.getBus().post(new TileClickedEvent(getAdapterPosition()));
            }
        });
    }
}
