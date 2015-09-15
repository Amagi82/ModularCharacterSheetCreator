package amagi82.modularcharactersheetcreator.ui.edit.axis;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.entities.characters.Splat;
import amagi82.modularcharactersheetcreator.entities.games.GameSystem;
import amagi82.modularcharactersheetcreator.ui.base.BaseFragment;
import amagi82.modularcharactersheetcreator.ui.edit.EditActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.State;

import static amagi82.modularcharactersheetcreator.ui.edit.EditActivity.LEFT;

public class AxisFragment extends BaseFragment {

    private AxisAdapter adapter;
    private boolean isLeft;
    @State ArrayList<Splat> list;
    @State int previousPage;
    @State int page;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_default, container, false);
        ButterKnife.bind(this, rootView);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.character_axis_span_count)));
        adapter = new AxisAdapter(this);
        recyclerView.setAdapter(adapter);

        isLeft = getArguments().getBoolean(LEFT, true);

        return rootView;
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(list != null) adapter.addAll(list); //Restore the list on screen rotation
        adapter.setLeft(isLeft);
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

    private void addItems() {
        GameCharacter character = ((EditActivity) getActivity()).getGameCharacter();
        GameSystem system = character.getGameSystem();

        if (system != null) {
            if (isLeft) {
                adapter.addAll(system.getListLeft(character.left()));
                setTvPrompt(system.getLeftTitle());
            } else if (character.left() != null) {
                adapter.addAll(system.getListRight(character.left()));
                setTvPrompt(system.getRightTitle(character.left()));
            }
        }
    }

    private void setTvPrompt(@StringRes int title) {
        tvPrompt.setText(String.format(getString(R.string.choose), getString(title)));
    }

//    @Subscribe public void onItemSelected(TileSplatClickedEvent event) {
//        //Both Axis fragments get this event. Make sure we don't send two events.
//        if (isLeft && event.left || !isLeft && !event.left) {
//            Splat splat = event.splat;
//            if (splat.isEndPoint()) BUS.getBus().post(event.left ? new LeftAxisEvent(splat) : new RightAxisEvent(splat));
//            else adapter.replaceAll(event.left ? system.getListLeft(splat) : system.getListRight(splat));
//        }
//    }

    @Override public void onSaveInstanceState(Bundle outState) {
        if(adapter != null) list = adapter.getAll(); //Restore the list on screen rotation
        super.onSaveInstanceState(outState);
    }
}
