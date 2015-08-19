package amagi82.modularcharactersheetcreator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.activities.EditCharacterActivity;
import amagi82.modularcharactersheetcreator.adapters.CharacterAxisAdapter;
import amagi82.modularcharactersheetcreator.events.LeftAxisEvent;
import amagi82.modularcharactersheetcreator.events.RightAxisEvent;
import amagi82.modularcharactersheetcreator.events.TileSplatClickedEvent;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.games.Splat;
import amagi82.modularcharactersheetcreator.models.games.systems.GameSystem;
import butterknife.Bind;
import butterknife.ButterKnife;

import static amagi82.modularcharactersheetcreator.activities.EditCharacterActivity.LEFT;
import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;

public class CharacterAxisFragment extends Fragment{

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.tvPrompt) TextView tvPrompt;
    private GameSystem system;
    private CharacterAxisAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view, container, false);
        ButterKnife.bind(this, rootView);

        GameCharacter character = ((EditCharacterActivity) getActivity()).getGameCharacter();
        system = character.getGameSystem();
        boolean isLeft = getArguments().getBoolean(LEFT, true);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.character_axis_span_count)));
        adapter = new CharacterAxisAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setLeft(isLeft);
        adapter.addAll(isLeft ? system.getListLeft(character.getLeft()) : system.getListRight(character.getLeft()));

        tvPrompt.setText(String.format(getString(R.string.choose), getString(isLeft ? system.getLeftTitle() : system.getRightTitle())));

        return rootView;
    }

    @Subscribe
    public void onItemSelected(TileSplatClickedEvent event) {
        Splat splat = event.splat;
        if(splat.isEndPoint()) BUS.getBus().post(event.left? new LeftAxisEvent(splat) : new RightAxisEvent(splat));
        else adapter.addAll(event.left? system.getListLeft(splat) : system.getListRight(splat));
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
