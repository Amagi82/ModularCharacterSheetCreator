package amagi82.modularcharactersheetcreator.adapters;


import android.content.Context;
import android.content.res.Resources;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.viewholders.CharacterViewHolder;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.utils.CircleIcon;

public class MainAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    SortedList<GameCharacter> characters;
    private CircleIcon iconFactory;
    private Resources res;

    public MainAdapter(Context context) {
        characters = new SortedList<>(GameCharacter.class, new SortedList.Callback<GameCharacter>() {
            @Override public int compare(GameCharacter o1, GameCharacter o2) {
                return o1.getTimeStamp() > o2.getTimeStamp()? -1 : o1.getTimeStamp() < o2.getTimeStamp()? 1 : 0;
            }

            @Override public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }

            @Override public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override public boolean areContentsTheSame(GameCharacter oldCharacter, GameCharacter newCharacter) {
                return false;
            }

            @Override public boolean areItemsTheSame(GameCharacter item1, GameCharacter item2) {
                return item1.getEntityId().equals(item2.getEntityId());
            }
        });
        res = context.getResources();
        iconFactory = new CircleIcon(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final CharacterViewHolder vh, final int position) {
        final GameCharacter gameCharacter = characters.get(position);
        if(gameCharacter.getIcon() == null){
            int color = gameCharacter.getColorPrimary() < 1? res.getColor(R.color.primary) : gameCharacter.getColorPrimary();
            gameCharacter.setIcon(iconFactory.createIcon(gameCharacter.getName(), color));
        }
        vh.icon.setImageBitmap(gameCharacter.getIcon());
        vh.tvName.setText(gameCharacter.getName());
        vh.tvArchetype.setText(gameCharacter.getArchetype());
        vh.tvGameSystem.setTextColor(res.getColor(gameCharacter.getGameSystem().getColor()));
        vh.tvGameSystem.setText(gameCharacter.getGameSystem().getName());
        vh.gameCharacter = characters.get(position);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public GameCharacter get(int position) {
        return characters.get(position);
    }

    public int add(GameCharacter character) {
        return characters.add(character);
    }

    public int indexOf(GameCharacter character) {
        return characters.indexOf(character);
    }

    public void updateItemAt(int index, GameCharacter character) {
        characters.updateItemAt(index, character);
    }

    public void addAll(List<GameCharacter> list) {
        characters.beginBatchedUpdates();
        for (GameCharacter character : list) characters.add(character);
        characters.endBatchedUpdates();
    }

    public boolean remove(GameCharacter character) {
        return characters.remove(character);
    }

    public GameCharacter removeItemAt(int index) {
        return characters.removeItemAt(index);
    }

    public void clear() {
        characters.beginBatchedUpdates();
        while (characters.size() > 0) characters.removeItemAt(characters.size() - 1);
        characters.endBatchedUpdates();
    }
}