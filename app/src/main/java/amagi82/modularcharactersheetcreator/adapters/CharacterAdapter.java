package amagi82.modularcharactersheetcreator.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

//        Log.i(null, "newChoices = "+newChoices.toString());
//        Log.i(null, "Setting grid");
//        this.isGrid = isGrid;
//        Log.i(null, "Notifying item range removed");
//        notifyItemRangeRemoved(0, choices.size());
//        Log.i(null, "Clearing choices");
//        choices.clear();
////        new Handler().postDelayed(new Runnable() {
////            @Override public void run() {
//                Log.i(null, "updating choices");
//                choices.addAll(newChoices);
//                Log.i(null, "notifying inserted");
//                notifyItemRangeInserted(0, newChoices.size());
////            }
////        }, 250);
    }

    @Override
    public TileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(null, "onCreateViewHolder parent == "+parent.toString()+" and viewType == "+viewType);
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new TileViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TileViewHolder holder, int position) {
        Log.i(null, "onBindViewHolder holder == "+holder.toString()+"and position == "+position);
        Choice choice = choices.get(position);
        //Log.i(null, "choice hasDrawable? "+choice.hasDrawable());
        Log.i(null, "choice eName = "+choice.geteName());
//        Log.i(null, "choice url = "+choice.getUrl());
//        Log.i(null, "choice baseUrl = "+choice.getBaseUrl());
//        Log.i(null, "choice drawable = "+choice.getDrawable());
//        Log.i(null, "choice title = "+choice.getTitle());
        if(choice.getDrawable() != -1) holder.imageTitle.setImageResource(choice.getDrawable());
        else if(choice.getUrl() != -1) holder.imageTitle.setImageUrl(getString(choice.getBaseUrl())+getString(choice.getUrl()), VolleySingleton.INSTANCE.getImageLoader());
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