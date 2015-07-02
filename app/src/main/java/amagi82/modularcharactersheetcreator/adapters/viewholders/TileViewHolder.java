package amagi82.modularcharactersheetcreator.adapters.viewholders;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.events.TileClickedEvent;
import amagi82.modularcharactersheetcreator.utils.Otto;
import amagi82.modularcharactersheetcreator.widgets.AnimatedNetworkImageView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TileViewHolder extends RecyclerView.ViewHolder{

    @Nullable @Bind(R.id.imageViewNetwork) public AnimatedNetworkImageView imageViewNetwork;
    @Nullable @Bind(R.id.imageView) public ImageView imageView;
    @Bind(R.id.tvTitle) public TextView tvTitle;
    public String eName;

    public TileViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Otto.BUS.getBus().post(new TileClickedEvent(eName));
            }
        });
    }
}
