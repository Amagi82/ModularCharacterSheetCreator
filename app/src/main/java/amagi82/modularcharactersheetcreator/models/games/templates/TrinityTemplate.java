package amagi82.modularcharactersheetcreator.models.games.templates;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.games.Trinity;
import amagi82.modularcharactersheetcreator.models.modules.Module;

public class TrinityTemplate extends Template{

    TrinityTemplate(Resources res) {
        super(res);
    }

    @SuppressWarnings("ConstantConditions")
    @Override Sheet createSheet(GameCharacter character) {
        if (character.leftId() == 0) return null;
        List<Module> modules = new ArrayList<>();
        if(character.leftId() == Trinity.ADVENTURE) return createAdventure(modules);
        else if(character.leftId() == Trinity.ABERRANT) return createAberrant(modules);
        else if(character.leftId() == Trinity.AEON) return createAeon(modules);
        else return null;
    }

    private Sheet createAdventure(List<Module> modules){
        modules.add(header(R.string.attributes_and_abilities));
        modules.add(header(R.string.physical, Module.ONE));
        modules.add(header(R.string.mental, Module.ONE));
        modules.add(header(R.string.social, Module.ONE));

        modules.add(statBlock(0, R.array.Trinity_Adventure_Strength, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Adventure_Perception, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Adventure_Appearance, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Adventure_Dexterity, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Adventure_Intelligence, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Adventure_Manipulation, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Adventure_Stamina, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Adventure_Wits, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Adventure_Charisma, 0, 5));

        modules.add(header(R.string.advantages));
        modules.add(text(R.string.knacks));
        modules.add(statBlock(R.string.backgrounds, 0, 0, 5));
        modules.add(health()); //TODO: Adjust penalties for other systems
        modules.add(stat(R.string.willpower, 0, 0, 10));
        modules.add(stat(R.string.inspiration, 0, 0, 10));
        //TODO: add facets, soak, xp counter, and movement

        return sheet(modules);
    }

    private Sheet createAberrant(List<Module> modules){
        modules.add(header(R.string.attributes_and_abilities));
        modules.add(header(R.string.physical, Module.ONE));
        modules.add(header(R.string.mental, Module.ONE));
        modules.add(header(R.string.social, Module.ONE));

        modules.add(statBlock(0, R.array.Trinity_Aberrant_Strength, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Aberrant_Perception, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Aberrant_Appearance, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Aberrant_Dexterity, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Aberrant_Intelligence, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Aberrant_Manipulation, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Aberrant_Stamina, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Aberrant_Wits, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Aberrant_Charisma, 0, 5));

        modules.add(header(0));
        modules.add(statBlock(R.string.backgrounds, 0, 0, 5));
        modules.add(stat(R.string.willpower, 0, 0, 10));
        modules.add(stat(R.string.taint, 0, 0, 10));
        modules.add(stat(R.string.quantum, 0, 0, 10));
        //TODO: Add quantum powers, health module, and quantum pool

        return sheet(modules);
    }

    private Sheet createAeon(List<Module> modules){
        modules.add(header(R.string.attributes_and_abilities));
        modules.add(header(R.string.physical, Module.ONE));
        modules.add(header(R.string.mental, Module.ONE));
        modules.add(header(R.string.social, Module.ONE));

        modules.add(statBlock(0, R.array.Trinity_Strength, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Perception, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Appearance, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Dexterity, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Intelligence, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Manipulation, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Stamina, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Wits, 0, 5));
        modules.add(statBlock(0, R.array.Trinity_Charisma, 0, 5));

        modules.add(header(0));
        modules.add(statBlock(R.string.backgrounds, 0, 0, 5));
        modules.add(stat(R.string.willpower, 0, 0, 10));
        modules.add(stat(R.string.psi, 0, 0, 10));
        modules.add(health());
        //TODO: Organize and add combat, possessions, initiative, xp counter, etc

        return sheet(modules);
    }
}
