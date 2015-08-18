package amagi82.modularcharactersheetcreator.models.games.systems;

import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Splat;

public class Trinity extends GameSystem {
    @TrinityAge private int trinityAge;

    public Trinity() {
        super();
        this.gameTitle = R.string.trinity;
        this.leftTitle = R.string.age;
        this.isArchetypeLeft = false;
        this.gameUrl = R.string.url_game_trinity;
        this.splashUrl = R.string.url_splash_trinity;
        this.gameColor = R.color.trinity;
    }

    @Override public int getRightTitle() {
        if(trinityAge == AEON) return R.string.psi_order;
        return R.string.allegiance;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(new Splat(R.string.adventure));
        list.add(new Splat(R.string.aberrant));
        list.add(new Splat(R.string.aeon));
        return list;
    }

    @Override public List<Splat> getListRight(Splat splat) {
        List<Splat> list = new ArrayList<>();
        switch(splat.getTitle()){
            case R.string.adventure:
                trinityAge = ADVENTURE;
                list.add(new Splat(R.string.the_aeon_society_for_gentlemen));
                list.add(new Splat(R.string.the_air_circus));
                list.add(new Splat(R.string.branch_9));
                list.add(new Splat(R.string.the_international_detective_agency));
                list.add(new Splat(R.string.the_ponatowski_foundation));
                break;
            case R.string.aberrant:
                trinityAge = ABERRANT;
                list.add(new Splat(R.string.aberrants));
                list.add(new Splat(R.string.project_utopia));
                list.add(new Splat(R.string.team_tomorrow));
                list.add(new Splat(R.string.project_proteus));
                list.add(new Splat(R.string.the_teragen));
                list.add(new Splat(R.string.the_directive));
                list.add(new Splat(R.string.corporate));
                list.add(new Splat(R.string.government));
                list.add(new Splat(R.string.other));
                list.add(new Splat(R.string.independent));
                break;
            default:
                trinityAge = AEON;
                list.add(new Splat(R.string.aesculapian_order));
                list.add(new Splat(R.string.chitra_bhanu));
                list.add(new Splat(R.string.isra));
                list.add(new Splat(R.string.the_legions));
                list.add(new Splat(R.string.the_ministry_of_noetic_affairs));
                list.add(new Splat(R.string.nova_forca_das_nacoes));
                list.add(new Splat(R.string.orgotek));
                list.add(new Splat(R.string.the_upeo_wa_macho));
                break;
        }
        return list;
    }


    @IntDef({ADVENTURE, ABERRANT, AEON})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TrinityAge {}
    public static final int ADVENTURE = 0;
    public static final int ABERRANT = 1;
    public static final int AEON = 2;

}
