package amagi82.modularcharactersheetcreator.events;

public class CharacterInsertedEvent {
    public int position;
    public int count;

    public CharacterInsertedEvent(int position, int count) {
        this.position = position;
        this.count = count;
    }
}
