package amagi82.modularcharactersheetcreator.models.games.templates;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.modules.Module;

public class CWerewolfTemplate extends Template {

    CWerewolfTemplate(Resources res) {
        super(res);
    }

    @Override Sheet createSheet(GameCharacter character) {
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
        modules.add(text(R.string.gifts));
        modules.add(text(R.string.gifts));

        modules.add(header(0));
        modules.add(stat(R.string.glory, 0, 0, 10));
        modules.add(stat(R.string.rage, 0, 0, 10));
        modules.add(health());
        modules.add(stat(R.string.honor, 0, 0, 10));
        modules.add(stat(R.string.gnosis, 0, 0, 10));
        modules.add(stat(R.string.wisdom, 0, 0, 10));
        modules.add(stat(R.string.willpower, 0, 0, 10));
        //TODO: add xp counter and everything from pg 2 of the werewolf sheet

        return sheet(modules);
    }
}
