package amagi82.modularcharactersheetcreator.events;

public class CharacterMovedEvent {
    public int fromPosition;
    public int toPosition;

    public CharacterMovedEvent(int fromPosition, int toPosition) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }
}
