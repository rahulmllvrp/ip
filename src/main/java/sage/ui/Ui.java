package sage.ui;

import java.util.Scanner;
import java.util.ArrayList;
import sage.task.Task;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm sage");
        System.out.println(" What can I do for you?");
        showLine();
    }

    public void showGoodbye() {
        showLine();
        System.out.println(" Bye. Hope to see you again soon!");
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        showLine();
        System.out.println(" OOPS!!! " + message);
        showLine();
    }

    public void showLoadingError() {
        showError("Error loading tasks from file.");
    }

    public void showMessage(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    public void showMatchingTasks(ArrayList<Task> tasks) {
        showLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i).toString());
        }
        showLine();
    }
}
