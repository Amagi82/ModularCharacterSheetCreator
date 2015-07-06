package amagi82.modularcharactersheetcreator.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.events.CharacterClickedEvent;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.utils.Otto;
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
