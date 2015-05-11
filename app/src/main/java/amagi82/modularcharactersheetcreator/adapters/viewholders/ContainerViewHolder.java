package amagi82.modularcharactersheetcreator.adapters.viewholders;


import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ContainerViewHolder extends RecyclerView.ViewHolder {

    public View container;

    public ContainerViewHolder(final View itemView) {
        super(itemView);

        container = itemView;
    }
}