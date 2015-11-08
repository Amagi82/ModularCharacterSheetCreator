package amagi82.modularcharactersheetcreator.models.games.templates;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.modules.Module;

public class ExaltedTemplate extends Template {

    ExaltedTemplate(Resources res) {
        super(res);
    }

    @Override Sheet createSheet(GameCharacter character) {
        List<Module> modules = new ArrayList<>();
        modules.add(header(R.string.attributes));
        modules.add(statBlock(0, R.array.CWod_Physical, 1, 5));
        modules.add(statBlock(0, R.array.CWod_Social, 1, 5));
        modules.add(statBlock(0, R.array.CWod_Mental, 1, 5));

        modules.add(header(R.string.abilities));
        modules.add(statBlock(R.string.dawn, R.array.Exalted_Dawn, 0, 5));
        modules.add(statBlock(R.string.zenith, R.array.Exalted_Zenith, 0, 5));
        modules.add(statBlock(R.string.twilight, R.array.Exalted_Twilight, 0, 5));
        modules.add(statBlock(R.string.night, R.array.Exalted_Night, 0, 5));
        modules.add(statBlock(R.string.eclipse, R.array.Exalted_Eclipse, 0, 5));
        modules.add(statBlock(R.string.specialties, 0, 0, 5));

        modules.add(header(R.string.advantages));
        modules.add(statBlock(R.string.backgrounds, 0, 0, 5));

        modules.add(stat(R.string.willpower, 0, 0, 10));
        //TODO: add Charms, weapons, anima sections, xp counter and check on others. Also consider 3rd edition

        return sheet(modules);
    }
}
