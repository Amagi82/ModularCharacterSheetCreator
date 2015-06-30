package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.List;

import amagi82.modularcharactersheetcreator.models.Choice;

public abstract class Onyx {

    public abstract String getSystemName();

    public abstract int getArchetype();

    public abstract Choice getLeft();

    public abstract Choice getRight();

    public abstract List<Choice> getLeftList(String eName);

    public abstract List<Choice> getRightList(String eName);
}
