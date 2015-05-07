package amagi82.modularcharactersheetcreator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;

import java.util.ArrayList;

import amagi82.modularcharactersheetcreator.adapters.MainRecyclerViewAdapter;
import amagi82.modularcharactersheetcreator.adapters.extras.DividerItemDecoration;
import amagi82.modularcharactersheetcreator.listeners.OnCharacterAddedListener;
import amagi82.modularcharactersheetcreator.models.GameCharacter;


public class MainFragment extends Fragment {

    OnCharacterAddedListener listener;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ArrayList<GameCharacter> gameCharacters = new ArrayList<>();
        gameCharacters.add(new GameCharacter("Thomas Anstis", "Vampire", "Gangrel"));
        gameCharacters.add(new GameCharacter("Tom Lytton", "Vampire", "Brujah"));
        gameCharacters.add(new GameCharacter("Georgia Johnson", "Vampire", "Tremere"));
        gameCharacters.add(new GameCharacter("Augustus von Rabenholtz", "Vampire", "Ventrue"));

        RecyclerView mainRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        mainRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), null));

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mainRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager mainLayoutManager = new LinearLayoutManager(getActivity());
        mainRecyclerView.setLayoutManager(mainLayoutManager);

        // specify an adapter (see also next example)
        RecyclerView.Adapter mainRecyclerViewAdapter = new MainRecyclerViewAdapter(gameCharacters);
        mainRecyclerView.setAdapter(mainRecyclerViewAdapter);

        //Set up the Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_person_add_white_24dp);
        fab.attachToRecyclerView(mainRecyclerView, new ScrollDirectionListener() {
            @Override
            public void onScrollDown() {
            }
            @Override
            public void onScrollUp() {
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCharacterAdded();
            }
        });


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnCharacterAddedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCharacterAddedListener");
        }
    }
}
