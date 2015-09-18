package amagi82.modularcharactersheetcreator.ui.main;

import android.databinding.BaseObservable;
import android.support.annotation.StringRes;
import android.util.Log;

import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;

public class MainItemViewModel extends BaseObservable {
    private GameCharacter character;
    @StringRes private int system;
    private int systemColor;
    private String name;
    @StringRes private int archetype;

    public MainItemViewModel(GameCharacter character) {
        this.character = character;
        system = character.gameTitle();
        systemColor = character.getGameSystem().getGameColor();
        name = character.name();
        archetype = character.getArchetype();
        Log.i(null, "MainItemViewModel created for " + name);
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public int getSystem() {
        return system;
    }
    public int getSystemColor() {
        return systemColor;
    }
    public String getName() {
        return name;
    }
    public int getArchetype() {
        return archetype;
    }
}

