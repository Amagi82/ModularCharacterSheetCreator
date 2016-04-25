package amagi82.modularcharactersheetcreator.extras

import android.app.Activity
import android.support.annotation.IntDef
import kotlin.annotation.AnnotationRetention.SOURCE

@kotlin.annotation.Retention(SOURCE)
@IntDef(START.toLong(), LEFT.toLong(), RIGHT.toLong(), FINISH.toLong())
annotation class CharacterProgress

const val START = 0
const val LEFT = 1
const val RIGHT = 2
const val FINISH = 3

@kotlin.annotation.Retention(SOURCE)
@IntDef(NONE.toLong(), EXALTED.toLong(), SCION.toLong(), TRINITY.toLong(), CMAGE.toLong(), CVAMPIRE.toLong(), CWEREWOLF.toLong(), CWRAITH.toLong(), NDEMON.toLong(), NMUMMY.toLong(), NVAMPIRE.toLong(), NWEREWOLF.toLong())
annotation class GameSystem

const val NONE = 0
const val EXALTED = 10
const val SCION = 20
const val TRINITY = 30
const val CMAGE = 101
const val CVAMPIRE = 102
const val CWEREWOLF = 103
const val CWRAITH = 104
const val NDEMON = 201
const val NMUMMY = 202
const val NVAMPIRE = 203
const val NWEREWOLF = 204

@kotlin.annotation.Retention(SOURCE)
@IntDef(HEADER_MODULE.toLong(), TEXT_MODULE.toLong(), STAT_MODULE.toLong(), STAT_BLOCK_MODULE.toLong(), HEALTH_MODULE.toLong(), BLOOD_MODULE.toLong(), IMAGE_MODULE.toLong())
annotation class ModuleType

const val HEADER_MODULE = 1
const val TEXT_MODULE = 2
const val STAT_MODULE = 3
const val STAT_BLOCK_MODULE = 4
const val HEALTH_MODULE = 5
const val BLOOD_MODULE = 6
const val IMAGE_MODULE = 7

//One-third, half, two-thirds, full width
@kotlin.annotation.Retention(SOURCE)
@IntDef(ONE_THIRD.toLong(), HALF.toLong(), TWO_THIRDS.toLong(), FULL.toLong())
annotation class ModuleSpanCount

const val ONE_THIRD = 2
const val HALF = 3
const val TWO_THIRDS = 4
const val FULL = 6


@IntDef(REQ_ADD.toLong(), REQ_MODIFY.toLong(), REQ_DEFAULT.toLong())
@kotlin.annotation.Retention(SOURCE)
annotation class ReqCode

const val REQ_ADD = 1
const val REQ_MODIFY = 2
const val REQ_DEFAULT = 5

@IntDef(Activity.RESULT_CANCELED.toLong(), Activity.RESULT_OK.toLong(), RESULT_DELETED.toLong(), RESULT_EDIT.toLong())
@kotlin.annotation.Retention(SOURCE)
annotation class ResultCode

const val RESULT_DELETED = 3
const val RESULT_EDIT = 4



const val CHARACTER = "CHARACTER"
const val LIST = "LIST"
const val MODULE = "MODULE"
