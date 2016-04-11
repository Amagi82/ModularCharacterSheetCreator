package amagi82.modularcharactersheetcreator.models.games.templates

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.GameCharacter
import amagi82.modularcharactersheetcreator.models.Sheet
import amagi82.modularcharactersheetcreator.models.games.Trinity
import amagi82.modularcharactersheetcreator.models.modules.Module
import android.content.res.Resources
import java.util.*

class TrinityTemplate internal constructor(res: Resources) : Template(res) {

    @SuppressWarnings("ConstantConditions")
    override fun createSheet(character: GameCharacter): Sheet? {
        if (character.leftId == 0) return null
        return when (character.leftId) {
            Trinity.ADVENTURE -> createAdventure()
            Trinity.ABERRANT -> createAberrant()
            Trinity.AEON -> createAeon()
            else -> null
        }
    }

    private fun createAdventure(modules: MutableList<Module>? = null): Sheet {
        val list = modules ?: ArrayList<Module>()
        list.add(header(R.string.attributes_and_abilities))
        list.add(header(R.string.physical, Module.ONE))
        list.add(header(R.string.mental, Module.ONE))
        list.add(header(R.string.social, Module.ONE))

        list.add(statBlock(0, R.array.Trinity_Adventure_Strength, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Adventure_Perception, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Adventure_Appearance, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Adventure_Dexterity, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Adventure_Intelligence, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Adventure_Manipulation, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Adventure_Stamina, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Adventure_Wits, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Adventure_Charisma, 0, 5))

        list.add(header(R.string.advantages))
        list.add(text(R.string.knacks))
        list.add(statBlock(R.string.backgrounds, 0, 0, 5))
        list.add(health()) //TODO: Adjust penalties for other systems
        list.add(stat(R.string.willpower, 0, 0, 10))
        list.add(stat(R.string.inspiration, 0, 0, 10))
        //TODO: add facets, soak, xp counter, and movement

        return sheet(list)
    }

    private fun createAberrant(modules: MutableList<Module>? = null): Sheet {
        val list = modules ?: ArrayList<Module>()
        list.add(header(R.string.attributes_and_abilities))
        list.add(header(R.string.physical, Module.ONE))
        list.add(header(R.string.mental, Module.ONE))
        list.add(header(R.string.social, Module.ONE))

        list.add(statBlock(0, R.array.Trinity_Aberrant_Strength, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Aberrant_Perception, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Aberrant_Appearance, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Aberrant_Dexterity, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Aberrant_Intelligence, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Aberrant_Manipulation, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Aberrant_Stamina, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Aberrant_Wits, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Aberrant_Charisma, 0, 5))

        list.add(header(0))
        list.add(statBlock(R.string.backgrounds, 0, 0, 5))
        list.add(stat(R.string.willpower, 0, 0, 10))
        list.add(stat(R.string.taint, 0, 0, 10))
        list.add(stat(R.string.quantum, 0, 0, 10))
        //TODO: Add quantum powers, health module, and quantum pool

        return sheet(list)
    }

    private fun createAeon(modules: MutableList<Module>? = null): Sheet {
        val list = modules ?: ArrayList<Module>()
        list.add(header(R.string.attributes_and_abilities))
        list.add(header(R.string.physical, Module.ONE))
        list.add(header(R.string.mental, Module.ONE))
        list.add(header(R.string.social, Module.ONE))

        list.add(statBlock(0, R.array.Trinity_Strength, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Perception, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Appearance, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Dexterity, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Intelligence, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Manipulation, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Stamina, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Wits, 0, 5))
        list.add(statBlock(0, R.array.Trinity_Charisma, 0, 5))

        list.add(header(0))
        list.add(statBlock(R.string.backgrounds, 0, 0, 5))
        list.add(stat(R.string.willpower, 0, 0, 10))
        list.add(stat(R.string.psi, 0, 0, 10))
        list.add(health())
        //TODO: Organize and add combat, possessions, initiative, xp counter, etc

        return sheet(list)
    }
}
