package amagi82.modularcharactersheetcreator.entities.characters;

import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.UUID;

import amagi82.modularcharactersheetcreator.entities.games.Game;
import amagi82.modularcharactersheetcreator.entities.games.GameSystem;
import auto.parcel.AutoParcel;

import static amagi82.modularcharactersheetcreator.ui.main.MainActivity.NONE;

@AutoParcel
public abstract class GameCharacter implements Parcelable {
    public abstract String name();
    @Nullable public abstract Splat left();
    @Nullable public abstract Splat right();
    public abstract @StringRes int gameTitle();
    @Nullable public abstract ColorScheme colorScheme();
    @Nullable public abstract List<Sheet> sheets();
    @Nullable public abstract CharacterImage image();
    public abstract String entityId();
    public abstract long timeStamp();

    GameCharacter() {}

    public static GameCharacter create(String name, GameSystem system, Splat left, Splat right) {
        return builder().name(name).gameTitle(system.getGameTitle()).left(left).right(right).build();
    }

    @AutoParcel.Builder
    public abstract static class Builder {
        public abstract Builder name(String name);
        public abstract Builder left(Splat left);
        public abstract Builder right(Splat right);
        public abstract Builder gameTitle(@StringRes int title);
        public abstract Builder colorScheme(ColorScheme colorScheme);
        public abstract Builder sheets(List<Sheet> sheets);
        public abstract Builder image(CharacterImage image);
        abstract Builder entityId(String id);
        abstract Builder timeStamp(long currentTime);
        public abstract GameCharacter build();

        Builder() {}
    }
    public static Builder builder() {
        return new AutoParcel_GameCharacter.Builder()
                .name("")
                .gameTitle(NONE)
                .entityId(UUID.randomUUID().toString())
                .timeStamp(System.currentTimeMillis());
    }

    public abstract Builder toBuilder();

    @AutoParcel
    public static abstract class ColorScheme {
        public abstract @ColorInt int colorBackground();
        public abstract @ColorInt int colorText();
        public abstract @ColorInt int colorTextDim();

        ColorScheme() {}

        public static ColorScheme create(int colorBackground, int colorText, int colorTextDim) {
            return new AutoParcel_GameCharacter_ColorScheme(colorBackground, colorText, colorTextDim);
        }
    }

    @AutoParcel
    public static abstract class CharacterImage {
        public abstract Uri uri();
        @IntRange(from = 100, to = 2560) public abstract int height();
        @IntRange(from = 100, to = 2560) public abstract int width();

        CharacterImage() {}

        public static CharacterImage create(Uri uri, int height, int width) {
            return new AutoParcel_GameCharacter_CharacterImage(uri, height, width);
        }
    }

    //Minimum requirements necessary to save the character
    public boolean isComplete() {
        return name().length() > 0 && getProgress() == FINISH;
    }

    //Used during character creation/editing. Gets the current step in the character creation process
    @Progress
    public int getProgress() {
        return gameTitle() == NONE ? START : left() == null ? LEFT : right() == null ? RIGHT : FINISH;
    }

    //Used during character creation/editing. Removes progress on back.
    public GameCharacter removeProgress(@IntRange(from = 0, to = 3) int fromStep) {
        int gameTitle = fromStep <= LEFT ? NONE : gameTitle();
        Splat left = fromStep <= RIGHT ? null : left();
        Splat right = fromStep <= FINISH ? null : right();
        return toBuilder().gameTitle(gameTitle).left(left).right(right).build();
    }

    public GameSystem getGameSystem() {
        return new Game().getSystem(gameTitle()); //May return null.
    }

    @SuppressWarnings("ConstantConditions")
    public int getArchetype() {
        if (left() == null || right() == null || gameTitle() == NONE) return NONE;
        return new Game().getSystem(gameTitle()).isArchetypeLeft() ? left().title() : right().title();
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({START, LEFT, RIGHT, FINISH})
    public @interface Progress {}
    public static final int START = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int FINISH = 3;
}