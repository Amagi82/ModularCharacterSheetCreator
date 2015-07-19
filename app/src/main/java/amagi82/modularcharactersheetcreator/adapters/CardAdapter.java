package amagi82.modularcharactersheetcreator.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.viewholders.RowStatViewHolder;
import amagi82.modularcharactersheetcreator.models.modules.Stat;

public class CardAdapter extends RecyclerView.Adapter<RowStatViewHolder> {

    private List<Stat> stats;

    public CardAdapter(List<Stat> stats) {
        this.stats = stats;
    }

    @Override
    public RowStatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RowStatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_stat, parent, false));
    }

    @Override
    public void onBindViewHolder(RowStatViewHolder vh, int position) {
        Stat stat = stats.get(position);
        vh.statBar.setTitle(stat.getCategory());
        vh.statBar.setSpecialty(stat.getSpecialty());
        vh.statBar.setNumStars(stat.getNumStars());
        vh.statBar.setRatingMax(stat.getValueMax());
        vh.statBar.setRating(stat.getValueTemporary());
        vh.statBar.setRatingBase(stat.getValue());
        vh.statBar.setRatingMin(stat.getValueMin());
    }

    @Override
    public int getItemCount() {
        return stats.size();
    }
}
