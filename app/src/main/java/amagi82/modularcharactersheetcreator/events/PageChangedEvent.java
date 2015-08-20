package amagi82.modularcharactersheetcreator.events;

public class PageChangedEvent {
    public final int currentPage;

    public PageChangedEvent(int currentPage) {
        this.currentPage = currentPage;
    }
}
