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
import amagi82.modularcharactersheetcreator.models.games.Splat;
import amagi82.modularcharactersheetcreator.utils.Icon;
import amagi82.modularcharactersheetcreator.utils.ScreenSize;

public class CharacterAxisAdapter extends RecyclerView.Adapter<TileGridViewHolder> {

    private Fragment fragment;
    private Resources res;
    private SortedList<Splat> splats;
    private boolean left = true;
    private int gridImageSize;

    public CharacterAxisAdapter(Fragment fragment) {
        this.fragment = fragment;
        res = fragment.getResources();
        splats = new SortedList<>(Splat.class, new SortedList.Callback<Splat>() {
            @Override public int compare(Splat o1, Splat o2) {
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

            @Override public boolean areContentsTheSame(Splat oldItem, Splat newItem) {
                return false;
            }

            @Override public boolean areItemsTheSame(Splat item1, Splat item2) {
                return item1.getTitle() == item2.getTitle();
            }
        });

        int margins = res.getDimensionPixelSize(R.dimen.card_margin) * 2;
        int spanCount = res.getInteger(R.integer.character_grid_span_count);
        int widthAvail = new ScreenSize(fragment.getActivity()).getWidth() - margins;
        gridImageSize = (widthAvail - margins) / spanCount;
    }

    @Override
    public TileGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TileGridViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tile_grid, parent, false));
    }

    @Override
    public void onBindViewHolder(TileGridViewHolder vh, int position) {
        Splat splat = splats.get(position);
        Glide.with(fragment).load(new Icon(res, splat, gridImageSize).getUrl()).into(vh.imageView);
        vh.tvTitle.setText(splat.getTitle());
        vh.splat = splat;
        vh.left = left;
    }

    @Override
    public int getItemCount() {
        return splats.size();
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public Splat get(int position) {
        return splats.get(position);
    }

    public int add(Splat choice) {
        return splats.add(choice);
    }

    public void addAll(List<Splat> list) {
        int i = splats.size();
        splats.beginBatchedUpdates();
        for (Splat splat : list) {
            splat.setPosition(i);
            i++;
            splats.add(splat);
        }
        splats.endBatchedUpdates();
    }

    public void remove(String eName) {
        for (int i = splats.size() - 1; i >= 0; i--) {
            if (splats.get(i).geteName().equals(eName)) {
                removeItemAt(i);
                return;
            }
        }
    }

    public void removeItemAt(int index) {
        splats.removeItemAt(index);
    }

    public void clear() {
        splats.beginBatchedUpdates();
        while (splats.size() > 0) splats.removeItemAt(splats.size() - 1);
        splats.endBatchedUpdates();
    }

    public void replaceAll(List<Splat> list) {
        clear();
        addAll(list);
    }
}
