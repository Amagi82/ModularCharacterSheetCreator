package amagi82.modularcharactersheetcreator;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import amagi82.modularcharactersheetcreator.adapters.CharacterSheetRecyclerViewAdapter;
import amagi82.modularcharactersheetcreator.listeners.OnFabClickedListener;
import amagi82.modularcharactersheetcreator.models.GameCharacter;

public class CharacterSheetFragment extends Fragment {

    private OnFabClickedListener listener;

    public CharacterSheetFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        GameCharacter character = MainApplication.getGameCharacters().get(getArguments().getInt("character"));
        getActivity().setTitle(character.getCharacterName());

        int padding = getResources().getDimensionPixelSize(R.dimen.card_margin);
        RecyclerView characterSheetRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        characterSheetRecyclerView.setPadding(padding,padding,padding,padding);

        //characterSheetRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), null));

        // use a staggered grid layout manager
        RecyclerView.LayoutManager characterSheetLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL); //columns,orientation
        characterSheetRecyclerView.setLayoutManager(characterSheetLayoutManager);

        // specify an adapter (see also next example)
        RecyclerView.Adapter characterRecyclerViewAdapter = new CharacterSheetRecyclerViewAdapter(character.getModuleList(), getActivity());
        characterSheetRecyclerView.setAdapter(characterRecyclerViewAdapter);

        //Set up the Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_white_24dp);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnFabClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFabClickedListener");
        }
    }
}