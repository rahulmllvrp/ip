package sage.task;

import java.util.ArrayList;

/**
 * Deals with interactions with the user.
 * Handles displaying messages to the user and reading user input.
 */
public class Ui {

    /**
     * Displays the welcome message to the user.
     */
    public String showWelcomeAndReturn() {
        return "Hello! I'm sage\nWhat can I do for you?";
    }

    /**
     * Displays the goodbye message to the user.
     */
    public String showGoodbyeAndReturn() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public String showErrorAndReturn(String message) {
        return "OOPS!!! " + message;
    }

    /**
     * Displays a generic error message for loading tasks.
     */
    public String showLoadingErrorAndReturn() {
        return showErrorAndReturn("Error loading tasks from file.");
    }

    /**
     * Displays a general message to the user, surrounded by lines.
     * @param message The message to display.
     */
    public String showMessageAndReturn(String message) {
        return message;
    }
  
    public String showMatchingTasksAndReturn(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}

