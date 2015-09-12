package amagi82.modularcharactersheetcreator.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.activities.MainActivity;
import amagi82.modularcharactersheetcreator.adapters.SheetAdapter;
import amagi82.modularcharactersheetcreator.events.ModuleAddedEvent;
import amagi82.modularcharactersheetcreator.core_models.GameCharacter;
import amagi82.modularcharactersheetcreator.core_models.Sheet;
import amagi82.modularcharactersheetcreator.core_models.modules.Module;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TabFragment extends BaseFragment {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    private SheetAdapter adapter;
    private List<Module> modules;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
        ButterKnife.bind(this, rootView);

        GameCharacter character = ((MainActivity) getActivity()).getCurrentCharacter();
        Log.i(null, "Current character is "+character.name());
        Sheet sheet = character.sheets().get(getArguments().getInt("position"));
        int spanCount = sheet.numColumns();
        modules = sheet.modules();

        adapter = new SheetAdapter(getResources(), modules);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), spanCount);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override public int getSpanSize(int position) {
                return modules.get(position).getSpanCount();
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Subscribe
    public void onModuleAdded(ModuleAddedEvent event){
        Log.i(null, "Received module... adding it");
        modules.add(event.module);
        adapter.notifyDataSetChanged();
    }
}
