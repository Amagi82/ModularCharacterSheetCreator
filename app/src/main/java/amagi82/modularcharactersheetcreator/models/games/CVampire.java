package amagi82.modularcharactersheetcreator.models.games;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.Splat;

/*
    Classic World of Darkness
    Vampire: The Masquerade
    20th Anniversary Edition
 */
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
        this.isLeftListFinal = false;
    }

    @Override public List<Splat> getListLeft(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(18);
        if (splat != null && splat.title() == R.string.bloodlines) {
            list.add(Splat.create(R.string.ahrimanes, R.string.url_cwod_vampire_clan_ahrimanes));
            list.add(Splat.create(R.string.anda, R.string.url_cwod_vampire_clan_anda));
            list.add(Splat.create(R.string.baali, R.string.url_cwod_vampire_clan_baali));
            list.add(Splat.create(R.string.blood_brothers, R.string.url_cwod_vampire_clan_blood_brothers));
            list.add(Splat.create(R.string.cappadocians, R.string.url_cwod_vampire_clan_cappadocians));
            list.add(Splat.create(R.string.children_of_osiris, R.string.url_cwod_vampire_clan_children_of_osiris));
            list.add(Splat.create(R.string.daughers_of_cacophony, R.string.url_cwod_vampire_clan_daughers_of_cacophony));
            list.add(Splat.create(R.string.gargoyle, R.string.url_cwod_vampire_clan_gargoyle));
            list.add(Splat.create(R.string.harbingers_of_skulls, R.string.url_cwod_vampire_clan_harbingers_of_skulls));
            list.add(Splat.create(R.string.kiasyd, R.string.url_cwod_vampire_clan_kiasyd));
            list.add(Splat.create(R.string.lamia, R.string.url_cwod_vampire_clan_lamia));
            list.add(Splat.create(R.string.lhiannan, R.string.url_cwod_vampire_clan_lhiannan));
            list.add(Splat.create(R.string.nagaraja, R.string.url_cwod_vampire_clan_nagaraja));
            list.add(Splat.create(R.string.noiad, R.string.url_cwod_vampire_clan_noiad));
            list.add(Splat.create(R.string.panders, R.string.url_cwod_vampire_clan_panders));
            list.add(Splat.create(R.string.salubri, R.string.url_cwod_vampire_clan_salubri));
            list.add(Splat.create(R.string.samedi, R.string.url_cwod_vampire_clan_samedi));
            list.add(Splat.create(R.string.true_brujah, R.string.url_cwod_vampire_clan_true_brujah));
        } else {
            list.add(Splat.create(R.string.assamite, R.string.url_cwod_vampire_clan_assamite));
            list.add(Splat.create(R.string.brujah, R.string.url_cwod_vampire_clan_brujah));
            list.add(Splat.create(R.string.followers_of_set, R.string.url_cwod_vampire_clan_followers_of_set));
            list.add(Splat.create(R.string.gangrel, R.string.url_cwod_vampire_clan_gangrel));
            list.add(Splat.create(R.string.giovanni, R.string.url_cwod_vampire_clan_giovanni));
            list.add(Splat.create(R.string.lasombra, R.string.url_cwod_vampire_clan_lasombra));
            list.add(Splat.create(R.string.malkavian, R.string.url_cwod_vampire_clan_malkavian));
            list.add(Splat.create(R.string.nosferatu, R.string.url_cwod_vampire_clan_nosferatu));
            list.add(Splat.create(R.string.ravnos, R.string.url_cwod_vampire_clan_ravnos));
            list.add(Splat.create(R.string.toreador, R.string.url_cwod_vampire_clan_toreador));
            list.add(Splat.create(R.string.tremere, R.string.url_cwod_vampire_clan_tremere));
            list.add(Splat.create(R.string.tzimisce, R.string.url_cwod_vampire_clan_tzimisce));
            list.add(Splat.create(R.string.ventrue, R.string.url_cwod_vampire_clan_ventrue));
            list.add(Splat.create(R.string.caitiff, R.string.url_cwod_vampire_clan_caitiff));
            list.add(Splat.create(R.string.bloodlines, false));
        }
        return list;
    }

    @Override public List<Splat> getListRight(@Nullable Splat splat) {
        List<Splat> list = new ArrayList<>(4);
        list.add(Splat.create(R.string.camarilla, R.string.url_cwod_vampire_sect_camarilla));
        list.add(Splat.create(R.string.anarchs, R.string.url_cwod_vampire_sect_anarchs));
        list.add(Splat.create(R.string.sabbat, R.string.url_cwod_vampire_sect_sabbat));
        list.add(Splat.create(R.string.independent, R.string.url_cwod_vampire_sect_independent));
        return list;
    }

    @Override public Splat updateLeft(Splat left, Splat right) {
        switch (left.title()) {
            case R.string.assamite:
            case R.string.assamite_antitribu:
                return right.title() == R.string.sabbat ? Splat.create(R.string.assamite_antitribu, R.string.url_cwod_vampire_antitribu_assamite) :
                        Splat.create(R.string.assamite, R.string.url_cwod_vampire_clan_assamite);
            case R.string.brujah:
            case R.string.brujah_antitribu:
                return right.title() == R.string.sabbat ? Splat.create(R.string.brujah_antitribu, R.string.url_cwod_vampire_antitribu_brujah) :
                        Splat.create(R.string.brujah, R.string.url_cwod_vampire_clan_brujah);
            case R.string.gangrel:
            case R.string.gangrel_antitribu:
                return right.title() == R.string.sabbat ? Splat.create(R.string.gangrel_antitribu, R.string.url_cwod_vampire_antitribu_gangrel) :
                        Splat.create(R.string.gangrel, R.string.url_cwod_vampire_clan_gangrel);
            case R.string.lasombra:
            case R.string.lasombra_antitribu:
                return right.title() != R.string.sabbat ? Splat.create(R.string.lasombra_antitribu, R.string.url_cwod_vampire_antitribu_lasombra) :
                        Splat.create(R.string.lasombra, R.string.url_cwod_vampire_clan_lasombra);
            case R.string.malkavian:
            case R.string.malkavian_antitribu:
                return right.title() == R.string.sabbat ? Splat.create(R.string.malkavian_antitribu, R.string.url_cwod_vampire_antitribu_malkavian) :
                        Splat.create(R.string.malkavian, R.string.url_cwod_vampire_clan_malkavian);
            case R.string.nosferatu:
            case R.string.nosferatu_antitribu:
                return right.title() == R.string.sabbat ? Splat.create(R.string.nosferatu_antitribu, R.string.url_cwod_vampire_antitribu_nosferatu) :
                        Splat.create(R.string.nosferatu, R.string.url_cwod_vampire_clan_nosferatu);
            case R.string.ravnos:
            case R.string.ravnos_antitribu:
                return right.title() != R.string.independent ? Splat.create(R.string.ravnos_antitribu, R.string.url_cwod_vampire_antitribu_ravnos) :
                        Splat.create(R.string.ravnos, R.string.url_cwod_vampire_clan_ravnos);
            case R.string.followers_of_set:
            case R.string.serpents_of_the_light:
                return right.title() == R.string.camarilla ? Splat.create(R.string.serpents_of_the_light, R.string.url_cwod_vampire_antitribu_serpents_of_the_light) :
                        Splat.create(R.string.followers_of_set, R.string.url_cwod_vampire_clan_followers_of_set);
            case R.string.toreador:
            case R.string.toreador_antitribu:
                return right.title() == R.string.sabbat ? Splat.create(R.string.toreador_antitribu, R.string.url_cwod_vampire_antitribu_toreador) :
                        Splat.create(R.string.toreador, R.string.url_cwod_vampire_clan_toreador);
            case R.string.tremere:
            case R.string.tremere_antitribu:
                return right.title() == R.string.sabbat ? Splat.create(R.string.tremere_antitribu, R.string.url_cwod_vampire_antitribu_tremere) :
                        Splat.create(R.string.tremere, R.string.url_cwod_vampire_clan_tremere);
            case R.string.ventrue:
            case R.string.ventrue_antitribu:
                return right.title() == R.string.sabbat ? Splat.create(R.string.ventrue_antitribu, R.string.url_cwod_vampire_antitribu_ventrue) :
                        Splat.create(R.string.ventrue, R.string.url_cwod_vampire_clan_ventrue);
            case R.string.salubri:
            case R.string.salubri_antitribu:
                return right.title() == R.string.sabbat ? Splat.create(R.string.salubri_antitribu, R.string.url_cwod_vampire_antitribu_salubri) :
                        Splat.create(R.string.salubri, R.string.url_cwod_vampire_clan_salubri);
        }
        return left;
    }
}
