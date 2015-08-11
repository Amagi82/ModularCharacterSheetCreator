package amagi82.modularcharactersheetcreator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.CharacterAdapter;
import amagi82.modularcharactersheetcreator.events.GameSystemEvent;
import amagi82.modularcharactersheetcreator.events.TileItemClickedEvent;
import amagi82.modularcharactersheetcreator.models.game_systems.Game;
import butterknife.Bind;
import butterknife.ButterKnife;

import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;

public class CharacterGameFragment extends Fragment {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    private Game game = new Game();
    private CharacterAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view, container, false);
        ButterKnife.bind(this, rootView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CharacterAdapter(this);
        recyclerView.setAdapter(adapter);

        adapter.addAll(game.getList(Game.Category.DEFAULT));

        return rootView;
    }

    @Subscribe public void onGameSystemSelected(TileItemClickedEvent event) {
        //If a system with subcategories has been selected, show them
        if (event.system == Game.System.CWOD) adapter.replaceAll(game.getList(Game.Category.CWOD));
        else if (event.system == Game.System.NWOD) adapter.replaceAll(game.getList(Game.Category.NWOD));
        else BUS.getBus().post(new GameSystemEvent(event.system.getOnyx()));
    }

    @Override
    public void onStart() {
        super.onStart();
        BUS.getBus().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        BUS.getBus().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
