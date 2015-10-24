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
        //modules.add(statBlock(R.string.disciplines), )

        return modules;
    }




    @ArrayRes private int getDisciplines(int clanTitleId){
        switch (clanTitleId){
            case R.string.ahrimanes:
                return R.array.CVampire_Disc_Ahrimanes;
            case R.string.anda:
                return R.array.CVampire_Disc_Anda;
            case R.string.assamite:
            case R.string.baali:
            case R.string.blood_brothers:
            case R.string.brujah:
            case R.string.cappadocians:
            case R.string.children_of_osiris:
            case R.string.daughers_of_cacophony:
            case R.string.followers_of_set:
            case R.string.gangrel:
            case R.string.gargoyle:
            case R.string.giovanni:
            case R.string.harbingers_of_skulls:
            case R.string.kiasyd:
            case R.string.lamia:
            case R.string.lasombra:
            case R.string.lhiannan:
            case R.string.malkavian:
            case R.string.nagaraja:
            case R.string.noiad:
            case R.string.nosferatu:
            case R.string.panders:
            case R.string.ravnos:
            case R.string.salubri:
            case R.string.samedi:
            case R.string.toreador:
            case R.string.tremere:
            case R.string.true_brujah:
            case R.string.tzimisce:
            case R.string.ventrue:
            case R.string.caitiff:


            default:
                return 0;
        }
    }

}
