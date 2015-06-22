package amagi82.modularcharactersheetcreator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import amagi82.modularcharactersheetcreator.R;

public class ModuleFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_module, container, false);
        setHasOptionsMenu(true);


        return rootView;
    }
}
