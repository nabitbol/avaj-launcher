package avaj_launcher.weather;

import java.util.ArrayList;
import java.util.List;

import avaj_launcher.aircraft.Flyable;

class Tower {

    private List<Flyable> observers;

    private boolean isRegistered(Flyable observer) {
        return this.observers.contains(observer);
    }

    public Tower() {
        this.observers = new ArrayList<>();
    }

    public void register(Flyable observer) {
        if (isRegistered(observer)) {
            System.out.println("Error: " + observer + " already registered");
        } else {
            this.observers.add(observer);
        }
    }

    /*
    * TODO
    * Check if exception is better to handle notRegistered observer
     */
    public void unregister(Flyable observer) {

        if (!isRegistered(observer)) {
            System.out.println("Error: " + observer + " Doesn't exist");
        } else {
            var observerToRemove = this.observers.indexOf(observer);
            this.observers.remove(observerToRemove);
        }
    }

    /*
    * TODO
    * implement the condition changing alteration according to the subject
     */
    protected void conditionChanged() {
        for (var observer : observers) {
            System.out.println("Todo update condition: " + observer);
        }
    }

}
