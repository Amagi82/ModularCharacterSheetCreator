package amagi82.modularcharactersheetcreator.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.models.modules.TextOnlyModule;

public class CharacterSheetRecyclerViewAdapter extends RecyclerView.Adapter<CharacterSheetRecyclerViewAdapter.ViewHolder> {

    private ArrayList<? extends Module> modules;
    private OnModuleClickedListener listener;

    public CharacterSheetRecyclerViewAdapter(ArrayList<? extends Module> modules) {
        this.modules = modules;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CharacterSheetRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_text_only, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        TextOnlyModule module = (TextOnlyModule) modules.get(position);
        holder.tvText.setText(module.getText());
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

    public interface OnModuleClickedListener {
        void onModuleClicked(ArrayList<? extends Module> module, int position);
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