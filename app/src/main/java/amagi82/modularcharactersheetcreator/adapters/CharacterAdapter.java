package amagi82.modularcharactersheetcreator.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import amagi82.modularcharactersheetcreator.adapters.viewholders.TileViewHolder;
import amagi82.modularcharactersheetcreator.models.Choice;
import amagi82.modularcharactersheetcreator.network.VolleySingleton;

public class CharacterAdapter extends RecyclerView.Adapter<TileViewHolder> {

    private Context context;
    private List<Choice> choices;
    private int layoutId;

    public CharacterAdapter(Context context, List<Choice> choices, int layoutId) {
        this.context = context;
        this.choices = choices;
        this.layoutId = layoutId;
    }

    public void setList(final List<Choice> newChoices){
        choices = newChoices;
        notifyDataSetChanged();
    }

    @Override
    public TileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new TileViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TileViewHolder holder, int position) {
        Choice choice = choices.get(position);
        if(choice.getDrawable() != -1) holder.imageTitle.setDefaultImageResId(choice.getDrawable());
        else if(choice.getUrl() != -1) holder.imageTitle.setImageUrl(getString(choice.getBaseUrl()) + getString(choice.getUrl()), VolleySingleton.INSTANCE.getImageLoader());
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