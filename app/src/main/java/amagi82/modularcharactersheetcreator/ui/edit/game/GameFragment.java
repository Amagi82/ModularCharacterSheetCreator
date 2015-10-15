package amagi82.modularcharactersheetcreator.ui.edit.game;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.FragmentEditGameBinding;
import amagi82.modularcharactersheetcreator.models.games.Game;

public class GameFragment extends Fragment {

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentEditGameBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_game, container, false);
        binding.setGameViewModel(new GameViewModel(new Game().getList()));

        return binding.getRoot();
    }
}
