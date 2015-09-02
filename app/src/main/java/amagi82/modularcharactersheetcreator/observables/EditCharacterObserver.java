package amagi82.modularcharactersheetcreator.observables;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import amagi82.modularcharactersheetcreator.BR;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.games.Splat;

import static amagi82.modularcharactersheetcreator.activities.MainActivity.NONE;

public class EditCharacterObserver extends BaseObservable{
    private GameCharacter character;
    private int splashUrl = NONE;
    private Splat left;
    private Splat right;
    private String name;

    public EditCharacterObserver(GameCharacter character) {
        setCharacter(character);
    }

    @Bindable public GameCharacter getCharacter() {
        return character;
    }

    public void setCharacter(GameCharacter character) {
        Log.i(null, "setCharacter called");
        this.character = character;
        int url = (character.getGameSystem() == null) ? NONE : character.getGameSystem().getSplashUrl();
        if(url != this.splashUrl) {
            Log.i(null, "updating splashUrl");
            this.splashUrl = url;
            notifyPropertyChanged(BR.splashUrl);
        }
        if(character.left() != this.left) {
            Log.i(null, "updating left");
            this.left = character.left();
            notifyPropertyChanged(BR.left);
        }
        if(character.right() != this.right) {
            Log.i(null, "updating right");
            this.right = character.right();
            notifyPropertyChanged(BR.right);
        }
        if (!character.name().equals(this.name)) {
            Log.i(null, "updating name");
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
