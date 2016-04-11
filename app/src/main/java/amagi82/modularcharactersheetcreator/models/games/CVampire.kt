package amagi82.modularcharactersheetcreator.models.games

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.models.Splat
import android.support.annotation.ArrayRes
import android.util.SparseArray

/*
    Classic World of Darkness
    Vampire: The Masquerade
    20th Anniversary Edition
 */
class CVampire : Game() {
    override val gameTitle = getString(R.string.cwod_vampire)
    override val leftTitle = getString(R.string.clan)
    override val rightTitle = getString(R.string.sect)
    override val gameUrl = getString(R.string.url_game_cwod_vampire)
    override val splashUrl = getString(R.string.url_splash_cwod_vampire)
    override val gameColor = R.color.cwod_vampire
    override val isArchetypeLeft = true
    override val checkLeft = true
    override val isLeftListFinal = false
    override val isRightListFinal = true
    override val splats: SparseArray<Splat>
        get() {
            val splats = SparseArray<Splat>(49)

            splats.put(ASSAMITE, splat(R.string.assamite, R.string.url_cwod_vampire_clan_assamite))
            splats.put(BRUJAH, splat(R.string.brujah, R.string.url_cwod_vampire_clan_brujah))
            splats.put(FOLLOWERS_OF_SET, splat(R.string.followers_of_set, R.string.url_cwod_vampire_clan_followers_of_set))
            splats.put(GANGREL, splat(R.string.gangrel, R.string.url_cwod_vampire_clan_gangrel))
            splats.put(GIOVANNI, splat(R.string.giovanni, R.string.url_cwod_vampire_clan_giovanni))
            splats.put(LASOMBRA, splat(R.string.lasombra, R.string.url_cwod_vampire_clan_lasombra))
            splats.put(MALKAVIAN, splat(R.string.malkavian, R.string.url_cwod_vampire_clan_malkavian))
            splats.put(NOSFERATU, splat(R.string.nosferatu, R.string.url_cwod_vampire_clan_nosferatu))
            splats.put(RAVNOS, splat(R.string.ravnos, R.string.url_cwod_vampire_clan_ravnos))
            splats.put(TOREADOR, splat(R.string.toreador, R.string.url_cwod_vampire_clan_toreador))
            splats.put(TREMERE, splat(R.string.tremere, R.string.url_cwod_vampire_clan_tremere))
            splats.put(TZIMISCE, splat(R.string.tzimisce, R.string.url_cwod_vampire_clan_tzimisce))
            splats.put(VENTRUE, splat(R.string.ventrue, R.string.url_cwod_vampire_clan_ventrue))
            splats.put(CAITIFF, splat(R.string.caitiff, R.string.url_cwod_vampire_clan_caitiff))
            splats.put(BLOODLINES, splat(R.string.bloodlines, false))

            splats.put(AHRIMANES, splat(R.string.ahrimanes, R.string.url_cwod_vampire_clan_ahrimanes))
            splats.put(ANDA, splat(R.string.anda, R.string.url_cwod_vampire_clan_anda))
            splats.put(BAALI, splat(R.string.baali, R.string.url_cwod_vampire_clan_baali))
            splats.put(BLOOD_BROTHERS, splat(R.string.blood_brothers, R.string.url_cwod_vampire_clan_blood_brothers))
            splats.put(CAPPADOCIANS, splat(R.string.cappadocians, R.string.url_cwod_vampire_clan_cappadocians))
            splats.put(CHILDREN_OF_OSIRIS, splat(R.string.children_of_osiris, R.string.url_cwod_vampire_clan_children_of_osiris))
            splats.put(DAUGHERS_OF_CACOPHONY, splat(R.string.daughers_of_cacophony, R.string.url_cwod_vampire_clan_daughers_of_cacophony))
            splats.put(GARGOYLE, splat(R.string.gargoyle, R.string.url_cwod_vampire_clan_gargoyle))
            splats.put(HARBINGERS_OF_SKULLS, splat(R.string.harbingers_of_skulls, R.string.url_cwod_vampire_clan_harbingers_of_skulls))
            splats.put(KIASYD, splat(R.string.kiasyd, R.string.url_cwod_vampire_clan_kiasyd))
            splats.put(LAMIA, splat(R.string.lamia, R.string.url_cwod_vampire_clan_lamia))
            splats.put(LHIANNAN, splat(R.string.lhiannan, R.string.url_cwod_vampire_clan_lhiannan))
            splats.put(NAGARAJA, splat(R.string.nagaraja, R.string.url_cwod_vampire_clan_nagaraja))
            splats.put(NOIAD, splat(R.string.noiad, R.string.url_cwod_vampire_clan_noiad))
            splats.put(PANDERS, splat(R.string.panders, R.string.url_cwod_vampire_clan_panders))
            splats.put(SALUBRI, splat(R.string.salubri, R.string.url_cwod_vampire_clan_salubri))
            splats.put(SAMEDI, splat(R.string.samedi, R.string.url_cwod_vampire_clan_samedi))
            splats.put(TRUE_BRUJAH, splat(R.string.true_brujah, R.string.url_cwod_vampire_clan_true_brujah))

            splats.put(ASSAMITE_ANTITRUBU, splat(R.string.assamite_antitribu, R.string.url_cwod_vampire_antitribu_assamite))
            splats.put(BRUJAH_ANTITRUBU, splat(R.string.brujah_antitribu, R.string.url_cwod_vampire_antitribu_brujah))
            splats.put(SERPENTS_OF_THE_LIGHT, splat(R.string.serpents_of_the_light, R.string.url_cwod_vampire_antitribu_serpents_of_the_light))
            splats.put(GANGREL_ANTITRUBU, splat(R.string.gangrel_antitribu, R.string.url_cwod_vampire_antitribu_gangrel))
            splats.put(LASOMBRA_ANTITRUBU, splat(R.string.lasombra_antitribu, R.string.url_cwod_vampire_antitribu_lasombra))
            splats.put(MALKAVIAN_ANTITRUBU, splat(R.string.malkavian_antitribu, R.string.url_cwod_vampire_antitribu_malkavian))
            splats.put(NOSFERATU_ANTITRUBU, splat(R.string.nosferatu_antitribu, R.string.url_cwod_vampire_antitribu_nosferatu))
            splats.put(RAVNOS_ANTITRUBU, splat(R.string.ravnos_antitribu, R.string.url_cwod_vampire_antitribu_ravnos))
            splats.put(TOREADOR_ANTITRUBU, splat(R.string.toreador_antitribu, R.string.url_cwod_vampire_antitribu_toreador))
            splats.put(TREMERE_ANTITRUBU, splat(R.string.tremere_antitribu, R.string.url_cwod_vampire_antitribu_tremere))
            splats.put(VENTRUE_ANTITRUBU, splat(R.string.ventrue_antitribu, R.string.url_cwod_vampire_antitribu_ventrue))
            splats.put(SALUBRI_ANTITRIBU, splat(R.string.salubri_antitribu, R.string.url_cwod_vampire_antitribu_salubri))

            splats.put(CAMARILLA, splat(R.string.camarilla, R.string.url_cwod_vampire_sect_camarilla))
            splats.put(ANARCHS, splat(R.string.anarchs, R.string.url_cwod_vampire_sect_anarchs))
            splats.put(SABBAT, splat(R.string.sabbat, R.string.url_cwod_vampire_sect_sabbat))
            splats.put(INDEPENDENT, splat(R.string.independent, R.string.url_cwod_vampire_sect_independent))

            return splats
        }

