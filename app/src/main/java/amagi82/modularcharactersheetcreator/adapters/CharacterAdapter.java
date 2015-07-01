package amagi82.modularcharactersheetcreator.adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.viewholders.TileViewHolder;
import amagi82.modularcharactersheetcreator.models.Choice;
import amagi82.modularcharactersheetcreator.network.VolleySingleton;

public class CharacterAdapter extends RecyclerView.Adapter<TileViewHolder> {

    private Context context;
    private List<Choice> choices;
    private boolean isGrid = false;

    public CharacterAdapter(Context context, List<Choice> choices) {
        this.context = context;
        this.choices = choices;
    }

    public void setList(final List<Choice> choices, boolean isGrid){
        this.isGrid = isGrid;
        notifyItemRangeRemoved(0, choices.size());
        choices.clear();
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                CharacterAdapter.this.choices = choices;
                notifyItemRangeInserted(0, choices.size());
            }
        }, 250);
    }

    @Override
    public TileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(isGrid? R.layout.tile_default : R.layout.tile_game_system, parent, false);
        return new TileViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TileViewHolder holder, int position) {
        Choice choice = choices.get(position);
        if(choice.hasDrawable()) holder.imageTitle.setImageResource(choice.getDrawable());
        else holder.imageTitle.setImageUrl(getString(choice.getBaseUrl())+getString(choice.getUrl()), VolleySingleton.INSTANCE.getImageLoader());
        holder.tvTitle.setText(choice.getTitle());
        holder.eName = choices.get(position).geteName();
    }

    @Override
    public int getItemCount() {
        return choices.size();
    }

    private String getString(int resId){
        return context.getResources().getString(resId);
    }
}