package amagi82.modularcharactersheetcreator.templates;

import android.content.Context;

import java.util.List;

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



    public static class Builder{

    }
}
