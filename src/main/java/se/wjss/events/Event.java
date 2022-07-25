package se.wjss.events;

public class Event {
    private EventType type;
    boolean handled;

    protected Event(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }
}
