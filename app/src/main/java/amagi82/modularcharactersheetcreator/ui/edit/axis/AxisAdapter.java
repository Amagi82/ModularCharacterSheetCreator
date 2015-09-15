package amagi82.modularcharactersheetcreator.ui.edit.axis;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.ui.extras.databinding.TileGridBinding;
import amagi82.modularcharactersheetcreator.ui.extras.events.TileSplatClickedEvent;
import amagi82.modularcharactersheetcreator.entities.characters.Splat;
import amagi82.modularcharactersheetcreator.ui.extras.utils.ScreenSize;
import amagi82.modularcharactersheetcreator.ui.extras.utils.SplatIcon;

import static amagi82.modularcharactersheetcreator.ui.extras.utils.Otto.BUS;

public class AxisAdapter extends RecyclerView.Adapter<AxisAdapter.ViewHolder> {

    private Fragment fragment;
    private Resources res;
    private SortedList<Splat> splats;
    private boolean left = true;
    private int gridImageSize;
    private LayoutInflater inflater;

    public AxisAdapter(Fragment fragment) {
        this.fragment = fragment;
        res = fragment.getResources();
        inflater = LayoutInflater.from(fragment.getContext());
        splats = new SortedList<>(Splat.class, new SortedList.Callback<Splat>() {
            @Override public int compare(Splat o1, Splat o2) {
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
                return item1.title() == item2.title();
            }
        });

        int margins = res.getDimensionPixelSize(R.dimen.card_margin) * 2;
        int spanCount = res.getInteger(R.integer.character_axis_span_count);
        int widthAvail = new ScreenSize(fragment.getActivity()).getWidth() - margins;
        gridImageSize = (widthAvail - margins) / spanCount;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TileGridBinding binding = TileGridBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        final Splat splat = splats.get(position);
        Glide.with(fragment).load(new SplatIcon(res, splat, gridImageSize).getUrl()).into(vh.binding.imageView);
        vh.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                BUS.getBus().post(new TileSplatClickedEvent(splat, left));
            }
        });
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

    public ArrayList<Splat> getAll() {
        int size = splats.size();
        ArrayList<Splat> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) list.add(splats.get(i));
        return list;
    }

    public int add(Splat choice) {
        return splats.add(choice);
    }

    public void addAll(List<Splat> list) {
        splats.addAll(list);
    }

    public void clear() {
        splats.clear();
    }

    public void replaceAll(List<Splat> list) {
        clear();
        addAll(list);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TileGridBinding binding;

        public ViewHolder(final View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
