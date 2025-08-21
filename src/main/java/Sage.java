import java.util.Scanner;

public class Sage {

    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    static String name = "sage";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void printLine() {
        System.out.println(ANSI_YELLOW + "____________________________________________________________" + ANSI_RESET);
    }

    public static void greet() {
        printLine();
        System.out.println(ANSI_GREEN + " Hello! I'm " + name + ANSI_RESET);
        System.out.println(ANSI_GREEN + " What can I do for you?" + ANSI_RESET);
        printLine();
    }

    public static void exit() {
        printLine();
        System.out.println(ANSI_BLUE + " Bye. Hope to see you again soon!" + ANSI_RESET);
        printLine();
    }

    public static void listTasks() {
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(ANSI_BLUE + (i + 1) + "." + tasks[i] + ANSI_RESET);
        }
        printLine();
    }

    public static void markTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < taskCount) {
            tasks[taskIndex].markAsDone();
            printLine();
            System.out.println(ANSI_GREEN + " Nice! I've marked this task as done:" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "   " + tasks[taskIndex] + ANSI_RESET);
            printLine();
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public static void unmarkTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < taskCount) {
            tasks[taskIndex].unmarkAsDone();
            printLine();
            System.out.println(ANSI_GREEN + " OK, I've marked this task as not done yet:" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "   " + tasks[taskIndex] + ANSI_RESET);
            printLine();
        } else {
            System.out.println("Invalid task number.");
        }
    }


    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];

            if (input.startsWith("Sage says")) {
                String output = input.substring("Sage says".length()).trim();
                printLine();
                System.out.println(ANSI_BLUE + output + ANSI_RESET);
                printLine();
                continue;
            }

            switch (command) {
                case "bye":
                    scanner.close();
                    exit();
                    return;
                case "list":
                    listTasks();
                    break;
                case "mark":
                    int taskIndexMark = Integer.parseInt(parts[1]) - 1;
                    markTask(taskIndexMark);
                    break;
                case "unmark":
                    int taskIndexUnmark = Integer.parseInt(parts[1]) - 1;
                    unmarkTask(taskIndexUnmark);
                    break;
                case "todo":
                    tasks[taskCount] = new Todo(parts[1]);
                    taskCount++;
                    printLine();
                    System.out.println(ANSI_GREEN + " Got it. I've added this task:" + ANSI_RESET);
                    System.out.println(ANSI_GREEN + "   " + tasks[taskCount - 1] + ANSI_RESET);
                    System.out.println(ANSI_GREEN + " Now you have " + taskCount + " tasks in the list." + ANSI_RESET);
                    printLine();
                    break;
                case "deadline":
                    String[] deadlineParts = parts[1].split(" /by ");
                    tasks[taskCount] = new Deadline(deadlineParts[0], deadlineParts[1]);
                    taskCount++;
                    printLine();
                    System.out.println(ANSI_GREEN + " Got it. I've added this task:" + ANSI_RESET);
                    System.out.println(ANSI_GREEN + "   " + tasks[taskCount - 1] + ANSI_RESET);
                    System.out.println(ANSI_GREEN + " Now you have " + taskCount + " tasks in the list." + ANSI_RESET);
                    printLine();
                    break;
                case "event":
                    String[] eventParts = parts[1].split(" /from ");
                    String[] fromToParts = eventParts[1].split(" /to ");
                    tasks[taskCount] = new Event(eventParts[0], fromToParts[0], fromToParts[1]);
                    taskCount++;
                    printLine();
                    System.out.println(ANSI_GREEN + " Got it. I've added this task:" + ANSI_RESET);
                    System.out.println(ANSI_GREEN + "   " + tasks[taskCount - 1] + ANSI_RESET);
                    System.out.println(ANSI_GREEN + " Now you have " + taskCount + " tasks in the list." + ANSI_RESET);
                    printLine();
                    break;
                default:
                    System.out.println("I don't understand that command.");
            }
        }
    }
}