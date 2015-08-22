package amagi82.modularcharactersheetcreator.models;

import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.util.List;
import java.util.UUID;

import amagi82.modularcharactersheetcreator.models.games.Game;
import amagi82.modularcharactersheetcreator.models.games.Splat;
import amagi82.modularcharactersheetcreator.models.games.systems.GameSystem;
import auto.parcel.AutoParcel;

import static amagi82.modularcharactersheetcreator.activities.MainActivity.NONE;

@AutoParcel
public abstract class GameCharacter implements Parcelable{
    public abstract String name();
    @Nullable public abstract Splat left();
    @Nullable public abstract Splat right();
    public abstract @StringRes int gameTitle();
    public abstract @ColorInt int colorBackground();
    public abstract @ColorInt int colorText();
    public abstract @ColorInt int colorTextDim();
    @Nullable public abstract List<Sheet> sheets();
    @Nullable public abstract Uri imageUriPort();
    @Nullable public abstract Uri imageUriLand();
    public abstract String entityId();
    public abstract long timeStamp();

    GameCharacter() {
    }

    public static GameCharacter create(String name, GameSystem system, Splat left, Splat right){
        return builder().name(name).gameTitle(system.getGameTitle()).left(left).right(right).build();
    }

    @AutoParcel.Builder
    public abstract static class Builder{
        public abstract Builder name(String name);
        public abstract Builder left(Splat left);
        public abstract Builder right(Splat right);
        public abstract Builder gameTitle(@StringRes int title);
        public abstract Builder colorBackground(@ColorInt int color);
        public abstract Builder colorText(@ColorInt int color);
        public abstract Builder colorTextDim(@ColorInt int color);
        public abstract Builder sheets(List<Sheet> sheets);
        public Builder imageUri(Uri image, boolean portrait){
            return portrait? imageUriPort(image) : imageUriLand(image);
        }
        public Builder clearImageUri(){
            return imageUriPort(null).imageUriLand(null);
        }
        abstract Builder imageUriPort(Uri imagePort);
        abstract Builder imageUriLand(Uri imageLand);
        abstract Builder entityId(String id);
        abstract Builder timeStamp(long currentTime);
        public abstract GameCharacter build();

        Builder() {
        }
    }
    public static Builder builder(){
        return new AutoParcel_GameCharacter.Builder()
                .name("")
                .gameTitle(NONE)
                .colorBackground(NONE)
                .colorText(NONE)
                .colorTextDim(NONE)
                .entityId(UUID.randomUUID().toString())
                .timeStamp(System.currentTimeMillis());
    }

    public abstract Builder toBuilder();

    //Minimum requirements necessary to save the character
    public boolean isComplete() {
        return name().length() > 0 && gameTitle() != NONE && left() != null && right() != null;
    }

    //Used during character creation/editing. Gets the current step in the character creation process
    public int getProgress(){
        return gameTitle() == NONE ? 0 : left() == null? 1 : right() == null? 2 : 3;
    }

    //Used during character creation/editing. Removes progress on back.
    public GameCharacter removeProgress(int fromStep){
        int gameTitle = fromStep <= 1? NONE : gameTitle();
        Splat left = fromStep <= 2? null : left();
        Splat right = fromStep <= 3? null : right();
        return toBuilder().gameTitle(gameTitle).left(left).right(right).build();
    }

    public GameSystem getGameSystem(){
        return new Game().getSystem(gameTitle()); //May return null.
    }

    public int getArchetype() {
        if(left() == null || right() == null || gameTitle() == NONE) return NONE;
        //noinspection ConstantConditions
        return new Game().getSystem(gameTitle()).isArchetypeLeft()? left().title() : right().title();
    }
}