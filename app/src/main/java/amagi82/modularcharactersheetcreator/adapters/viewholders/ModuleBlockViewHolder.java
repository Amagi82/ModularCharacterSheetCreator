package amagi82.modularcharactersheetcreator.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import amagi82.modularcharactersheetcreator.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ModuleBlockViewHolder extends ModuleViewHolder {

    @Bind(R.id.cardRecyclerView) public RecyclerView cardRecyclerView;

    public ModuleBlockViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}