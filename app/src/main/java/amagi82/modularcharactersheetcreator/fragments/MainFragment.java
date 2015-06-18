package amagi82.modularcharactersheetcreator.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import amagi82.modularcharactersheetcreator.MainApplication;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.MainRecyclerViewAdapter;
import amagi82.modularcharactersheetcreator.events.CreateCharacterEvent;
import amagi82.modularcharactersheetcreator.utils.Otto;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainFragment extends Fragment {

    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.recycler_view) RecyclerView recyclerView;
    @InjectView(R.id.fab) FloatingActionButton fab;
    //@InjectView(R.id.fab_frame) FrameLayout fab_frame;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.inject(this, rootView);
        toolbar.setTitle(getString(R.string.characters));

        recyclerView.setHasFixedSize(true); //Improves performance if changes in content never change layout size
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MainRecyclerViewAdapter recyclerViewAdapter = new MainRecyclerViewAdapter(getActivity(), MainApplication.getGameCharacters());
        recyclerView.setAdapter(recyclerViewAdapter);
        return rootView;
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
//        float scale = (float) (2* Math.hypot(fab_frame.getWidth(), fab_frame.getHeight()) / fab.getHeight());
//        fab.setImageResource(0);
//        fab.animate()
//                .setInterpolator(new LinearInterpolator())
//                .setDuration(400)
//                .scaleX(scale)
//                .scaleY(scale)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        super.onAnimationEnd(animation);
                        Otto.BUS.getBus().post(new CreateCharacterEvent());
//                    }
//                });
//
//        toolbar.animate().alpha(0).setDuration(400);
    }

    @Override public void onStart() {
        super.onStart();
        Otto.BUS.getBus().register(this);
    }

    @Override public void onStop() {
        super.onStop();
        Otto.BUS.getBus().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}