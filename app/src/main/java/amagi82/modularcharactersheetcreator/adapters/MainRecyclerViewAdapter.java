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
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private ArrayList<GameCharacter> gameCharacters;
    private OnItemClickedListener listener;

    public MainRecyclerViewAdapter(ArrayList<GameCharacter> gameCharacters, Activity activity) {
        listener = (OnItemClickedListener) activity;
        this.gameCharacters = gameCharacters;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (gameCharacters.get(position).getImageCharacterIcon() == null) {
            holder.imageCharacterIcon.setImageResource(R.drawable.ic_face_grey600_24dp);
        } else holder.imageCharacterIcon.setImageBitmap(gameCharacters.get(position).getImageCharacterIcon());
        holder.tvName.setText(gameCharacters.get(position).getCharacterName());
        holder.tvCharacterClass.setText(gameCharacters.get(position).getCharacterClass());
        holder.tvGameSystem.setText(gameCharacters.get(position).getGameSystem());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCharacterClicked(position);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return gameCharacters.size();
    }

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {

        View container;
        CircleImageView imageCharacterIcon;
        TextView tvName;
        TextView tvCharacterClass;
        TextView tvGameSystem;

        public ViewHolder(View itemView) {
            super(itemView);

            container = itemView;
            imageCharacterIcon = (CircleImageView) itemView.findViewById(R.id.imageCharacterIcon);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvCharacterClass = (TextView) itemView.findViewById(R.id.tvCharacterClass);
            tvGameSystem = (TextView) itemView.findViewById(R.id.tvGameSystem);
        }
    }
}