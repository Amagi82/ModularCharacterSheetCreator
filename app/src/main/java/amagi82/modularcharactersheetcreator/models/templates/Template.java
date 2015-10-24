package amagi82.modularcharactersheetcreator.models.templates;

import android.content.res.Resources;
import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.modules.BloodPool;
import amagi82.modularcharactersheetcreator.models.modules.Health;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.models.modules.Stat;

public abstract class Template {
    private Resources res;

    public Template(Resources res) {
        this.res = res;
    }

    Module bloodPool(){
        return Module.createBloodPool(getString(R.string.blood_pool), BloodPool.createDefault());
    }

    Module header(@StringRes int resId){
        return Module.createHeader(getString(resId));
    }

    Module health(){
        return Module.createHealth(getString(R.string.health), Health.createDefault());
    }

    Module statBlock(@StringRes int titleId, @ArrayRes int arrayId, int valueMin, int valueMax){
        return Module.createStatBlock(getString(titleId), createDefaultStats(arrayId, valueMin, valueMax));
    }

    Module stat(@StringRes int titleId, @StringRes int bodyId, int valueMin, int valueMax){
        return Module.createStat(getString(titleId), getString(bodyId), Stat.createDefault("", valueMin, valueMax));
    }

    Module text(@StringRes int titleId, String bodyText){
        return Module.createText(getString(titleId), bodyText);
    }

    private List<Stat> createDefaultStats(int arrayId, int valueMin, int valueMax){
        List<Stat> stats = new ArrayList<>();
        String[] categories = getArray(arrayId);
        for (String category : categories){
            stats.add(Stat.createDefault(category, valueMin, valueMax));
        }
        return stats;
    }

    private String getString(@StringRes int resId){
        return res.getString(resId);
    }

    private String[] getArray(@ArrayRes int resId){
        return res.getStringArray(resId);
    }
}
