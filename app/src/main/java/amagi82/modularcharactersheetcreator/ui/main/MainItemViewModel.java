package amagi82.modularcharactersheetcreator.ui.main;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.support.annotation.StringRes;

import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;

public class MainItemViewModel extends BaseObservable {
    private Resources res;
    private GameCharacter character;
    private String system;
    private int systemColor;
    private String name;
    private String archetype;

    public MainItemViewModel(Context context, GameCharacter character) {
        res = context.getResources();
        this.character = character;
        system = getString(character.gameTitle());
        systemColor = character.getGameSystem().getGameColor();
        name = character.name();
        archetype = getString(character.getArchetype());
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public String getSystem() {
        return system;
    }

    public int getSystemColor() {
        return systemColor;
    }

    public String getName() {
        return name;
    }

    public String getArchetype() {
        return archetype;
    }

    private String getString(@StringRes int id) {
        return res.getString(id);
    }
}
