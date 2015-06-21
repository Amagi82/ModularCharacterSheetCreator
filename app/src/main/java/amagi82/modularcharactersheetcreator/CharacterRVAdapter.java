package amagi82.modularcharactersheetcreator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import amagi82.modularcharactersheetcreator.adapters.MainRVAdapter;
import amagi82.modularcharactersheetcreator.models.GameCharacter;

public class CharacterRVAdapter extends RecyclerView.Adapter<MainRVAdapter.ViewHolder> {

    private GameCharacter character;

    public CharacterRVAdapter(Context context, GameCharacter character) {
        this.character = character;
    }

    @Override public MainRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override public void onBindViewHolder(MainRVAdapter.ViewHolder holder, int position) {

    }

    @Override public int getItemCount() {
        return 0;
    }
}