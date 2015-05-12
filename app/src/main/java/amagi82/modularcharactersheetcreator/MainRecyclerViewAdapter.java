package amagi82.modularcharactersheetcreator;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.listeners.OnItemClickedListener;
import amagi82.modularcharactersheetcreator.listeners.OnItemLongClickedListener;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private ArrayList<GameCharacter> gameCharacters;
    private OnItemClickedListener listener;
    private OnItemLongClickedListener longClickListener;
    private SparseBooleanArray selectedItems;

    public MainRecyclerViewAdapter(ArrayList<GameCharacter> gameCharacters, Activity activity) {
        listener = (OnItemClickedListener) activity;
        longClickListener = (OnItemLongClickedListener) activity;
        this.gameCharacters = gameCharacters;

        selectedItems = new SparseBooleanArray();
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
        Log.i(null,position+" bound to viewholder");
        GameCharacter gameCharacter = gameCharacters.get(position);
        if (gameCharacter.getImageCharacterIcon() == null) {
            holder.imageCharacterIcon.setImageResource(R.drawable.ic_face_grey600_36dp);
        } else holder.imageCharacterIcon.setImageBitmap(gameCharacter.getImageCharacterIcon());
        holder.container.setBackgroundResource(0);
        holder.tvName.setText(gameCharacter.getCharacterName());
        holder.tvCharacterClass.setText(gameCharacter.getCharacterRace() +" "+ gameCharacter.getCharacterClass());
        holder.tvGameSystem.setText(gameCharacter.getGameSystem());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCharacterClicked(position);
            }
        });
        holder.container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longClickListener.onCharacterLongClicked(position);
                return true;
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return gameCharacters.size();
    }

    public void toggleSelection(int position) {
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
            Log.i(null, "deleted "+ position);
        }
        else {
            selectedItems.put(position, true);
            Log.i(null,"added "+ position);
        }
        notifyItemChanged(position);
    }

    public void clearSelections() {
        selectedItems.clear();
    }

    public int getSelectedItemCount() {
        Log.i(null, "selectedItems contains: "+ selectedItems.toString());
        return selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

    /**
     * Adds and item into the underlying data set
     * at the position passed into the method.
     *
     * @param newCharacter The item to add to the data set.
     * @param position The index of the item to remove.
     */
    public void addData(GameCharacter newCharacter, int position) {
        gameCharacters.add(position, newCharacter);
        notifyItemInserted(position);
    }

    /**
     * Removes the item that currently is at the passed in position from the
     * underlying data set.
     *
     * @param position The index of the item to remove.
     */
    public void removeData(int position) {
        gameCharacters.remove(position);
        notifyItemRemoved(position);
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