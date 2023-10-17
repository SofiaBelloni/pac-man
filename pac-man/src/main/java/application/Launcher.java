package application;

/**
 * This class represents the Launcher of the system, to bypass JAVA 11 modules constraints.
 */
public final class Launcher {

    private Launcher() { }

    /**
     * @param args unused
     */
    public static void main(final String[] args) {
        PacManApp.main(args);
    }
}
