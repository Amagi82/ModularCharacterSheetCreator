package amagi82.modularcharactersheetcreator.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import amagi82.modularcharactersheetcreator.models.GameCharacter;

public class CharacterAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private GameCharacter character;

    public CharacterAdapter(Context context, GameCharacter character) {
        this.character = character;
    }

    @Override public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {

    }

    @Override public int getItemCount() {
        return 0;
    }
}