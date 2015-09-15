package amagi82.modularcharactersheetcreator.ui.edit.game;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import amagi82.modularcharactersheetcreator.ui.xtras.databinding.TileGridGameBinding;
import amagi82.modularcharactersheetcreator.ui.xtras.events.TileGameClickedEvent;
import amagi82.modularcharactersheetcreator.entities.games.GameSystem;

import static amagi82.modularcharactersheetcreator.ui.xtras.utils.Otto.BUS;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private SortedList<GameSystem> systems;
    private Fragment fragment;
    private LayoutInflater inflater;

    public GameAdapter(Fragment fragment) {
        this.fragment = fragment;
        inflater = LayoutInflater.from(fragment.getContext());

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TileGridGameBinding binding = TileGridGameBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        Glide.with(fragment).load(fragment.getString(systems.get(position).getGameUrl())).into(vh.binding.imageView);
        vh.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                BUS.getBus().post(new TileGameClickedEvent(systems.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return systems.size();
    }

    public void addAll(List<GameSystem> list) {
        systems.addAll(list);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TileGridGameBinding binding;

        public ViewHolder(final View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
