package amagi82.modularcharactersheetcreator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import amagi82.modularcharactersheetcreator.App;
import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.SheetActivity;
import amagi82.modularcharactersheetcreator.models.Choice;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TabProfileFragment extends Fragment {

    @Bind(R.id.imagePortrait) ImageView imagePortrait;
    @Bind(R.id.iconLeft) ImageView iconLeft;
    @Bind(R.id.iconRight) ImageView iconRight;
    @Bind(R.id.tvIconLeft) TextView tvIconLeft;
    @Bind(R.id.tvIconRight) TextView tvIconRight;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_profile, container, false);
        ButterKnife.bind(this, rootView);

        GameCharacter character = ((SheetActivity) getActivity()).getCharacter();
        Glide.with(this).load(character.getPortraitUri()).into(imagePortrait);
        Glide.with(this).load(getUrl(character.getLeft())).into(iconLeft);
        if(character.getGameSystem().getOnyx().hasRight()) {
            Glide.with(this).load(getUrl(character.getRight())).into(iconRight);
            tvIconRight.setText(character.getRight().getTitle());
        }
        tvIconLeft.setText(character.getLeft().getTitle());

        return rootView;
    }

    private String getUrl(Choice choice){
        return choice.getUrl() == App.NONE? getString(R.string.url_default) : getString(choice.getBaseUrl()) + getString(choice.getUrl());
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}