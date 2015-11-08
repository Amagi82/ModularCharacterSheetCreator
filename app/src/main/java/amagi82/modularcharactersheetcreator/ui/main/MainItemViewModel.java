package amagi82.modularcharactersheetcreator.ui.main;

import android.view.View;

import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.ui._extras.utils.Otto;

public class MainItemViewModel {
    private final GameCharacter character;

    public MainItemViewModel(GameCharacter character) {
        this.character = character;
    }

    public GameCharacter getCharacter() {
        return character;
    }

    public String getGameTitle() {
        return character.getGameSystem().getGameTitle();
    }

    public int getSystemColor() {
        return character.getGameSystem().getGameColor();
    }

    public void onItemClick(View view){
        Otto.BUS.get().post(new CharacterClickedEvent(character));
    }
}

