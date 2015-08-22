package amagi82.modularcharactersheetcreator.models.modules;


import java.util.List;

/*
    Module with bold title on the left and normal text beside it. Used for basic character info, like "Name: Black Widow"
 */
public class TitleTextBlockModule extends Module {

    List<Stat> stats;

    public TitleTextBlockModule() {
        super(Type.TITLETEXTBLOCK);
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }
}