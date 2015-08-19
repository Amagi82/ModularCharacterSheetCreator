package amagi82.modularcharactersheetcreator.models.games.systems;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.games.Splat;

public class CVampire extends GameSystem {

    public CVampire() {
        super();
        this.gameTitle = R.string.cwod_vampire;
        this.leftTitle = R.string.clan;
        this.rightTitle = R.string.sect;
        this.gameUrl = R.string.url_game_cwod_vampire;
        this.splashUrl = R.string.url_splash_cwod_vampire;
        this.gameColor = R.color.cwod_vampire;
        this.checkLeft = true;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>();
        if(splat != null && splat.getTitle() == R.string.bloodlines){
            list.add(new Splat(R.string.ahrimanes, R.string.url_cwod_vampire_clan_ahrimanes));
            list.add(new Splat(R.string.anda, R.string.url_cwod_vampire_clan_anda));
            list.add(new Splat(R.string.baali, R.string.url_cwod_vampire_clan_baali));
            list.add(new Splat(R.string.blood_brothers, R.string.url_cwod_vampire_clan_blood_brothers));
            list.add(new Splat(R.string.cappadocians, R.string.url_cwod_vampire_clan_cappadocians));
            list.add(new Splat(R.string.children_of_osiris, R.string.url_cwod_vampire_clan_children_of_osiris));
            list.add(new Splat(R.string.daughers_of_cacophony, R.string.url_cwod_vampire_clan_daughers_of_cacophony));
            list.add(new Splat(R.string.gargoyle, R.string.url_cwod_vampire_clan_gargoyle));
            list.add(new Splat(R.string.harbingers_of_skulls, R.string.url_cwod_vampire_clan_harbingers_of_skulls));
            list.add(new Splat(R.string.kiasyd, R.string.url_cwod_vampire_clan_kiasyd));
            list.add(new Splat(R.string.lamia, R.string.url_cwod_vampire_clan_lamia));
            list.add(new Splat(R.string.lhiannan, R.string.url_cwod_vampire_clan_lhiannan));
            list.add(new Splat(R.string.nagaraja, R.string.url_cwod_vampire_clan_nagaraja));
            list.add(new Splat(R.string.noiad, R.string.url_cwod_vampire_clan_noiad));
            list.add(new Splat(R.string.panders, R.string.url_cwod_vampire_clan_panders));
            list.add(new Splat(R.string.salubri, R.string.url_cwod_vampire_clan_salubri));
            list.add(new Splat(R.string.samedi, R.string.url_cwod_vampire_clan_samedi));
            list.add(new Splat(R.string.true_brujah, R.string.url_cwod_vampire_clan_true_brujah));
        }else{
            list.add(new Splat(R.string.assamite, R.string.url_cwod_vampire_clan_assamite));
            list.add(new Splat(R.string.brujah, R.string.url_cwod_vampire_clan_brujah));
            list.add(new Splat(R.string.followers_of_set, R.string.url_cwod_vampire_clan_followers_of_set));
            list.add(new Splat(R.string.gangrel, R.string.url_cwod_vampire_clan_gangrel));
            list.add(new Splat(R.string.giovanni, R.string.url_cwod_vampire_clan_giovanni));
            list.add(new Splat(R.string.lasombra, R.string.url_cwod_vampire_clan_lasombra));
            list.add(new Splat(R.string.malkavian, R.string.url_cwod_vampire_clan_malkavian));
            list.add(new Splat(R.string.nosferatu, R.string.url_cwod_vampire_clan_nosferatu));
            list.add(new Splat(R.string.ravnos, R.string.url_cwod_vampire_clan_ravnos));
            list.add(new Splat(R.string.toreador, R.string.url_cwod_vampire_clan_toreador));
            list.add(new Splat(R.string.tremere, R.string.url_cwod_vampire_clan_tremere));
            list.add(new Splat(R.string.tzimisce, R.string.url_cwod_vampire_clan_tzimisce));
            list.add(new Splat(R.string.ventrue, R.string.url_cwod_vampire_clan_ventrue));
            list.add(new Splat(R.string.caitiff, R.string.url_cwod_vampire_clan_caitiff));
            list.add(new Splat(R.string.bloodlines, false));
        }
        return list;
    }

    @Nullable @Override public List<Splat> getListRight(Splat splat) {
        List<Splat> list = new ArrayList<>();
        list.add(new Splat(R.string.camarilla, R.string.url_cwod_vampire_sect_camarilla));
        list.add(new Splat(R.string.anarchs, R.string.url_cwod_vampire_sect_anarchs));
        list.add(new Splat(R.string.sabbat, R.string.url_cwod_vampire_sect_sabbat));
        list.add(new Splat(R.string.independent, R.string.url_cwod_vampire_sect_independent));
        return list;
    }

    @Override public Splat updateLeft(Splat left, Splat right) {
        switch (left.getTitle()){
            case R.string.assamite:
                return right.getTitle() == R.string.sabbat? new Splat(R.string.assamite_antitribu, R.string.url_cwod_vampire_antitribu_assamite) : left;
            case R.string.brujah:
                return right.getTitle() == R.string.sabbat? new Splat(R.string.brujah_antitribu, R.string.url_cwod_vampire_antitribu_brujah) : left;
            case R.string.gangrel:
                return right.getTitle() == R.string.sabbat? new Splat(R.string.gangrel_antitribu, R.string.url_cwod_vampire_antitribu_gangrel) : left;
            case R.string.lasombra:
                return right.getTitle() == R.string.camarilla? new Splat(R.string.lasombra_antitribu, R.string.url_cwod_vampire_antitribu_lasombra) : left;
            case R.string.malkavian:
                return right.getTitle() == R.string.sabbat? new Splat(R.string.malkavian_antitribu, R.string.url_cwod_vampire_antitribu_malkavian) : left;
            case R.string.nosferatu:
                return right.getTitle() == R.string.sabbat? new Splat(R.string.nosferatu_antitribu, R.string.url_cwod_vampire_antitribu_nosferatu) : left;
            case R.string.ravnos:
                return right.getTitle() != R.string.independent? new Splat(R.string.ravnos_antitribu, R.string.url_cwod_vampire_antitribu_ravnos) : left;
            case R.string.followers_of_set:
                return right.getTitle() != R.string.independent? new Splat(R.string.serpents_of_the_light, R.string.url_cwod_vampire_antitribu_serpents_of_the_light) : left;
            case R.string.toreador:
                return right.getTitle() == R.string.sabbat? new Splat(R.string.toreador_antitribu, R.string.url_cwod_vampire_antitribu_toreador) : left;
            case R.string.tremere:
                return right.getTitle() == R.string.sabbat? new Splat(R.string.tremere_antitribu, R.string.url_cwod_vampire_antitribu_tremere) : left;
            case R.string.ventrue:
                return right.getTitle() == R.string.sabbat? new Splat(R.string.ventrue_antitribu, R.string.url_cwod_vampire_antitribu_ventrue) : left;
            case R.string.salubri:
                return right.getTitle() == R.string.sabbat? new Splat(R.string.salubri_antitribu, R.string.url_cwod_vampire_antitribu_salubri) : left;
        }
        return left;
    }
}
