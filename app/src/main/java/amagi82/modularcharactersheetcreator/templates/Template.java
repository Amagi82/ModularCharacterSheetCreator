package amagi82.modularcharactersheetcreator.templates;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.Sheet;
import amagi82.modularcharactersheetcreator.models.game_systems.CVampire;
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
        this.character = character;
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
        modules.add(new StatusModule(getString(R.string.willpower), 1, 10));
        //TODO: create and add Quintessence wheel and experience counter

        return createCharacterSheet();
    }

    private Sheet createCVampire() {
        addCWodAttributes();
        addCWodAbilities(R.array.CVampire_Talents, R.array.CVampire_Skills, R.array.CVampire_Knowledges);
        modules.add(new HeaderModule(getString(R.string.advantages), spanCount));
        Log.i(null, "creating Vampire");
        Log.i(null, "character: "+character.getName());
        Log.i(null, "system: "+ character.getGameSystem().getOnyx().getSystemName());
        Log.i(null, "clan: "+character.getRight().geteName());
        Log.i(null, "array: "+ CVampire.Clan.valueOf(character.getRight().geteName()).getDisciplineArrayId());

        modules.add(new StatBlockModule(getArray(CVampire.Clan.valueOf(
                character.getRight().geteName()).getDisciplineArrayId()), 0, 5, getString(R.string.disciplines)));
        modules.add(new StatBlockModule(null, 0, 5, getString(R.string.backgrounds)));
        modules.add(new StatBlockModule(getArray(R.array.CVampire_Virtues), 1, 5, getString(R.string.virtues)));
        modules.add(new HeaderModule(null, spanCount));
        //Add merits and flaws?
        modules.add(new StatusModule(getString(R.string.humanity), 1, 10));
        modules.add(new HealthModule(getCWodHealthLevels()));
        modules.add(new StatusModule(getString(R.string.willpower), 1, 10));
        modules.add(new BloodPoolModule());
        //Add weakness?
        //Add experience counter

        return createCharacterSheet();
    }

    private Sheet createCWerewolf() {
        addCWodAttributes();
        addCWodAbilities(R.array.CWerewolf_Talents, R.array.CWerewolf_Skills, R.array.CWerewolf_Knowledges);
        modules.add(new HeaderModule(getString(R.string.advantages), spanCount));
        modules.add(new StatBlockModule(null, 0, 5, getString(R.string.backgrounds)));
        //Add gifts lists
        modules.add(new HeaderModule(getString(R.string.renown), 1));
        modules.add(new StatusModule(getString(R.string.rage), 1, 10));
        modules.add(new HealthModule(getCWodHealthLevels()));
        modules.add(new StatusModule(getString(R.string.glory), 1, 10));
        modules.add(new StatusModule(getString(R.string.gnosis), 1, 10));
        modules.add(new StatusModule(getString(R.string.honor), 1, 10));
        modules.add(new StatusModule(getString(R.string.willpower), 1, 10));
        modules.add(new StatusModule(getString(R.string.wisdom), 1, 10));
        //Add experience counter
        //TODO: Add changer forms and modify stats accordingly

        return createCharacterSheet();
    }

    private Sheet createCWraith() {
        addCWodAttributes();
        addCWodAbilities(R.array.CWraith_Talents, R.array.CWraith_Skills, R.array.CWraith_Knowledges);
        modules.add(new HeaderModule(getString(R.string.advantages), spanCount));
        modules.add(new StatBlockModule(null, 0, 5, getString(R.string.backgrounds)));
        modules.add(new StatBlockModule(null, 0, 5, getString(R.string.passions)));
        modules.add(new StatBlockModule(null, 0, 5, getString(R.string.arcanoi)));
        modules.add(new StatusModule(getString(R.string.corpus), 1, 10));
        modules.add(new StatBlockModule(null, 0, 5, getString(R.string.fetters)));
        modules.add(new StatusModule(getString(R.string.willpower), 1, 10));
        modules.add(new StatusModule(getString(R.string.pathos), 1, 10));
        //Add experience counter

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
        for(int i = 0; i< (injuries != null ? injuries.length : 0); i++) stats.add(new Stat(injuries[i], levels[i]));
        return stats;
    }

    private String[] getDisciplines(){
        return getArray(CVampire.Clan.valueOf(character.getGameSystem().getOnyx().getRight().geteName()).getDisciplineArrayId());
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
        if(id == -1) return null;
        return context.getResources().getStringArray(id);
    }
}
