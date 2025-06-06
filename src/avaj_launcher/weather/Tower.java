package avaj_launcher.weather;

import avaj_launcher.aircraft.Flyable;
import java.util.ArrayList;
import java.util.List;

class Tower {

    private List<Flyable> observers;

    protected boolean isRegistered(Flyable observer) {
        return this.observers.contains(observer);
    }

    public Tower() {
        this.observers = new ArrayList<>();
    }

    public void register(Flyable observer) {
        this.observers.add(observer);
    }

    public void unregister(Flyable observer) {
        var observerToRemove = this.observers.indexOf(observer);
        this.observers.remove(observerToRemove);
    }

    protected void conditionChanged() {
        List<Flyable> observersToUnregister = new ArrayList<>(this.observers);

        for (Flyable observer : this.observers) {
            observer.updateConditions();
            if (observer.getToUnregister()) {
                // List all observers to be removed and 
                observersToUnregister.add(observer);
            }
        }

        this.observers.removeAll(observersToUnregister);
    }

}
