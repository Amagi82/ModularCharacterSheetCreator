package amagi82.modularcharactersheetcreator.models.templates;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.modules.Module;

public class CMageTemplate extends Template{

    public CMageTemplate(Resources res) {
        super(res);
    }

    public List<Module> create(){
        List<Module> modules = new ArrayList<>();
        modules.add(header(R.string.attributes));
        modules.add(statBlock(R.string.physical, R.array.CWod_Physical, 1, 5));
        modules.add(statBlock(R.string.social, R.array.CWod_Social, 1, 5));
        modules.add(statBlock(R.string.mental, R.array.CWod_Mental, 1, 5));

        modules.add(header(R.string.abilities));
        modules.add(statBlock(R.string.talents, R.array.CMage_Talents, 0, 5));
        modules.add(statBlock(R.string.skills, R.array.CMage_Skills, 0, 5));
        modules.add(statBlock(R.string.knowledges, R.array.CMage_Knowledges, 0, 5));

        modules.add(header(R.string.spheres));
        modules.add(statBlock(0, R.array.CMage_Spheres_Left, 0, 5));
        modules.add(statBlock(0, R.array.CMage_Spheres_Center, 0, 5));
        modules.add(statBlock(0, R.array.CMage_Spheres_Right, 0, 5));

        modules.add(header(R.string.advantages));
        modules.add(statBlock(R.string.backgrounds, 0, 0, 5));
        modules.add(stat(R.string.arete, 0, 1, 10));
        modules.add(health());
        modules.add(stat(R.string.willpower, 0, 1, 10));
        //TODO: add other traits, quintessence/paradox, and xp counter

        return modules;
    }
}
