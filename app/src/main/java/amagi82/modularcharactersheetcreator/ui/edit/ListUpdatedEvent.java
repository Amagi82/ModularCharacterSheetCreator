package amagi82.modularcharactersheetcreator.ui.edit;

import java.util.List;

import amagi82.modularcharactersheetcreator.entities.characters.Splat;

public class ListUpdatedEvent {
    public final List<Splat> list;

    public ListUpdatedEvent(List<Splat> list) {
        this.list = list;
    }
}
