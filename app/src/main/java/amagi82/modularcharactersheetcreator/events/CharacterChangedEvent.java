package amagi82.modularcharactersheetcreator.events;

public class CharacterChangedEvent {
    public int position;
    public int count;

    public CharacterChangedEvent(int position, int count) {
        this.position = position;
        this.count = count;
    }
}
