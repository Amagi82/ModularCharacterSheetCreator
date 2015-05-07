package amagi82.modularcharactersheetcreator;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;

import java.util.ArrayList;

import amagi82.modularcharactersheetcreator.adapters.CharacterSheetRecyclerViewAdapter;
import amagi82.modularcharactersheetcreator.listeners.OnFabClickedListener;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.models.modules.TextOnlyModule;

public class CharacterSheetFragment extends Fragment {

    private OnFabClickedListener listener;

    public CharacterSheetFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        GameCharacter character = MainActivity.gameCharacterList.get(getArguments().getInt("character"));
        getActivity().setTitle(character.getName());
        ArrayList<Module> modules = character.getModuleList();

        RecyclerView characterSheetRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        //characterSheetRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), null));

        TextOnlyModule module1 = new TextOnlyModule();
        module1.setText("Test text 1");
        TextOnlyModule module2 = new TextOnlyModule();
        module2.setText("Jurassic World comes out next month");

        modules.add(module1);
        modules.add(module2);

        // use a staggered grid layout manager
        RecyclerView.LayoutManager characterSheetLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL); //columns,orientation
        characterSheetRecyclerView.setLayoutManager(characterSheetLayoutManager);

        // specify an adapter (see also next example)
        RecyclerView.Adapter characterRecyclerViewAdapter = new CharacterSheetRecyclerViewAdapter(modules, getActivity());
        characterSheetRecyclerView.setAdapter(characterRecyclerViewAdapter);

        //Set up the Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_white_24dp);
        fab.attachToRecyclerView(characterSheetRecyclerView, new ScrollDirectionListener() {
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
                listener.onModuleAdded();
            }
        });


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