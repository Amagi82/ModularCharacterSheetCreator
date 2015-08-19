package amagi82.modularcharactersheetcreator.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.events.TileSplatClickedEvent;
import amagi82.modularcharactersheetcreator.models.games.Splat;
import butterknife.Bind;
import butterknife.ButterKnife;

import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;

public class TileAxisViewHolder extends RecyclerView.ViewHolder{

    @Bind(R.id.imageView) public ImageView imageView;
    @Bind(R.id.tvTitle) public TextView tvTitle;
    public Splat splat;
    public boolean left;

    public TileAxisViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                BUS.getBus().post(new TileSplatClickedEvent(splat, left));
            }
        });
    }
}
