package amagi82.modularcharactersheetcreator.adapters;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.viewholders.TileGridViewHolder;
import amagi82.modularcharactersheetcreator.adapters.viewholders.TileViewHolder;
import amagi82.modularcharactersheetcreator.models.Choice;
import amagi82.modularcharactersheetcreator.models.game_systems.Game;
import amagi82.modularcharactersheetcreator.utils.Icon;
import amagi82.modularcharactersheetcreator.utils.ScreenSize;

public class CharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Fragment fragment;
    private Resources res;
    private SortedList<Choice> choices;
    private boolean left = true;
    private int gridImageSize;

    public CharacterAdapter(Fragment fragment) {
        this.fragment = fragment;
        res = fragment.getResources();
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

        int margins = res.getDimensionPixelSize(R.dimen.card_margin) * 2;
        int spanCount = res.getInteger(R.integer.character_grid_span_count);
        int widthAvail = new ScreenSize(fragment.getActivity()).getWidth() - margins;
        gridImageSize = (widthAvail - margins) / spanCount;
    }

    @Override public int getItemViewType(int position) {
        for (Game.System system : Game.System.values()) {
            if (choices.get(position).geteName().equals(system.name())) return 0;
        }
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) return new TileGridViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tile_grid, parent, false));
        else return new TileViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tile_game_system, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
        Choice choice = choices.get(position);
        if (getItemViewType(position) == 1) bind((TileGridViewHolder) vh, choice);
        else bind((TileViewHolder) vh, choice);
    }

    private void bind(TileGridViewHolder vh, Choice choice) {
        Glide.with(fragment).load(new Icon(res, choice, gridImageSize).getUrl()).into(vh.imageView);
        vh.tvTitle.setText(choice.getTitle());
        vh.eName = choice.geteName();
        vh.left = left;
    }

    private void bind(TileViewHolder vh, Choice choice) {
        vh.imageView.setImageResource(choice.getDrawable());
        vh.tvTitle.setText(choice.getTitle());
        vh.system = Game.System.valueOf(choice.geteName());
    }

    @Override
    public int getItemCount() {
        return choices.size();
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public Choice get(int position) {
        return choices.get(position);
    }

    public int add(Choice choice) {
        return choices.add(choice);
    }

    public void addAll(List<Choice> list) {
        int i = choices.size();
        choices.beginBatchedUpdates();
        for (Choice choice : list) {
            choice.setPosition(i);
            i++;
            choices.add(choice);
        }
        choices.endBatchedUpdates();
    }

    public void remove(String eName) {
        for (int i = choices.size() - 1; i >= 0; i--) {
            if (choices.get(i).geteName().equals(eName)) {
                removeItemAt(i);
                return;
            }
        }
    }

    public void removeItemAt(int index) {
        choices.removeItemAt(index);
    }

    public void clear() {
        choices.beginBatchedUpdates();
        while (choices.size() > 0) choices.removeItemAt(choices.size() - 1);
        choices.endBatchedUpdates();
    }

    public void replaceAll(List<Choice> list) {
        clear();
        addAll(list);
    }
}