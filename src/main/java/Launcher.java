import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * To launch the GUI application for Duke.
     * @param args Unused
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
