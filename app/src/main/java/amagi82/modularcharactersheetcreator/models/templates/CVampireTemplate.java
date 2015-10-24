package amagi82.modularcharactersheetcreator.models.templates;

import android.content.res.Resources;
import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.modules.Module;

public class CVampireTemplate extends Template{

    public CVampireTemplate(Resources res) {
        super(res);
    }

    public List<Module> create(@StringRes int clanTitleId){
        List<Module> modules = new ArrayList<>();
        modules.add(header(R.string.attributes));
        modules.add(statBlock(R.string.physical, R.array.CWod_Physical, 1, 5));
        modules.add(statBlock(R.string.social, R.array.CWod_Social, 1, 5));
        modules.add(statBlock(R.string.mental, R.array.CWod_Mental, 1, 5));

        modules.add(header(R.string.abilities));
        modules.add(statBlock(R.string.talents, R.array.CVampire_Talents, 0, 5));
        modules.add(statBlock(R.string.skills, R.array.CVampire_Skills, 0, 5));
        modules.add(statBlock(R.string.knowledges, R.array.CVampire_Knowledges, 0, 5));

        modules.add(header(R.string.advantages));
        modules.add(statBlock(R.string.disciplines, getDisciplines(clanTitleId), 0, 5));
        modules.add(statBlock(R.string.backgrounds, 0, 0, 5));
        modules.add(statBlock(R.string.virtues, R.array.CVampire_Virtues, 1, 5));

        modules.add(header(0));


        return modules;
    }




    @ArrayRes private int getDisciplines(int clanTitleId){
        switch (clanTitleId){
            case R.string.ahrimanes:
                return R.array.CVampire_Disc_Ahrimanes;
            case R.string.anda:
                return R.array.CVampire_Disc_Anda;
            case R.string.assamite:
            case R.string.assamite_antitribu:
                return R.array.CVampire_Disc_Assamite;
            case R.string.baali:
                return R.array.CVampire_Disc_Baali;
            case R.string.blood_brothers:
                return R.array.CVampire_Disc_BloodBrothers;
            case R.string.brujah:
            case R.string.brujah_antitribu:
                return R.array.CVampire_Disc_Brujah;
            case R.string.cappadocians:
                return R.array.CVampire_Disc_Cappadocians;
            case R.string.daughers_of_cacophony:
                return R.array.CVampire_Disc_DaughtersOfCacophony;
            case R.string.followers_of_set:
            case R.string.serpents_of_the_light:
                return R.array.CVampire_Disc_FollowersOfSet;
            case R.string.gangrel:
            case R.string.gangrel_antitribu:
                return R.array.CVampire_Disc_Gangrel;
            case R.string.gargoyle:
                return R.array.CVampire_Disc_Gargoyles;
            case R.string.giovanni:
                return R.array.CVampire_Disc_Giovanni;
            case R.string.harbingers_of_skulls:
                return R.array.CVampire_Disc_HarbingersOfSkulls;
            case R.string.kiasyd:
                return R.array.CVampire_Disc_Kiasyd;
            case R.string.lamia:
                return R.array.CVampire_Disc_Lamia;
            case R.string.lasombra:
            case R.string.lasombra_antitribu:
                return R.array.CVampire_Disc_Lasombra;
            case R.string.lhiannan:
                return R.array.CVampire_Disc_Lhiannan;
            case R.string.malkavian:
            case R.string.malkavian_antitribu:
                return R.array.CVampire_Disc_Malkavian;
            case R.string.nagaraja:
                return R.array.CVampire_Disc_Nagaraja;
            case R.string.noiad:
                return R.array.CVampire_Disc_Noiad;
            case R.string.nosferatu:
            case R.string.nosferatu_antitribu:
                return R.array.CVampire_Disc_Nosferatu;
            case R.string.ravnos:
            case R.string.ravnos_antitribu:
                return R.array.CVampire_Disc_Ravnos;
            case R.string.salubri:
                return R.array.CVampire_Disc_Salubri;
            case R.string.salubri_antitribu:
                return R.array.CVampire_Disc_Valaren;
            case R.string.samedi:
                return R.array.CVampire_Disc_Samedi;
            case R.string.toreador:
            case R.string.toreador_antitribu:
                return R.array.CVampire_Disc_Toreador;
            case R.string.tremere:
            case R.string.tremere_antitribu:
                return R.array.CVampire_Disc_Tremere;
            case R.string.true_brujah:
                return R.array.CVampire_Disc_TrueBrujah;
            case R.string.tzimisce:
                return R.array.CVampire_Disc_Tzimisce;
            case R.string.ventrue:
            case R.string.ventrue_antitribu:
                return R.array.CVampire_Disc_Ventrue;
            default:
                return 0;
        }
    }
}
