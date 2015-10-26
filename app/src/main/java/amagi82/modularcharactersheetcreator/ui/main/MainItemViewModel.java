package amagi82.modularcharactersheetcreator.ui.main;

import android.view.View;

import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto;

public class MainItemViewModel {

    private final GameCharacter character;

    public MainItemViewModel(GameCharacter character) {
        this.character = character;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public int getSystem() {
        return character.gameTitle();
    }

    public int getSystemColor() {
        return character.getGameSystem().getGameColor();
    }

    public String getName() {
        return character.name();
    }

    public int getArchetype() {
        return character.getArchetype();
    }

    public String getEntityId(){
        return character.entityId();
    }

    public void onItemClick(View view){
        Otto.BUS.get().post(new CharacterClickedEvent(character));
    }
}

