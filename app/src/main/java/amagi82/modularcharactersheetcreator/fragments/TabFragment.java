package amagi82.modularcharactersheetcreator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.SheetAdapter;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TabFragment extends Fragment {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        ButterKnife.bind(this, rootView);

        GameCharacter character = ((SheetFragment) getParentFragment()).getCharacter();
        Sheet sheet = character.getSheets().get(getArguments().getInt("sheet"));
        int spanCount = sheet.getNumColumns();
        List<Module> modules = sheet.getModules();

        StaggeredGridLayoutManager staggeredGrid = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        staggeredGrid.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(staggeredGrid);
        SheetAdapter adapter = new SheetAdapter(getResources(), modules);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
