package sage.ui;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 * Handles displaying messages to the user and reading user input.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a Ui object, initializing the Scanner to read from standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command line from the user.
     * @return The full command string entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm sage");
        System.out.println(" What can I do for you?");
        showLine();
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showGoodbye() {
        showLine();
        System.out.println(" Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public void showError(String message) {
        showLine();
        System.out.println(" OOPS!!! " + message);
        showLine();
    }

    /**
     * Displays a generic error message for loading tasks.
     */
    public void showLoadingError() {
        showError("Error loading tasks from file.");
    }

    /**
     * Displays a general message to the user, surrounded by lines.
     * @param message The message to display.
     */
    public void showMessage(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }
}