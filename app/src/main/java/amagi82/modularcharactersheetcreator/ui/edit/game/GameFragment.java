package amagi82.modularcharactersheetcreator.ui.edit.game;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.ui.base.BaseFragment;
import amagi82.modularcharactersheetcreator.entities.games.Game;
import butterknife.Bind;
import butterknife.ButterKnife;

public class GameFragment extends BaseFragment {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.tvPrompt) TextView tvPrompt;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_default, container, false);
        ButterKnife.bind(this, rootView);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.character_game_span_count)));
        GameAdapter adapter = new GameAdapter(this);
        recyclerView.setAdapter(adapter);
        if (adapter.getItemCount() == 0) adapter.addAll(new Game().getList());

        tvPrompt.setText(R.string.choose_game);

        return rootView;
    }
}
