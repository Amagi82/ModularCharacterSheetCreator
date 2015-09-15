package amagi82.modularcharactersheetcreator.ui.extras.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.entities.characters.Sheet;
import amagi82.modularcharactersheetcreator.entities.games.GameSystem;
import amagi82.modularcharactersheetcreator.entities.modules.BloodPoolModule;
import amagi82.modularcharactersheetcreator.entities.modules.HeaderModule;
import amagi82.modularcharactersheetcreator.entities.modules.HealthModule;
import amagi82.modularcharactersheetcreator.entities.modules.Module;
import amagi82.modularcharactersheetcreator.entities.modules.Stat;
import amagi82.modularcharactersheetcreator.entities.modules.StatBlockModule;
import amagi82.modularcharactersheetcreator.entities.modules.StatusModule;

public class Template {

    private Context context;
    private GameCharacter character;
    private GameSystem system;
    private List<Module> modules;
    private int spanCount = 3;

    public Template(Context context, GameCharacter character) {
        this.context = context;
        this.character = character;
        //system = character.getGameSystem();
    }

    public Sheet createDefaultSheet(){
        modules = new ArrayList<>();
//        switch(system){
//            case CMAGE:
//                return createCMage();
//            case CVAMPIRE:
//                return createCVampire();
//            case CWEREWOLF:
//                return createCWerewolf();
//            case CWRAITH:
//                return createCWraith();
//            case NVAMPIRE:
//                return createNVampire();
//            case NWEREWOLF:
//                return createNWerewolf();
//            case NMUMMY:
//                return createNMummy();
//            case NDEMON:
//                return createNDemon();
//            case SCION:
//                return createScion();
//            case TRINITY:
//                return createTrinity();
//            case EXALTED:
//                return createExalted();
//        }
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
//        Log.i(null, "creating Vampire");
//        Log.i(null, "character: " + character.getName());
//        Log.i(null, "system: " + character.getGameSystem().getOnyx().getSystemName());
//        Log.i(null, "clan: "+character.getRight().geteName());

//        modules.add(new StatBlockModule(getArray(CVampire.Clan.valueOf(
//                character.getRight().geteName()).getDisciplineArrayId()), 0, 5, getString(R.string.disciplines)));
        modules.add(new StatBlockModule(null, 0, 5, getString(R.string.backgrounds)));
        modules.add(new StatBlockModule(getArray(R.array.CVampire_Virtues), 1, 5, getString(R.string.virtues)));
        modules.add(new HeaderModule(null, spanCount));
        //Add merits and flaws?
        modules.add(new StatusModule(getString(R.string.humanity), 1, 10));
        modules.add(new HealthModule(getCWodHealthLevels()));
        modules.add(new StatusModule(getString(R.string.willpower), 1, 10));
        addBloodpool();
        //Add weakness?
        //Add experience counter

        //Tests
        StatBlockModule modPhys = (StatBlockModule) modules.get(1);
        List<Stat> statsPhys = modPhys.getStats();
        statsPhys.get(0).setSpecialty("Big");
        statsPhys.get(2).setValueMax(3);
        StatBlockModule module = (StatBlockModule) modules.get(5);
        List<Stat> stats = module.getStats();
        stats.get(1).setSpecialty("Parkour");
        stats.get(1).setValueTemporary(5);
        stats.get(1).setValue(4);
        stats.get(6).setSpecialty("Creepy stare");
        stats.get(6).setValue(3);

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
        return Sheet.create(getString(R.string.character_sheet), modules);
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
        return null;
        //return getArray(CVampire.Clan.valueOf(character.getGameSystem().getOnyx().getRight().geteName()).getDisciplineArrayId());
    }

    private void addBloodpool(){
        modules.add(new BloodPoolModule(getString(R.string.blood_pool)));
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
