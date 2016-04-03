package amagi82.modularcharactersheetcreator.models.games.templates;

import android.content.res.Resources;
import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.games.Game;
import amagi82.modularcharactersheetcreator.models.modules.Blood;
import amagi82.modularcharactersheetcreator.models.modules.Health;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.models.modules.Stat;

import static amagi82.modularcharactersheetcreator.models.games.Game.CMAGE;
import static amagi82.modularcharactersheetcreator.models.games.Game.CVAMPIRE;
import static amagi82.modularcharactersheetcreator.models.games.Game.CWEREWOLF;
import static amagi82.modularcharactersheetcreator.models.games.Game.CWRAITH;
import static amagi82.modularcharactersheetcreator.models.games.Game.EXALTED;
import static amagi82.modularcharactersheetcreator.models.games.Game.NDEMON;
import static amagi82.modularcharactersheetcreator.models.games.Game.NMUMMY;
import static amagi82.modularcharactersheetcreator.models.games.Game.NVAMPIRE;
import static amagi82.modularcharactersheetcreator.models.games.Game.NWEREWOLF;
import static amagi82.modularcharactersheetcreator.models.games.Game.SCION;
import static amagi82.modularcharactersheetcreator.models.games.Game.TRINITY;

public abstract class Template {
    private Resources res;

    Template(Resources res) {
        this.res = res;
    }

    abstract Sheet createSheet(GameCharacter character);

    public static Sheet create(Resources res, GameCharacter character) {
        switch (character.gameId()) {
            case CMAGE:
                return new CMageTemplate(res).createSheet(character);
            case CVAMPIRE:
                return new CVampireTemplate(res).createSheet(character);
            case CWEREWOLF:
                return new CWerewolfTemplate(res).createSheet(character);
            case CWRAITH:
                return new CWraithTemplate(res).createSheet(character);
            case EXALTED:
                return new ExaltedTemplate(res).createSheet(character);
            case NDEMON:
                return new NDemonTemplate(res).createSheet(character);
            case NMUMMY:
                return new NMummyTemplate(res).createSheet(character);
            case NVAMPIRE:
                return new NVampireTemplate(res).createSheet(character);
            case NWEREWOLF:
                return new NWerewolfTemplate(res).createSheet(character);
            case SCION:
                return new ScionTemplate(res).createSheet(character);
            case TRINITY:
                return new TrinityTemplate(res).createSheet(character);
            default:
                return null;
        }
    }

    Game getSystem(GameCharacter character){
        return character.getGameSystem();
    }

    Sheet sheet(List<Module> modules) {
        return Sheet.Companion.create(getString(R.string.character_sheet), modules);
    }

    Module bloodPool() {
        return Module.createBlood(getString(R.string.blood_pool), Blood.createDefault());
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
