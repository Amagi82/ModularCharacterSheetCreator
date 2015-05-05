package amagi82.modularcharactersheetcreator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.FloatingActionButton;


public class MainFragment extends Fragment {

    OnCharacterCreatedListener listener;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //Set up the Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
//        fab.attachToListView(gridView, new ScrollDirectionListener() {
//            @Override
//            public void onScrollDown() {
//                Log.d("ListViewFragment", "onScrollDown()");
//            }
//
//            @Override
//            public void onScrollUp() {
//                Log.d("ListViewFragment", "onScrollUp()");
//            }
//        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCharacterCreated();
            }
        });


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnCharacterCreatedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCharacterCreatedListener");
        }
    }

    public interface OnCharacterCreatedListener {
        void onCharacterCreated();
    }

}
