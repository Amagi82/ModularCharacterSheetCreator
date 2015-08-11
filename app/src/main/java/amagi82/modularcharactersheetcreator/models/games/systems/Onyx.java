package amagi82.modularcharactersheetcreator.models.games.systems;

import java.util.List;

import amagi82.modularcharactersheetcreator.models.games.Choice;

/*
    Onyx is the base class for all Onyx Path games.
 */
public abstract class Onyx {

    public abstract String getSystemName();

    public abstract int getArchetype();

    public abstract boolean hasRight();

    public abstract Choice getLeft();

    public abstract void setLeft(String eName);

    public abstract Choice getRight();

    public abstract void setRight(String eName);

    public abstract List<Choice> getListLeft(String eName);

    public abstract List<Choice> getListRight(String eName);
}
