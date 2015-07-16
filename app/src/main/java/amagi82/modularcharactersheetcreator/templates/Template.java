package amagi82.modularcharactersheetcreator.templates;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.game_systems.Game;
import amagi82.modularcharactersheetcreator.models.modules.BloodPoolModule;
import amagi82.modularcharactersheetcreator.models.modules.HeaderModule;
import amagi82.modularcharactersheetcreator.models.modules.HealthModule;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.models.modules.Stat;
import amagi82.modularcharactersheetcreator.models.modules.StatBlockModule;
import amagi82.modularcharactersheetcreator.models.modules.StatusModule;

public class Template {

    private Context context;
    private Game.System system;
    private int spanCount;

    public Template(Context context, Game.System system, int spanCount) {
        this.system = system;
        this.context = context;
        this.spanCount = spanCount;
    }

    public List<Sheet> create(){
        List<Sheet> sheets = new ArrayList<>();
        switch(system){
            case CMAGE:
                return createCMage(sheets);
            case CVAMPIRE:
                return createCVampire(sheets);
            case CWEREWOLF:
                return createCWerewolf(sheets);
            case CWRAITH:
                return createCWraith(sheets);
            case NVAMPIRE:
                return createNVampire(sheets);
            case NWEREWOLF:
                return createNWerewolf(sheets);
            case NMUMMY:
                return createNMummy(sheets);
            case NDEMON:
                return createNDemon(sheets);
            case SCION:
                return createScion(sheets);
            case TRINITY:
                return createTrinity(sheets);
            case EXALTED:
                return createExalted(sheets);
        }
        return null;
    }

    private List<Sheet> createCMage(List<Sheet> sheets){
        List<Module> modules = new ArrayList<>();
        modules.add(new StatBlockModule(getArray(R.array.CMage_Physical), 1, 5, getString(R.string.physical), true));


        sheets.add(createCharacterSheet(modules));
        return sheets;
    }

    private List<Sheet> createCVampire(List<Sheet> sheets) {
        return null;
    }

    private List<Sheet> createCWerewolf(List<Sheet> sheets) {
        return null;
    }

    private List<Sheet> createCWraith(List<Sheet> sheets) {
        return null;
    }

    private List<Sheet> createNVampire(List<Sheet> sheets) {
        return null;
    }

    private List<Sheet> createNWerewolf(List<Sheet> sheets) {
        return null;
    }

    private List<Sheet> createNMummy(List<Sheet> sheets) {
        return null;
    }

    private List<Sheet> createNDemon(List<Sheet> sheets) {
        return null;
    }

    private List<Sheet> createScion(List<Sheet> sheets) {
        return null;
    }

    private List<Sheet> createTrinity(List<Sheet> sheets) {
        return null;
    }

    private List<Sheet> createExalted(List<Sheet> sheets) {
        return null;
    }

    private Sheet createCharacterSheet(List<Module> modules){
        Sheet sheet = new Sheet();
        sheet.setTitle(getString(R.string.character_sheet));
        sheet.setModules(modules);
        return sheet;
    }


    private BloodPoolModule addBloodpool(){
        return new BloodPoolModule();
    }

    private HeaderModule addHeader(String header){
        return new HeaderModule(header, spanCount);
    }

    private HealthModule addHealth(List<Stat> healthLevels){
        return new HealthModule(healthLevels);
    }

    private Module addModule(String title, String text, boolean fullWidth){
        return new Module(title, text, fullWidth? spanCount : 1);
    }

    private StatBlockModule addStatBlock(List<Stat> stats, String category, boolean compact){
        return new StatBlockModule(stats, category, compact);
    }

    private StatusModule addStatus(String title){
        return new StatusModule(title);
    }

    private String getString(int id){
        return context.getString(id);
    }

    private String[] getArray(int id){
        return context.getResources().getStringArray(id);
    }

}
