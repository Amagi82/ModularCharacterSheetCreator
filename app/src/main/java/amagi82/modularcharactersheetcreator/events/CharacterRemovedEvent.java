package amagi82.modularcharactersheetcreator.events;

public class CharacterRemovedEvent {
    public int position;
    public int count;

    public CharacterRemovedEvent(int position, int count) {
        this.position = position;
        this.count = count;
    }
}
