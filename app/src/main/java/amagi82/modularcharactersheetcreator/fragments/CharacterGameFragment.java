package amagi82.modularcharactersheetcreator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.CharacterGameAdapter;
import amagi82.modularcharactersheetcreator.models.games.Game;
import butterknife.Bind;
import butterknife.ButterKnife;

public class CharacterGameFragment extends Fragment {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.tvPrompt) TextView tvPrompt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view, container, false);
        ButterKnife.bind(this, rootView);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.character_game_span_count)));
        CharacterGameAdapter adapter = new CharacterGameAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.addAll(new Game().getList());

        tvPrompt.setText(R.string.choose_game);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
