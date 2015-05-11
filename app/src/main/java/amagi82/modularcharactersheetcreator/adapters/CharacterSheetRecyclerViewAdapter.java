package amagi82.modularcharactersheetcreator.adapters;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.listeners.OnItemClickedListener;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.models.modules.TextModule;

public class CharacterSheetRecyclerViewAdapter extends RecyclerView.Adapter<CharacterSheetRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Module> modules;
    private OnItemClickedListener listener;

    public CharacterSheetRecyclerViewAdapter(ArrayList<Module> modules, Activity activity) {
        listener = (OnItemClickedListener) activity;
        this.modules = modules;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CharacterSheetRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Log.i("viewtype = ", viewType + "");

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_text_only, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        switch(modules.get(position).getViewType()){
            case TEXT:
                TextModule module = (TextModule) modules.get(position);
                holder.tvText.setText(module.getText());
                break;

        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onModuleClicked(modules, position);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return modules.size();
    }

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvText;
        View container;

        public ViewHolder(final View itemView) {
            super(itemView);

            container = itemView;
            tvText = (TextView) itemView.findViewById(R.id.tvText);
        }
    }
}