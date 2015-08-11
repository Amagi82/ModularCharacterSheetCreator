package amagi82.modularcharactersheetcreator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.CharacterAdapter;
import amagi82.modularcharactersheetcreator.events.TileGridItemClickedEvent;
import amagi82.modularcharactersheetcreator.models.game_systems.Game;
import amagi82.modularcharactersheetcreator.models.game_systems.Onyx;
import butterknife.Bind;
import butterknife.ButterKnife;

import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;

public class CharacterAxisFragment extends Fragment{

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    private Game game = new Game();
    private Onyx onyx;
    private CharacterAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view, container, false);
        ButterKnife.bind(this, rootView);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.character_grid_span_count)));
        adapter = new CharacterAdapter(this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Subscribe
    public void onItemSelected(TileGridItemClickedEvent event) {
        String eName = event.eName;
        if (event.left) {
            //Choose the left category
            if (onyx.getListLeft(eName).size() > 0) {
                //If there are additional choices available, present them
                adapter.addAll(onyx.getListLeft(eName));
            } else {
                //An option has been selected. Set the image and load the right side if needed
            }
        } else {
            //Choose the right category
            if (onyx.getListRight(eName).size() > 0) {
                //If there are additional options available, present them
                adapter.remove("BLOODLINES");
                adapter.remove("OTHERS");
                adapter.addAll(onyx.getListRight(eName));
            } else {
                //Finished. Set our images and finalize the game character
                //BUS.getBus().post(new RightAxisEvent(onyx.g));
            }
        }
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
