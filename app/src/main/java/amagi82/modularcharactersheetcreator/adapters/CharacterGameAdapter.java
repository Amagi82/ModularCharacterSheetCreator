package amagi82.modularcharactersheetcreator.adapters;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.viewholders.TileViewHolder;
import amagi82.modularcharactersheetcreator.models.games.systems.GameSystem;

public class CharacterGameAdapter extends RecyclerView.Adapter<TileViewHolder> {

    private SortedList<GameSystem> systems;

    public CharacterGameAdapter() {

        systems = new SortedList<>(GameSystem.class, new SortedList.Callback<GameSystem>() {
            @Override public int compare(GameSystem o1, GameSystem o2) {
//                if (o1.getPosition() > o2.getPosition()) return 1;
//                if (o1.getPosition() < o2.getPosition()) return -1;
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

            @Override public boolean areContentsTheSame(GameSystem oldItem, GameSystem newItem) {
                return false;
            }

            @Override public boolean areItemsTheSame(GameSystem item1, GameSystem item2) {
                return item1.getGameTitle() == item2.getGameTitle();
            }
        });
    }

    @Override
    public TileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TileViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tile_game_system, parent, false));
    }

    @Override
    public void onBindViewHolder(TileViewHolder vh, int position) {
        GameSystem choice = systems.get(position);
        vh.imageView.setImageResource(choice.getGameDrawable());
        vh.tvTitle.setText(choice.getGameTitle());
        vh.system = systems.get(position);
    }

    @Override
    public int getItemCount() {
        return systems.size();
    }

    public GameSystem get(int position) {
        return systems.get(position);
    }

    public void addItems(List<GameSystem> list) {
        systems.addAll(list);
    }

    public void removeItems(List<GameSystem> list){
        systems.beginBatchedUpdates();
        for(GameSystem system : list) systems.remove(system);
        systems.endBatchedUpdates();
    }
}
