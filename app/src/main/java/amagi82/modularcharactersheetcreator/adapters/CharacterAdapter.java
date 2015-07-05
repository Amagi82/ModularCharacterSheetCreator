package amagi82.modularcharactersheetcreator.adapters;

import android.content.res.Resources;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.viewholders.TileGridViewHolder;
import amagi82.modularcharactersheetcreator.adapters.viewholders.TileViewHolder;
import amagi82.modularcharactersheetcreator.models.Choice;
import amagi82.modularcharactersheetcreator.models.game_systems.Game;
import amagi82.modularcharactersheetcreator.network.VolleySingleton;

public class CharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Resources resources;
    private SortedList<Choice> choices;
    private boolean grid;
    private boolean left = true;

    public CharacterAdapter(Resources resources, SortedList<Choice> choices, boolean grid) {
        this.resources = resources;
        this.choices = choices;
        this.grid = grid;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(grid) return new TileGridViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tile_grid, parent, false));
        else return new TileViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tile_game_system, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
        Choice choice = choices.get(position);
        if(grid) bind((TileGridViewHolder) vh, choice);
        else bind((TileViewHolder) vh, choice);
    }

    private void bind(TileGridViewHolder vh, Choice choice){
        vh.imageViewNetwork.setImageUrl(choice.getUrl() == -1? resources.getString(R.string.url_default) :
                resources.getString(choice.getBaseUrl()) + resources.getString(choice.getUrl()), VolleySingleton.INSTANCE.getImageLoader());
        vh.tvTitle.setText(choice.getTitle());
        vh.eName = choice.geteName();
        vh.left = left;
    }

    private void bind(TileViewHolder vh, Choice choice){
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