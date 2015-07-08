package amagi82.modularcharactersheetcreator.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import amagi82.modularcharactersheetcreator.MainActivity;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.MainAdapter;
import amagi82.modularcharactersheetcreator.events.CreateNewCharacterEvent;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.utils.Otto;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends Fragment{

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.fab) FloatingActionButton fab;
    private SortedList<GameCharacter> characters;
    private MainAdapter adapter;
    //@InjectView(R.id.fab_frame) FrameLayout fab_frame;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        toolbar.setLogo(R.drawable.title_onyx);

        if(characters == null) characters = new SortedList<>(GameCharacter.class, new SortedList.Callback<GameCharacter>() {
            @Override public int compare(GameCharacter o1, GameCharacter o2) {
                return o1.getTimeStamp() > o2.getTimeStamp()? -1 : o1.getTimeStamp() < o2.getTimeStamp()? 1 : 0;
            }

            @Override public void onInserted(int position, int count) {
                adapter.notifyItemRangeInserted(position,count);
            }

            @Override public void onRemoved(int position, int count) {
                adapter.notifyItemRangeRemoved(position, count);
            }

            @Override public void onMoved(int fromPosition, int toPosition) {
                adapter.notifyItemMoved(fromPosition, toPosition);
            }

            @Override public void onChanged(int position, int count) {
                adapter.notifyItemRangeChanged(position, count);
            }

            @Override public boolean areContentsTheSame(GameCharacter oldCharacter, GameCharacter newCharacter) {
                return false;
            }

            @Override public boolean areItemsTheSame(GameCharacter item1, GameCharacter item2) {
                return item1.getEntityId().equals(item2.getEntityId());
            }
        });

        recyclerView.setHasFixedSize(true); //Improves performance if changes in content never change layout size
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(adapter == null) adapter = new MainAdapter(getActivity(), characters);
        else Log.i(null, "Adapter was not null");
        recyclerView.setAdapter(adapter);

        //Get characters from MainActivity and add to SortedList
        characters.beginBatchedUpdates();
        for(GameCharacter character : ((MainActivity) getActivity()).getCharacters()) characters.add(character);
        characters.endBatchedUpdates();

        return rootView;
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
        Otto.BUS.getBus().post(new CreateNewCharacterEvent());

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
//                        Otto.BUS.getBus().post(new CharacterAddedEvent());
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
        Log.i(null, "MainFragment destroyed");
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}