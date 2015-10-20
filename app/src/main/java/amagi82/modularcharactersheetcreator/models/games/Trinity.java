package amagi82.modularcharactersheetcreator.models.games;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.Splat;

/*
    Trinity Continuum
    Includes Aeon, Aberrant, and Adventure
 */
public class Trinity extends GameSystem {

    public Trinity() {
        super();
        this.gameTitle = R.string.trinity;
        this.leftTitle = R.string.age;
        this.isArchetypeLeft = false;
        this.gameUrl = R.string.url_game_trinity;
        this.splashUrl = R.string.url_splash_trinity;
        this.gameColor = R.color.trinity;
        this.isRightListFinal = false;
    }

    @Override public int getRightTitle(@Nullable Splat leftSplat) {
        if (leftSplat != null && leftSplat.title() == R.string.aeon) return R.string.psi_order;
        return R.string.allegiance;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(3);
        list.add(Splat.create(R.string.adventure));
        list.add(Splat.create(R.string.aberrant));
        list.add(Splat.create(R.string.aeon));
        return list;
    }

    @Override public List<Splat> getListRight(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(10);
        if (splat == null) return list;
        switch (splat.title()) {
            case R.string.adventure:
                list.add(Splat.create(R.string.the_aeon_society_for_gentlemen));
                list.add(Splat.create(R.string.the_air_circus));
                list.add(Splat.create(R.string.branch_9));
                list.add(Splat.create(R.string.the_international_detective_agency));
                list.add(Splat.create(R.string.the_ponatowski_foundation));
                break;
            case R.string.aberrant:
                list.add(Splat.create(R.string.aberrants));
                list.add(Splat.create(R.string.project_utopia));
                list.add(Splat.create(R.string.team_tomorrow));
                list.add(Splat.create(R.string.project_proteus));
                list.add(Splat.create(R.string.the_teragen));
                list.add(Splat.create(R.string.the_directive));
                list.add(Splat.create(R.string.corporate));
                list.add(Splat.create(R.string.government));
                list.add(Splat.create(R.string.other));
                list.add(Splat.create(R.string.independent));
                break;
            default:
                list.add(Splat.create(R.string.aesculapian_order));
                list.add(Splat.create(R.string.chitra_bhanu));
                list.add(Splat.create(R.string.isra));
                list.add(Splat.create(R.string.the_legions));
                list.add(Splat.create(R.string.the_ministry_of_noetic_affairs));
                list.add(Splat.create(R.string.nova_forca_das_nacoes));
                list.add(Splat.create(R.string.orgotek));
                list.add(Splat.create(R.string.the_upeo_wa_macho));
                break;
        }
        return list;
    }
}
