package se.wjss.events;

public class EventDispatcher {
    private Event event;

    public void dispatch(EventType type, EventHandler handler) {
        if(event.handled) {
            return;
        }

        if (event.getType() == type) {
            event.handled = handler.onEvent(event);
        }
    }
}
