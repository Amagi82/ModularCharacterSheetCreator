package amagi82.modularcharactersheetcreator.models.games.templates

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.GameCharacter
import amagi82.modularcharactersheetcreator.models.Sheet
import amagi82.modularcharactersheetcreator.models.games.CVampire
import amagi82.modularcharactersheetcreator.models.modules.Module
import android.content.res.Resources
import java.util.*

class CVampireTemplate internal constructor(res: Resources) : Template(res) {

    internal override fun createSheet(character: GameCharacter): Sheet? {
        if (character.leftId == 0) return null
        val modules = ArrayList<Module>()
        modules.add(header(R.string.attributes))
        modules.add(statBlock(R.string.physical, R.array.CWod_Physical, 1, 5))
        modules.add(statBlock(R.string.social, R.array.CWod_Social, 1, 5))
        modules.add(statBlock(R.string.mental, R.array.CWod_Mental, 1, 5))

        modules.add(header(R.string.abilities))
        modules.add(statBlock(R.string.talents, R.array.CVampire_Talents, 0, 5))
        modules.add(statBlock(R.string.skills, R.array.CVampire_Skills, 0, 5))
        modules.add(statBlock(R.string.knowledges, R.array.CVampire_Knowledges, 0, 5))

        modules.add(header(R.string.advantages))
        modules.add(statBlock(R.string.disciplines, (getSystem(character) as CVampire).getDisciplines(character.leftId), 0, 5))
        modules.add(statBlock(R.string.backgrounds, 0, 0, 5))
        modules.add(statBlock(R.string.virtues, R.array.CVampire_Virtues, 1, 5))

        //TODO: Organize these, add merits and flaws?, add clan weakness, add xp counter
        modules.add(header(0))
        modules.add(stat(R.string.humanity, 0, 5, 10))
        modules.add(stat(R.string.willpower, 0, 1, 10))
        modules.add(bloodPool())
        modules.add(health())

        return sheet(modules)
    }


}
