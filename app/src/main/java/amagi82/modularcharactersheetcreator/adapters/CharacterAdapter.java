package amagi82.modularcharactersheetcreator.adapters;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.viewholders.TileViewHolder;
import amagi82.modularcharactersheetcreator.models.game_systems.GameSystem;

public class CharacterAdapter extends RecyclerView.Adapter<TileViewHolder> {

    private GameSystem system;
    boolean isMainSystemSelected = false;
    private List<GameSystem.System> gameSystemList;

    public CharacterAdapter(List<GameSystem.System> gameSystemList) {
        this.gameSystemList = gameSystemList;
    }

    public void setGameSystemList(final List<GameSystem.System> gameSystemList){
        isMainSystemSelected = false;
        notifyItemRangeRemoved(0, gameSystemList.size());
        gameSystemList.clear();
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                CharacterAdapter.this.gameSystemList = gameSystemList;
                notifyItemRangeInserted(0, gameSystemList.size());
            }
        }, 250);
    }

    public void setGameSystem(GameSystem system){
        this.system = system;
        isMainSystemSelected = true;
    }

    @Override
    public TileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tile_game_system, parent, false);
        return new TileViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TileViewHolder holder, int position) {
        holder.imageTitle.setImageResource(isMainSystemSelected? system.getUrl(position) : gameSystemList.get(position).getImageMain());
        holder.tvTitle.setText(isMainSystemSelected ? system.getName(position) : gameSystemList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return gameSystemList.size();
    }
}