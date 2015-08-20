package amagi82.modularcharactersheetcreator.fragments;

import android.os.Bundle;
import android.support.annotation.StringRes;
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
import amagi82.modularcharactersheetcreator.events.PageChangedEvent;
import amagi82.modularcharactersheetcreator.events.RightAxisEvent;
import amagi82.modularcharactersheetcreator.events.TileSplatClickedEvent;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.games.Splat;
import amagi82.modularcharactersheetcreator.models.games.systems.GameSystem;
import butterknife.Bind;
import butterknife.ButterKnife;

import static amagi82.modularcharactersheetcreator.activities.EditCharacterActivity.LEFT;
import static amagi82.modularcharactersheetcreator.utils.Otto.BUS;

public class CharacterAxisFragment extends Fragment {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.tvPrompt) TextView tvPrompt;
    private GameSystem system;
    private CharacterAxisAdapter adapter;
    private boolean isLeft;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view, container, false);
        ButterKnife.bind(this, rootView);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.character_axis_span_count)));
        adapter = new CharacterAxisAdapter(this);
        recyclerView.setAdapter(adapter);

        isLeft = getArguments().getBoolean(LEFT, true);
        adapter.setLeft(isLeft);

        return rootView;
    }

    @Subscribe public void onPageChanged(PageChangedEvent event) {
        int currentPage = event.currentPage;
        if(isLeft){
            if(currentPage == 1) updateLeft();
            else adapter.clear();
            if(currentPage == 2) updateLeft();
        }else{
            if(currentPage == 2) updateRight();
            else adapter.clear();
            if(currentPage == 3) updateRight();
        }
    }

    private void updateLeft() {
        GameCharacter character = ((EditCharacterActivity) getActivity()).getGameCharacter();
        system = character.getGameSystem();

        if (system != null) {
            adapter.addAll(system.getListLeft(character.getLeft()));
            setTvPrompt(system.getLeftTitle());
        }
    }

    private void updateRight() {
        GameCharacter character = ((EditCharacterActivity) getActivity()).getGameCharacter();
        system = character.getGameSystem();

        if (system != null && character.getLeft() != null) {
            adapter.addAll(system.getListRight(character.getLeft()));
            setTvPrompt(system.getRightTitle(character.getLeft()));
        }
    }

    private void setTvPrompt(@StringRes int title) {
        tvPrompt.setText(String.format(getString(R.string.choose), getString(title)));
    }

    @Subscribe public void onItemSelected(TileSplatClickedEvent event) {
        //Both Axis fragments get this event. Make sure we don't send two events.
        if (isLeft && event.left || !isLeft && !event.left) {
            Splat splat = event.splat;
            if (splat.isEndPoint()) BUS.getBus().post(event.left ? new LeftAxisEvent(splat) : new RightAxisEvent(splat));
            else adapter.replaceAll(event.left ? system.getListLeft(splat) : system.getListRight(splat));
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

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
