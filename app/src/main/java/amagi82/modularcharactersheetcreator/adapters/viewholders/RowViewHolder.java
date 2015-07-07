package amagi82.modularcharactersheetcreator.adapters.viewholders;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class RowViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.tvCategory) public TextView tvCategory;
    @Nullable @Bind(R.id.tvText) public TextView tvText;

    public RowViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}