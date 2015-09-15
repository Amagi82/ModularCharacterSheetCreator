package amagi82.modularcharactersheetcreator.ui.edit;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.entities.characters.Splat;

import static amagi82.modularcharactersheetcreator.ui.main.MainActivity.NONE;

public class EditViewModel extends BaseObservable{
    private GameCharacter character;
    private int splashUrl = NONE;
    private Splat left;
    private Splat right;
    private String name;

    public EditViewModel(GameCharacter character) {
        setCharacter(character);
    }

    @Bindable public GameCharacter getCharacter() {
        return character;
    }

    public void setCharacter(GameCharacter character) {
        this.character = character;
        int url = (character.getGameSystem() == null) ? NONE : character.getGameSystem().getSplashUrl();
        if(url != this.splashUrl) {
            this.splashUrl = url;
            notifyPropertyChanged(BR.splashUrl);
        }
        if(character.left() != this.left) {
            this.left = character.left();
            notifyPropertyChanged(BR.left);
        }
        if(character.right() != this.right) {
            this.right = character.right();
            notifyPropertyChanged(BR.right);
        }
        if (!character.name().equals(this.name)) {
            this.name = character.name();
            notifyPropertyChanged(BR.name);
        }
    }

    @Bindable public int getSplashUrl() {
        return splashUrl;
    }

    @Bindable public Splat getLeft() {
        return left;
    }

    @Bindable public Splat getRight() {
        return right;
    }

    @Bindable public String getName() {
        return name;
    }
}