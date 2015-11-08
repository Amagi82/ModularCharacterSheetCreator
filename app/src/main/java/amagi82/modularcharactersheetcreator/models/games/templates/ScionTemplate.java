package amagi82.modularcharactersheetcreator.models.games.templates;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.modules.Module;

public class ScionTemplate extends Template {

    ScionTemplate(Resources res) {
        super(res);
    }

    @Override Sheet createSheet(GameCharacter character) {
        List<Module> modules = new ArrayList<>();
        modules.add(header(R.string.attributes));
        modules.add(statBlock(R.string.physical, R.array.CWod_Physical, 1, 10));
        modules.add(statBlock(R.string.social, R.array.CWod_Social, 1, 10));
        modules.add(statBlock(R.string.mental, R.array.CWod_Mental, 1, 10));

        modules.add(header(R.string.abilities));
        modules.add(statBlock(0, R.array.Scion_Abilities_Left, 0, 5));
        modules.add(statBlock(0, R.array.Scion_Abilities_Center, 0, 5));
        modules.add(statBlock(0, R.array.Scion_Abilities_Right, 0, 5));

        modules.add(statBlock(R.string.birthrights, 0, 0, 5));
        modules.add(text(R.string.boons));
        modules.add(stat(R.string.willpower, 0, 0, 10));
        modules.add(statBlock(R.string.virtues, 0, 0, 5));
        modules.add(text(R.string.knacks));
        modules.add(stat(R.string.legend, 0, 2, 12));

        //TODO: Add weapons, combat, soak, armor, movement, health, and xp counter

        return sheet(modules);
    }
}
