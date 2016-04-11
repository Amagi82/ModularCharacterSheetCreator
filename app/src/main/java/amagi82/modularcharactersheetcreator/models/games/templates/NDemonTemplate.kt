package amagi82.modularcharactersheetcreator.models.games.templates

import android.content.res.Resources

import java.util.ArrayList

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.GameCharacter
import amagi82.modularcharactersheetcreator.models.Sheet
import amagi82.modularcharactersheetcreator.models.modules.Module

class NDemonTemplate internal constructor(res: Resources) : Template(res) {

    internal override fun createSheet(character: GameCharacter): Sheet {
        val modules = ArrayList<Module>()
        modules.add(header(R.string.attributes))
        modules.add(statBlock(0, R.array.NWod_Physical, 1, 5))
        modules.add(statBlock(0, R.array.NWod_Social, 1, 5))
        modules.add(statBlock(0, R.array.NWod_Mental, 1, 5))

        modules.add(header(R.string.skills, Module.ONE))
        modules.add(header(R.string.other_merits, Module.TWO))

        modules.add(statBlock(R.string.mental, R.string.unskilled_neg_3, R.array.CMage_Talents, 0, 5))
        modules.add(statBlock(R.string.physical, R.string.unskilled_neg_1, R.array.CMage_Skills, 0, 5))
        modules.add(statBlock(R.string.social, R.string.unskilled_neg_1, R.array.CMage_Knowledges, 0, 5))

        modules.add(statBlock(R.string.merits, 0, 0, 5))
        modules.add(health()) //TODO: Add NWod-specific health module
        modules.add(stat(R.string.willpower, 0, 0, 10))
        modules.add(stat(R.string.cover, 0, 0, 10))
        modules.add(stat(R.string.primum, 0, 0, 10))
        modules.add(stat(R.string.aether, 0, 0, 10)) //Two rows? 20 total?

        //TODO: Organize this sheet and add the remaining modules

        return sheet(modules)
    }
}
