
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    // static void setOutputStream(String outputFile) {
    //     try {
    //         FileOutputStream fileOutputStream = new FileOutputStream(outputFile, true);
    //         PrintStream outputStream = new PrintStream(fileOutputStream);
    //         System.setOut(outputStream);
    //     } catch (IOException e) {
    //         System.err.printf("%s couldn't write to file", outputFile);
    //     }
    // }
    static Path getInputFilePath(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Invalid number of arguments.");
        }
        return Paths.get(args[0]);
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();

        try {

            Path inputFile = getInputFilePath(args);
            // Path outputFile = load config
            // setOutputStream(outputFile);
            simulation.loadSimulation(inputFile);
            simulation.runSimulation();

        } catch (IllegalArgumentException e) {
            System.err.printf(e.getMessage());
        }
    }
}
