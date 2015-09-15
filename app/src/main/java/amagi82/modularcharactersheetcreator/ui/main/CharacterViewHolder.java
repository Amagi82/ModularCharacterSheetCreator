package amagi82.modularcharactersheetcreator.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.ui.extras.events.CharacterClickedEvent;
import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.ui.extras.utils.Otto;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CharacterViewHolder extends RecyclerView.ViewHolder  {
    @Bind(R.id.icon) public CircleImageView icon;
    @Bind(R.id.tvName) public TextView tvName;
    @Bind(R.id.tvArchetype) public TextView tvArchetype;
    @Bind(R.id.tvGameSystem) public TextView tvGameSystem;
    public GameCharacter gameCharacter;

    public CharacterViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Otto.BUS.getBus().post(new CharacterClickedEvent(gameCharacter));
            }
        });
    }
}
