package amagi82.modularcharactersheetcreator.adapters;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.viewholders.TileGridViewHolder;
import amagi82.modularcharactersheetcreator.adapters.viewholders.TileViewHolder;
import amagi82.modularcharactersheetcreator.models.Choice;
import amagi82.modularcharactersheetcreator.models.game_systems.Game;
import amagi82.modularcharactersheetcreator.network.VolleySingleton;

public class CharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Resources resources;
    private List<Choice> choices;
    private boolean grid;

    public CharacterAdapter(Resources resources, List<Choice> choices, boolean grid) {
        this.resources = resources;
        this.choices = choices;
        this.grid = grid;
    }

    public void setList(List<Choice> newChoices){
        choices = newChoices;
        notifyDataSetChanged();
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
        if(choice.getUrl() != -1) vh.imageViewNetwork.setImageUrl(
                resources.getString(choice.getBaseUrl()) + resources.getString(choice.getUrl()), VolleySingleton.INSTANCE.getImageLoader());
        vh.tvTitle.setText(choice.getTitle());
        vh.eName = choice.geteName();
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
}