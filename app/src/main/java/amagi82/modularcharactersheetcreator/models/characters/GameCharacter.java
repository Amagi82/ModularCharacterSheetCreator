package amagi82.modularcharactersheetcreator.models.characters;

import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import amagi82.modularcharactersheetcreator.models.games.Game;
import auto.parcelgson.AutoParcelGson;

@AutoParcelGson
public abstract class GameCharacter implements Parcelable {
    public abstract String name();
    public abstract @StringRes int gameTitle();
    @Nullable public abstract Splat left();
    @Nullable public abstract Splat right();
    @Nullable public abstract ColorScheme colorScheme();
    @NonNull public abstract List<Sheet> sheets();
    @Nullable public abstract CharacterImage image();
    public abstract String entityId();
    public abstract long timeStamp();

    GameCharacter() {}

    public static GameCharacter create(String name, Game system, Splat left, Splat right) {
        return builder().name(name).gameTitle(system.getGameTitle()).left(left).right(right).build();
    }

    public static GameCharacter create(){
        return builder().build();
    }

    public GameCharacter withName(String name){
        return toBuilder().name(name).timeStamp(System.currentTimeMillis()).build();
    }

    public GameCharacter withGame(@StringRes int gameTitle){
        return toBuilder().gameTitle(gameTitle).timeStamp(System.currentTimeMillis()).build();
    }

    public GameCharacter withLeft(Splat left){
        return toBuilder().left(left).timeStamp(System.currentTimeMillis()).build();
    }

    public GameCharacter withRight(Splat right){
        return toBuilder().right(right).timeStamp(System.currentTimeMillis()).build();
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
        abstract Builder gameTitle(@StringRes int title);
        abstract Builder left(Splat left);
        abstract Builder right(Splat right);
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
                .gameTitle(0)
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
        return gameTitle() == 0 ? START : left() == null ? LEFT : right() == null ? RIGHT : FINISH;
    }

    //Used during character creation/editing. Removes progress on back.
    public GameCharacter removeProgress(@Progress int toStep) {
        int gameTitle = toStep == START ? 0 : gameTitle();
        Splat left = toStep <= LEFT ? null : left();
        Splat right = toStep <= RIGHT ? null : right();
        return toBuilder().gameTitle(gameTitle).left(left).right(right).timeStamp(System.currentTimeMillis()).build();
    }

    public Game getGameSystem() {
        return Game.getSystem(gameTitle());
    }

    @SuppressWarnings("ConstantConditions")
    public int getArchetype() {
        if (left() == null || right() == null || gameTitle() == 0) return 0;
        return Game.getSystem(gameTitle()).isArchetypeLeft() ? left().title() : right().title();
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({START, LEFT, RIGHT, FINISH})
    public @interface Progress {}
    public static final int START = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int FINISH = 3;
}