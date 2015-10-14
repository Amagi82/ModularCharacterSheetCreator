package amagi82.modularcharactersheetcreator.ui.sheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.ui.base.BaseFragment;

public class TabFragment extends BaseFragment {

    //@Bind(R.id.recycler_view) RecyclerView recyclerView;
    private SheetAdapter adapter;
    private List<Module> modules;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sheet_tab, container, false);
        //ButterKnife.bind(this, rootView);

//        GameCharacter character = ((MainActivity) getActivity()).getCurrentCharacter();
//        Log.i(null, "Current character is "+character.name());
//        Sheet sheet = character.sheets().get(getArguments().getInt("url"));
//        int spanCount = sheet.numColumns();
//        modules = sheet.modules();
//
//        adapter = new SheetAdapter(getResources(), modules);
//        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), spanCount);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override public int getSpanSize(int url) {
//                return modules.get(url).getSpanCount();
//            }
//        });
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
        return rootView;
    }

//    @Subscribe
//    public void onModuleAdded(ModuleAddedEvent event){
//        Log.i(null, "Received module... adding it");
//        modules.add(event.module);
//        adapter.notifyDataSetChanged();
//    }
}
