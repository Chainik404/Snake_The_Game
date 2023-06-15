import java.util.ArrayList;
import java.util.List;

public class CustomEventGenerator {
    // List of event listeners
    private List<CustomEventListener> listeners = new ArrayList<>();

    // Method to subscribe to event
    public void addCustomEventListener(CustomEventListener listener) {
        listeners.add(listener);
    }

    // Method to unsubscribe from event
    public void removeCustomEventListener(CustomEventListener listener) {
        listeners.remove(listener);
    }

    // Method to fire event
    public void fireCustomEvent() {
        for (CustomEventListener listener : listeners) {
            listener.onCustomEvent();
        }
    }
}


