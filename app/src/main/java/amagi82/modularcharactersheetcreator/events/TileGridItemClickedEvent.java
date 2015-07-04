package amagi82.modularcharactersheetcreator.events;

public class TileGridItemClickedEvent {
    public final String eName;
    public final boolean left;

    public TileGridItemClickedEvent(String eName, boolean left) {
        this.eName = eName;
        this.left = left;
    }
}
