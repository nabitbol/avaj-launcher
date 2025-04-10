import java.util.List;
import java.util.ArrayList;

class Tower {
    
    private List<Integer> observers;

    private boolean isRegistered(int observer) {
        return this.observers.contains(observer);
    }

    public Tower() {
        this.observers = new ArrayList<>();
    }

    public void register(int observer) {
        if (isRegistered(observer))
            System.out.println("Error: " + observer + " already registered");
        else 
            this.observers.add(observer);
    }

    /*
    * TODO
    * Check if exception is better to handle notRegistered observer
    */
    public void unregister(int observer) {
        int observerToRemove;

        if (!isRegistered(observer))
            System.out.println("Error: " + observer + " Doesn't exist");
        else {
            observerToRemove = this.observers.indexOf(observer);
            this.observers.remove(observerToRemove);
        }
    }

    /*
    * TODO
    * implement the condition changing alteration according to the subject
    */
    protected void conditionChanged() {
        for (var observer:observers) {
            System.out.println("Todo update condition: " + observer);
        }
    }

}
