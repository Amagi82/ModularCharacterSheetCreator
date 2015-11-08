package amagi82.modularcharactersheetcreator.models;

import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import amagi82.modularcharactersheetcreator.models.games.Game;
import auto.parcelgson.AutoParcelGson;

import static amagi82.modularcharactersheetcreator.models.games.Game.NONE;

@AutoParcelGson
public abstract class GameCharacter implements Parcelable {
    public abstract String name();
    public abstract @Game.System int gameId();
    public abstract int leftId();
    public abstract int rightId();
    @Nullable public abstract ColorScheme colorScheme();
    @NonNull public abstract List<Sheet> sheets();
    @Nullable public abstract CharacterImage image();
    public abstract String entityId();
    public abstract long timeStamp();

    GameCharacter() {}

    //Used to generate test objects, remove for production
    public static GameCharacter create(String name, @Game.System int system, int left, int right) {
        return builder().name(name).gameId(system).leftId(left).rightId(right).build();
    }

    public static GameCharacter create(){
        return builder().build();
    }

    public GameCharacter withName(String name){
        return toBuilder().name(name).timeStamp(System.currentTimeMillis()).build();
    }

    public GameCharacter withGame(@Game.System int gameId){
        return toBuilder().gameId(gameId).timeStamp(System.currentTimeMillis()).build();
    }

    public GameCharacter withLeft(int leftId){
        return toBuilder().leftId(leftId).timeStamp(System.currentTimeMillis()).build();
    }

    public GameCharacter withRight(int rightId){
        return toBuilder().rightId(rightId).timeStamp(System.currentTimeMillis()).build();
    }

    public GameCharacter withSheets(List<Sheet> sheets){
        return toBuilder().sheets(sheets).timeStamp(System.currentTimeMillis()).build();
    }

    public GameCharacter withImage(CharacterImage image, ColorScheme scheme){
        return toBuilder().image(image).colorScheme(scheme).timeStamp(System.currentTimeMillis()).build();
    }

    @AutoParcelGson.Builder
    abstract static class Builder {
        abstract Builder name(String name);
        abstract Builder gameId(@Game.System int gameId);
        abstract Builder leftId(int leftId);
        abstract Builder rightId(int rightId);
        abstract Builder colorScheme(ColorScheme colorScheme);
        abstract Builder sheets(List<Sheet> sheets);
        abstract Builder image(CharacterImage image);
        abstract Builder entityId(String id);
        abstract Builder timeStamp(long currentTime);
        abstract GameCharacter build();

        Builder() {}
    }

    static Builder builder() {
        return new AutoParcelGson_GameCharacter.Builder()
                .name("")
                .gameId(NONE)
                .leftId(0)
                .rightId(0)
                .sheets(new ArrayList<Sheet>())
                .entityId(UUID.randomUUID().toString())
                .timeStamp(System.currentTimeMillis());
    }

    abstract Builder toBuilder();

    @AutoParcelGson
    public static abstract class ColorScheme implements Parcelable{
        @ColorInt public abstract int colorBackground();
        @ColorInt public abstract int colorText();
        @ColorInt public abstract int colorTextDim();

        ColorScheme() {}

        public static ColorScheme create(int colorBackground, int colorText, int colorTextDim) {
            return new AutoParcelGson_GameCharacter_ColorScheme(colorBackground, colorText, colorTextDim);
        }
    }

    @AutoParcelGson
    public static abstract class CharacterImage implements Parcelable{
        @NonNull public abstract String uri();
        public abstract int height();
        public abstract int width();

        CharacterImage() {}

        public static CharacterImage create(String uri, int height, int width) {
            return new AutoParcelGson_GameCharacter_CharacterImage(uri, height, width);
        }
    }

    //Minimum requirements necessary to save the character
    public boolean isComplete() {
        return name().length() > 0 && getProgress() == FINISH;
    }

    //Used during character creation/editing. Gets the current step in the character creation process
    @Progress
    public int getProgress() {
        return gameId() == NONE ? START : leftId() == 0 ? LEFT : rightId() == 0 ? RIGHT : FINISH;
    }

    //Used during character creation/editing. Removes progress on back.
    public GameCharacter removeProgress(@Progress int toStep) {
        int game = toStep == START ? NONE : gameId();
        int left = toStep <= LEFT ? 0 : leftId();
        int right = toStep <= RIGHT ? 0 : rightId();
        return toBuilder().gameId(game).leftId(left).rightId(right).timeStamp(System.currentTimeMillis()).build();
    }

    public Game getGameSystem() {
        return Game.getSystem(gameId());
    }

    public String getArchetype() {
        if (leftId() == 0 || rightId() == 0 || gameId() == NONE) return null;
        Game system = getGameSystem();
        return system.isArchetypeLeft() ? system.getSplat(leftId()).title() : system.getSplat(rightId()).title();
    }

    public Splat getLeft(){
        return getGameSystem().getSplat(leftId());
    }

    public Splat getRight(){
        return getGameSystem().getSplat(rightId());
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({START, LEFT, RIGHT, FINISH})
    public @interface Progress {}
    public static final int START = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int FINISH = 3;
}