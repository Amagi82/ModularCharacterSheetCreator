package amagi82.modularcharactersheetcreator.templates;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
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
    private GameCharacter character;
    private Game.System system;
    private List<Module> modules;
    private int spanCount = 3;

    public Template(Context context, GameCharacter character) {
        this.context = context;
        system = character.getGameSystem();
    }

    public Sheet createDefaultSheet(){
        modules = new ArrayList<>();
        switch(system){
            case CMAGE:
                return createCMage();
            case CVAMPIRE:
                return createCVampire();
            case CWEREWOLF:
                return createCWerewolf();
            case CWRAITH:
                return createCWraith();
            case NVAMPIRE:
                return createNVampire();
            case NWEREWOLF:
                return createNWerewolf();
            case NMUMMY:
                return createNMummy();
            case NDEMON:
                return createNDemon();
            case SCION:
                return createScion();
            case TRINITY:
                return createTrinity();
            case EXALTED:
                return createExalted();
        }
        return null;
    }

    private Sheet createCMage(){
        addCWodAttributes();
        addCWodAbilities(R.array.CMage_Talents, R.array.CMage_Skills, R.array.CMage_Knowledges);
        modules.add(new HeaderModule(getString(R.string.spheres), spanCount));
        modules.add(new StatBlockModule(getArray(R.array.CMage_Spheres_Left), 0, 5, null));
        modules.add(new StatBlockModule(getArray(R.array.CMage_Spheres_Center), 0, 5, null));
        modules.add(new StatBlockModule(getArray(R.array.CMage_Spheres_Right), 0, 5, null));
        modules.add(new HeaderModule(getString(R.string.advantages), spanCount));
        modules.add(new StatBlockModule(null, 0, 5, getString(R.string.backgrounds)));
        modules.add(new StatusModule(getString(R.string.arete), 1, 10));
        modules.add(new HealthModule(getCWodHealthLevels()));
        modules.add(new StatusModule(getString(R.string.willpower), 0, 10));
        //TODO: create and add Quintessence wheel and experience counter

        return createCharacterSheet();
    }

    private Sheet createCVampire() {
        addCWodAttributes();
        addCWodAbilities(R.array.CVampire_Talents, R.array.CVampire_Skills, R.array.CVampire_Knowledges);
        modules.add(new HeaderModule(getString(R.string.advantages), spanCount));
        modules.add(new StatusModule(getString(R.string.backgrounds)));


        return createCharacterSheet();
    }

    private Sheet createCWerewolf() {
        addCWodAttributes();
        addCWodAbilities(R.array.CWerewolf_Talents, R.array.CWerewolf_Skills, R.array.CWerewolf_Knowledges);
        modules.add(new HeaderModule(getString(R.string.advantages), spanCount));


        return createCharacterSheet();
    }

    private Sheet createCWraith() {
        addCWodAttributes();
        addCWodAbilities(R.array.CWraith_Talents, R.array.CWraith_Skills, R.array.CWraith_Knowledges);
        modules.add(new HeaderModule(getString(R.string.advantages), spanCount));


        return createCharacterSheet();
    }

    private Sheet createNVampire() {
        return createCharacterSheet();
    }

    private Sheet createNWerewolf() {
        return createCharacterSheet();
    }

    private Sheet createNMummy() {
        return createCharacterSheet();
    }

    private Sheet createNDemon() {
        return createCharacterSheet();
    }

    private Sheet createScion() {
        return createCharacterSheet();
    }

    private Sheet createTrinity() {
        return createCharacterSheet();
    }

    private Sheet createExalted() {
        return createCharacterSheet();
    }




    private Sheet createCharacterSheet(){
        Sheet sheet = new Sheet();
        sheet.setTitle(getString(R.string.character_sheet));
        sheet.setModules(modules);
        return sheet;
    }

    private void addCWodAttributes(){
        modules.add(new HeaderModule(getString(R.string.attributes), spanCount));
        modules.add(new StatBlockModule(getArray(R.array.CWod_Physical), 1, 5, getString(R.string.physical)));
        modules.add(new StatBlockModule(getArray(R.array.CWod_Social), 1, 5, getString(R.string.social)));
        modules.add(new StatBlockModule(getArray(R.array.CWod_Mental), 1, 5, getString(R.string.mental)));
    }

    private void addCWodAbilities(int talents, int skills, int knowledges){
        modules.add(new HeaderModule(getString(R.string.abilities), spanCount));
        modules.add(new StatBlockModule(getArray(talents), 0, 5, getString(R.string.talents)));
        modules.add(new StatBlockModule(getArray(skills), 0, 5, getString(R.string.skills)));
        modules.add(new StatBlockModule(getArray(knowledges), 0, 5, getString(R.string.knowledges)));
    }

    private List<Stat> getCWodHealthLevels(){
        List<Stat> stats = new ArrayList<>();
        String[] injuries = getArray(R.array.CWod_Health_Levels);
        int[] levels = context.getResources().getIntArray(R.array.CWod_Health_Penalties);
        for(int i = 0; i< injuries.length; i++) stats.add(new Stat(injuries[i], levels[i]));
        return stats;
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
        return new StatBlockModule(stats, category);
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
