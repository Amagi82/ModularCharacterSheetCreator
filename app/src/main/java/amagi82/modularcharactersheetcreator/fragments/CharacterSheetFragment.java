package amagi82.modularcharactersheetcreator.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import amagi82.modularcharactersheetcreator.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class CharacterSheetFragment extends Fragment {

    @InjectView(R.id.recycler_view) RecyclerView recyclerView;
    @InjectView(R.id.fab) FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);

//        GameCharacter character = MainApplication.getGameCharacters().get(getArguments().getInt("character"));
//        getActivity().setTitle(character.getName());

        int padding = getResources().getDimensionPixelSize(R.dimen.card_margin);
        recyclerView.setPadding(padding,padding,padding,padding);

        //characterSheetRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), null));

        // use a staggered grid layout manager
        RecyclerView.LayoutManager characterSheetLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL); //columns,orientation
        recyclerView.setLayoutManager(characterSheetLayoutManager);

        // specify an adapter (see also next example)
//        RecyclerView.Adapter characterRecyclerViewAdapter = new CharacterSheetRecyclerViewAdapter(character.getModuleList(), getActivity());
//        recyclerView.setAdapter(characterRecyclerViewAdapter);

        //Set up the Floating Action Button
        fab.setImageResource(R.drawable.ic_add_white_24dp);

        return rootView;
    }
}