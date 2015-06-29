package amagi82.modularcharactersheetcreator.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.viewholders.TileViewHolder;
import amagi82.modularcharactersheetcreator.models.game_systems.GameSystem;

public class CharacterAdapter extends RecyclerView.Adapter<TileViewHolder> {

    private List<GameSystem.System> list;

    public CharacterAdapter(List<GameSystem.System> list) {
        this.list = list;
    }

    @Override
    public TileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tile_game_system, parent, false);
        return new TileViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TileViewHolder holder, int position) {
        holder.imageTitle.setImageResource(list.get(position).getImageMain());
        holder.tvTitle.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}