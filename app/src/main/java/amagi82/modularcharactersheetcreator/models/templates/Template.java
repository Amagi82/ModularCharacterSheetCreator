package amagi82.modularcharactersheetcreator.models.templates;

import android.content.res.Resources;
import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.characters.Sheet;
import amagi82.modularcharactersheetcreator.models.modules.BloodPool;
import amagi82.modularcharactersheetcreator.models.modules.Health;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.models.modules.Stat;

public abstract class Template {
    private Resources res;

    Template(Resources res) {
        this.res = res;
    }

    abstract Sheet createSheet(GameCharacter character);

    public static Sheet create(Resources res, GameCharacter character) {
        switch (character.gameTitle()) {
            case R.string.cwod_mage:
                return new CMageTemplate(res).createSheet(character);
            case R.string.cwod_vampire:
                return new CVampireTemplate(res).createSheet(character);
            case R.string.cwod_werewolf:
                return new CWerewolfTemplate(res).createSheet(character);
            case R.string.cwod_wraith:
                //return new CWraithTemplate(res).createSheet(character);
            case R.string.exalted:
                //return new ExaltedTemplate(res).createSheet(character);
            case R.string.nwod_demon:
                //return new NDemonTemplate(res).createSheet(character);
            case R.string.nwod_mummy:
                //return new NMummyTemplate(res).createSheet(character);
            case R.string.nwod_vampire:
                //return new NVampireTemplate(res).createSheet(character);
            case R.string.nwod_werewolf:
                //return new NWerewolfTemplate(res).createSheet(character);
            case R.string.scion:
                //return new ScionTemplate(res).createSheet(character);
            case R.string.trinity:
                //return new TrinityTemplate(res).createSheet(character);
            default:
                return null;
        }
    }

    Sheet sheet(List<Module> modules) {
        return Sheet.create(getString(R.string.character_sheet), modules);
    }

    Module bloodPool() {
        return Module.createBloodPool(getString(R.string.blood_pool), BloodPool.createDefault());
    }

    Module header(@StringRes int resId) {
        return Module.createHeader(getString(resId));
    }

    Module header(@StringRes int resId, @Module.SpanCount int spanCount) {
        return Module.createHeader(getString(resId), spanCount);
    }

    Module health() {
        return Module.createHealth(getString(R.string.health), Health.createDefault());
    }

    Module statBlock(@StringRes int titleId, @ArrayRes int arrayId, int valueMin, int valueMax) {
        return Module.createStatBlock(getString(titleId), createDefaultStats(arrayId, valueMin, valueMax));
    }

    Module statBlock(@StringRes int titleId, @StringRes int textBody, @ArrayRes int arrayId, int valueMin, int valueMax) {
        return Module.createStatBlock(getString(titleId), getString(textBody), createDefaultStats(arrayId, valueMin, valueMax));
    }

    Module stat(@StringRes int titleId, @StringRes int bodyId, int valueMin, int valueMax) {
        return Module.createStat(getString(titleId), getString(bodyId), Stat.createDefault("", valueMin, valueMax));
    }

    Module text(@StringRes int titleId) {
        return Module.createText(getString(titleId), "");
    }

    private List<Stat> createDefaultStats(int arrayId, int valueMin, int valueMax) {
        List<Stat> stats = new ArrayList<>();
        String[] categories = getArray(arrayId);
        if (categories != null) {
            for (String category : categories) {
                stats.add(Stat.createDefault(category, valueMin, valueMax));
            }
        }
        return stats;
    }

    private String getString(@StringRes int resId) {
        return resId == 0 ? "" : res.getString(resId);
    }

    private String[] getArray(@ArrayRes int resId) {
        return resId == 0 ? null : res.getStringArray(resId);
    }
}