    override fun getListLeft(splatId: Int) = if (splatId == BLOODLINES) intArrayOf(ANDA, BAALI, BLOOD_BROTHERS, CAPPADOCIANS, CHILDREN_OF_OSIRIS, DAUGHERS_OF_CACOPHONY, GARGOYLE, HARBINGERS_OF_SKULLS, KIASYD, LAMIA, LHIANNAN, NAGARAJA, NOIAD, PANDERS, SALUBRI, SAMEDI, TRUE_BRUJAH)
    else intArrayOf(ASSAMITE, BRUJAH, FOLLOWERS_OF_SET, GANGREL, GIOVANNI, LASOMBRA, MALKAVIAN, NOSFERATU, RAVNOS, TOREADOR, TREMERE, TZIMISCE, VENTRUE, CAITIFF, BLOODLINES)


    override fun getListRight(splatId: Int) = intArrayOf(CAMARILLA, ANARCHS, SABBAT, INDEPENDENT)

    override fun updateLeft(leftSplatId: Int, rightSplatId: Int) = when {
        leftSplatId == ASSAMITE || leftSplatId == ASSAMITE_ANTITRUBU -> if (rightSplatId == SABBAT) ASSAMITE_ANTITRUBU else ASSAMITE
        leftSplatId == BRUJAH || leftSplatId == BRUJAH_ANTITRUBU -> if (rightSplatId == SABBAT) BRUJAH_ANTITRUBU else BRUJAH
        leftSplatId == GANGREL || leftSplatId == GANGREL_ANTITRUBU -> if (rightSplatId == SABBAT) GANGREL_ANTITRUBU else GANGREL
        leftSplatId == LASOMBRA || leftSplatId == LASOMBRA_ANTITRUBU -> if (rightSplatId != SABBAT) LASOMBRA_ANTITRUBU else LASOMBRA
        leftSplatId == MALKAVIAN || leftSplatId == MALKAVIAN_ANTITRUBU -> if (rightSplatId == SABBAT) MALKAVIAN_ANTITRUBU else MALKAVIAN
        leftSplatId == NOSFERATU || leftSplatId == NOSFERATU_ANTITRUBU -> if (rightSplatId == SABBAT) NOSFERATU_ANTITRUBU else NOSFERATU
        leftSplatId == RAVNOS || leftSplatId == RAVNOS_ANTITRUBU -> if (rightSplatId != INDEPENDENT) RAVNOS_ANTITRUBU else RAVNOS
        leftSplatId == FOLLOWERS_OF_SET || leftSplatId == SERPENTS_OF_THE_LIGHT -> if (rightSplatId == CAMARILLA) SERPENTS_OF_THE_LIGHT else FOLLOWERS_OF_SET
        leftSplatId == TOREADOR || leftSplatId == TOREADOR_ANTITRUBU -> if (rightSplatId == SABBAT) TOREADOR_ANTITRUBU else TOREADOR
        leftSplatId == TREMERE || leftSplatId == TREMERE_ANTITRUBU -> if (rightSplatId == SABBAT) TREMERE_ANTITRUBU else TREMERE
        leftSplatId == VENTRUE || leftSplatId == VENTRUE_ANTITRUBU -> if (rightSplatId == SABBAT) VENTRUE_ANTITRUBU else VENTRUE
        leftSplatId == SALUBRI || leftSplatId == SALUBRI_ANTITRIBU -> if (rightSplatId == SABBAT) SALUBRI_ANTITRIBU else SALUBRI
        else -> leftSplatId
    }

