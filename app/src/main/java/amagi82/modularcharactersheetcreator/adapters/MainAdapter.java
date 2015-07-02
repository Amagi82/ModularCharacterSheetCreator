package amagi82.modularcharactersheetcreator.adapters;


import android.content.Context;
import android.content.res.Resources;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.events.CharacterClickedEvent;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.utils.CircleIcon;
import amagi82.modularcharactersheetcreator.utils.Otto;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private SortedList<GameCharacter> gameCharacters;
    private CircleIcon iconFactory;
    private Resources res;

    public MainAdapter(Context context, SortedList<GameCharacter> gameCharacters) {
        res = context.getResources();
        this.gameCharacters = gameCharacters;
        iconFactory = new CircleIcon(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GameCharacter gameCharacter = gameCharacters.get(position);
        if(gameCharacter.getIcon() == null){
            int color = gameCharacter.getColorPrimary() < 1? res.getColor(R.color.primary) : gameCharacter.getColorPrimary();
            gameCharacter.setIcon(iconFactory.createIcon(gameCharacter.getName(), color));
        }
        holder.icon.setImageBitmap(gameCharacter.getIcon());
        holder.tvName.setText(gameCharacter.getName());
        holder.tvArchetype.setText(gameCharacter.getArchetype());
        holder.tvGameSystem.setTextColor(res.getColor(gameCharacter.getGameSystem().getColor()));
        holder.tvGameSystem.setText(gameCharacter.getGameSystem().getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return gameCharacters.size();
    }

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //View container;
        @Bind(R.id.icon) CircleImageView icon;
        @Bind(R.id.tvName) TextView tvName;
        @Bind(R.id.tvArchetype) TextView tvArchetype;
        @Bind(R.id.tvGameSystem) TextView tvGameSystem;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Otto.BUS.getBus().post(new CharacterClickedEvent(getAdapterPosition()));
                }
            });
        }
    }
}