package sage.task;

import java.util.ArrayList;

import sage.util.UiMessages;

/**
 * Deals with interactions with the user.
 * Handles displaying messages to the user and reading user input.
 */
public class Ui {

    /**
     * Displays the welcome message to the user.
     */
    public String getWelcomeMessage() {
        return UiMessages.WELCOME_MESSAGE;
    }

    /**
     * Displays the goodbye message to the user.
     */
    public String getGoodbyeMessage() {
        return UiMessages.GOODBYE_MESSAGE;
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public String getErrorMessage(String message) {
        return UiMessages.ERROR_PREFIX + message;
    }

    /**
     * Displays a generic error message for loading tasks.
     */
    public String getLoadingError() {
        return getErrorMessage(UiMessages.LOADING_ERROR);
    }

    /**
     * Displays a general message to the user, surrounded by lines.
     * @param message The message to display.
     */
    public String getMessage(String message) {
        return message;
    }
  
    public String getMatchingTasks(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(UiMessages.MATCHING_TASKS_HEADER);
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    public String getUpcomingDeadlinesMessage(ArrayList<Deadline> deadlines) {
        if (deadlines.isEmpty()) {
            return UiMessages.NO_UPCOMING_DEADLINES;
        }

        StringBuilder sb = new StringBuilder(UiMessages.UPCOMING_DEADLINES_HEADER);
        for (int i = 0; i < deadlines.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(deadlines.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}

