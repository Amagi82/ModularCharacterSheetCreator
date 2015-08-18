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
import amagi82.modularcharactersheetcreator.adapters.CharacterGameAdapter;
import amagi82.modularcharactersheetcreator.events.GameSystemEvent;
import amagi82.modularcharactersheetcreator.events.GameClickedEvent;
import amagi82.modularcharactersheetcreator.models.games.Game;
import butterknife.Bind;
import butterknife.ButterKnife;

import static amagi82.modularcharactersheetcreator.models.games.Game.CWOD;
import static amagi82.modularcharactersheetcreator.models.games.Game.DEFAULT;
import static amagi82.modularcharactersheetcreator.models.games.Game.NWOD;
import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;

public class CharacterGameFragment extends Fragment {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    private Game game = new Game();
    private CharacterGameAdapter adapter;
    private boolean cWodDisplayed = false;
    private boolean nWodDisplayed = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view, container, false);
        ButterKnife.bind(this, rootView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CharacterGameAdapter();
        recyclerView.setAdapter(adapter);
        adapter.addItems(game.getList(DEFAULT));

        return rootView;
    }

    @Subscribe public void onGameSystemSelected(GameClickedEvent event) {
        if (event.system.getGameTitle() == R.string.cwod) {
            if(!cWodDisplayed) adapter.addItems(game.getList(CWOD));
            else adapter.removeItems(game.getList(CWOD));
            cWodDisplayed = !cWodDisplayed;
        } else if (event.system.getGameTitle() == R.string.nwod) {
            if(!nWodDisplayed) adapter.addItems(game.getList(NWOD));
            else adapter.removeItems(game.getList(NWOD));
            nWodDisplayed = !nWodDisplayed;
        } else BUS.getBus().post(new GameSystemEvent(event.system));
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
