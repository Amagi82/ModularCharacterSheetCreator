package amagi82.modularcharactersheetcreator.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class TileViewHolder extends RecyclerView.ViewHolder{

    @InjectView(R.id.imageTitle) public ImageView imageTitle;
    @InjectView(R.id.tvTitle) public TextView tvTitle;

    public TileViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }
}
