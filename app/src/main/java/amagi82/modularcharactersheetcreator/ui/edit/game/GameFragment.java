package amagi82.modularcharactersheetcreator.ui.edit.game;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.FragmentEditGameBinding;
import amagi82.modularcharactersheetcreator.entities.games.Game;
import amagi82.modularcharactersheetcreator.ui.base.BaseFragment;

public class GameFragment extends BaseFragment {

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentEditGameBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_game, container, false);

        binding.setGameViewModel(new GameViewModel(new Game().getList()));
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.character_game_span_count)));

        return binding.getRoot();
    }
}
