package amagi82.modularcharactersheetcreator.models.templates;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.characters.Sheet;
import amagi82.modularcharactersheetcreator.models.modules.Module;

public class CWerewolfTemplate extends Template {

    public CWerewolfTemplate(Resources res) {
        super(res);
    }

    @Override Sheet getDefaultSheet(GameCharacter character) {
        List<Module> modules = new ArrayList<>();
        modules.add(header(R.string.attributes));
        modules.add(statBlock(R.string.physical, R.array.CWod_Physical, 1, 5));
        modules.add(statBlock(R.string.social, R.array.CWod_Social, 1, 5));
        modules.add(statBlock(R.string.mental, R.array.CWod_Mental, 1, 5));

        modules.add(header(R.string.abilities));
        modules.add(statBlock(R.string.talents, R.array.CWerewolf_Talents, 0, 5));
        modules.add(statBlock(R.string.skills, R.array.CWerewolf_Skills, 0, 5));
        modules.add(statBlock(R.string.knowledges, R.array.CWerewolf_Knowledges, 0, 5));

        modules.add(header(R.string.advantages));
        modules.add(statBlock(R.string.backgrounds, 0, 0, 5));


        return sheet(modules);
    }
}
