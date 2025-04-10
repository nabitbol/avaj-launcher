public class Simulation {

    public static void main(String[] args) {
        // int numberOfArguments;

        Tower tower = new  Tower();
        tower.register(3);
        tower.register(4);
        tower.register(5);
        tower.unregister(3);
        tower.unregister(3);
        tower.register(3);
        tower.conditionChanged();

    }

}
