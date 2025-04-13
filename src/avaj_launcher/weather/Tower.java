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

    /*
    * TODO
    * Check if exception is better to handle notRegistered observer
     */
    public void unregister(Flyable observer) {
        var observerToRemove = this.observers.indexOf(observer);
        this.observers.remove(observerToRemove);
    }

    /*
    * TODO
    * implement the condition changing alteration according to the subject
     */
    protected void conditionChanged() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
    }

}
