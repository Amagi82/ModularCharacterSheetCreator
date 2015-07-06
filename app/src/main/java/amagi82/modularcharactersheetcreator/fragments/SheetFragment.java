package amagi82.modularcharactersheetcreator.fragments;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.IOException;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.widgets.AnimatedNetworkImageView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SheetFragment extends Fragment {

    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout toolbar;
    @Bind(R.id.imagePortrait) AnimatedNetworkImageView imagePortrait;
    @Bind(R.id.iconLeft) AnimatedNetworkImageView iconLeft;
    @Bind(R.id.tvIconLeft) TextView tvIconLeft;
    @Bind(R.id.iconRight) AnimatedNetworkImageView iconRight;
    @Bind(R.id.tvIconRight) TextView tvIconRight;
    @Bind(R.id.tabs) TabLayout tabLayout;
    @Bind(R.id.fab) FloatingActionButton fab;
    private GameCharacter gameCharacter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sheet, container, false);
        ButterKnife.bind(this, rootView);

        try {
            gameCharacter = LoganSquare.parse(getArguments().getString("Character"), GameCharacter.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (gameCharacter == null) getFragmentManager().popBackStack();

        toolbar.setTitle(gameCharacter.getName());

        //tabLayout.addTab();

        //int padding = getResources().getDimensionPixelSize(R.dimen.card_margin);
        //recyclerView.setPadding(padding,padding,padding,padding);

        //characterSheetRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), null));

        // use a staggered grid layout manager
        //RecyclerView.LayoutManager characterSheetLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL); //columns,orientation
        //recyclerView.setLayoutManager(characterSheetLayoutManager);

        // specify an adapter (see also next example)
//        RecyclerView.Adapter characterRecyclerViewAdapter = new SheetAdapter(character.getModuleList(), getActivity());
//        recyclerView.setAdapter(characterRecyclerViewAdapter);

        //Set up the Floating Action Button
        fab.setImageResource(R.drawable.ic_add_white_24dp);

        return rootView;
    }
}