package amagi82.modularcharactersheetcreator.adapters;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.viewholders.TileViewHolder;
import amagi82.modularcharactersheetcreator.models.Choice;
import amagi82.modularcharactersheetcreator.models.game_systems.Game;

public class CharacterGameAdapter extends RecyclerView.Adapter<TileViewHolder> {

    private SortedList<Choice> choices;

    public CharacterGameAdapter() {

        choices = new SortedList<>(Choice.class, new SortedList.Callback<Choice>() {
            @Override public int compare(Choice o1, Choice o2) {
                if (o1.getPosition() > o2.getPosition()) return 1;
                if (o1.getPosition() < o2.getPosition()) return -1;
                return 0;
            }

            @Override public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }

            @Override public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override public boolean areContentsTheSame(Choice oldItem, Choice newItem) {
                return false;
            }

            @Override public boolean areItemsTheSame(Choice item1, Choice item2) {
                return item1.geteName().equals(item2.geteName());
            }
        });
    }

    @Override
    public TileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TileViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tile_game_system, parent, false));
    }

    @Override
    public void onBindViewHolder(TileViewHolder vh, int position) {
        Choice choice = choices.get(position);
        vh.imageView.setImageResource(choice.getDrawable());
        vh.tvTitle.setText(choice.getTitle());
        vh.system = Game.System.valueOf(choice.geteName());
    }

    @Override
    public int getItemCount() {
        return choices.size();
    }

    public Choice get(int position) {
        return choices.get(position);
    }

    public void addItems(List<Choice> list) {
        choices.beginBatchedUpdates();
        choices.addAll(list);
        choices.endBatchedUpdates();
    }

    public void removeItems(List<Choice> list){
        choices.beginBatchedUpdates();
        for(Choice choice : list) choices.remove(choice);
        choices.endBatchedUpdates();
    }
}
