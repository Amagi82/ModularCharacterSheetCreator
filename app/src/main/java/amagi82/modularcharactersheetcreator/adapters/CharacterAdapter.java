package amagi82.modularcharactersheetcreator.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.bumptech.glide.Glide;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.viewholders.TileGridViewHolder;
import amagi82.modularcharactersheetcreator.adapters.viewholders.TileViewHolder;
import amagi82.modularcharactersheetcreator.models.Choice;
import amagi82.modularcharactersheetcreator.models.game_systems.Game;
import amagi82.modularcharactersheetcreator.network.SizedImage;

public class CharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Fragment fragment;
    private Resources res;
    private SortedList<Choice> choices;
    private boolean left = true;
    private int gridImageSize;

    public CharacterAdapter(Fragment fragment, SortedList<Choice> choices) {
        this.fragment = fragment;
        this.choices = choices;
        res = fragment.getResources();

        WindowManager wm = (WindowManager) fragment.getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int widthAvail = size.x - (res.getDimensionPixelSize(R.dimen.card_margin) * 2);
        int spanCount = res.getInteger(R.integer.character_grid_span_count);
        gridImageSize = (widthAvail -res.getDimensionPixelSize(R.dimen.card_margin) * 2) /spanCount;
    }

    @Override public int getItemViewType(int position) {
        for (Game.System system : Game.System.values()) if (choices.get(position).geteName().equals(system.name())) return 0;
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
        Glide.with(fragment).load(new SizedImage(res, choice, gridImageSize).getUrl()).into(vh.imageView);
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
}