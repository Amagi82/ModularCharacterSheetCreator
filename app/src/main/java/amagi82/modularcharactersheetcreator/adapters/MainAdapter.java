package amagi82.modularcharactersheetcreator.adapters;


import android.content.Context;
import android.content.res.Resources;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.viewholders.CharacterViewHolder;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.utils.CircleIcon;

public class MainAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    SortedList<GameCharacter> gameCharacters;
    private CircleIcon iconFactory;
    private Resources res;

    public MainAdapter(Context context, SortedList<GameCharacter> gameCharacters) {
        res = context.getResources();
        this.gameCharacters = gameCharacters;
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
        final GameCharacter gameCharacter = gameCharacters.get(position);
        if(gameCharacter.getIcon() == null){
            int color = gameCharacter.getColorPrimary() < 1? res.getColor(R.color.primary) : gameCharacter.getColorPrimary();
            gameCharacter.setIcon(iconFactory.createIcon(gameCharacter.getName(), color));
        }
        vh.icon.setImageBitmap(gameCharacter.getIcon());
        vh.tvName.setText(gameCharacter.getName());
        vh.tvArchetype.setText(gameCharacter.getArchetype());
        vh.tvGameSystem.setTextColor(res.getColor(gameCharacter.getGameSystem().getColor()));
        vh.tvGameSystem.setText(gameCharacter.getGameSystem().getName());
        vh.gameCharacter = gameCharacters.get(position);
    }

    @Override
    public int getItemCount() {
        return gameCharacters.size();
    }
}