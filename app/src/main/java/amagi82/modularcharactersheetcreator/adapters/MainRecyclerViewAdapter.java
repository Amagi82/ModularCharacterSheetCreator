package amagi82.modularcharactersheetcreator.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.utils.DefaultIconFactory;
import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private List<GameCharacter> gameCharacters;
    private DefaultIconFactory iconFactory;
    private Context context;

    public MainRecyclerViewAdapter(Context context, List<GameCharacter> gameCharacters) {
        this.context = context;
        this.gameCharacters = gameCharacters;
        iconFactory = new DefaultIconFactory(context);
    }

    public MainRecyclerViewAdapter(Context context) {
        this.context = context;
        gameCharacters = new ArrayList<>();
        iconFactory = new DefaultIconFactory(context);
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
        final GameCharacter gameCharacter = gameCharacters.get(position);
        if(gameCharacter.getCharacterIcon() == null){
            int color = gameCharacter.getColorPrimary() < 1? context.getResources().getColor(R.color.primary) : gameCharacter.getColorPrimary();
            gameCharacter.setCharacterIcon(iconFactory.createIcon(gameCharacter.getCharacterName(),color));
        }
        holder.imageCharacterIcon.setImageBitmap(gameCharacter.getCharacterIcon());
        holder.tvName.setText(gameCharacter.getCharacterName());
        holder.tvCharacterClass.setText(gameCharacter.getCharacterRace() + " " + gameCharacter.getCharacterClass());
        holder.tvGameSystem.setText(gameCharacter.getGameSystem());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return gameCharacters.size();
    }

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //View container;
        @InjectView(R.id.characterIcon) CircleImageView imageCharacterIcon;
        @InjectView(R.id.tvName) TextView tvName;
        @InjectView(R.id.tvCharacterClass) TextView tvCharacterClass;
        @InjectView(R.id.tvGameSystem) TextView tvGameSystem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

//            container = itemView;
//            container.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    listener.onCharacterClicked(getAdapterPosition());
//                }
//            });
//            container.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    longClickListener.onCharacterLongClicked(getAdapterPosition());
//                    return true;
//                }
//            });
        }
    }
}