package amagi82.modularcharactersheetcreator.models.games.templates;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.modules.Module;

public class CWraithTemplate extends Template {

    CWraithTemplate(Resources res) {
        super(res);
    }

    @Override Sheet createSheet(GameCharacter character) {
        List<Module> modules = new ArrayList<>();
        modules.add(header(R.string.attributes));
        modules.add(statBlock(R.string.physical, R.array.CWod_Physical, 1, 5));
        modules.add(statBlock(R.string.social, R.array.CWod_Social, 1, 5));
        modules.add(statBlock(R.string.mental, R.array.CWod_Mental, 1, 5));

        modules.add(header(R.string.abilities));
        modules.add(statBlock(R.string.talents, R.array.CWraith_Talents, 0, 5));
        modules.add(statBlock(R.string.skills, R.array.CWerewolf_Skills, 0, 5));
        modules.add(statBlock(R.string.knowledges, R.array.CWraith_Knowledges, 0, 5));

        modules.add(header(R.string.advantages));
        modules.add(statBlock(R.string.backgrounds, 0, 0, 5));
        modules.add(statBlock(R.string.arcanoi, 0, 0, 5));
        modules.add(statBlock(R.string.passions, 0, 0, 5));

        modules.add(header(0));
        modules.add(statBlock(R.string.fetters, 0, 0, 5));
        modules.add(stat(R.string.corpus, 0, 0, 10));
        modules.add(stat(R.string.angst, 0, 0, 10));
        modules.add(stat(R.string.willpower, 0, 0, 10));
        modules.add(stat(R.string.pathos, 0, 0, 10));
        //TODO: add xp counter and cross check with V20 edition standards

        return sheet(modules);
    }
}