    @ArrayRes fun getDisciplines(clanId: Int) = when (clanId) {
        AHRIMANES -> R.array.CVampire_Disc_Ahrimanes
        ANDA -> R.array.CVampire_Disc_Anda
        ASSAMITE, ASSAMITE_ANTITRUBU -> R.array.CVampire_Disc_Assamite
        BAALI -> R.array.CVampire_Disc_Baali
        BLOOD_BROTHERS -> R.array.CVampire_Disc_BloodBrothers
        BRUJAH, BRUJAH_ANTITRUBU -> R.array.CVampire_Disc_Brujah
        CAPPADOCIANS -> R.array.CVampire_Disc_Cappadocians
        DAUGHERS_OF_CACOPHONY -> R.array.CVampire_Disc_DaughtersOfCacophony
        FOLLOWERS_OF_SET, SERPENTS_OF_THE_LIGHT -> R.array.CVampire_Disc_FollowersOfSet
        GANGREL, GANGREL_ANTITRUBU -> R.array.CVampire_Disc_Gangrel
        GARGOYLE -> R.array.CVampire_Disc_Gargoyles
        GIOVANNI -> R.array.CVampire_Disc_Giovanni
        HARBINGERS_OF_SKULLS -> R.array.CVampire_Disc_HarbingersOfSkulls
        KIASYD -> R.array.CVampire_Disc_Kiasyd
        LAMIA -> R.array.CVampire_Disc_Lamia
        LASOMBRA, LASOMBRA_ANTITRUBU -> R.array.CVampire_Disc_Lasombra
        LHIANNAN -> R.array.CVampire_Disc_Lhiannan
        MALKAVIAN, MALKAVIAN_ANTITRUBU -> R.array.CVampire_Disc_Malkavian
        NAGARAJA -> R.array.CVampire_Disc_Nagaraja
        NOIAD -> R.array.CVampire_Disc_Noiad
        NOSFERATU, NOSFERATU_ANTITRUBU -> R.array.CVampire_Disc_Nosferatu
        RAVNOS, RAVNOS_ANTITRUBU -> R.array.CVampire_Disc_Ravnos
        SALUBRI -> R.array.CVampire_Disc_Salubri
        SALUBRI_ANTITRIBU -> R.array.CVampire_Disc_Valaren
        SAMEDI -> R.array.CVampire_Disc_Samedi
        TOREADOR, TOREADOR_ANTITRUBU -> R.array.CVampire_Disc_Toreador
        TREMERE, TREMERE_ANTITRUBU -> R.array.CVampire_Disc_Tremere
        TRUE_BRUJAH -> R.array.CVampire_Disc_TrueBrujah
        TZIMISCE -> R.array.CVampire_Disc_Tzimisce
        VENTRUE, VENTRUE_ANTITRUBU -> R.array.CVampire_Disc_Ventrue
        else -> 0

    }

