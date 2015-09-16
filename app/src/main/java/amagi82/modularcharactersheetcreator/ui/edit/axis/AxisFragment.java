package amagi82.modularcharactersheetcreator.ui.edit.axis;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.FragmentEditAxisBinding;
import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.entities.characters.Splat;
import amagi82.modularcharactersheetcreator.entities.games.GameSystem;
import amagi82.modularcharactersheetcreator.ui.base.BaseFragment;
import amagi82.modularcharactersheetcreator.ui.edit.EditActivity;
import amagi82.modularcharactersheetcreator.ui.edit.ListUpdatedEvent;
import amagi82.modularcharactersheetcreator.ui.main.MainActivity;
import amagi82.modularcharactersheetcreator.ui.main.MainViewModel;
import icepick.State;

import static amagi82.modularcharactersheetcreator.ui.edit.EditActivity.LEFT;

public class AxisFragment extends BaseFragment {

    private AxisViewModel viewModel;
    @State ArrayList<Splat> list;
    @State int previousPage;
    @State int page;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentEditAxisBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_axis, container, false);
        if(list == null) list = new ArrayList<>();

        viewModel = new AxisViewModel(getResources(), list);
        binding.setViewModel(viewModel);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.character_axis_span_count)));

        return binding.getRoot();
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        page = isLeft ? 1 : 2;
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

    @Subscribe public void updateList(ListUpdatedEvent event){
        viewModel.replaceAll(event.list);
    }
}
