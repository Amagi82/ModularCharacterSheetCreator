package amagi82.modularcharactersheetcreator.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import amagi82.modularcharactersheetcreator.models.modules.Module;

public class CharacterSheetRecyclerViewAdapter extends RecyclerView.Adapter<CharacterSheetRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Module> modules;

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
//        CircleImageView imageCharacterIcon;
//        TextView tvName;
//        TextView tvCharacterClass;
//        TextView tvGameSystem;

        public ViewHolder(View itemView) {
            super(itemView);
//            imageCharacterIcon = (CircleImageView) itemView.findViewById(R.id.imageCharacterIcon);
//            tvName = (TextView) itemView.findViewById(R.id.tvName);
//            tvCharacterClass = (TextView) itemView.findViewById(R.id.tvCharacterClass);
//            tvGameSystem = (TextView) itemView.findViewById(R.id.tvGameSystem);
        }
    }

    public CharacterSheetRecyclerViewAdapter(ArrayList<Module> modules) {
        this.modules = modules;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CharacterSheetRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        if (modules.get(position).getImageCharacterIcon() == null) {
//            holder.imageCharacterIcon.setImageResource(R.drawable.ic_face_grey600_24dp);
//        } else holder.imageCharacterIcon.setImageBitmap(modules.get(position).getImageCharacterIcon());
//        holder.tvName.setText(modules.get(position).getName());
//        holder.tvCharacterClass.setText(modules.get(position).getCharacterClass());
//        holder.tvGameSystem.setText(modules.get(position).getGameSystem());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return modules.size();
    }
}