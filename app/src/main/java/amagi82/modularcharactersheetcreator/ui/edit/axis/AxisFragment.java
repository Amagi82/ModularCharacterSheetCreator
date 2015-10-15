package amagi82.modularcharactersheetcreator.ui.edit.axis;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.FragmentEditAxisBinding;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.ui.base.BaseFragment;
import amagi82.modularcharactersheetcreator.ui.edit.CharacterUpdatedEvent;
import amagi82.modularcharactersheetcreator.ui.edit.EditActivity;

public class AxisFragment extends BaseFragment {
    private AxisViewModel axisViewModel;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentEditAxisBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_axis, container, false);

        axisViewModel = new AxisViewModel(getCurrentCharacter(), getArguments().getBoolean(EditActivity.LEFT));
        binding.setAxisViewModel(axisViewModel);

        return binding.getRoot();
    }

    private GameCharacter getCurrentCharacter() {
        return ((EditActivity) getActivity()).getGameCharacter();
    }

    @Subscribe public void axisUpdated(AxisUpdateEvent event) {
        axisViewModel.update(getCurrentCharacter(), event.splat);
    }

    @Subscribe public void characterUpdated(CharacterUpdatedEvent event) {
        axisViewModel.update(getCurrentCharacter(), null);
    }
}
