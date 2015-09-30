package amagi82.modularcharactersheetcreator.ui.edit.axis;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.FragmentEditAxisBinding;
import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.entities.characters.Splat;
import amagi82.modularcharactersheetcreator.ui.base.BaseFragment;
import amagi82.modularcharactersheetcreator.ui.edit.CharacterUpdatedEvent;
import amagi82.modularcharactersheetcreator.ui.edit.EditActivity;

public class AxisFragment extends BaseFragment {
    private AxisViewModel axisViewModel;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentEditAxisBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_axis, container, false);

        axisViewModel = new AxisViewModel(getActivity(), getCurrentCharacter(), getArguments().getBoolean(EditActivity.LEFT));
        binding.setAxisViewModel(axisViewModel);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.character_axis_span_count)));

        return binding.getRoot();
    }

    public Splat getSplat(int position){
        Splat splat = axisViewModel.list.get(position).splat;
        updateIfNotEndpoint(splat);
        return splat;
    }

    private void updateIfNotEndpoint(Splat splat){
        if(!splat.isEndPoint()) axisViewModel.updateList(getCurrentCharacter(), splat);
    }

    private GameCharacter getCurrentCharacter(){
        return ((EditActivity) getActivity()).getGameCharacter();
    }

    @Subscribe public void characterUpdated(CharacterUpdatedEvent event){
        axisViewModel.updateList(getCurrentCharacter(), null);
    }
}
