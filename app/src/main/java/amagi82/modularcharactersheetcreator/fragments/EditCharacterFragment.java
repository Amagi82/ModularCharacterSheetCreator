package amagi82.modularcharactersheetcreator.fragments;

import android.content.res.Resources;
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
import amagi82.modularcharactersheetcreator.events.TileGridItemClickedEvent;
import amagi82.modularcharactersheetcreator.events.TileItemClickedEvent;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.game_systems.Game;
import amagi82.modularcharactersheetcreator.models.game_systems.Onyx;
import butterknife.Bind;
import butterknife.ButterKnife;

import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;

public class EditCharacterFragment extends Fragment {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    private Resources res;
    private GameCharacter character;
    private Game game = new Game();
    private Onyx onyx;
    private CharacterAdapter adapter;
    private boolean isEditMode = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view, container, false);
        ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);

        res = getResources();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CharacterAdapter(this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    //Game System selected
    @Subscribe public void onTileClicked(TileItemClickedEvent event) {
        //If a system with subcategories has been selected, show them
        if (event.system == Game.System.CWOD) adapter.replaceAll(game.getList(Game.Category.CWOD));
        else if (event.system == Game.System.NWOD) adapter.replaceAll(game.getList(Game.Category.NWOD));
        else {
            //System selected. Update onyx and choose archetype.
            onyx = event.system.getOnyx();
            //chooseLeftCategory();
        }
    }

    //Game-specific subcategory selected
    @Subscribe
    public void onGridTileClicked(TileGridItemClickedEvent event) {
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

            }
        }
    }

    @Override public void onStart() {
        super.onStart();
        BUS.getBus().register(this);
    }

    @Override public void onStop() {
        super.onStop();
        BUS.getBus().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
