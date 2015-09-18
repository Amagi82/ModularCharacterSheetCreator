package amagi82.modularcharactersheetcreator.ui.main;

import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;

public class MainItemViewModel extends BaseObservable {

    @Bindable private GameCharacter character;
    @Bindable private String system;
    @Bindable private int systemColor;
    @Bindable private String name;
    @Bindable private String archetype;

    public MainItemViewModel(Resources res, GameCharacter character) {
        this.character = character;
        system = res.getString(character.gameTitle());
        systemColor = character.getGameSystem().getGameColor();
        name = character.name();
        archetype = res.getString(character.getArchetype());
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
}