    companion object {

        //Left
        private val ASSAMITE = 101
        private val BRUJAH = 102
        private val FOLLOWERS_OF_SET = 103
        private val GANGREL = 104
        private val GIOVANNI = 105
        private val LASOMBRA = 106
        private val MALKAVIAN = 107
        private val NOSFERATU = 108
        private val RAVNOS = 109
        private val TOREADOR = 120
        private val TREMERE = 121
        private val TZIMISCE = 122
        private val VENTRUE = 123
        private val CAITIFF = 124
        private val BLOODLINES = 125

        private val AHRIMANES = 201
        private val ANDA = 202
        private val BAALI = 203
        private val BLOOD_BROTHERS = 204
        private val CAPPADOCIANS = 205
        private val CHILDREN_OF_OSIRIS = 206
        private val DAUGHERS_OF_CACOPHONY = 207
        private val GARGOYLE = 208
        private val HARBINGERS_OF_SKULLS = 209
        private val KIASYD = 210
        private val LAMIA = 211
        private val LHIANNAN = 212
        private val NAGARAJA = 213
        private val NOIAD = 214
        private val PANDERS = 215
        private val SALUBRI = 216
        private val SAMEDI = 217
        private val TRUE_BRUJAH = 218

        private val ASSAMITE_ANTITRUBU = 301
        private val BRUJAH_ANTITRUBU = 302
        private val SERPENTS_OF_THE_LIGHT = 303
        private val GANGREL_ANTITRUBU = 304
        private val LASOMBRA_ANTITRUBU = 305
        private val MALKAVIAN_ANTITRUBU = 306
        private val NOSFERATU_ANTITRUBU = 307
        private val RAVNOS_ANTITRUBU = 308
        private val TOREADOR_ANTITRUBU = 309
        private val TREMERE_ANTITRUBU = 310
        private val VENTRUE_ANTITRUBU = 311
        private val SALUBRI_ANTITRIBU = 312

        //Right
        private val CAMARILLA = 1001
        private val ANARCHS = 1002
        private val SABBAT = 1003
        private val INDEPENDENT = 1004
    }
}
