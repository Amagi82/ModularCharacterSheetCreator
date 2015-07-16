package amagi82.modularcharactersheetcreator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import java.util.List;

import amagi82.modularcharactersheetcreator.MainActivity;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.SheetAdapter;
import amagi82.modularcharactersheetcreator.events.ModuleAddedEvent;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.utils.Otto;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TabFragment extends Fragment {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    private SheetAdapter adapter;
    private List<Module> modules;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        ButterKnife.bind(this, rootView);

        GameCharacter character = ((MainActivity) getActivity()).getCurrentCharacter();
        Log.i(null, "Current character is "+character.getName());
        Sheet sheet = character.getSheets().get(getArguments().getInt("position"));
        int spanCount = sheet.getNumColumns();
        modules = sheet.getModules();

        StaggeredGridLayoutManager staggeredGrid = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        staggeredGrid.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(staggeredGrid);
        adapter = new SheetAdapter(getResources(), sheet);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @Override public void onStart() {
        super.onStart();
        Otto.BUS.getBus().register(this);
    }

    @Override public void onStop() {
        super.onStop();
        Otto.BUS.getBus().unregister(this);
    }

    @Subscribe
    public void onModuleAdded(ModuleAddedEvent event){
        Log.i(null, "Received module... adding it");
        modules.add(event.module);
        adapter.notifyDataSetChanged();
    }
}
