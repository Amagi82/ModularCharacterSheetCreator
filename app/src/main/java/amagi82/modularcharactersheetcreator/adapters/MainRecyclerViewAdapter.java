package amagi82.modularcharactersheetcreator.adapters;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.MainActivity;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.listeners.OnItemClickedListener;
import amagi82.modularcharactersheetcreator.listeners.OnItemLongClickedListener;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private static OnItemClickedListener listener;
    private static OnItemLongClickedListener longClickListener;

    public MainRecyclerViewAdapter(Activity activity) {
        listener = (OnItemClickedListener) activity;
        longClickListener = (OnItemLongClickedListener) activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GameCharacter gameCharacter = MainActivity.gameCharacterList.get(position);
        if (gameCharacter.getImageCharacterIcon() == null) {
            holder.imageCharacterIcon.setImageResource(R.drawable.ic_face_grey600_36dp);
        } else holder.imageCharacterIcon.setImageBitmap(gameCharacter.getImageCharacterIcon());
        holder.container.setBackgroundResource(0);
        holder.tvName.setText(gameCharacter.getCharacterName());
        holder.tvCharacterClass.setText(gameCharacter.getCharacterRace() + " " + gameCharacter.getCharacterClass());
        holder.tvGameSystem.setText(gameCharacter.getGameSystem());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return MainActivity.gameCharacterList.size();
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
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCharacterClicked(getAdapterPosition());
                }
            });
            container.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longClickListener.onCharacterLongClicked(getAdapterPosition());
                    return true;
                }
            });
            imageCharacterIcon = (CircleImageView) itemView.findViewById(R.id.imageCharacterIcon);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvCharacterClass = (TextView) itemView.findViewById(R.id.tvCharacterClass);
            tvGameSystem = (TextView) itemView.findViewById(R.id.tvGameSystem);
        }
    }
}