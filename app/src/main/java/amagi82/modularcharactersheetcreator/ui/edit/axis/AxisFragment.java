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
import amagi82.modularcharactersheetcreator.ui.base.BaseFragment;
import amagi82.modularcharactersheetcreator.ui.edit.CharacterUpdatedEvent;
import amagi82.modularcharactersheetcreator.ui.edit.EditActivity;
import icepick.State;

public class AxisFragment extends BaseFragment {

    private AxisViewModel viewModel;
    @State int previousPage;
    @State int page;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentEditAxisBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_axis, container, false);

        viewModel = new AxisViewModel(getResources(), getCurrentCharacter(), getArguments().getBoolean(EditActivity.LEFT));
        binding.setAxisViewModel(viewModel);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.character_axis_span_count)));

        return binding.getRoot();
    }
    @Override public void onResume() {
        super.onResume();
        viewModel.update(getCurrentCharacter());
    }

    private GameCharacter getCurrentCharacter(){
        return ((EditActivity) getActivity()).getGameCharacter();
    }

    @Subscribe public void characterUpdated(CharacterUpdatedEvent event){
        viewModel.update(getCurrentCharacter());
    }

    //    @Subscribe public void onPageChanged(PageChangedEvent event) {
//        int currentPage = event.currentPage;
//        if (currentPage == page && previousPage < currentPage) addItems(); //Items just became available. Add them
//        else if (currentPage - 1 == page) { //We've already selected a category. Refresh with default categories in case we have a sublist and the user navigates back
//            adapter.clear();
//            addItems();
//        } else if (currentPage < page) adapter.clear(); //The user navigated backward. Clear the data.
//
//        previousPage = currentPage;
//    }


}
